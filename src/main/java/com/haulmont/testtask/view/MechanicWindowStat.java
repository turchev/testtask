package com.haulmont.testtask.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.elements.Rectangle.RectangleEdge;
import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.MechanicDao;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Mechanic;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class MechanicWindowStat extends Window {
	private static final Logger LOG = LogManager.getLogger();
	private DaoFactory hsqlDaoFactory;
	private MechanicDao mechanicDao;

	public MechanicWindowStat() throws UiException {
		try {
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			mechanicDao = hsqlDaoFactory.getMechanicDao();
			VerticalLayout vlLayout = new VerticalLayout(getChart());
			this.setContent(vlLayout);
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created MechanicWindowStat");
	}

	public Component getChart() throws DaoException {
		List<Mechanic.Stat> mechanicStat = mechanicDao.getStatAll();
		LOG.debug(mechanicStat);
		List<String> fio = new ArrayList<>();
		List<Integer> dataOrdersSum = new ArrayList<>();
		List<BigDecimal> dataHhSum = new ArrayList<>();
		List<BigDecimal> dataPriceSum = new ArrayList<>();
		for (Mechanic.Stat itrMechanicStat : mechanicStat) {
			fio.add(itrMechanicStat.getMechanicFio());
			dataOrdersSum.add(itrMechanicStat.getOrdersSum());
			dataHhSum.add(itrMechanicStat.getHhSum());
			dataPriceSum.add(itrMechanicStat.getPriceSum());
		}		
		BarChartConfig barConfig = new BarChartConfig();
		barConfig.horizontal();
		barConfig.data().labelsAsList(fio);
		barConfig.data().addDataset(new BarDataset().backgroundColor("rgba(220,220,220,0.5)").label("Заявки(шт)"))
				.addDataset(new BarDataset().backgroundColor("rgba(151,187,205,0.5)").label("Время(ч)").hidden(true))
				.addDataset(
						new BarDataset().backgroundColor("rgba(151,187,205,0.5)").label("Стоимость(руб)").hidden(true))
				.and().options().responsive(true).title().display(true).text("Chart.js Horizontal Bar Chart").and()
				.elements().rectangle().borderWidth(2).borderColor("rgb(0, 255, 0)").borderSkipped(RectangleEdge.LEFT)
				.and().and().legend().fullWidth(false).position(Position.LEFT).and().done();

		for (Dataset<?, ?> ds : barConfig.data().getDatasets()) { // проходит по полям графика (заявки, время, стоимость)
			BarDataset lds = (BarDataset) ds;
			List<Double> data = new ArrayList<>();
			for (int i = 0; i < fio.size(); i++) {  // проходит по участникам (петров, сидоров...)
				data.add(100d);
			}
			lds.dataAsList(data);
			System.out.println("!!!!");
		}

		ChartJs chart = new ChartJs(barConfig);
		chart.setJsLoggingEnabled(true);
		chart.setWidth(1400.0f, Unit.PIXELS);
		return chart;
	}
}
