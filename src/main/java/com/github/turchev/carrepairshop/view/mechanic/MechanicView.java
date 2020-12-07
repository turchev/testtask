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
@SuppressWarnings("serial")
public class MechanicView extends AbstractView {
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
			this.add(btnShowStat);
			btnShowStat.addClickListener(event -> {
				btnShowStatClick();
			});
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

	private void btnShowStatClick() {
		try {
			MechanicWindowStat subWindowEdit = new MechanicWindowStat();
			subWindowEdit.open();
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Вывод статистического отчета завершился ошибкой");
		}
	}

	private void showAll() throws UiException {
		try {
			Notification.show("Mechanic!!!!!");
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
			subWindowAdd.open();
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
			subWindowEdit.open();
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
						} catch (UiException xe) {
							LOG.error(xe);
							Notification.show("Не удалось выполнить удаление");
						}
					}, ButtonOption.focus(), ButtonOption.caption("Подтвердить"))
					.withCancelButton(ButtonOption.caption("Отменить"))
					.open();

//			ConfirmDialog.show(getUI(), "Внимание", MESSAGE_1, "Подтвердить", "Отменить", dialog -> {
//				if (dialog.isConfirmed()) {
//					try {
//						mechanicDao.delete(selectedMachanic.getId());
//						showAll();
//					} catch (DaoException ex) {
//						LOG.debug(ex);
//						Notification.show(ex.getMessage());
//					} catch (UiException xe) {
//						LOG.error(xe);
//						Notification.show("Не удалось выполнить удаление");
//					}
//				} else {
//					return;
//				}
//			});

		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось выполнить удаление");
		}
	}
}
