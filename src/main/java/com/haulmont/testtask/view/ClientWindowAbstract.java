package com.haulmont.testtask.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.ds.DsType;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public abstract class ClientWindowAbstract extends Window implements SaveAndEdit {
	private static final Logger LOG = LogManager.getLogger();
	protected ClientDao clientDao;	
	protected TextField txtFirstName, txtLastName, txtPatronnymic, ptxtPhone;

	public ClientWindowAbstract() {
		try {
			clientDao = DaoFactory.getFactory(DsType.HSQLDB).getClientDAO();
		} catch (Exception e) {
			LOG.error("No client data source", e);
			close();
		}
		init();
	}

	private void init() {
		txtFirstName = new TextField("Имя");
		txtFirstName.setSizeFull();
		txtLastName = new TextField("Фамилия");
		txtLastName.setSizeFull();
		txtPatronnymic = new TextField("Отчество");
		txtPatronnymic.setSizeFull();
		ptxtPhone = new TextField("Телефон");
		ptxtPhone.setSizeFull();
		VerticalLayout vlLayout = new VerticalLayout(txtFirstName, txtLastName, txtPatronnymic, ptxtPhone);
		this.setWidth(400.0f, Unit.PIXELS);
		this.setModal(true);
		this.setResizable(false);
		Button btnApple = new Button("Ok");
		btnApple.addClickListener(event -> {
			btnAppleClick();
		});
		Button btnCancel = new Button("Отменить");
		btnCancel.addClickListener(event -> {
			btnCancelClick();
		});
		HorizontalLayout hLayout = new HorizontalLayout(btnApple, btnCancel);
		vlLayout.addComponent(hLayout);
		this.setContent(vlLayout);
	}

	protected abstract void btnCancelClick();

	protected abstract void btnAppleClick();

	@Override
	public void save() {
		// TODO Сохранение данных в таблицу client
		update();
	}

	@Override
	public void cancel() {
		// TODO Отмена действий
	}

	@Override
	public void update() {
		// TODO Обновление отображаемых данных
	}

}
