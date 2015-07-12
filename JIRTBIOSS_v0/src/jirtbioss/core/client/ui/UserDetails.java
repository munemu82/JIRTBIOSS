package jirtbioss.core.client.ui;

import jirtbioss.core.client.authentication.InputValidator;
import jirtbioss.core.client.service.AdminServiceClientImpl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UserDetails extends Composite{
 //main panel
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel userDetailsPanel = new HorizontalPanel();
	private HTML addUserTitle = new HTML("<div class='titles'> Add a user </div> <hr />");
//user details components
	private Label saveResultLbl = new Label();
	private Label usernameLbl = new Label("Username: ");
	private Label passwordLbl = new Label("Password: ");
	private Label firstnameLbl = new Label("First Name: ");
	private Label lastnameLbl = new Label("Last Name: ");
	private Label emailLbl = new Label("Email Address");
	private Label userAccessLevel = new Label("User Access level:");
	
	//Text boxes
	private TextBox usernameTxtBox = new TextBox(); 
	private PasswordTextBox passwordTxtBox = new PasswordTextBox();
	private TextBox firstnameTxtBox = new TextBox(); 
	private TextBox lastnameTxtBox = new TextBox(); 
	private TextBox emailTxtBox = new TextBox(); 
	private ListBox userAccessListBox = new ListBox();
	//buttons
	Button saveBtn = new Button("Save");
	Button cancelBtn = new Button("Cancel");
	//this.userid = userid;		
			private HorizontalPanel userNamePane = new HorizontalPanel();
			private HorizontalPanel passwordPane = new HorizontalPanel();
			private HorizontalPanel firstnamePane = new HorizontalPanel();
			private HorizontalPanel lastnamePane = new HorizontalPanel();
			private HorizontalPanel emailPane = new HorizontalPanel();
			VerticalPanel labelsPanel = new VerticalPanel();
			VerticalPanel textBoxesPanel = new VerticalPanel();
			HorizontalPanel formPanel = new HorizontalPanel(); 
			private HorizontalPanel buttonsPane = new HorizontalPanel();
	//---------Service ---------------
	private AdminServiceClientImpl userformServiceImpl;
	public UserDetails(AdminServiceClientImpl theAdminImpl){
		initWidget(this.mainPanel);
		
		this.userformServiceImpl = theAdminImpl;
		
		//Styling the panels
		textBoxesPanel.setStyleName("formTextBoxesPanel");
		labelsPanel.setSpacing(6);buttonsPane.setSpacing(10);
		usernameTxtBox.setStyleName("input");passwordTxtBox.setStyleName("input");firstnameTxtBox.setStyleName("input");
		lastnameTxtBox.setStyleName("input");emailTxtBox.setStyleName("input");userAccessListBox.setStyleName("input");
		cancelBtn.setStyleName("close"); saveBtn.setStyleName("success");
		
		//add access list 
		userAccessListBox.addItem("0");userAccessListBox.setSelectedIndex(0);
		userAccessListBox.addItem("1");
		userAccessListBox.addItem("2");
		//Add form components to panels
		mainPanel.add(addUserTitle);
		mainPanel.add(saveResultLbl);
		labelsPanel.add(usernameLbl);
		textBoxesPanel.add(usernameTxtBox);
		labelsPanel.add(passwordLbl);
		textBoxesPanel.add(passwordTxtBox);
		labelsPanel.add(firstnameLbl);
		textBoxesPanel.add(firstnameTxtBox);
		labelsPanel.add(lastnameLbl);
		textBoxesPanel.add(lastnameTxtBox);
		labelsPanel.add(emailLbl);
		textBoxesPanel.add(emailTxtBox);
		labelsPanel.add(userAccessLevel);
		textBoxesPanel.add(userAccessListBox);
		//add form widget to the form panel
		formPanel.add(labelsPanel); formPanel.add(textBoxesPanel);
		//buttons for this user
		buttonsPane.add(saveBtn);
		buttonsPane.add(cancelBtn);
		//Event Handler
		
		saveBtn.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				saveResultLbl.setStyleName("error");
				//setup email validation object
				InputValidator emailValid = new InputValidator();
				if(usernameTxtBox.getText().equals("") || passwordTxtBox.getText().equals("") || firstnameTxtBox.getText().equals("") || lastnameTxtBox.getText().equals("") || emailTxtBox.getText().equals(""))
					{
						saveResultLbl.setText("All fields must be populated !!!");
					
					}
				else if(!emailValid.isValidEmailAddress(emailTxtBox.getText())){
					saveResultLbl.setText("Email Address is invalid!!");
				}
				else{
						int userid = 0; // this is just to complete parameter list and reuse this method, userid is not required at this point
						userformServiceImpl.saveUser(usernameTxtBox.getText(), passwordTxtBox.getText(), firstnameTxtBox.getText(), lastnameTxtBox.getText(), emailTxtBox.getText(), userid, userAccessListBox.getSelectedItemText(), "Insert");
					}
				}
			
		});
		cancelBtn.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				mainPanel.clear();				
			}
			
		});
		mainPanel.add(formPanel);
		mainPanel.add(buttonsPane);
	}
	public void saveUser(String saveStatus){
		System.out.println(saveStatus);
		saveResultLbl.setStyleName("success");
		saveResultLbl.setText(saveStatus);
		//clear fields
		usernameTxtBox.setText("");passwordTxtBox.setText("");firstnameTxtBox.setText("");lastnameTxtBox.setText("");
		emailTxtBox.setText("");
		
	}

		
	
}
