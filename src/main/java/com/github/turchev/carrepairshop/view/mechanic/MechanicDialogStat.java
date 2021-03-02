package com.github.turchev.carrepairshop.view.mechanic;

import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.plotoptions.bar.builder.ColorsBuilder;
import com.github.appreciated.apexcharts.config.plotoptions.builder.BarBuilder;
import com.github.appreciated.apexcharts.helper.Series;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrepairshop.dao.DaoFactory;
import com.github.turchev.carrepairshop.dao.MechanicDao;
import com.github.turchev.carrepairshop.domain.person.Mechanic;
import com.github.turchev.carrepairshop.ds.DsType;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

class MechanicDialogStat extends Dialog {
	private static final Logger LOG = LogManager.getLogger();
	private DaoFactory hsqlDaoFactory;
	private MechanicDao mechanicDao;

	protected MechanicDialogStat() throws UiException {
		try {
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			mechanicDao = hsqlDaoFactory.getMechanicDao();
			VerticalLayout vlLayout = new VerticalLayout(getChart());
			vlLayout.setSizeFull();
			this.add(vlLayout);
			setSizeFull();
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created MechanicWindowStat");
	}

	protected Component getChart() throws UiException {
		/**
		 * 		https://github.com/appreciated/apexcharts-flow
		 */
		ApexChartsBuilder barChartOrderSum = new ApexChartsBuilder();
		ApexChartsBuilder barChartPriceSum = new ApexChartsBuilder();

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

			barChartOrderSum.withChart(ChartBuilder.get()
						.withType(Type.bar)
						.build())
						.withPlotOptions(PlotOptionsBuilder.get()
								.withBar(BarBuilder.get()
										.withHorizontal(true)
										.withColors(ColorsBuilder.get()
												.build())
										.build())
								.build())
						.withDataLabels(DataLabelsBuilder.get()
								.withEnabled(false).build())
						.withStroke(StrokeBuilder.get()
								.withShow(true)
								.withColors("transparent")
								.build())
						.withSeries(new Series<>("Количество заказаов", dataOrdersSum.toArray()))
						.withXaxis(XAxisBuilder.get().withCategories(fio).build())
						.withFill(FillBuilder.get().build());
			VerticalLayout chart1 = new VerticalLayout(barChartOrderSum.build());
			chart1.setWidth("50em");

			barChartPriceSum.withChart(ChartBuilder.get()
					.withType(Type.bar)
					.build())
					.withPlotOptions(PlotOptionsBuilder.get()
							.withBar(BarBuilder.get()
									.withHorizontal(true)
									.withColors(ColorsBuilder.get()
											.build())
									.build())
							.build())
					.withDataLabels(DataLabelsBuilder.get()
							.withEnabled(false)
							.build())
					.withStroke(StrokeBuilder.get()
							.withShow(true)
							.withColors("transparent")
							.build())
					.withSeries(new Series<>("Стоимость", dataPriceSum.toArray()))
					.withXaxis(XAxisBuilder.get().withCategories(fio).build())
					.withFill(FillBuilder.get()
							.build());
			VerticalLayout chart2 = new VerticalLayout(barChartPriceSum.build());
			chart2.setWidth("50em");

			return new HorizontalLayout(chart1, chart2);

		} catch (Exception e) {
			throw new UiException(e);
		}
	}
}


