package br.com.m2msolutions.controleviagens.client;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ControleViagens implements EntryPoint {

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	WidgetsContainer container = new WidgetsContainer();
	public void onModuleLoad() {

		final Button controleViagensButton01 = new Button("Controle de Viagens - 01");
		final Button controleViagensButton02 = new Button("Controle de Viagens - 02");

		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");

		final Label errorLabel = new Label();

		// We can add style names to widgets
		controleViagensButton01.addStyleName("sendButton");
		controleViagensButton02.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		//		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("controleViagensButton01").add(controleViagensButton01);
		RootPanel.get("controleViagensButton01").add(controleViagensButton02);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		RootPanel.get("controls").add(container);
		container.show();

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		dialogBox.setPixelSize(500, 300);
		final Button closeButton = new Button("Close");

		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");

		final Window window = new Window();  
		window.setSize(817, 450);  
		window.setPlain(true);  
		window.setModal(true);  
		window.setBlinkModal(true);  
		window.setHeading("Controle de Viagens :: 001-Santa Cruz X Alvorada (Parador)");  
		window.setLayout(new FitLayout());
		window.setResizable(false);

		final ControleViagensContainer cvLayoutContainer = new ControleViagensContainer();
		window.add(cvLayoutContainer);

		window.getHeader().addTool(new ToolButton("x-tool-gear", new SelectionListener<IconButtonEvent>() {  
			@Override  
			public void componentSelected(IconButtonEvent ce) {
			}
		}));

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}
			private void sendNameToServer() {
				window.show();
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		controleViagensButton01.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);


		final Window window1 = new Window();  
		window1.setSize(817, 450);  
		window1.setPlain(true);  
		window1.setModal(true);  
		window1.setBlinkModal(true);  
		window1.setHeading("Controle de Viagens :: 001-Santa Cruz X Alvorada (Parador)");  
		window1.setLayout(new FitLayout());
		window1.setResizable(false);

		final ControleViagensContainer cvLayoutContainer1 = new ControleViagensContainer(true);
		window1.add(cvLayoutContainer1);

		window1.getHeader().addTool(new ToolButton("x-tool-gear", new SelectionListener<IconButtonEvent>() {  
			@Override  
			public void componentSelected(IconButtonEvent ce) {
			}
		}));

		// Create a handler for the sendButton and nameField
		class MyHandler1 implements ClickHandler, KeyUpHandler {
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}
			private void sendNameToServer() {
				window1.show();
			}
		}

		// Add a handler to send the name to the server
		MyHandler1 handler1 = new MyHandler1();
		controleViagensButton02.addClickHandler(handler1);
		nameField.addKeyUpHandler(handler1);
	}
}
