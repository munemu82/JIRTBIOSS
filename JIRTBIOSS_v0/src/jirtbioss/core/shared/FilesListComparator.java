package jirtbioss.core.shared;

import java.util.ArrayList;

public class FilesListComparator {
	private ArrayList<String> l1 = new ArrayList<String>();
	private ArrayList<String> l2 = new ArrayList<String>();
	private ArrayList<String> noDuplicateList = new ArrayList<String>();
	
	
	//Construct object 
	public FilesListComparator(ArrayList<String> l1, ArrayList<String> l2) {
		super();
		this.l1 = l1;
		this.l2 = l2;
	}
	
	//GETTERS AND SETTERS
	public ArrayList<String> getL1() {
		return l1;
	}
	public void setL1(ArrayList<String> l1) {
		this.l1 = l1;
	}
	public ArrayList<String> getL2() {
		return l2;
	}
	public void setL2(ArrayList<String> l2) {
		this.l2 = l2;
	}
	public ArrayList<String> getNoDuplicateList() {
		return noDuplicateList;
	}
	public void setNoDuplicateList(ArrayList<String> noDuplicateList) {
		this.noDuplicateList = noDuplicateList;
	}
	//Compare and display the differences
	public void compareLists() {
		for(int i=0;i<l1.size();i++){
			if(l1.contains(l2.get(i))){
				System.out.println("Exist : "+l2.get(i));
			}else{
				System.out.println("Not Exist : "+l2.get(i));
			}
		}
				
	}
	//compare and return only the files unique files exising in the first list (l1,l2) - l1
	public ArrayList<String> getNonDuplicateList() {
		for(int i=0;i<l1.size();i++){
			if(l1.contains(l2.get(i))){
				System.out.println("Exist : "+l2.get(i));
			}else{
				System.out.println("Not Exist : "+l2.get(i));
				//adding the unique files found in the list 1
				noDuplicateList.add(l2.get(i));
			}
		}
		return noDuplicateList	;			
	}
	

}
