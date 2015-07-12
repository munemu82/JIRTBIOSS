package jirtbioss.core.client.authentication;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class InputValidator{
  
 public boolean isValidEmailAddress(String emailAddress){
/*   String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
   CharSequence inputStr = emailAddress; 
   Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
   Matcher matcher = pattern.matcher(inputStr);
   return matcher.matches();*/
	 Boolean b = emailAddress.matches(
	  "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	 if(b==true){
		 return true;
	 }
	 else return false;
 }
 
 public boolean isEmpty(String textTest){
	 if(textTest.equals("")){
		 return true;
	 }else{
		 return false;
	 }
 }
}
