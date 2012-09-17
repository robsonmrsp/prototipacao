package br.com.m2msolutions.controleviagens.client;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.fx.Draggable;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class WidgetsContainer extends LayoutContainer {
	private LayoutContainer iconContainer;
	private Image image;
	private WidgetDepartureControl controlPartica;

	public WidgetsContainer() {
		setLayout(new RowLayout(Orientation.HORIZONTAL));
		add(getIconContainer());
		setSize(200, 65);
		setId("WidgetsContainer");
		initControls();
	}

	private LayoutContainer getIconContainer() {
		if (iconContainer == null) {
			iconContainer = new LayoutContainer();
			iconContainer.setSize("65", "65");
			setId("iconContainer");
			iconContainer.add(getImage());
		}
		return iconContainer;
	}

	private Image getImage() {
		if (image == null) {
			image = new Image("http://cdn1.iconfinder.com/data/icons/Mobile-Icons/64/08_settings.png");
			image.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (controlPartica.isVisible()) {
						controlPartica.hide();
					} else {
						controlPartica.show();
					}
				}
			});
		}
		return image;
	}

	private void initControls() {
		controlPartica = new WidgetDepartureControl();

		Draggable d = new Draggable(controlPartica, controlPartica);
		d.setUseProxy(true);
		RootPanel.get("controlsContainer").add(controlPartica);
		controlPartica.hide();
	}
}