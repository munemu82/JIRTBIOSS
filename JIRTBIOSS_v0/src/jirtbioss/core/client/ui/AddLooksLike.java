package jirtbioss.core.client.ui;

import java.util.List;

import jirtbioss.core.client.model.Species;
import jirtbioss.core.client.service.AdminServiceClientImpl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AddLooksLike extends Composite {
	
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel buttonsPanel = new HorizontalPanel();
	//user details components
	private Label saveResultLbl = new Label();
	//Text boxes & buttons
	private TextBox lookslikeNameTxtBx = new TextBox(); 
	private Button saveBtn = new Button("Save");
	private Button cancelBtn = new Button("Cancel");
	AdminServiceClientImpl looklikeFormService;
	public AddLooksLike(AdminServiceClientImpl thelooklikeFormService){
		initWidget(this.mainPanel);
				HorizontalPanel userButtonsPane = new HorizontalPanel();
				VerticalPanel labelsPanel = new VerticalPanel();
				VerticalPanel textBoxesPanel = new VerticalPanel();
				HorizontalPanel formPanel = new HorizontalPanel(); 
				this.looklikeFormService = thelooklikeFormService;
				//styling
				textBoxesPanel.setStyleName("formTextBoxesPanel");labelsPanel.setSpacing(6);userButtonsPane.setSpacing(10);
				saveBtn.setStyleName("success"); cancelBtn.setStyleName("close");
				//populate the textBox
			
				//Styling textBoxes
				lookslikeNameTxtBx.setStyleName("input");
				Label lookslikeNameLbl = new Label("Looks Like Species: ");
				
				labelsPanel.add(lookslikeNameLbl); textBoxesPanel.add(lookslikeNameTxtBx);
				formPanel.add(labelsPanel);formPanel.add(textBoxesPanel);
				//Clear the form
				cancelBtn.addClickHandler(new ClickHandler(){

					@Override
					public void onClick(ClickEvent event) {
						mainPanel.clear();						
					}
					
				});
				//perform RPC call
				saveBtn.addClickHandler(new ClickHandler(){

					@Override
					public void onClick(ClickEvent event) {
						if(lookslikeNameTxtBx.getText().equals("")){
							saveResultLbl.setStyleName("error");saveResultLbl.setText("Field cannot be blank..");
						}else{
							looklikeFormService.saveLooksLike(lookslikeNameTxtBx.getText());
							lookslikeNameTxtBx.setText("");
						}
					}
					
				});
				mainPanel.add(saveResultLbl);
				buttonsPanel.add(saveBtn);
				buttonsPanel.add(cancelBtn);
				mainPanel.add(formPanel);
				mainPanel.add(buttonsPanel);
				
		}
		public void displaySaveLookslike(String saveStatus){
			saveResultLbl.setStyleName("success");saveResultLbl.setText(saveStatus);
			
		}

}

