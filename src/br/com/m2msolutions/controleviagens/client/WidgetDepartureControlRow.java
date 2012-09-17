package br.com.m2msolutions.controleviagens.client;



import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.google.gwt.user.client.ui.Widget;

public class WidgetDepartureControlRow extends LayoutContainer {
	private static final String TABLE_A = "TABLE_A";
	private static final String VEHICLE_A = "VEHICLE_A";
	private static final String REALIZED_A = "REALIZED_A";
	private static final String PLANNED_A = "PLANNED_A";

	private static final String TABLE_B = "TABLE_B";
	private static final String VEHICLE_B = "VEHICLE_B";
	private static final String REALIZED_B = "REALIZED_B";
	private static final String PLANNED_B = "PLANNED_B";

	private LayoutContainer colunmA;
	private Html colunmAPlanned;
	private Html columnARealized;
	private Html columnAVehicle;
	private Html columnATable;

	private LayoutContainer colunmB;
	private Html colunmBPlanned;
	private Html columnBRealized;
	private Html columnBVehicle;
	private Html columnBTable;

	public WidgetDepartureControlRow() {
		initComponents();
	}

	public void defineAsHeader() {
		setStyleName("detail");
	}
	public void setContent(DTOArrivedControl content, Chronology chronology) {
		colunmAPlanned.setHtml(content.getPlannedA());
		columnARealized.setHtml(content.getRealizedA());
		columnAVehicle.setHtml(content.getVehicleA());
		columnATable.setHtml(content.getTableA());
		colunmBPlanned.setHtml(content.getPlannedB());
		columnBRealized.setHtml(content.getRealizedB());
		columnBVehicle.setHtml(content.getVehicleB());
		columnBTable.setHtml(content.getTableB());
		updateVisual(chronology);
	}

	/**Para dar o aspecto apagado nos horários que já passaram, destaque no horário corrente e normal nos horários futuros.
	 * 
	 * @param chronology
	 */
	private void updateVisual(Chronology chronology) {
		switch (chronology) {
		case PAST:	
			setStyleName("past");
			break;
		case PRESENT:			
			setStyleName("present");
			break;
		case FUTURE:			
			setStyleName("future");
			break;
		default:
			break;
		}
	}

	private void initComponents() {
		setSize("440", "21");
		setStyleName("row");
		setLayout(new ColumnLayout());
		add(getColunmA(), new ColumnData(.5));
		add(getColunmB(), new ColumnData(.5));
	}

	private Widget getColunmA() {
		if (colunmA == null) {
			colunmA = new LayoutContainer();
			colunmA.setSize("220px", "21px");
			colunmA.setBorders(false);
			colunmA.setLayout(new ColumnLayout());
			colunmA.add(getColunmAPlanned(), new ColumnData(60.0));
			colunmA.add(getColumnARealized(), new ColumnData(60.0));
			colunmA.add(getColumnAVehicle(), new ColumnData(60.0));
			colunmA.add(getColumnATable(), new ColumnData(32.0));
		}
		return colunmA;
	}

	private Widget getColunmB() {
		if (colunmB == null) {
			colunmB = new LayoutContainer();
			colunmB.setSize("220px", "21px");
			colunmB.setBorders(false);
			colunmB.setLayout(new ColumnLayout());
			colunmB.add(getColunmBPlanned(), new ColumnData(60.0));
			colunmB.add(getColumnBRealized(), new ColumnData(60.0));
			colunmB.add(getColumnBVehicle(), new ColumnData(60.0));
			colunmB.add(getColumnBTable(), new ColumnData(30.0));
		}
		return colunmB;
	}

	private Html getColunmAPlanned() {
		if (colunmAPlanned == null) {
			colunmAPlanned = new Html("");
			colunmAPlanned.setBorders(false);
			colunmAPlanned.setStyleName("left-cell");
		}
		return colunmAPlanned;
	}

	private Html getColumnARealized() {
		if (columnARealized == null) {
			columnARealized = new Html("");
			columnARealized.setBorders(false);
			columnARealized.setStyleName("left-cell");
		}
		return columnARealized;
	}

	private Html getColumnAVehicle() {
		if (columnAVehicle == null) {
			columnAVehicle = new Html("");
			columnAVehicle.setStyleName("left-cell");
			columnAVehicle.setBorders(false);
		}
		return columnAVehicle;
	}

	private Html getColumnATable() {
		if (columnATable == null) {
			columnATable = new Html("");
			columnATable.setStyleName("left-cell");
			columnATable.setBorders(false);
		}
		return columnATable;
	}

	private Html getColunmBPlanned() {
		if (colunmBPlanned == null) {
			colunmBPlanned = new Html("");
			colunmBPlanned.setStyleName("right-cell");
			colunmBPlanned.setBorders(false);
		}
		return colunmBPlanned;
	}

	private Html getColumnBRealized() {
		if (columnBRealized == null) {
			columnBRealized = new Html("");
			columnBRealized.setStyleName("right-cell");
			columnBRealized.setBorders(false);
		}
		return columnBRealized;
	}

	private Html getColumnBVehicle() {
		if (columnBVehicle == null) {
			columnBVehicle = new Html("");
			columnBVehicle.setStyleName("right-cell");
			columnBVehicle.setBorders(false);

		}
		return columnBVehicle;
	}

	private Html getColumnBTable() {
		if (columnBTable == null) {
			columnBTable = new Html("");
			columnBTable.setStyleName("right-cell");
			columnBTable.setBorders(false);
		}
		return columnBTable;
	}
}
