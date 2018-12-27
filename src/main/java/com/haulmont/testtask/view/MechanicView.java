package com.haulmont.testtask.view;

import java.util.List;

import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.MechanicDao;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Mechanic;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;


@SuppressWarnings("serial")
public class MechanicView extends AbstractView implements View {
    
    private DaoFactory hsqlDaoFactory;
	private MechanicDao mechanicDao;

	public MechanicView() throws DaoException {
    	Grid<Mechanic> grid = new Grid<>(Mechanic.class);
        grid.setWidth(100.0f, Unit.PERCENTAGE);        
		hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
		mechanicDao = hsqlDaoFactory.getMechanicDao();
		List<Mechanic> mechanic = mechanicDao.findAll();
		grid.setColumnOrder("id", "firstName", "lastName", "patronnymic", "wages");
		grid.setItems(mechanic);
		this.addComponent(grid);
		
    }

	@Override
	void btnAddClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void btnChangeClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void btnDeleteClick() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to Mechanic View");
    }

}
