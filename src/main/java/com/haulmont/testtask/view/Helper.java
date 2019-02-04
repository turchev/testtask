package com.haulmont.testtask.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

final class Helper {

	public static boolean isDialogWindow(String message) {
		DialogWindow subWindow = new DialogWindow(message);
		UI.getCurrent().addWindow(subWindow);
		return subWindow.btnClickFlag;
	}

	@SuppressWarnings("serial")
	private static class DialogWindow extends Window {
		Boolean btnClickFlag = null;

		DialogWindow(String message) {			
			Label label = new Label(message);			
			this.setWidth(400.0f, Unit.PIXELS);
			this.setModal(true);
			this.setResizable(false);
			Button btnApple = new Button("Ok");
			btnApple.addClickListener(event -> {
				btnClickFlag = true;
				close();
			});
			Button btnCancel = new Button("Отменить");
			btnCancel.addClickListener(event -> {
				btnClickFlag = false;
				close();
			});
			HorizontalLayout hLayout = new HorizontalLayout(btnApple, btnCancel);
			VerticalLayout vlLayout = new VerticalLayout(label, hLayout);
//			vlLayout.addComponent(hLayout);
			this.setContent(vlLayout);
		}
	}
}
