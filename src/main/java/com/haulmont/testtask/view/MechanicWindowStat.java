package com.haulmont.testtask.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.MechanicDao;
import com.haulmont.testtask.ds.DsType;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class MechanicWindowStat extends Window {
	private static final Logger LOG = LogManager.getLogger();
	private DaoFactory hsqlDaoFactory;
	private MechanicDao mechanicDao;
	private Long id;

	public MechanicWindowStat(long id) throws UiException {
		try {
			this.id = id;
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			mechanicDao = hsqlDaoFactory.getMechanicDao();
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created MechanicWindowStat");
	}
	
	
}
