package com.haulmont.testtask.view;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vaadin.dialogs.ConfirmDialog;

import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.MechanicDao;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Mechanic;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class MechanicView extends AbstractView implements View {
	private static final Logger LOG = LogManager.getLogger();
	public static final String NAME = "mechanic";
	private DaoFactory hsqlDaoFactory;
	private MechanicDao mechanicDao;
	private Grid<Mechanic> grid;
	private Button btnShowStat = new Button("Показать статистику");

	public MechanicView() throws UiException {
		try {
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			mechanicDao = hsqlDaoFactory.getMechanicDao();
			this.addComponent(btnShowStat);
			btnShowStat.addClickListener(event -> {
				btnShowStatClick();
			});
			grid = new Grid<>();
			grid.setWidth(100.0f, Unit.PERCENTAGE);
			grid.setSelectionMode(SelectionMode.SINGLE);
			grid.addColumn(Mechanic::getId).setId("id").setCaption("№");
			grid.addColumn(Mechanic::getLastName).setId("lastName").setCaption("Фамилия").setWidth(500);
			grid.addColumn(Mechanic::getFirstName).setId("firstName").setCaption("Имя");
			grid.addColumn(Mechanic::getPatronnymic).setId("patronnymic").setCaption("Отчество");
			grid.addColumn(Mechanic::getWages).setId("wages").setCaption("Почасовая оплата");
			grid.setColumnOrder("id", "lastName", "firstName", "patronnymic", "wages");
			this.addComponent(grid);
			showAll();
		} catch (Exception e) {
			throw new UiException(e);
		}		
	}
	
	private void btnShowStatClick() {	
		try {			
			MechanicWindowStat subWindowEdit = new MechanicWindowStat();
			UI.getCurrent().addWindow(subWindowEdit);	
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Вывод статистического отчета завершился ошибкой");
		}		
	}

	private void showAll() throws UiException {
		try {
			List<Mechanic> mechanic = mechanicDao.findAll();
			grid.setItems(mechanic);
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	@Override
	protected void btnAddClick() {
		try {
			MechanicWindowAdd subWindowAdd = new MechanicWindowAdd();
			UI.getCurrent().addWindow(subWindowAdd);
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Ошибка диалогового окна создания записи");
		}
	}

	@Override
	protected void btnChangeClick() {
		try {			
			if (grid.asSingleSelect().isEmpty()) {
				Notification.show("Выберите механика из списка");
				return;
			}
			Mechanic selectedMachanic = grid.asSingleSelect().getValue();
			MechanicWindowEdit subWindowEdit = new MechanicWindowEdit(selectedMachanic.getId());
			UI.getCurrent().addWindow(subWindowEdit);
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Ошибка диалогового окна редактирования");
		}
	}

	@Override
	protected void btnDeleteClick() {
		try {
			if (grid.asSingleSelect().isEmpty()) {
				Notification.show("Выберите механика из списка");
				return;
			}
			Mechanic selectedMachanic = grid.asSingleSelect().getValue();
			final String MESSAGE_1 = "Удаление записи " + selectedMachanic.getLastName() + " "
					+ selectedMachanic.getFirstName() + " " + selectedMachanic.getPatronnymic() + "?";

			ConfirmDialog.show(getUI(), "Внимание", MESSAGE_1, "Подтвердить", "Отменить", dialog -> {
				if (dialog.isConfirmed()) {
					try {
						mechanicDao.delete(selectedMachanic.getId());
						showAll();
					} catch (Exception ex) {
						LOG.error(ex);
					}
				} else {
					return;
				}
			});

		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось выполнить удаление", Type.ERROR_MESSAGE);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		LOG.debug("Welcome to Mechanic View");
	}
}
