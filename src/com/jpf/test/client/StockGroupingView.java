package com.jpf.test.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.event.CollapseItemEvent;
import com.sencha.gxt.widget.core.client.event.CollapseItemEvent.CollapseItemHandler;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent.ExpandItemHandler;
import com.sencha.gxt.widget.core.client.grid.GroupingView;

public class StockGroupingView<M> extends GroupingView<M>{

	private HandlerManager stockHandlerManager = new HandlerManager(this);

	public NodeList<Element> getStockGroups(){
		return getGroups();
	}

	public HandlerRegistration addCollapseGroupHandler(CollapseItemHandler<Element> handler) {
		return stockHandlerManager.addHandler(CollapseItemEvent.getType(), handler);
	}

	public HandlerRegistration addExpandGroupHandler(ExpandItemHandler<Element> handler) {
		return stockHandlerManager.addHandler(ExpandItemEvent.getType(), handler);
	}

	public void toggleGroup(Element group, boolean expanded){
		super.toggleGroup(group, expanded);
		if (expanded) {
			stockHandlerManager.fireEvent(new ExpandItemEvent<Element>(group));
		} else {
			stockHandlerManager.fireEvent(new CollapseItemEvent<Element>(group));
		}
	}
}
