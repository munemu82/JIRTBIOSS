package jirtbioss.core.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AboutProjectPage extends Composite {

	//define fields
		private VerticalPanel abProjPanel = new VerticalPanel();
		
		//Constructor
		public AboutProjectPage(){
			initWidget(this.abProjPanel);
			HTML aboutProjectText = new HTML(""
					+ "<p><h1> About JIRTBIOS Project </h1>"
					+"<hr />"
					+"<font size='4em'JIRTBIOS designed to have a very easy to use interface for users to interact with camera trap photos and to rapidly browse provide identification information for the hundreds of thousands images that a typical deployment collects."
					+"Such technology is not widely available to the public or scientific community. We would like this to be custom designed to our particular needs for biosecurity monitoring and for the software to be rapidly re-configurable to allow for different records or for automated identification algorithms to be inserted."
					+"</p>"
					+"<p>This software will play a critical role in the biosecurity surveillance program, allowing a database of identifications to be collected to train machine learning algorithms in automated identification, to manage identifications of species from images across a team of observers and also to manage records of who has viewed and downloaded which images." 
					+"<p></font>");
			
			this.abProjPanel.add(aboutProjectText);
		}

}
