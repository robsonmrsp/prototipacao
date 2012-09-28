package br.com.m2msolutions.controleviagens.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.menu.CheckMenuItem;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ControleViagensContainer extends LayoutContainer {

	ControleViagensServiceAsync controleViagenService = GWT.create(ControleViagensService.class);

	private EditorGrid<TripBaseModel> gridLeft;
	private EditorGrid<TripBaseModel> gridRight;
	private GridCellRenderer<TripBaseModel> renderer;
	private List<DTOTrip.Status> listFilterControlPointOne;
	private List<DTOTrip.Status> listFilterControlPointTow;
	private List<DTOTrip> lastListDto;
	private Text txtResumoFinal;
	private Text txtResumoInitial;
	private Text txtInitialPoint;
	private Text txtFinalPoint;
	private boolean inverse;

	private SortMode sortMode;

	private CheckBox cNh;
	private CheckBox cAt;
	private CheckBox cAd;
	private CheckBox cEx;
	private CheckBox cNh1;
	private CheckBox cAt1;
	private CheckBox cAd1;
	private CheckBox cEx1;

	private CheckMenuItem noHorario;
	private CheckMenuItem adiantadas;
	private CheckMenuItem atrasadas;
	private CheckMenuItem extras;

	private CheckMenuItem chEmExecucao;
	private CheckMenuItem chExcecoes;

	private LayoutContainer lContainerCheck;
	private LayoutContainer  lContainerCheckRight;

	private long departureControlPointOne;
	private long departureControlPointTow;

	private CheckMenuItem oVPlan;
	private CheckMenuItem oVExec;

	DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("hh:mm");

	public ControleViagensContainer() {
		this(false);
	}

	public ControleViagensContainer(boolean enableToolBar) {

		setSize("800", "300");

		renderer = new GridCellRenderer<TripBaseModel>() { 

			public String render(TripBaseModel model, String property, ColumnData config, int rowIndex, 
					int colIndex, ListStore<TripBaseModel> store, 
					Grid<TripBaseModel> grid) {  

				DTOTrip dto = (DTOTrip) model.get(property);

				return 	generateHTMLCel(dto);
			} 

		};

		listFilterControlPointOne = new ArrayList<DTOTrip.Status>();
		listFilterControlPointTow = new ArrayList<DTOTrip.Status>();
		lastListDto = new ArrayList<DTOTrip>();

		sortMode = SortMode.ASCENDING;

		setLayout(new FitLayout());
		setDepartureControlPointOne(1);
		setDepartureControlPointTow(2);

		ContentPanel cpMaster = new ContentPanel();
		cpMaster.setHeaderVisible(false);
		cpMaster.setCollapsible(true);
		cpMaster.setBodyBorder(false);
		cpMaster.setLayout(new FillLayout(Orientation.HORIZONTAL));
		cpMaster.setContextMenu(createContextMenu());

		ContentPanel cpLeft = new ContentPanel();
		cpLeft.setBodyBorder(false);
		cpLeft.setHeaderVisible(false);
		cpLeft.setCollapsible(true);
		cpMaster.add(cpLeft);
		cpLeft.setLayout(new RowLayout(Orientation.VERTICAL));

		ContentPanel cpLeftTop = new ContentPanel();
		cpLeftTop.setBodyBorder(false);
		cpLeftTop.setHeaderVisible(false);
		cpLeftTop.setCollapsible(true);
		cpLeftTop.setLayout(new AbsoluteLayout());

		txtInitialPoint = new Text("");
		txtInitialPoint.setStyleName("controleViagens-tituloPonto");
		cpLeftTop.add(txtInitialPoint, new AbsoluteData(6, 6));
		txtInitialPoint.setSize("213px", "16px");

		txtResumoInitial = new Text("");
		txtResumoInitial.setStyleName("controleViagens-totalViagens");
		cpLeftTop.add(txtResumoInitial, new AbsoluteData(294, 58));
		txtResumoInitial.setSize("85px", "18px");

		lContainerCheck = new LayoutContainer();
		lContainerCheck.setLayout(new RowLayout(Orientation.HORIZONTAL));
		lContainerCheck.setSize("281px", "18px");
		lContainerCheck.setBorders(false);
		cpLeftTop.add(lContainerCheck, new AbsoluteData(6, 58));
		cpLeft.add(cpLeftTop, new RowData(1.0, Style.DEFAULT, new Margins()));
		cpLeftTop.setHeight("80");

		lContainerCheck.setVisible(false);

		ContentPanel cpLeftCenter = new ContentPanel();
		cpLeftCenter.setBodyBorder(false);
		cpLeftCenter.setHeaderVisible(false);
		cpLeftCenter.setCollapsible(true);
		cpLeftCenter.setLayout(new FitLayout());

		gridLeft = new EditorGrid<TripBaseModel>(new ListStore<TripBaseModel>(), getColumnModel());
		gridLeft.setStripeRows(true);
		gridLeft.setBorders(false);
		gridLeft.setHideHeaders(true);
		gridLeft.getView().setShowDirtyCells(false);
		cpLeftCenter.add(gridLeft);
		cpLeft.add(cpLeftCenter, new RowData(1.0, 1.0, new Margins()));

		ContentPanel cpRight = new ContentPanel();
		cpRight.setBodyBorder(false);
		cpRight.setHeaderVisible(false);
		cpRight.setCollapsible(true);
		cpMaster.add(cpRight);
		cpRight.setLayout(new RowLayout(Orientation.VERTICAL));

		ContentPanel cpRightTop = new ContentPanel();
		cpRightTop.setBodyBorder(false);
		cpRightTop.setHeaderVisible(false);
		cpRightTop.setCollapsible(true);
		cpRightTop.setLayout(new AbsoluteLayout());

		lContainerCheckRight = new LayoutContainer();
		lContainerCheckRight.setLayout(new RowLayout(Orientation.HORIZONTAL));
		lContainerCheckRight.setSize("281px", "18px");
		lContainerCheckRight.setBorders(false);
		cpRightTop.add(lContainerCheckRight, new AbsoluteData(6, 58));
		lContainerCheckRight.setVisible(false);

		txtFinalPoint = new Text("");
		txtFinalPoint.setStyleName("controleViagens-tituloPonto");
		cpRightTop.add(txtFinalPoint, new AbsoluteData(6, 6));
		txtFinalPoint.setSize("213px", "18px");

		txtResumoFinal = new Text("");
		txtResumoFinal.setStyleName("controleViagens-totalViagens");
		cpRightTop.add(txtResumoFinal, new AbsoluteData(294, 58));
		txtResumoFinal.setSize("85px", "18px");
		cpRight.add(cpRightTop, new RowData(1.0, Style.DEFAULT, new Margins()));
		cpRightTop.setHeight("80");

		ContentPanel cpRightCenter = new ContentPanel();
		cpRightCenter.setBodyBorder(false);
		cpRightCenter.setHeaderVisible(false);
		cpRightCenter.setCollapsible(true);
		cpRightCenter.setLayout(new FitLayout());

		gridRight = new EditorGrid<TripBaseModel>(new ListStore<TripBaseModel>(), getColumnModel());
		gridRight.setHideHeaders(true);
		gridRight.setBorders(false);
		cpRightCenter.add(gridRight);
		cpRight.add(cpRightCenter, new RowData(1.0, 1.0, new Margins()));
		add(cpMaster);
		cpMaster.setWidth("750px");

		createCheckBoxPanel();

		if(enableToolBar){
			cpMaster.setTopComponent(getToolBar());
		}

	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		loadData();
	}

	private Menu createContextMenu (){
		Menu contextMenu = new Menu();  

		criaMenuPrincipal(contextMenu);

		contextMenu.add(new SeparatorMenuItem());

		criaMenuFiltro(contextMenu);

		contextMenu.add(new SeparatorMenuItem());

		MenuItem miOrdenacao = new MenuItem();  
		miOrdenacao.setText("Ordenação");

		Menu mOrdencao = new Menu();

		criaMenuOrdencacao(mOrdencao);

		miOrdenacao.setSubMenu(mOrdencao);

		contextMenu.add(miOrdenacao);


		return contextMenu;
	}

	private ToolBar getToolBar(){

		ToolBar toolBar = new ToolBar();

		Button bPrincipal = new Button("Principal");

		Menu mPrincipal = new Menu();
		criaMenuPrincipal(mPrincipal);
		bPrincipal.setMenu(mPrincipal);

		toolBar.add(bPrincipal);

		Button bFiltro = new Button("Filtro");

		Menu mFiltro = new Menu();
		criaMenuFiltro(mFiltro);
		bFiltro.setMenu(mFiltro);

		toolBar.add(bFiltro);

		Button bOrdenacao = new Button("Ordenação");

		Menu mOrdenacao = new Menu();
		criaMenuOrdencacao(mOrdenacao);
		bOrdenacao.setMenu(mOrdenacao);

		toolBar.add(bOrdenacao);

		return toolBar;
	}

	private void criaMenuOrdencacao(Menu mOrdencao) {

		oVPlan = new CheckMenuItem();  
		oVPlan.setText("Partidas Planejadas");
		oVPlan.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {
				if(oVPlan.isChecked()){
					Collections.sort(lastListDto,new TripSortListByDeparturePlan(getSortMode()));
				}
				updateGrid(lastListDto);
			}
		});
		oVPlan.setGroup("miOrdenacao");
		oVPlan.setChecked(true); 
		mOrdencao.add(oVPlan);

		oVExec = new CheckMenuItem();  
		oVExec.setText("Partidas Executadas");
		oVExec.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {
				if(oVExec.isChecked()){
					Collections.sort(lastListDto,new TripSortListByDepartureExec(getSortMode()));
				}
				updateGrid(lastListDto);
			}
		});

		oVExec.setGroup("miOrdenacao");
		mOrdencao.add(oVExec);

		mOrdencao.add(new SeparatorMenuItem());

		final CheckMenuItem oCrecente = new CheckMenuItem();  
		oCrecente.setText("Crecente");
		oCrecente.setGroup("crecente");
		oCrecente.setChecked(true);
		oCrecente.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {
				if(oCrecente.isChecked()){
					setSortMode(SortMode.ASCENDING);
					orderBySortMode();
				}
				updateGrid(lastListDto);
			}
		});
		mOrdencao.add(oCrecente);

		final CheckMenuItem oDCrecente = new CheckMenuItem();  
		oDCrecente.setText("Decrecente");
		oDCrecente.setGroup("crecente");
		oDCrecente.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {
				if(oDCrecente.isChecked()){
					setSortMode(SortMode.DESCENDING);
					orderBySortMode();
				}
				updateGrid(lastListDto);
			}
		});
		mOrdencao.add(oDCrecente);
	}

	private void criaMenuFiltro(Menu contextMenu) {
		chEmExecucao = new CheckMenuItem();  
		chEmExecucao.setText("Só em execução");
		chEmExecucao.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {

				chExcecoes.setChecked(false);
				limpaEstadoCheck();
				listFilterControlPointOne.clear();
				listFilterControlPointTow.clear();

				if(chEmExecucao.isChecked()){
					listFilterControlPointOne.add(DTOTrip.Status.ADIANTADO);
					listFilterControlPointOne.add(DTOTrip.Status.ATRASADO);
					listFilterControlPointOne.add(DTOTrip.Status.NO_HORARIO);
					listFilterControlPointOne.add(DTOTrip.Status.EXTRA_EXEC);
					listFilterControlPointTow.add(DTOTrip.Status.ADIANTADO);
					listFilterControlPointTow.add(DTOTrip.Status.ATRASADO);
					listFilterControlPointTow.add(DTOTrip.Status.NO_HORARIO);
					listFilterControlPointTow.add(DTOTrip.Status.EXTRA_EXEC);
				}else{
					listFilterControlPointOne.remove(DTOTrip.Status.ADIANTADO);
					listFilterControlPointOne.remove(DTOTrip.Status.ATRASADO);
					listFilterControlPointOne.remove(DTOTrip.Status.NO_HORARIO);
					listFilterControlPointOne.remove(DTOTrip.Status.EXTRA_EXEC);
					listFilterControlPointTow.remove(DTOTrip.Status.ADIANTADO);
					listFilterControlPointTow.remove(DTOTrip.Status.ATRASADO);
					listFilterControlPointTow.remove(DTOTrip.Status.NO_HORARIO);
					listFilterControlPointTow.remove(DTOTrip.Status.EXTRA_EXEC);
				}
				updateGrid(lastListDto);
			}
		});
		contextMenu.add(chEmExecucao);

		contextMenu.add(new SeparatorMenuItem());

		chExcecoes = new CheckMenuItem();  
		chExcecoes.setText("Só Exceções");
		chExcecoes.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {

				chEmExecucao.setChecked(false);
				limpaEstadoCheck();
				listFilterControlPointOne.clear();
				listFilterControlPointTow.clear();

				if(chExcecoes.isChecked()){
					listFilterControlPointOne.add(DTOTrip.Status.ADIANTADO);
					listFilterControlPointOne.add(DTOTrip.Status.ATRASADO);
					listFilterControlPointTow.add(DTOTrip.Status.ADIANTADO);
					listFilterControlPointTow.add(DTOTrip.Status.ATRASADO);
					cAt.setValue(true);
					cAt1.setValue(true);
					cAd.setValue(true);
					cAd1.setValue(true);
				}else{
					listFilterControlPointOne.remove(DTOTrip.Status.ADIANTADO);
					listFilterControlPointOne.remove(DTOTrip.Status.ATRASADO);
					listFilterControlPointTow.remove(DTOTrip.Status.ADIANTADO);
					listFilterControlPointTow.remove(DTOTrip.Status.ATRASADO);
					cAt.setValue(false);
					cAt1.setValue(false);
					cAd.setValue(false);
					cAd1.setValue(false);
				}
				updateGrid(lastListDto);
			}
		});
		contextMenu.add(chExcecoes);

		contextMenu.add(new SeparatorMenuItem());

		noHorario = new CheckMenuItem();
		noHorario.setText("No Horário");
		noHorario.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {

				limpaEstadoFiltro();

				if(noHorario.isChecked()){
					listFilterControlPointOne.add(DTOTrip.Status.NO_HORARIO);
					listFilterControlPointTow.add(DTOTrip.Status.NO_HORARIO);
					cNh.setValue(true);
					cNh1.setValue(true);
				}else{
					listFilterControlPointOne.remove(DTOTrip.Status.NO_HORARIO);
					listFilterControlPointTow.remove(DTOTrip.Status.NO_HORARIO);
					cNh.setValue(false);
					cNh1.setValue(false);
				}
				updateGrid(lastListDto);
			}
		});
		contextMenu.add(noHorario);

		adiantadas = new CheckMenuItem();  
		adiantadas.setText("Adiantadas");
		adiantadas.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {
				if(adiantadas.isChecked()){

					limpaEstadoFiltro();

					listFilterControlPointOne.add(DTOTrip.Status.ADIANTADO);
					listFilterControlPointTow.add(DTOTrip.Status.ADIANTADO);
					cAd.setValue(true);
					cAd1.setValue(true);
				}else{
					listFilterControlPointOne.remove(DTOTrip.Status.ADIANTADO);
					listFilterControlPointTow.remove(DTOTrip.Status.ADIANTADO);
					cAd.setValue(false);
					cAd1.setValue(false);
				}
				updateGrid(lastListDto);
			}
		});
		contextMenu.add(adiantadas);

		atrasadas = new CheckMenuItem();  
		atrasadas.setText("Atrasadas");
		atrasadas.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {

				limpaEstadoFiltro();

				if(atrasadas.isChecked()){
					listFilterControlPointOne.add(DTOTrip.Status.ATRASADO);
					listFilterControlPointTow.add(DTOTrip.Status.ATRASADO);
					cAt.setValue(true);
					cAt1.setValue(true);
				}else{
					listFilterControlPointOne.remove(DTOTrip.Status.ATRASADO);
					listFilterControlPointTow.remove(DTOTrip.Status.ATRASADO);
					cAt.setValue(false);
					cAt1.setValue(false);
				}
				updateGrid(lastListDto);
			}
		});
		contextMenu.add(atrasadas);

		extras = new CheckMenuItem();  
		extras.setText("Extras");
		extras.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {

				limpaEstadoFiltro();

				if(extras.isChecked()){
					listFilterControlPointOne.add(DTOTrip.Status.EXTRA_EXEC);
					listFilterControlPointOne.add(DTOTrip.Status.EXTRA_FINAL);
					listFilterControlPointTow.add(DTOTrip.Status.EXTRA_EXEC);
					listFilterControlPointTow.add(DTOTrip.Status.EXTRA_FINAL);
					cEx.setValue(true);
					cEx1.setValue(true);
				}else{
					listFilterControlPointOne.remove(DTOTrip.Status.EXTRA_EXEC);
					listFilterControlPointOne.remove(DTOTrip.Status.EXTRA_FINAL);
					listFilterControlPointTow.remove(DTOTrip.Status.EXTRA_EXEC);
					listFilterControlPointTow.remove(DTOTrip.Status.EXTRA_FINAL);
					cEx.setValue(false);
					cEx1.setValue(false);
				}
				updateGrid(lastListDto);
			}
		});
		contextMenu.add(extras);
	}

	private void createCheckBoxPanel() {

		cNh = new CheckBox();
		cNh.setBoxLabel("Nh");
		cNh.setStyleName("controleViagens-Nh");
		cNh.addListener(Events.Change, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if(cNh.getValue()){
					listFilterControlPointOne.add(DTOTrip.Status.NO_HORARIO);
				}else{
					listFilterControlPointOne.remove(DTOTrip.Status.NO_HORARIO);
				}
				updateGrid(lastListDto);
			}
		});
		lContainerCheck.add(cNh, new RowData(55.0, Style.DEFAULT, new Margins()));

		cAt = new CheckBox();
		cAt.setBoxLabel("At");
		cAt.setStyleName("controleViagens-At");
		cAt.addListener(Events.Change, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if(cAt.getValue()){
					listFilterControlPointOne.add(DTOTrip.Status.ATRASADO);
				}else{
					listFilterControlPointOne.remove(DTOTrip.Status.ATRASADO);
				}
				updateGrid(lastListDto);
			}
		});
		lContainerCheck.add(cAt, new RowData(55.0, Style.DEFAULT, new Margins()));


		cAd = new CheckBox();
		cAd.setBoxLabel("Ad");
		cAd.setStyleName("controleViagens-Ad");
		cAd.addListener(Events.Change, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if(cAd.getValue()){
					listFilterControlPointOne.add(DTOTrip.Status.ADIANTADO);
				}else{
					listFilterControlPointOne.remove(DTOTrip.Status.ADIANTADO);
				}
				updateGrid(lastListDto);
			}
		});
		lContainerCheck.add(cAd, new RowData(55.0, Style.DEFAULT, new Margins()));


		cEx = new CheckBox();
		cEx.setBoxLabel("Ex");
		cEx.setStyleName("controleViagens-Ex");
		cEx.addListener(Events.Change, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if(cEx.getValue()){
					listFilterControlPointOne.add(DTOTrip.Status.EXTRA_EXEC);
					listFilterControlPointOne.add(DTOTrip.Status.EXTRA_FINAL);
				}else{
					listFilterControlPointOne.remove(DTOTrip.Status.EXTRA_EXEC);
					listFilterControlPointOne.remove(DTOTrip.Status.EXTRA_FINAL);
				}
				updateGrid(lastListDto);
			}
		});
		lContainerCheck.add(cEx, new RowData(55.0, Style.DEFAULT, new Margins()));

		cNh1 = new CheckBox();
		cNh1.setBoxLabel("Nh");
		cNh1.setStyleName("controleViagens-Nh");
		cNh1.addListener(Events.Change, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if(cNh1.getValue()){
					listFilterControlPointTow.add(DTOTrip.Status.NO_HORARIO);
				}else{
					listFilterControlPointTow.remove(DTOTrip.Status.NO_HORARIO);
				}
				updateGrid(lastListDto);
			}
		});

		lContainerCheckRight.add(cNh1, new RowData(55.0, Style.DEFAULT, new Margins()));

		cAt1 = new CheckBox();
		cAt1.setBoxLabel("At");
		cAt1.setStyleName("controleViagens-At");
		cAt1.addListener(Events.Change, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if(cAt1.getValue()){
					listFilterControlPointTow.add(DTOTrip.Status.ATRASADO);
				}else{
					listFilterControlPointTow.remove(DTOTrip.Status.ATRASADO);
				}
				updateGrid(lastListDto);
			}
		});
		lContainerCheckRight.add(cAt1, new RowData(55.0, Style.DEFAULT, new Margins()));


		cAd1 = new CheckBox();
		cAd1.setBoxLabel("Ad");
		cAd1.setStyleName("controleViagens-Ad");
		cAd1.addListener(Events.Change, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if(cAd1.getValue()){
					listFilterControlPointTow.add(DTOTrip.Status.ADIANTADO);
				}else{
					listFilterControlPointTow.remove(DTOTrip.Status.ADIANTADO);
				}
				updateGrid(lastListDto);
			}
		});
		lContainerCheckRight.add(cAd1, new RowData(55.0, Style.DEFAULT, new Margins()));


		cEx1 = new CheckBox();
		cEx1.setBoxLabel("Ex");
		cEx1.setStyleName("controleViagens-Ex");
		cEx1.addListener(Events.Change, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if(cEx1.getValue()){
					listFilterControlPointTow.add(DTOTrip.Status.EXTRA_EXEC);
					listFilterControlPointTow.add(DTOTrip.Status.EXTRA_FINAL);
				}else{
					listFilterControlPointTow.remove(DTOTrip.Status.EXTRA_EXEC);
					listFilterControlPointTow.remove(DTOTrip.Status.EXTRA_FINAL);
				}
				updateGrid(lastListDto);
			}
		});
		lContainerCheckRight.add(cEx1, new RowData(55.0, Style.DEFAULT, new Margins()));
	}

	private void limpaEstadoFiltro() {

		if(chExcecoes.isChecked() || chEmExecucao.isChecked()){

			limpaEstadoCheck();

			chExcecoes.setChecked(false);
			chEmExecucao.setChecked(false);
			listFilterControlPointOne.clear();
			listFilterControlPointTow.clear();
		}

	}

	private void limpaEstadoCheck() {

		cAt.setValue(false);
		cAt1.setValue(false);
		cAd.setValue(false);
		cAd1.setValue(false);
		cNh.setValue(false);
		cNh1.setValue(false);
		cEx.setValue(false);
		cEx1.setValue(false);

		noHorario.setChecked(false);
		adiantadas.setChecked(false);
		atrasadas.setChecked(false);
		extras.setChecked(false);

	}

	private void criaMenuPrincipal(Menu contextMenu) {

		MenuItem selLinha = new MenuItem();  
		selLinha.setText("Selecionar Linhas");
		contextMenu.add(selLinha);

		MenuItem linhasRecente = new MenuItem();  
		linhasRecente.setText("Linhas Recentes");

		Menu subMenuLinhasRecentes = new Menu();
		MenuItem linha1 = new MenuItem();  
		linha1.setText("001-Santa Cruz X Alvorada (Parador)");
		subMenuLinhasRecentes.add(linha1);

		MenuItem linha2 = new MenuItem();  
		linha2.setText("002-Santa Cruz X Alvorada (Expresso)");
		subMenuLinhasRecentes.add(linha2);

		linhasRecente.setSubMenu(subMenuLinhasRecentes);

		contextMenu.add(linhasRecente);
		contextMenu.add(new SeparatorMenuItem());

		MenuItem inverterTerminais = new MenuItem();  
		inverterTerminais.setText("Inverter Terminais");
		inverterTerminais.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent ce) {
				setInverse(!isInverse());
				updateView(lastListDto);
			}
		});
		contextMenu.add(inverterTerminais);
	}

	private void orderBySortMode() {
		if(oVPlan.isChecked())
			Collections.sort(lastListDto,new TripSortListByDeparturePlan(getSortMode()));
		if(oVExec.isChecked())
			Collections.sort(lastListDto,new TripSortListByDepartureExec(getSortMode()));
	}

	private String generateHTMLCel(DTOTrip dto){

		String html = "";

		if(dto == null)
			return html;

		String valorAdAt = "";
		String statusDesc = "";
		String hora = (dto.getHora() == null)?"":dto.getHora();

		if(dto.getStatus() == DTOTrip.Status.ADIANTADO){
			valorAdAt = (dto.getDiferencaPlanExec() == null)?"":" <span style=\"color:#ff0000; font-weight : bold\">(-"+dto.getDiferencaPlanExec()+")</span>";
			statusDesc= "<span style=\"color:#ff0000; font-weight : bold\"> Adiantado </span>";
		}else if(dto.getStatus() == DTOTrip.Status.ATRASADO){
			valorAdAt = (dto.getDiferencaPlanExec() == null)?"":" <span style=\"color:#0000ff; font-weight : bold\">(-"+dto.getDiferencaPlanExec()+")</span>";
			statusDesc= "<span style=\"color:#0000ff; font-weight : bold\"> Atrasado </span>";
		}else if(dto.getStatus() == DTOTrip.Status.FINALIZADA){
			String hFim = (dto.getHorarioFim() == 0)?"":dateTimeFormat.format(new Date(dto.getHorarioFim()));
			valorAdAt = (dto.getDiferencaPlanExec() == null)?"":" <span style=\"color:#006400; font-weight : bold\">(-"+dto.getDiferencaPlanExec()+")</span>";
			statusDesc= "Fim "+hFim;
		}else if(dto.getStatus() == DTOTrip.Status.NO_HORARIO){
			valorAdAt = (dto.getDiferencaPlanExec() == null)?"":" <span style=\"color:#006400; font-weight : bold\">(-"+dto.getDiferencaPlanExec()+")</span>";
			statusDesc= "<span style=\"color:#006400; font-weight : bold\"> No Horário </span>";
		}
		String hPlan = (dto.getHorarioPlanejado() == 0)?"--:--":dateTimeFormat.format(new Date(dto.getHorarioPlanejado()));
		String hExec = (dto.getHorarioExecutado() == 0)?"--:--":dateTimeFormat.format(new Date(dto.getHorarioExecutado()));

		html = "<b>"+hPlan+"</b> "+hExec+valorAdAt+"<br/> "+
				dto.getVehicleCode()+" "+hora+" <br/>"+ 
				statusDesc;

		return html;
	}

	private ColumnModel getColumnModel(){

		int celWidth = 95;
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  

		ColumnConfig column = new ColumnConfig();  
		column.setId("trip1");  
		column.setHeader("Trip1");
		column.setRenderer(renderer);
		column.setWidth(celWidth);   
		configs.add(column);  

		column = new ColumnConfig();  
		column.setId("trip2");  
		column.setHeader("Trip2");
		column.setRenderer(renderer);
		column.setWidth(celWidth);   
		configs.add(column);

		column = new ColumnConfig();  
		column.setId("trip3");  
		column.setHeader("Trip3");
		column.setRenderer(renderer);
		column.setWidth(celWidth);   
		configs.add(column);

		column = new ColumnConfig();  
		column.setId("trip4");  
		column.setHeader("Trip4");
		column.setRenderer(renderer);
		column.setWidth(celWidth);   
		configs.add(column);

		ColumnModel cm = new ColumnModel(configs);
		return cm;

	}

	public void loadData(){

		controleViagenService.getTripList(new AsyncCallback<List<DTOTrip>>() {
			@Override
			public void onSuccess(List<DTOTrip> listDto) {
				updateView(listDto);
			}
			@Override
			public void onFailure(Throwable caught) {
			}
		});

	}

	private void updateView(List<DTOTrip> listDto) {

		updateGrid(listDto);
		confPanelsTop();
		confPainelCheck(listDto);

		lastListDto = listDto;

	}

	private void confPanelsTop() {

		if(isInverse()){
			txtInitialPoint.setText("Alvorada");
			txtFinalPoint.setText("Santa Cruz");
		}else{
			txtInitialPoint.setText("Santa Cruz");
			txtFinalPoint.setText("Alvorada");
		}

		lContainerCheck.setVisible(true);
		lContainerCheckRight.setVisible(true);
	}

	private void updateGrid(List<DTOTrip> listDto){

		List<TripBaseModel> listOne = new ArrayList<TripBaseModel>();
		List<TripBaseModel> listTwo = new ArrayList<TripBaseModel>();

		if(isInverse()){
			listOne = generateTripBaseModelList(listDto,getControlPointTow());
			listTwo = generateTripBaseModelList(listDto,getControlPointOne());
		}else{
			listOne = generateTripBaseModelList(listDto,getControlPointOne());
			listTwo = generateTripBaseModelList(listDto,getControlPointTow());
		}

		gridLeft.getStore().removeAll();
		gridLeft.getStore().add(listOne);
		gridLeft.repaint();

		gridRight.getStore().removeAll();
		gridRight.getStore().add(listTwo);
		gridRight.repaint();

	}

	private void confPainelCheck(List<DTOTrip> listDto){

		int tNh = 0;
		int tAt = 0;
		int tAd = 0;
		int tEx = 0;

		int tNh1 = 0;
		int tAt1 = 0;
		int tAd1 = 0;
		int tEx1 = 0;

		for(DTOTrip dto:listDto){

			if(!isInverse()){

				if(dto.getControlPointId() == this.departureControlPointOne){

					if(DTOTrip.Status.NO_HORARIO == dto.getStatus()){
						tNh++;
					}else if(DTOTrip.Status.ADIANTADO == dto.getStatus()){
						tAt++;
					}else if(DTOTrip.Status.ATRASADO == dto.getStatus()){
						tAd++;
					}else if(DTOTrip.Status.EXTRA_EXEC == dto.getStatus()){
						tEx++;
					}else if(DTOTrip.Status.EXTRA_FINAL == dto.getStatus()){
						tEx++;
					}

				}else{

					if(DTOTrip.Status.NO_HORARIO == dto.getStatus()){
						tNh1++;
					}else if(DTOTrip.Status.ADIANTADO == dto.getStatus()){
						tAt1++;
					}else if(DTOTrip.Status.ATRASADO == dto.getStatus()){
						tAd1++;
					}else if(DTOTrip.Status.EXTRA_EXEC == dto.getStatus()){
						tEx1++;
					}else if(DTOTrip.Status.EXTRA_FINAL == dto.getStatus()){
						tEx1++;
					}
				}

			}else{

				if(dto.getControlPointId() == this.departureControlPointTow){

					if(DTOTrip.Status.NO_HORARIO == dto.getStatus()){
						tNh++;
					}else if(DTOTrip.Status.ADIANTADO == dto.getStatus()){
						tAt++;
					}else if(DTOTrip.Status.ATRASADO == dto.getStatus()){
						tAd++;
					}else if(DTOTrip.Status.EXTRA_EXEC == dto.getStatus()){
						tEx++;
					}else if(DTOTrip.Status.EXTRA_FINAL == dto.getStatus()){
						tEx++;
					}

				}else{

					if(DTOTrip.Status.NO_HORARIO == dto.getStatus()){
						tNh1++;
					}else if(DTOTrip.Status.ADIANTADO == dto.getStatus()){
						tAt1++;
					}else if(DTOTrip.Status.ATRASADO == dto.getStatus()){
						tAd1++;
					}else if(DTOTrip.Status.EXTRA_EXEC == dto.getStatus()){
						tEx1++;
					}else if(DTOTrip.Status.EXTRA_FINAL == dto.getStatus()){
						tEx1++;
					}
				}

			}
		}

		cNh.setBoxLabel("Nh("+tNh+")");
		cNh.setVisible(true);
		if(tNh > 0){  
			cNh.setEnabled(true);
		}else{
			cNh.setEnabled(false);
		}
		cAt.setBoxLabel("At("+tAt+")");
		cAt.setVisible(true);
		if(tAt > 0){
			cAt.setEnabled(true);
		}else{
			cAt.setEnabled(false);
		}
		cAd.setBoxLabel("Ad("+tAd+")");
		cAd.setVisible(true);
		if(tAd > 0){ 
			cAd.setEnabled(true);
		}else{
			cAd.setEnabled(false);
		}
		cEx.setBoxLabel("Ex("+tEx+")");
		cEx.setVisible(true);
		if(tEx > 0){
			cEx.setEnabled(true);
		}else{
			cEx.setEnabled(false);
		}
		cNh1.setBoxLabel("Nh("+tNh1+")");
		cNh1.setVisible(true);
		if(tNh1 > 0){  
			cNh1.setEnabled(true);
		}else{
			cNh1.setEnabled(false);
		}
		cAt1.setBoxLabel("At("+tAt1+")");
		cAt1.setVisible(true);
		if(tAt1 > 0){
			cAt1.setEnabled(true);
		}else{
			cAt1.setEnabled(false);
		}
		cAd1.setBoxLabel("Ad("+tAd1+")");
		cAd1.setVisible(true);
		if(tAd1 > 0){ 
			cAd1.setEnabled(true);
		}else{
			cAd1.setEnabled(false);
		}
		cEx1.setBoxLabel("Ex("+tEx1+")");
		cEx1.setVisible(true);
		if(tEx1 > 0){
			cEx1.setEnabled(true);
		}else{
			cEx1.setEnabled(false);
		}

	}

	private List<TripBaseModel> generateTripBaseModelList(List<DTOTrip> listDto,long departureControlPoint){
		List<TripBaseModel> list = new ArrayList<TripBaseModel>();

		int i = 1;
		int total = 0;

		TripBaseModel tripBaseModel = new TripBaseModel();

		for(DTOTrip dto:listDto){

			if(dto.getControlPointId() != departureControlPoint)
				continue;

			if(departureControlPoint == this.departureControlPointOne){
				if(listFilterControlPointOne.isEmpty() || listFilterControlPointOne.contains(dto.getStatus())){

					if(i == 1){
						tripBaseModel.setTrip1(dto);
						list.add(tripBaseModel);
						i++;
					}else if(i == 2){
						tripBaseModel.setTrip2(dto);
						i++;
					}else if(i == 3){
						tripBaseModel.setTrip3(dto);
						i++;
					}else if(i == 4){
						tripBaseModel.setTrip4(dto);
						tripBaseModel = new TripBaseModel();
						i=1;
					}
					total++;
				}
			}else if(departureControlPoint == this.departureControlPointTow){
				if(listFilterControlPointTow.isEmpty() || listFilterControlPointTow.contains(dto.getStatus())){

					if(i == 1){
						tripBaseModel.setTrip1(dto);
						list.add(tripBaseModel);
						i++;
					}else if(i == 2){
						tripBaseModel.setTrip2(dto);
						i++;
					}else if(i == 3){
						tripBaseModel.setTrip3(dto);
						i++;
					}else if(i == 4){
						tripBaseModel.setTrip4(dto);
						tripBaseModel = new TripBaseModel();
						i=1;
					}
					total++;
				}
			}
		}

		updateTxtTotal(total,departureControlPoint);

		return list;
	}

	private void updateTxtTotal(int total,long departureControlPoint){
		if(departureControlPoint == departureControlPointOne){
			String resultado = "";

			if(listFilterControlPointOne.isEmpty()){
				resultado += "Todos("+total+")";

				if(isInverse()){
					txtResumoFinal.setText(resultado);
				}else{
					txtResumoInitial.setText(resultado);
				}

				return;
			}

			if(listFilterControlPointOne.contains(DTOTrip.Status.NO_HORARIO)){
				resultado += "Nh";
			}
			if(listFilterControlPointOne.contains(DTOTrip.Status.ATRASADO)){
				resultado += (resultado.length()>0)?"/":"";
				resultado += "At";
			}
			if(listFilterControlPointOne.contains(DTOTrip.Status.ADIANTADO)){
				resultado += (resultado.length()>0)?"/":"";
				resultado += "Ad";
			}

			resultado += "("+total+")";

			if(isInverse()){
				txtResumoFinal.setText(resultado);
			}else{
				txtResumoInitial.setText(resultado);
			}

		}else if(departureControlPoint == departureControlPointTow){

			String resultado = "";

			if(listFilterControlPointTow.isEmpty()){
				resultado += "Todos("+total+")";

				if(isInverse()){
					txtResumoInitial.setText(resultado);
				}else{
					txtResumoFinal.setText(resultado);
				}

				return;
			}
			if(listFilterControlPointTow.contains(DTOTrip.Status.NO_HORARIO)){
				resultado += "Nh";
			}
			if(listFilterControlPointTow.contains(DTOTrip.Status.ATRASADO)){
				resultado += (resultado.length()>0)?"/":"";
				resultado += "At";
			}
			if(listFilterControlPointTow.contains(DTOTrip.Status.ADIANTADO)){
				resultado += (resultado.length()>0)?"/":"";
				resultado += "Ad";
			}

			resultado += "("+total+")";

			if(isInverse()){
				txtResumoInitial.setText(resultado);
			}else{
				txtResumoFinal.setText(resultado);
			}
		}
	}

	public long getControlPointOne() {
		return departureControlPointOne;
	}

	public long getControlPointTow() {
		return departureControlPointTow;
	}

	public long getDepartureControlPointOne() {
		return departureControlPointOne;
	}

	public long getDepartureControlPointTow() {
		return departureControlPointTow;
	}

	public void setDepartureControlPointOne(long departureControlPointOne) {
		this.departureControlPointOne = departureControlPointOne;
	}

	public void setDepartureControlPointTow(long departureControlPointTow) {
		this.departureControlPointTow = departureControlPointTow;
	}

	public SortMode getSortMode() {
		return sortMode;
	}

	public void setSortMode(SortMode sortMode) {
		this.sortMode = sortMode;
	}

	public boolean isInverse() {
		return inverse;
	}

	public void setInverse(boolean inverse) {
		this.inverse = inverse;
	}
}
