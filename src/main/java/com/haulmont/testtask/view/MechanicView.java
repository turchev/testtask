package com.haulmont.testtask.view;

import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.MechanicDao;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Mechanic;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class MechanicView extends AbstractView implements View {
	private DaoFactory hsqlDaoFactory;
	private MechanicDao mechanicDao;
	private Grid<Mechanic> grid;

	public MechanicView() throws UiException {
		init();
		showAll();
	}

	private void init() throws UiException {
		try {			
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			mechanicDao = hsqlDaoFactory.getMechanicDao();
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
		} catch (Exception e) {
//			UI.getCurrent().getNavigator().navigateTo("mechanic");
			throw new UiException(e);
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
	void btnAddClick() {
		MechanicWindowAdd subWindowAdd = new MechanicWindowAdd();
		UI.getCurrent().addWindow(subWindowAdd);
	}

	@Override
	void btnChangeClick() {
		if (grid.asSingleSelect().isEmpty()) {
			Notification.show("Выберите механика из списка");
			return;
		}
		Mechanic selectedMachanic = grid.asSingleSelect().getValue();
		MechanicWindowEdit subWindowEdit = new MechanicWindowEdit(selectedMachanic.getId());
		UI.getCurrent().addWindow(subWindowEdit);
	}

	@Override
	void btnDeleteClick() {
		try {
			if (grid.asSingleSelect().isEmpty()) {
				Notification.show("Выберите механика из списка");
				return;
			}
			Mechanic selectedMachanic = grid.asSingleSelect().getValue();
			final String MESSAGE_1 = "Удаление записи " + selectedMachanic.getLastName() + " "
					+ selectedMachanic.getFirstName() + " " + selectedMachanic.getPatronnymic() + "?";

			ConfirmDialog.show(getUI(), "Внимание", MESSAGE_1, "Подтвердить", "Отменить", new ConfirmDialog.Listener() {
				public void onClose(ConfirmDialog dialog) {
					if (dialog.isConfirmed()) {
						try {
							mechanicDao.delete(selectedMachanic.getId());							
							try {
								showAll();
							} catch (UiException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (DaoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						return;
					}
				}
			});

		} catch (Exception e) {
			Notification.show("Не удалось выполнить удаление", Type.ERROR_MESSAGE);
		}	
	}

	@Override
	public void enter(ViewChangeEvent event) {
//		Notification.show("Welcome to Mechanic View");
	}
}
