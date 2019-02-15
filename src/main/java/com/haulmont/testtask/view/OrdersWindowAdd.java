package com.haulmont.testtask.view;

@SuppressWarnings("serial")
public class OrdersWindowAdd extends OrdersWindowAbstract {

	public OrdersWindowAdd() {
		super.setCaption("Создать заявку");
		ntsStatus.setVisible(false);
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected void btnAppleClick() {
//		Orders orders = new Orders(txtLastName.getValue(), txtFirstName.getValue(), txtPatronnymic.getValue());
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
