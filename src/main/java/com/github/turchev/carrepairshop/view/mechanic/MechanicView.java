package com.github.turchev.carrepairshop.view.mechanic;

import java.util.List;

import com.github.turchev.carrepairshop.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrepairshop.MainLayout;
import com.github.turchev.carrepairshop.dao.DaoFactory;
import com.github.turchev.carrepairshop.dao.MechanicDao;
import com.github.turchev.carrepairshop.domain.person.Mechanic;
import com.github.turchev.carrepairshop.ds.DsType;
import com.github.turchev.carrepairshop.view.AbstractView;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.claspina.confirmdialog.ButtonOption;
import org.claspina.confirmdialog.ConfirmDialog;

@Route(value = MechanicView.NAME, layout = MainLayout.class)
@PageTitle(MechanicView.NAME)

public class MechanicView extends AbstractView {
	private static final Logger LOG = LogManager.getLogger();
	public static final String NAME = "mechanic";
	private final MechanicDao mechanicDao;
	private final Grid<Mechanic> grid;

	public MechanicView() throws UiException {
		try {
			DaoFactory hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			mechanicDao = hsqlDaoFactory.getMechanicDao();
			Button btnShowStat = new Button("Показать статистику");
			this.add(btnShowStat);
			btnShowStat.addClickListener(event -> btnShowStatClick());
			grid = new Grid<>();
			grid.setSelectionMode(SelectionMode.SINGLE);
			grid.addColumn(Mechanic::getId).setHeader("Id").setId("id");
			grid.addColumn(Mechanic::getLastName).setHeader("Фамилия").setId("lastName");
			grid.addColumn(Mechanic::getFirstName).setHeader("Имя").setId("firstName");
			grid.addColumn(Mechanic::getPatronnymic).setHeader("Отчество").setId("patronnymic");
			grid.addColumn(Mechanic::getWages).setHeader("Почасовая оплата").setId("wages");		
			this.add(grid);
			showAll();
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	private void showAll(){
		try {
			List<Mechanic> mechanic = mechanicDao.findAll();
			grid.setItems(mechanic);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private <T extends MechanicDialogAbstract> void refreshGridAfterClosingDialog(T dialog) {
		dialog.addOpenedChangeListener(event -> {
			if (!event.isOpened()) {
				showAll();
			}
		});
	}

	private void btnShowStatClick() {
		try {
			MechanicDialogStat subWindowEdit = new MechanicDialogStat();
			subWindowEdit.open();
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Вывод статистического отчета завершился ошибкой");
		}
	}

	@Override
	protected void btnAddClick() {
		try {
			MechanicDialogAdd subWindowAdd = new MechanicDialogAdd();
			subWindowAdd.open();
			refreshGridAfterClosingDialog(subWindowAdd);
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
			MechanicDialogEdit subWindowEdit = new MechanicDialogEdit(selectedMachanic.getId());
			subWindowEdit.open();
			refreshGridAfterClosingDialog(subWindowEdit);
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
			ConfirmDialog
					.createWarning()
					.withCaption("Внимание")
					.withMessage(MESSAGE_1)
					.withOkButton(() -> {
						try {
							mechanicDao.delete(selectedMachanic.getId());
							showAll();
						} catch (DaoException ex) {
							LOG.debug(ex);
							Notification.show(ex.getMessage());
						} catch (Exception xe) {
							LOG.error(xe);
							Notification.show("Не удалось выполнить удаление");
						}
					}, ButtonOption.focus(), ButtonOption.caption("Подтвердить"))
					.withCancelButton(ButtonOption.caption("Отменить"))
					.open();

		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось выполнить удаление");
		}
	}
}
