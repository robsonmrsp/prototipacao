package br.com.m2msolutions.controleviagens.client;


import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;

public class WidgetDepartureControl extends LayoutContainer {
	private LayoutContainer headerContainer;
	private LayoutContainer principalContainer;
	private LayoutContainer descriptionContainer;
	private Html nameHtml;
	private LayoutContainer gridsContainer;
	private LayoutContainer pointAContainer;
	private LayoutContainer pointBContainer;
	private Html descriptionHtml;
	private LayoutContainer headerContainerGridA;
	private Html pontoAHtml;
	private LayoutContainer headerContainerGridB;
	private Html pontoBHtml;
	
	private WidgetDepartureControlRow widgetDepartureControlRow;
	private WidgetDepartureControlRow widgetDepartureControlRow_1;
	private WidgetDepartureControlRow widgetDepartureControlRow_2;
	private WidgetDepartureControlRow widgetDepartureControlRow_3;
	private WidgetDepartureControlRow widgetDepartureControlRow_4;
	private WidgetDepartureControlRow widgetDepartureControlRow_5;
	private WidgetDepartureControlRow widgetDepartureControlRow_6;
	private WidgetDepartureControlRow widgetDepartureControlRow_7;
	private WidgetDepartureControlRow widgetDepartureControlRow_8;
	private WidgetDepartureControlRow widgetDepartureControlRow_9;
	private WidgetDepartureControlRow widgetDepartureControlRow_10;

	public WidgetDepartureControl() {
		initComponents();

	}
	@Override
	public void show() {
		super.show();
	}
	
	private void initComponents() {
		setSize("440px", "290px");
		setStyleName("master-style");
		setLayout(new RowLayout(Orientation.VERTICAL));
		add(getHeaderContainer());
		add(getPrincipalContainer(), new RowData(Style.DEFAULT, 255.0, new Margins()));
	}

	private LayoutContainer getPrincipalContainer() {
		if (principalContainer == null) {
			principalContainer = new LayoutContainer();
			principalContainer.setBorders(true);
			principalContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			principalContainer.	setStyleName("main-style");
			principalContainer.add(getDescriptionContainer());
			principalContainer.add(getGridsContainer(), new RowData(Style.DEFAULT, 25.0, new Margins()));
			principalContainer.add(getWidgetDepartureControlRow());
			
			principalContainer.add(getWidgetDepartureControlRow_9());
			principalContainer.add(getWidgetDepartureControlRow_1());
			principalContainer.add(getWidgetDepartureControlRow_2());
			principalContainer.add(getWidgetDepartureControlRow_3());
			principalContainer.add(getWidgetDepartureControlRow_4());
			principalContainer.add(getWidgetDepartureControlRow_5());
			principalContainer.add(getWidgetDepartureControlRow_6());
			principalContainer.add(getWidgetDepartureControlRow_7());
//			principalContainer.add(getWidgetDepartureControlRow_8());
		}
		return principalContainer;

	}

	private LayoutContainer getHeaderContainer() {
		if (headerContainer == null) {
			headerContainer = new LayoutContainer();
			headerContainer.setSize("-1", "26");
			headerContainer.setBorders(false);
			headerContainer.setLayout(new CenterLayout());
			headerContainer.add(getNameHtml());
		}
		return headerContainer;
	}

	private LayoutContainer getDescriptionContainer() {
		if (descriptionContainer == null) {
			descriptionContainer = new LayoutContainer();
			descriptionContainer.setSize("440", "40");
			descriptionContainer.setStyleName("main-header");
			descriptionContainer.setLayout(new CenterLayout());
			descriptionContainer.add(getDescriptionHtml());
		}
		return descriptionContainer;
	}

	private Html getNameHtml() {
		if (nameHtml == null) {
			nameHtml = new Html("CONTROLE DE PARTIDA");
			nameHtml.setSize("440", "26");
			nameHtml.setStyleName("window-name");
		}
		return nameHtml;
	}

	private LayoutContainer getGridsContainer() {	
		if (gridsContainer == null) {
			gridsContainer = new LayoutContainer();
			gridsContainer.setSize("440", "25");
			gridsContainer.setLayout(new ColumnLayout());
			gridsContainer.add(getPointAContainer(), new ColumnData(220.0));
			gridsContainer.add(getPointBContainer(), new ColumnData(220.0));
		}
		return gridsContainer;
	}

	private LayoutContainer getPointAContainer() {
		if (pointAContainer == null) {
			pointAContainer = new LayoutContainer();
			pointAContainer.setSize("220", "25");
			pointAContainer.setBorders(false);
			pointAContainer.setStyleName("point-A");
			pointAContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			pointAContainer.add(getHeaderContainerGridA());

		}
		return pointAContainer;
	}

	private LayoutContainer getPointBContainer() {
		if (pointBContainer == null) {
			pointBContainer = new LayoutContainer();
			pointAContainer.setSize("220", "25");
			pointBContainer.setBorders(false);
			pointBContainer.setStyleName("point-B");
			pointBContainer.add(getHeaderContainerGridB());
		}
		return pointBContainer;
	}

	private Html getDescriptionHtml() {
		if (descriptionHtml == null) {
			descriptionHtml = new Html("001 - SANTACRUZ X ALVORADA - PARADOR");
			descriptionHtml.setSize("440", "24");
			descriptionHtml.setStyleName("point-name");
		}
		return descriptionHtml;
	}

	private LayoutContainer getHeaderContainerGridA() {
		if (headerContainerGridA == null) {
			headerContainerGridA = new LayoutContainer();
			headerContainerGridA.setSize("220", "24");
			headerContainerGridA.setStyleName("a-point");
			headerContainerGridA.setLayout(new CenterLayout());
			headerContainerGridA.add(getPontoAHtml());
		}
		return headerContainerGridA;
	}

	private Html getPontoAHtml() {
		if (pontoAHtml == null) {
			pontoAHtml = new Html("STA. CRUZ");
			pontoAHtml.setSize("218", "14");
			pontoAHtml.setStyleName("point");
		}
		return pontoAHtml;
	}

	private LayoutContainer getHeaderContainerGridB() {
		if (headerContainerGridB == null) {
			headerContainerGridB = new LayoutContainer();
			headerContainerGridB.setSize("220", "24");
			headerContainerGridB.setStyleName("b-point");
			headerContainerGridB.setLayout(new CenterLayout());
			headerContainerGridB.add(getPontoBHtml());
		}
		return headerContainerGridB;
	}

	private Html getPontoBHtml() {
		if (pontoBHtml == null) {
			pontoBHtml = new Html("ALVORADA");
			pontoBHtml.setSize("220", "14");
			pontoBHtml.setStyleName("point");
		}
		return pontoBHtml;
	}
	private WidgetDepartureControlRow getWidgetDepartureControlRow() {
		if (widgetDepartureControlRow == null) {
			widgetDepartureControlRow = new WidgetDepartureControlRow();
			widgetDepartureControlRow.setContent(new DTOArrivedControl("Planej.", "Realiz.", "Veic.", "Tab.", "Planej.", "Realiz.", "Veic.", "Tab."),Chronology.NONE);
			widgetDepartureControlRow.defineAsHeader();
			
		}
		return widgetDepartureControlRow;
	}
	private WidgetDepartureControlRow getWidgetDepartureControlRow_1() {
		if (widgetDepartureControlRow_1 == null) {
			widgetDepartureControlRow_1 = new WidgetDepartureControlRow();
			widgetDepartureControlRow_1.setContent(new DTOArrivedControl("08:15", "08:14", "86865", "02", "08:03", "08:04", "87346", "02"),Chronology.PAST);
		}
		return widgetDepartureControlRow_1;
	}
	private WidgetDepartureControlRow getWidgetDepartureControlRow_2() {
		if (widgetDepartureControlRow_2 == null) {
			widgetDepartureControlRow_2 = new WidgetDepartureControlRow();
			widgetDepartureControlRow_2.setContent(new DTOArrivedControl("", "08:20", "88765", "04", "", "08:20", "87564", "02"),Chronology.PAST);
		}
		return widgetDepartureControlRow_2;
	}
	private WidgetDepartureControlRow getWidgetDepartureControlRow_3() {
		if (widgetDepartureControlRow_3 == null) {
			widgetDepartureControlRow_3 = new WidgetDepartureControlRow();
			widgetDepartureControlRow_3.setContent(new DTOArrivedControl("09:00", "09:01", "33346", "05", "09:01", "09:01", "87564", "05"),Chronology.PRESENT);
		}
		return widgetDepartureControlRow_3;
	}
	private WidgetDepartureControlRow getWidgetDepartureControlRow_4() {
		if (widgetDepartureControlRow_4 == null) {
			widgetDepartureControlRow_4 = new WidgetDepartureControlRow();
			widgetDepartureControlRow_4.setContent(new DTOArrivedControl("09:45", "", "", "05", "09:45", "", "", "05"),Chronology.FUTURE);
		}
		return widgetDepartureControlRow_4;
	}
	private WidgetDepartureControlRow getWidgetDepartureControlRow_5() {
		if (widgetDepartureControlRow_5 == null) {
			widgetDepartureControlRow_5 = new WidgetDepartureControlRow();
			widgetDepartureControlRow_5.setContent(new DTOArrivedControl("10:15", "", "", "05", "10:20", "", "", "05"),Chronology.FUTURE);
		}
		return widgetDepartureControlRow_5;
	}
	private WidgetDepartureControlRow getWidgetDepartureControlRow_6() {
		if (widgetDepartureControlRow_6 == null) {
			widgetDepartureControlRow_6 = new WidgetDepartureControlRow();
			widgetDepartureControlRow_6.setContent(new DTOArrivedControl("10:35", "", "", "03", "10:40", "", "", "02"),Chronology.FUTURE);
		}
		return widgetDepartureControlRow_6;
	}
	private WidgetDepartureControlRow getWidgetDepartureControlRow_7() {
		if (widgetDepartureControlRow_7 == null) {
			widgetDepartureControlRow_7 = new WidgetDepartureControlRow();
			widgetDepartureControlRow_7.setContent(new DTOArrivedControl("10:55", "", "", "04", "11:00", "", "", "02"),Chronology.FUTURE);
		}
		return widgetDepartureControlRow_7;
	}
	private WidgetDepartureControlRow getWidgetDepartureControlRow_8() {
		if (widgetDepartureControlRow_8 == null) {
			widgetDepartureControlRow_8 = new WidgetDepartureControlRow();
			widgetDepartureControlRow_7.setContent(new DTOArrivedControl("11:25", "", "", "04", "11:30", "", "", "02"),Chronology.FUTURE);
		}
		return widgetDepartureControlRow_8;
	}
	private WidgetDepartureControlRow getWidgetDepartureControlRow_9() {
		if (widgetDepartureControlRow_9 == null) {
			widgetDepartureControlRow_9 = new WidgetDepartureControlRow();
			widgetDepartureControlRow_9.setContent(new DTOArrivedControl("08:00", "08:04", "80792", "01", "08:12", "08:11", "80778", "02"),Chronology.PAST);
		}
		return widgetDepartureControlRow_9;
	}
	private WidgetDepartureControlRow getWidgetDepartureControlRow_10() {
		if (widgetDepartureControlRow_10 == null) {
			widgetDepartureControlRow_10 = new WidgetDepartureControlRow();
		}
		return widgetDepartureControlRow_10;
	}
}
