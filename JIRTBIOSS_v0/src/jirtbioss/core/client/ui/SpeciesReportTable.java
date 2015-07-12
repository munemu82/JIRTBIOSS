package jirtbioss.core.client.ui;

import java.util.List;

import jirtbioss.core.client.model.Imageidentity;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class SpeciesReportTable extends Composite {
private VerticalPanel mainpagePanel = new VerticalPanel();
	public SpeciesReportTable(final List<Imageidentity> listOfSpecies){
		//initiate the widget
		initWidget(this.mainpagePanel);
		 final CellTable<Imageidentity> table = new CellTable<Imageidentity>();
		    //set size of the table
		    table.setSize("1200px", "400px");
		    // Display 10 rows in one page
		    table.setPageSize(10);
		 
		   if(listOfSpecies.size() > 0){
		    Column<Imageidentity, Number> idColumn = new Column<Imageidentity, Number>(new NumberCell()) {
		        @Override
		        public Number getValue(Imageidentity object) {
		            return (Number) object.getRecordId();
		        }
		    };
		    table.addColumn(idColumn, "Record ID");

		    // Add a text column to show the Image ID.
		    TextColumn<Imageidentity> imageIdColumn = new TextColumn<Imageidentity>() {
		        @Override
		        public String getValue(Imageidentity object) {
		          return object.getImageid();
		        }
		      };
		      table.addColumn(imageIdColumn, "Image ID");

		    // Add a text column to show the behavior.
		    TextColumn<Imageidentity> behaviorColumn = new TextColumn<Imageidentity>() {
		      @Override
		      public String getValue(Imageidentity object) {
		        return object.getBehavior();
		      }
		    };
		    table.addColumn(behaviorColumn, "Behavior");
		    
		    // Add a text column to show the number of species in the species.
		    Column<Imageidentity, Number> numberColumn = new Column<Imageidentity, Number>(new NumberCell()) {
		        @Override
		        public Number getValue(Imageidentity object) {
		            return (Number) object.getNumber();
		        }
		    };
		    table.addColumn(numberColumn, "No. of Species");
		    
		    // Add a text column to show the pose.
		    TextColumn<Imageidentity> poseColumn = new TextColumn<Imageidentity>() {
		      @Override
		      public String getValue(Imageidentity object) {
		        return object.getPose();
		      }
		    };
		    table.addColumn(poseColumn, "Pose");
		    
		    // Add a text column to show children present.
		    TextColumn<Imageidentity> childrenColumn = new TextColumn<Imageidentity>() {
		      @Override
		      public String getValue(Imageidentity object) {
		        return object.getChildren();
		      }
		    };
		    table.addColumn(childrenColumn, "Children Present(y/n)");
		    
		 // Add a text column to show Scale.
		    TextColumn<Imageidentity> scaleColumn = new TextColumn<Imageidentity>() {
		      @Override
		      public String getValue(Imageidentity object) {
		        return object.getScale();
		      }
		    };
		    table.addColumn(scaleColumn, "Scale");
		    // Add a text column to show Scale.
		    TextColumn<Imageidentity> colorColumn = new TextColumn<Imageidentity>() {
		      @Override
		      public String getValue(Imageidentity object) {
		        return object.getColor();
		      }
		    };
		    table.addColumn(colorColumn, "Colour");
		    
		   // Add a text column to show Species Name.
		    TextColumn<Imageidentity> speciesColumn = new TextColumn<Imageidentity>() {
		      @Override
		      public String getValue(Imageidentity object) {
		        return object.getSpecies();
		      }
		    };
		    table.addColumn(speciesColumn, "Species Name");
		    
		    // Add a text column to show Study ID.
		    TextColumn<Imageidentity> studyIdColumn = new TextColumn<Imageidentity>() {
		      @Override
		      public String getValue(Imageidentity object) {
		        return object.getStudyId();
		      }
		    };
		    table.addColumn(studyIdColumn, "Study ID");
		    // Associate an async data pr
		    AsyncDataProvider<Imageidentity> provider = new AsyncDataProvider<Imageidentity>() {
		      @Override
		      protected void onRangeChanged(HasData<Imageidentity> display) {
		        int start = display.getVisibleRange().getStart();
		        int end = start + display.getVisibleRange().getLength();
		        end = end >= listOfSpecies.size() ? listOfSpecies.size() : end;
		        List<Imageidentity> sub = listOfSpecies.subList(start, end);
		        updateRowData(start, sub);
		      }
		    };
		    provider.addDataDisplay(table);
		    provider.updateRowCount(listOfSpecies.size(), true);

		    SimplePager pager = new SimplePager();
		    pager.setDisplay(table);
		    
		    //add buttons to the report
		
		    mainpagePanel.add(table);
		    mainpagePanel.add(pager);
		   }else{		//no species identification records
				 HTML noRecords = new HTML("<h2 align='center'> No identification records found</h2> <hr />");
				 mainpagePanel.add(noRecords);
				 
			}
}

}
