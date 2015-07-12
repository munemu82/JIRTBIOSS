package jirtbioss.core.client.ui;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import jirtbioss.core.client.authentication.InputValidator;
import jirtbioss.core.client.service.AdminServiceClientImpl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class StudyDetails extends Composite{
	//main panel
		private VerticalPanel mainStudyPanel = new VerticalPanel();
		private HorizontalPanel userDetailsPanel = new HorizontalPanel();
	//user details components
		private HTML addStudyTitle = new HTML("<div class='titles'> Add a study </div> <hr />");
		private Label saveResultLbl = new Label();
		private Label studyIdLbl = new Label("Study ID: ");
		private Label studyTitleLbl = new Label("Study Title: ");
		private Label studyDescLbl = new Label("Study Details: ");
		private Label studyStartDateLbl = new Label("Start Date: ");
		private Label studyEndDateLbl = new Label("End Date: ");
		private Label studySpeciesLbl = new Label("Study Species: ");
		//Text boxes
		 private TextBox studyIdTxtBx = new TextBox(); 
		 private TextBox studyTitleTxtBx = new TextBox(); 
		 private TextArea studyDescTxtBx = new TextArea(); 
		 private DateBox  studyStartDateTxtBx = new DateBox (); 
		 private DateBox  studyEndDateTxtBx = new DateBox(); 
		 private TextBox studySpeciesTxtBx = new TextBox(); 
		 private DatePicker datePicker = new DatePicker();
		//buttons
		Button saveBtn = new Button("Save");
		Button cancelBtn = new Button("Cancel");
		//Panels
		HorizontalPanel studyButtonsPane = new HorizontalPanel();
		VerticalPanel labelsPanel = new VerticalPanel();
		VerticalPanel textBoxesPanel = new VerticalPanel();
		HorizontalPanel formPanel = new HorizontalPanel(); 
		//RPC object
		AdminServiceClientImpl studyServiceImpl;
		public StudyDetails(AdminServiceClientImpl theStudyServiceImpl){
			initWidget(this.mainStudyPanel);
			this.studyServiceImpl =  theStudyServiceImpl;
			
			//Styling and initializing panels
			 DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
			 studyStartDateTxtBx.setFormat(new DateBox.DefaultFormat(dateFormat));
			 studyEndDateTxtBx.setFormat(new DateBox.DefaultFormat(dateFormat));
			textBoxesPanel.setStyleName("formTextBoxesPanel");//textBoxesPanel.setSpacing(10);
			labelsPanel.setSpacing(6);studyButtonsPane.setSpacing(10);
			studyIdTxtBx.setStyleName("input");studyTitleTxtBx.setStyleName("input");studyDescTxtBx.setStyleName("textAreaInput");studyStartDateTxtBx.setStyleName("input");studyEndDateTxtBx.setStyleName("input");
			studySpeciesTxtBx.setStyleName("input");
			cancelBtn.setStyleName("close"); saveBtn.setStyleName("success");
			
			//Add widgets to the panels
			mainStudyPanel.add(addStudyTitle);
			mainStudyPanel.add(saveResultLbl);
			labelsPanel.add(studyIdLbl);
			textBoxesPanel.add(studyIdTxtBx);
			labelsPanel.add(studyTitleLbl);
			textBoxesPanel.add(studyTitleTxtBx);
			labelsPanel.add(studyStartDateLbl);
			textBoxesPanel.add(studyStartDateTxtBx);
			labelsPanel.add(studyEndDateLbl);
			textBoxesPanel.add(studyEndDateTxtBx);
			labelsPanel.add(studySpeciesLbl);
			textBoxesPanel.add(studySpeciesTxtBx);
			labelsPanel.add(studyDescLbl);
			textBoxesPanel.add(studyDescTxtBx);
			//add form wdiget to the formPanel
			formPanel.add(labelsPanel); formPanel.add(textBoxesPanel);
			//buttons for this user
			studyButtonsPane.add(saveBtn);
			studyButtonsPane.add(cancelBtn);
			
			
			//Handle click event of save button
			saveBtn.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					//set result label style to error style
					saveResultLbl.setStyleName("error");
					//setup email validation object
					InputValidator textValidator = new InputValidator();
					if(textValidator.isEmpty(studyIdTxtBx.getText()) || textValidator.isEmpty(studyTitleTxtBx.getText()) || textValidator.isEmpty(studySpeciesTxtBx.getText()))
						{
							saveResultLbl.setText("Study ID, Title and Species must be populated !!!");
						
						}//All good then invoke RPC call
					else{
						//Format formatter = new SimpleDateFormat("yyyy-MM-dd");
						DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");
						String datestart = ""; String dateEnd = "";
						if(studyStartDateTxtBx.getValue()==null) datestart = "9999-00-00"; else datestart = fmt.format(studyStartDateTxtBx.getValue());//formatter.format(studyStartDateTxtBx.getValue());
						if(studyEndDateTxtBx.getValue()==null)  dateEnd = "9999-00-00";  else dateEnd = fmt.format(studyEndDateTxtBx.getValue());//formatter.format(studyEndDateTxtBx.getValue());
						if(textValidator.isEmpty(studyDescTxtBx.getText())==true) studyDescTxtBx.setText("TBS");
							//make an RPC call to save the study
						  studyServiceImpl.saveStudy(studyIdTxtBx.getText(), studyTitleTxtBx.getText(), studyDescTxtBx.getText(), datestart, dateEnd, studySpeciesTxtBx.getText(), "Insert");
						}
					}
				
			});
			cancelBtn.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					mainStudyPanel.clear();				
				}
				
			});
			//add widgets to the main panel
			mainStudyPanel.add(formPanel);
			mainStudyPanel.add(studyButtonsPane);
			
		}
		public void saveStudy(String saveStatus){
			System.out.println(saveStatus);
			if(saveStatus.equals("Failed !!")){
				saveResultLbl.setStyleName("error");
				saveResultLbl.setText(saveStatus);
			}else{
			saveResultLbl.setStyleName("success");
			saveResultLbl.setText(saveStatus);}
			//clear fields
			studyIdTxtBx.setText("");studyTitleTxtBx.setText("");studyDescTxtBx.setText("");studyStartDateTxtBx.setValue(null);;
			studyEndDateTxtBx.setValue(null);studySpeciesTxtBx.setText("");
			
		}

}
