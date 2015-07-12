package jirtbioss.core.client.utility;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

//this is a utility class to assist with handling of filter buttons
public class FilterButtonsHandler {
	//Buttons for pose filter
	private Button thePoseButton = new Button();
	private Button straightPoseBtn = new Button();
	private Button leftPoseBtn = new Button();
	private Button rightPoseBtn = new Button();
	private Button backTailBtn = new Button();
	
	//Buttons for Color filter
	private Button theColorButton = new Button();
	private Button redBtn = new Button();
	private Button whiteBtn = new Button();
	private Button yellowBtn = new Button();
	private Button greyBtn = new Button();
	private Button blackBtn = new Button();
	private Button brownBtn = new Button();
	//buttons for scale filter
	private Button tallBtn = new Button();
	private Button smallBtn = new Button();
	private Button lankyBtn = new Button();
	private Button stockyBtn = new Button();
	private Button theScaleBtn = new Button();
	
	public FilterButtonsHandler(){  //add more paremeters to rest to their styles.
			System.out.println(theColorButton.getText());
		}
	//Setup event handler for Pose filter buttons
	public void setPoseButton(Button strPoseBtn, Button lftPoseBtn, Button rgtPoseBtn, Button backTPoseBtn, Button changedBtn){
		//this.theClickedButten = requiredBtn;
		//this.theChangedButten = changedBtn;
		this.straightPoseBtn = strPoseBtn;
		this.leftPoseBtn = lftPoseBtn;
		this.rightPoseBtn = rgtPoseBtn;
		this.backTailBtn = backTPoseBtn;
		this.thePoseButton = changedBtn;
		
		//Straight pose button handler
		straightPoseBtn.addClickHandler(new ClickHandler(){
			@Override
				public void onClick(ClickEvent event) {
					straightPoseBtn.setStyleName("filterIconsStrPoseClicked");
					thePoseButton.setStyleName("Filteredgwt-Button");
					thePoseButton.setText(straightPoseBtn.getTitle());
						//reset the rest of the buttons  to
						leftPoseBtn.setStyleName("filterIconsLeftPose");
						rightPoseBtn.setStyleName("filterIconsRightPose");
						backTailBtn.setStyleName("filterIconsBackTailPose");
					}
				});
		
		//Left pose button handler
		leftPoseBtn.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				leftPoseBtn.setStyleName("filterIconsLeftPoseClicked");
				thePoseButton.setText(leftPoseBtn.getTitle());
				thePoseButton.setStyleName("Filteredgwt-Button");
				//Reset the rest
				straightPoseBtn.setStyleName("filterIconsStraightPose");
				rightPoseBtn.setStyleName("filterIconsRightPose");
				backTailBtn.setStyleName("filterIconsBackTailPose");
				}
			});
		
		//Right pose button handler
		rightPoseBtn.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				rightPoseBtn.setStyleName("filterIconsRightPoseClicked");
				thePoseButton.setText(rightPoseBtn.getTitle());
				thePoseButton.setStyleName("Filteredgwt-Button");
				//Reset the rest
				straightPoseBtn.setStyleName("filterIconsStraightPose");
				leftPoseBtn.setStyleName("filterIconsLeftPose");
				backTailBtn.setStyleName("filterIconsBackTailPose");
				}
			});
		
		//Backtail pose button handler
		backTailBtn.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				backTailBtn.setStyleName("filterIconsBackTailPoseClicked");
				thePoseButton.setText(backTailBtn.getTitle());
				thePoseButton.setStyleName("Filteredgwt-Button");
				//Reset the rest
				straightPoseBtn.setStyleName("filterIconsStraightPose");
				leftPoseBtn.setStyleName("filterIconsLeftPose");
				rightPoseBtn.setStyleName("filterIconsRightPose");
			}
		});
		}
	
	//Setup event handler for Color filter buttons
		public void setColorButton(Button theRedBtn, Button theWhiteBtn, Button theYellowBtn, Button theGreyBtn, Button theBlackBtn, Button theBrownBtn, Button colorBtn){
			this.redBtn = theRedBtn;
			this.whiteBtn = theWhiteBtn;
			this.yellowBtn = theYellowBtn;
			this.greyBtn = theGreyBtn;
			this.blackBtn = theBlackBtn;
			this.brownBtn = theBrownBtn;
			this.theColorButton = colorBtn;
			
			//Straight pose button handler
			redBtn.addClickHandler(new ClickHandler(){
				@Override
					public void onClick(ClickEvent event) {
					redBtn.setStyleName("filterIconsRedClicked");
					theColorButton.setStyleName("Filteredgwt-Button");
					theColorButton.setText(redBtn.getTitle());
							//reset the rest of the buttons  to
							yellowBtn.setStyleName("filterIconsYellow");
							whiteBtn.setStyleName("filterIconsWhite");
							greyBtn.setStyleName("filterIconsGray");
							blackBtn.setStyleName("filterIconsBlack");
							brownBtn.setStyleName("filterIconsBrown");
							
						}
					});
			
			//Left pose button handler
			whiteBtn.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					whiteBtn.setStyleName("filterIconsWhiteClicked");
					theColorButton.setStyleName("Filteredgwt-Button");
					theColorButton.setText(whiteBtn.getTitle());
							//reset the rest of the buttons  to
							yellowBtn.setStyleName("filterIconsYellow");
							redBtn.setStyleName("filterIconsRed");
							greyBtn.setStyleName("filterIconsGray");
							blackBtn.setStyleName("filterIconsBlack");
							brownBtn.setStyleName("filterIconsBrown");
					
					}
				});
			
			//yellow color button handler
			yellowBtn.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					rightPoseBtn.setStyleName("filterIconsYellowClicked");
					theColorButton.setStyleName("Filteredgwt-Button");
					theColorButton.setText(yellowBtn.getTitle());
							//reset the rest of the buttons  to
							whiteBtn.setStyleName("filterIconsWhite");
							redBtn.setStyleName("filterIconsRed");
							greyBtn.setStyleName("filterIconsGray");
							blackBtn.setStyleName("filterIconsBlack");
							brownBtn.setStyleName("filterIconsBrown");
					}
				});
			
			//Grey color button handler
			greyBtn.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					greyBtn.setStyleName("filterIconsGrayClicked");
					theColorButton.setStyleName("Filteredgwt-Button");
					theColorButton.setText(greyBtn.getTitle());
							//reset the rest of the buttons  to
							whiteBtn.setStyleName("filterIconsWhite");
							redBtn.setStyleName("filterIconsRed");
							yellowBtn.setStyleName("filterIconsYellow");
							blackBtn.setStyleName("filterIconsBlack");
							brownBtn.setStyleName("filterIconsBrown");
				}
			});
			//Black color button handler
			blackBtn.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					blackBtn.setStyleName("filterIconsBlackClicked");
					theColorButton.setStyleName("Filteredgwt-Button");
					theColorButton.setText(blackBtn.getTitle());
							//reset the rest of the buttons  to
							whiteBtn.setStyleName("filterIconsWhite");
							redBtn.setStyleName("filterIconsRed");
							yellowBtn.setStyleName("filterIconsYellow");
							greyBtn.setStyleName("filterIconsGray");
							brownBtn.setStyleName("filterIconsBrown");
				}
			});
			//Black color button handler
			brownBtn.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					brownBtn.setStyleName("filterIconsBrownClicked");
					theColorButton.setStyleName("Filteredgwt-Button");
					theColorButton.setText(brownBtn.getTitle());
							//reset the rest of the buttons  to
							whiteBtn.setStyleName("filterIconsWhite");
							redBtn.setStyleName("filterIconsRed");
							yellowBtn.setStyleName("filterIconsYellow");
							greyBtn.setStyleName("filterIconsGray");
							blackBtn.setStyleName("filterIconsBlack");
				}
			});
	}
		//Setup event handler for Scale filter buttons
				public void setScaleButton(Button tallbtn, Button smallbtn, Button lankybtn, Button stockybtn, Button scalebtn){
					this.tallBtn = tallbtn;
					this.smallBtn = smallbtn;
					this.lankyBtn = lankybtn;
					this.stockyBtn = stockybtn;
					this.theScaleBtn = scalebtn;
					
					//Tall Scale button handler
					tallBtn.addClickHandler(new ClickHandler(){
						@Override
							public void onClick(ClickEvent event) {
							tallBtn.setStyleName("filterIconsTallClicked");
							theScaleBtn.setStyleName("Filteredgwt-Button");
							theScaleBtn.setText(tallBtn.getTitle());
									//reset the rest of the buttons  to
									smallBtn.setStyleName("filterIconsSmall");
									lankyBtn.setStyleName("filterIconsLanky");
									stockyBtn.setStyleName("filterIconsStocky");
									}
							});
					
					//Smalle Scale button handler
					smallBtn.addClickHandler(new ClickHandler(){
						@Override
						public void onClick(ClickEvent event) {
							smallBtn.setStyleName("filterIconsSmallClicked");
							theScaleBtn.setStyleName("Filteredgwt-Button");
							theScaleBtn.setText(smallBtn.getTitle());
									//reset the rest of the buttons  to
									tallBtn.setStyleName("filterIconsTall");
									lankyBtn.setStyleName("filterIconsLanky");
									stockyBtn.setStyleName("filterIconsStocky");
									}
						});
					//Lanky Scale button handler
					lankyBtn.addClickHandler(new ClickHandler(){
						@Override
						public void onClick(ClickEvent event) {
							lankyBtn.setStyleName("filterIconsLankyClicked");
							theScaleBtn.setStyleName("Filteredgwt-Button");
							theScaleBtn.setText(lankyBtn.getTitle());
									//reset the rest of the buttons  to
									tallBtn.setStyleName("filterIconsTall");
									smallBtn.setStyleName("filterIconsSmall");
									stockyBtn.setStyleName("filterIconsStocky");
									}
						});
					//Stocky Scale button handler
					stockyBtn.addClickHandler(new ClickHandler(){
						@Override
						public void onClick(ClickEvent event) {
							stockyBtn.setStyleName("filterIconsStockyClicked");
							theScaleBtn.setStyleName("Filteredgwt-Button");
							theScaleBtn.setText(stockyBtn.getTitle());
									//reset the rest of the buttons  to
									tallBtn.setStyleName("filterIconsTall");
									smallBtn.setStyleName("filterIconsSmall");
									lankyBtn.setStyleName("filterIconsLanky");
									}
						});
					
					
				}

}
