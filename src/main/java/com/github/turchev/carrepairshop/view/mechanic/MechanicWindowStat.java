package com.github.turchev.carrepairshop.view.mechanic;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.elements.Rectangle;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.github.turchev.carrepairshop.dao.DaoFactory;
import com.github.turchev.carrepairshop.dao.MechanicDao;
import com.github.turchev.carrepairshop.domain.person.Mechanic;
import com.github.turchev.carrepairshop.ds.DsType;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
class MechanicWindowStat extends Window {
	private static final Logger LOG = LogManager.getLogger();
	private DaoFactory hsqlDaoFactory;
	private MechanicDao mechanicDao;

	protected MechanicWindowStat() throws UiException {
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

	protected Component getChart() throws UiException {
		/**
		 * Разбираться в дебрях ChartJs не стал, просто подогнал под свою задачу один из
		 * примеров
		 */

		try {
			List<Mechanic.Stat> mechanicStat = mechanicDao.getStatAll();
			LOG.debug(mechanicStat);
			List<String> fio = new ArrayList<>();
			List<Double> dataOrdersSum = new ArrayList<>();
			List<Double> dataPriceSum = new ArrayList<>();
			for (Mechanic.Stat itrMechanicStat : mechanicStat) {
				fio.add(itrMechanicStat.getMechanicFio());
				dataOrdersSum.add(itrMechanicStat.getOrdersSum().doubleValue());
				dataPriceSum.add(itrMechanicStat.getPriceSum().doubleValue());
			}
			
			BarChartConfig barConfig = new BarChartConfig();
			barConfig.horizontal();
			barConfig.data().labelsAsList(fio);
			barConfig.data().addDataset(new BarDataset().backgroundColor("rgba(220,220,220,0.5)").label("Заявки(шт)"))
					.and().options().responsive(true).title().display(true).text("Общее количество заявок").and()
					.scales()
					.add(Axis.X,
							new LinearScale().display(true).scaleLabel().display(true).and().ticks().suggestedMin(0)
									.and().position(Position.LEFT))
					.and().elements().rectangle().borderWidth(2).borderColor("rgb(0, 255, 0)")
					.borderSkipped(Rectangle.RectangleEdge.LEFT).and().and().legend().fullWidth(false)
					.position(Position.LEFT).and().done();

			BarDataset bds = (BarDataset) barConfig.data().getDatasetAtIndex(0);
			bds.dataAsList(dataOrdersSum);
		
			ChartJs chart = new ChartJs(barConfig);
			chart.setWidth(1400.0f, Unit.PIXELS);
			chart.setHeight(300.0f, Unit.PIXELS);
			
			BarChartConfig barConfig2 = new BarChartConfig();
			barConfig2.horizontal();
			barConfig2.data().labelsAsList(fio);
			barConfig2.data().addDataset(new BarDataset().backgroundColor("rgba(220,220,220,0.5)").label("Стоимость(руб)"))
					.and().options().responsive(true).title().display(true).text("Суммарная стоимость работ").and()
					.scales()
					.add(Axis.X,
							new LinearScale().display(true).scaleLabel().display(true).and().ticks().suggestedMin(0)
									.and().position(Position.LEFT))
					.and().elements().rectangle().borderWidth(2).borderColor("rgb(0, 255, 0)")
					.borderSkipped(Rectangle.RectangleEdge.LEFT).and().and().legend().fullWidth(false)
					.position(Position.LEFT).and().done();

			BarDataset bds2 = (BarDataset) barConfig2.data().getDatasetAtIndex(0);
			bds2.dataAsList(dataPriceSum);
		
			ChartJs chart2 = new ChartJs(barConfig2);
			chart2.setWidth(1400.0f, Unit.PIXELS);
			chart2.setHeight(300.0f, Unit.PIXELS);		

			return new VerticalLayout(chart, chart2);			
			
		} catch (Exception e) {
			throw new UiException(e);
		}
	}
}
