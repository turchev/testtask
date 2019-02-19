package com.haulmont.testtask.view;

import java.time.LocalDateTime;
import java.util.List;

import com.haulmont.testtask.entity.OrdersWithFio;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
public class OrdersWindowAdd extends OrdersWindowAbstract {
	private List<OrdersWithFio> orders;
	
	public OrdersWindowAdd(List<OrdersWithFio> orders) {	
		super(orders);
		this.orders = orders;		
		super.setCaption("Создать заявку");
		super.dtfDateCreat.setValue(LocalDateTime.now());
		super.dtfCompletionDate.setValue(LocalDateTime.now());		
		ntsStatus.setVisible(false);
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected synchronized void btnAppleClick() {		
		
		if (cmbClient.getValue() == null) {
			Notification.show("Выберите клиента из списка или создайте новую запись", Type.WARNING_MESSAGE);
			return;
		}
		
		if (cmbMechanic.getValue() == null) {
			Notification.show("Выберите механика из списка или создайте новую запись", Type.WARNING_MESSAGE);
			return;
		}
		Notification.show(cmbClient.getValue() + " \n " + cmbMechanic.getValue());
//		Orders orders = new Orders(txrDescription.getValue(), cmbClient.getValue(), cmbMechanic.getValue());
//		String description, Long clientId, Long mechanicId, Timestamp dateCreat, Timestamp completionDate,
//		BigDecimal price
//		try {
//			BigDecimal wages = (BigDecimal) super.dcf.parse(txtWages.getValue());
//			orders.setWages(wages);
//			mechanicDao.create(orders);
//			UI.getCurrent().getNavigator().navigateTo(MechanicView.NAME);
//			close();
//		} catch (DaoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
