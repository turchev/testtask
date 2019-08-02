package com.github.turchev.carrepairshop.view.orders;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vaadin.dialogs.ConfirmDialog;

import com.github.turchev.carrepairshop.MainLayout;
import com.github.turchev.carrepairshop.dao.ClientDao;
import com.github.turchev.carrepairshop.dao.DaoFactory;
import com.github.turchev.carrepairshop.dao.OrdersDao;
import com.github.turchev.carrepairshop.domain.orders.OrderStatusType;
import com.github.turchev.carrepairshop.domain.orders.OrdersWithFio;
import com.github.turchev.carrepairshop.ds.DsType;
import com.github.turchev.carrepairshop.view.AbstractView;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Route(value = OrdersView.NAME, layout = MainLayout.class)
@PageTitle(OrdersView.NAME)
public class OrdersView extends AbstractView implements View {
	private static final Logger LOG = LogManager.getLogger();
	public static final String NAME = "orders";
	private DaoFactory hsqlDaoFactory;
	private OrdersDao ordersDao;
	private ClientDao clientDao;
	private TextField filterDescription;
	private NativeSelect<String> filterClient;
	private NativeSelect<OrderStatusType> filterStatus;
	private Button btnAppleFilter, btnCleanFilter;
	private Grid<OrdersWithFio> grid = new Grid<>();	

	public OrdersView() throws UiException {
		try {
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			ordersDao = hsqlDaoFactory.getOrdersDao();
			clientDao = hsqlDaoFactory.getClientDao();
//			this.addComponent(getFilterPanel());
//			grid.setWidth(100.0f, Unit.PERCENTAGE);
			grid.setSelectionMode(SelectionMode.SINGLE);
			grid.addColumn(OrdersWithFio::getId).setId("id").setCaption("Id");
			grid.addColumn(OrdersWithFio::getDescription).setId("description").setCaption("Описание").setWidth(500);
			grid.addColumn(OrdersWithFio::getClientFio).setId("clientFio").setCaption("Клиент ФИО");
			grid.addColumn(OrdersWithFio::getMechanicFio).setId("mechanicFio").setCaption("Механик ФИО");
			grid.addColumn(OrdersWithFio::getDateCreat).setId("dateCreat").setCaption("Дата создания заявки");
			grid.addColumn(OrdersWithFio::getCompletionDate).setId("completionDate").setCaption("Дата окончания работ");
			grid.addColumn(OrdersWithFio::getPrice).setId("price").setCaption("Цена");
			grid.addColumn(OrdersWithFio::getStatus).setId("status").setCaption("Статус");
			grid.setColumnOrder("id", "description", "clientFio", "mechanicFio", "status", "dateCreat",
					"completionDate", "price");
//			this.addComponent(grid);
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

			filterStatus = new NativeSelect<>("Статус");
			filterStatus.setItems(OrderStatusType.values());

			filterClient = new NativeSelect<>("Фамилия клиента");
			filterClient.setItems(clientDao.getLastNameList());

			btnAppleFilter = new Button("Применить фильтр");
			btnAppleFilter.addClickListener(event -> {
				btnAppleFilterClick();
			});
			btnCleanFilter = new Button("Очистить фильтр");
			btnCleanFilter.addClickListener(event -> {
				btnCleanFilterClick();
			});
			HorizontalLayout filterPanel = new HorizontalLayout(filterDescription, filterStatus, filterClient,
					btnAppleFilter, btnCleanFilter);

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
			Notification.show("Не удалось применить фильтр", Type.WARNING_MESSAGE);
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
			Notification.show("Не удалось очистить фильтр", Type.WARNING_MESSAGE);
			LOG.warn(e);
		}
	}

	@Override
	protected void btnAddClick() {
		try {
			OrdersWindowAdd subWindowAdd = new OrdersWindowAdd();
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
				Notification.show("Выберите заказ из списка");
				return;
			}
			OrdersWithFio selectedOrders = grid.asSingleSelect().getValue();
			OrdersWindowEdit subWindowEdit = new OrdersWindowEdit(selectedOrders.getId());
			UI.getCurrent().addWindow(subWindowEdit);
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Ошибка диалогового окна редактирования");
		}
	}

	@Override
	protected void btnDeleteClick() {
//		try {
//			if (grid.asSingleSelect().isEmpty()) {
//				Notification.show("Выберите заказ из списка");
//				return;
//			}
//			OrdersWithFio selectedOrders = grid.asSingleSelect().getValue();
//			final String MESSAGE_1 = "Удалить запись №" + selectedOrders.getId() + " " + selectedOrders.getDescription()
//					+ "?";
//
//			ConfirmDialog.show(getUI(), "Внимание", MESSAGE_1, "Подтвердить", "Отменить", dialog -> {
//				if (dialog.isConfirmed()) {
//					try {
//						ordersDao.delete(selectedOrders.getId());
//						showAll();
//					} catch (Exception ex) {
//						LOG.error(ex);
//					}
//				} else {
//					return;
//				}
//			});
//
//		} catch (Exception e) {
//			Notification.show("Не удалось выполнить удаление", Type.ERROR_MESSAGE);
//		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		LOG.debug("Welcome to Orders View");
	}
}
