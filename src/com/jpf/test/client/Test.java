package com.jpf.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.Encoding;
import com.sencha.gxt.widget.core.client.form.FormPanel.Method;
import com.sencha.gxt.widget.core.client.form.TextField;

public class Test implements EntryPoint, IsWidget {

	private FramedPanel panel;
	private FormPanel form;
	Window window;

	@Override
	public Widget asWidget() {
		if (panel == null) {
			TextField firstName = new TextField();
			firstName.setAllowBlank(false);

			final FileUploadField file = new FileUploadField();
			file.addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {
					//Info.display("File Changed", "You selected " + file.getValue());
				}
			});
			file.setName("uploadedfile");
			file.setAllowBlank(false);

			StockProperties properties = GWT.create(StockProperties.class);

			ListStore<Stock> store = new ListStore<Stock>(properties.key());
			//store.addAll(TestData.getStocks());

			ComboBox<Stock> combo = new ComboBox<Stock>(store, properties.nameLabel());
			combo.setTriggerAction(TriggerAction.ALL);

			TextButton resetButton = new TextButton("Reset");
			resetButton.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					form.reset();
					file.reset();
				}
			});

			VerticalLayoutContainer vlc = new VerticalLayoutContainer();
			vlc.add(new FieldLabel(firstName, "Name"), new VerticalLayoutData(-18, -1));
			vlc.add(new FieldLabel(file, "File"), new VerticalLayoutData(-18, -1));
			vlc.add(new FieldLabel(combo, "Company"), new VerticalLayoutData(-18, -1));

			form = new FormPanel();
			form.setAction("myurl");
			form.setEncoding(Encoding.MULTIPART);
			form.setMethod(Method.POST);
			form.add(vlc, new MarginData(10));

			TextButton submitButton = new TextButton("Submit");
			submitButton.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					if (!form.isValid()) {
						return;
					}
					// normally would submit the form but for example no server set up to
					// handle the post
					// panel.submit();
					MessageBox box = new MessageBox("File Upload Example", "Your file was uploaded.");
					box.setIcon(MessageBox.ICONS.info());
					box.show();
				}
			});
			panel = new FramedPanel();
			panel.setHeadingText("File Upload Example");
			panel.setButtonAlign(BoxLayoutPack.CENTER);
			panel.setWidth(350);
			panel.setLayoutData(new MarginData(10));
			panel.add(form);
			panel.addButton(resetButton);
			panel.addButton(submitButton);
		}

		return panel;
	}

	@Override
	public void onModuleLoad() {
		RootPanel.get().add(asWidget());
	}}