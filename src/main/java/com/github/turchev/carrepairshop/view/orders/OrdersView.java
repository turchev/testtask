package com.github.turchev.carrepairshop.view.orders;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.claspina.confirmdialog.ButtonOption;
import org.claspina.confirmdialog.ConfirmDialog;

import com.github.turchev.carrepairshop.MainLayout;
import com.github.turchev.carrepairshop.dao.ClientDao;
import com.github.turchev.carrepairshop.dao.DaoFactory;
import com.github.turchev.carrepairshop.dao.OrdersDao;
import com.github.turchev.carrepairshop.domain.orders.OrderStatusType;
import com.github.turchev.carrepairshop.domain.orders.OrdersWithFio;
import com.github.turchev.carrepairshop.ds.DsType;
import com.github.turchev.carrepairshop.view.AbstractView;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@Route(value = OrdersView.NAME, layout = MainLayout.class)
@PageTitle(OrdersView.NAME)
public class OrdersView extends AbstractView {
	private static final Logger LOG = LogManager.getLogger();
	public static final String NAME = "orders";
	private DaoFactory hsqlDaoFactory;
	private OrdersDao ordersDao;
	private ClientDao clientDao;
	private TextField filterDescription;
	private ComboBox<String> filterClient;
	private ComboBox<OrderStatusType> filterStatus;
	private Button btnAppleFilter, btnCleanFilter;
	private Grid<OrdersWithFio> grid = new Grid<>();

	public OrdersView() throws UiException {
		try {
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			ordersDao = hsqlDaoFactory.getOrdersDao();
			clientDao = hsqlDaoFactory.getClientDao();
			this.add(getFilterPanel());
			grid.setSelectionMode(SelectionMode.SINGLE);
			grid.addColumn(OrdersWithFio::getId).setHeader("Id").setId("id");
			grid.addColumn(OrdersWithFio::getDescription).setHeader("Описание").setWidth("25%").setId("description");
			grid.addColumn(OrdersWithFio::getClientFio).setHeader("Клиент ФИО").setId("clientFio");
			grid.addColumn(OrdersWithFio::getMechanicFio).setHeader("Механик ФИО").setId("mechanicFio");
			grid.addColumn(OrdersWithFio::getDateCreat).setHeader("Дата создания заявки").setId("dateCreat");
			grid.addColumn(OrdersWithFio::getCompletionDate).setHeader("Дата окончания работ").setId("completionDate");
			grid.addColumn(OrdersWithFio::getPrice).setHeader("Цена").setId("price");
			grid.addColumn(OrdersWithFio::getStatus).setHeader("Статус").setId("status");
			this.add(grid);
			showAll();
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	private void showAll() throws UiException {
		try {
			List<OrdersWithFio> orders = ordersDao.findAll();
			grid.setItems(orders);
		} catch (Exception e) {
			throw new UiException(e);
		}

	}

	private HorizontalLayout getFilterPanel() throws UiException {
		try {
			filterDescription = new TextField("Описание");

			filterStatus = new ComboBox<>("Статус", OrderStatusType.values());

			filterClient = new ComboBox<>("Фамилия клиента", clientDao.getLastNameList());

			btnAppleFilter = new Button("Применить фильтр");
			btnAppleFilter.addClickListener(event -> {
				btnAppleFilterClick();
			});
			btnCleanFilter = new Button("Очистить фильтр");
			btnCleanFilter.addClickListener(event -> {
				btnCleanFilterClick();
			});
			HorizontalLayout filterPanel = new HorizontalLayout();
			filterPanel.add(filterDescription, filterStatus, filterClient, btnAppleFilter, btnCleanFilter);
			return filterPanel;

		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	private void btnAppleFilterClick() {
		try {
			String findDescription = filterDescription.getValue();
			String clientFio = filterClient.getValue();

			String status = null;
			if (!filterStatus.isEmpty())
				status = filterStatus.getValue().toString();

			List<OrdersWithFio> orders = ordersDao.findUsingFilter(findDescription, status, clientFio);
			grid.setItems(orders);
		} catch (Exception e) {
			Notification.show("Не удалось применить фильтр", 10000, Position.MIDDLE);
			LOG.warn(e);
		}
	}

	private void btnCleanFilterClick() {
		try {
			filterDescription.clear();
			filterStatus.clear();
			filterClient.clear();
			showAll();
		} catch (UiException e) {
			Notification.show("Не удалось очистить фильтр", 10000, Position.MIDDLE);
			LOG.warn(e);
		}
	}

	@Override
	protected void btnAddClick() {
		try {
			OrdersWindowAdd subWindowAdd = new OrdersWindowAdd();
			subWindowAdd.open();
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Ошибка диалогового окна создания записи", 10000, Position.MIDDLE);
		}
	}

	@Override
	protected void btnChangeClick() {
		try {
			if (grid.asSingleSelect().isEmpty()) {
				Notification.show("Выберите заказ из списка", 4000, Position.MIDDLE);
				return;
			}
			OrdersWithFio selectedOrders = grid.asSingleSelect().getValue();
			OrdersWindowEdit subWindowEdit = new OrdersWindowEdit(selectedOrders.getId());
			subWindowEdit.open();
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Ошибка диалогового окна редактирования", 10000, Position.MIDDLE);
		}
	}

	@Override
	protected void btnDeleteClick() {
		try {
			if (grid.asSingleSelect().isEmpty()) {
				Notification.show("Выберите заказ из списка");
				return;
			}
			OrdersWithFio selectedOrders = grid.asSingleSelect().getValue();
			final String MESSAGE_1 = "Удалить запись №" + selectedOrders.getId() + " " + selectedOrders.getDescription()
					+ "?";

			ConfirmDialog
					.createQuestion()
					.withCaption("Внимание")
					.withMessage(MESSAGE_1)
					.withOkButton(() -> {
						try {
							ordersDao.delete(selectedOrders.getId());
							showAll();
						} catch (Exception ex) {
							LOG.error(ex);
						}
					}, ButtonOption.focus(), ButtonOption.caption("Подтвердить"))
					.withCancelButton(ButtonOption.caption("Отменить"))
					.open();

//			ConfirmDialog dialog = new ConfirmDialog("Внимание", MESSAGE_1, "Подтвердить", event -> {
//				try {
//					ordersDao.delete(selectedOrders.getId());
//					showAll();
//				} catch (Exception ex) {
//					LOG.error(ex);
//				}
//			}, "Отменить", event -> {
//				return;
//			});
//			dialog.open();

		} catch (Exception e) {
			Notification.show("Не удалось выполнить удаление", 10000, Position.MIDDLE);
		}
	}
}
