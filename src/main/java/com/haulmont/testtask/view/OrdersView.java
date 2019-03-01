package com.haulmont.testtask.view;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vaadin.dialogs.ConfirmDialog;

import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.OrdersDao;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.OrderStatusType;
import com.haulmont.testtask.entity.OrdersWithFio;
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
public class OrdersView extends AbstractView implements View {
	private static final Logger LOG = LogManager.getLogger();
	public static final String NAME = "orders";
	private DaoFactory hsqlDaoFactory;
	private OrdersDao orderDao;
	private ClientDao clientDao;
	private TextField filterDescription;
	private NativeSelect<String> filterClient;
	private NativeSelect<OrderStatusType> filterStatus;
	private Button btnAppleFilter, btnCleanFilter;
	private Grid<OrdersWithFio> grid = new Grid<>();
	private List<OrdersWithFio> orders;

	public OrdersView() throws UiException {
		init();
		showAll();
	}

	private void init() throws UiException {
		try {
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			orderDao = hsqlDaoFactory.getOrdersDao();
			clientDao = hsqlDaoFactory.getClientDAO();
			this.addComponent(getFilterPanel());
			grid.setWidth(100.0f, Unit.PERCENTAGE);
			grid.setSelectionMode(SelectionMode.SINGLE);
			grid.addColumn(OrdersWithFio::getId).setId("id").setCaption("№");
			grid.addColumn(OrdersWithFio::getDescription).setId("description").setCaption("Описание").setWidth(500);
			grid.addColumn(OrdersWithFio::getClientFio).setId("clientFio").setCaption("Клиент ФИО");
			grid.addColumn(OrdersWithFio::getMechanicFio).setId("mechanicFio").setCaption("Механик ФИО");
			grid.addColumn(OrdersWithFio::getDateCreat).setId("dateCreat").setCaption("Дата создания заявки");
			grid.addColumn(OrdersWithFio::getCompletionDate).setId("completionDate").setCaption("Дата окончания работ");
			grid.addColumn(OrdersWithFio::getPrice).setId("price").setCaption("Цена");
			grid.addColumn(OrdersWithFio::getStatus).setId("status").setCaption("Статус");
			grid.setColumnOrder("id", "description", "clientFio", "mechanicFio", "status", "dateCreat",
					"completionDate", "price");
			this.addComponent(grid);
		} catch (Exception e) {
			LOG.error(e);
			throw new UiException(e);
		}
	}

	private void showAll() throws UiException {
		try {
			orders = orderDao.findAll();
			grid.setItems(orders);
		} catch (Exception e) {
			LOG.error(e);
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

			List<OrdersWithFio> orders = orderDao.findUsingFilter(findDescription, status, clientFio);
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
		OrdersWindowAdd subWindowAdd = new OrdersWindowAdd();
		UI.getCurrent().addWindow(subWindowAdd);
	}

	@Override
	protected void btnChangeClick() {
		if (grid.asSingleSelect().isEmpty()) {
			Notification.show("Выберите заказ из списка");
			return;
		}
		OrdersWithFio selectedOrders = grid.asSingleSelect().getValue();
		OrdersWindowEdit subWindowEdit = new OrdersWindowEdit(selectedOrders.getId());
		UI.getCurrent().addWindow(subWindowEdit);
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

			ConfirmDialog.show(getUI(), "Внимание", MESSAGE_1, "Подтвердить", "Отменить", new ConfirmDialog.Listener() {
				public void onClose(ConfirmDialog dialog) {
					if (dialog.isConfirmed()) {
						try {
							orderDao.delete(selectedOrders.getId());
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
//		Notification.show("Welcome to Orders View");
	}
}
