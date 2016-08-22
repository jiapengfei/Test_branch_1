package com.jpf.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class Test implements EntryPoint, IsWidget {

	@Override
	public Widget asWidget() {
		final Window w = new Window();
	    w.setHeadingText("Product Information");
	    w.setModal(true);
	    w.setPixelSize(600, 400);
	    w.setMaximizable(true);
	    w.setToolTip("The GXT product page...");
	    w.setWidget(new Frame("http://www.sencha.com/products/gxt"));
	    TextButton b = new TextButton("Close");
	    TextButton a = new TextButton("Close");
	    w.addButton(b);
	    w.addButton(a);
	    //w.show();
	    
	    TextButton btn = new TextButton("Hello World");
	      btn.addSelectHandler(new SelectHandler() {
	 
	        @Override
	        public void onSelect(SelectEvent event) {
	          w.show();
	        }
	      });
	    
	    VerticalPanel vp = new VerticalPanel();
	    vp.setSpacing(10);
	    vp.add(btn);
	    return vp;
	}

	@Override
	public void onModuleLoad() {
		RootPanel.get().add(asWidget());
	}}