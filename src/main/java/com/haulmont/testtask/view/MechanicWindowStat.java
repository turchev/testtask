package com.haulmont.testtask.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.data.Ticks;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;

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

//			DataSeries dataSeries = new DataSeries().add(322);
//			SeriesDefaults seriesDefault = new SeriesDefaults().setRenderer(SeriesRenderers.METER_GAUGE)
//					.setRendererOptions(
//							new MeterGaugeRenderer().setMin(100).setMax(500).setIntervals(200, 300, 400, 500)
//									.setIntervalColors("#66cc66", "#93b75f", "#E7E658", "#cc6666"));
//
//			Options options = new Options().setSeriesDefaults(seriesDefault);
//
////			DCharts chart = new DCharts().setDataSeries(dataSeries).setOptions(options).show();
//			DCharts chart = new DCharts().setDataSeries(dataSeries).setOptions(options);
////			HorizontalLayout hLayout = new HorizontalLayout(btnApple, btnCancel);
////			vlLayout.addComponent(hLayout);
//			this.setContent(chart);

			DataSeries dataSeries = new DataSeries()
					.add(2, 6, 7, 10);

				SeriesDefaults seriesDefaults = new SeriesDefaults()
					.setRenderer(SeriesRenderers.BAR);

				Axes axes = new Axes()
					.addAxis(
						new XYaxis()
							.setRenderer(AxisRenderers.CATEGORY)
							.setTicks(
								new Ticks()
									.add("a", "b", "c", "d")));

				Highlighter highlighter = new Highlighter()
					.setShow(false);

				Options options = new Options()
					.setSeriesDefaults(seriesDefaults)
					.setAxes(axes)
					.setHighlighter(highlighter);

				DCharts chart = new DCharts()
					.setDataSeries(dataSeries)
					.setOptions(options)
					.show();
				
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created MechanicWindowStat");
	}
}
