package experiments;

import java.util.Date;

public class Demo {

	public static void main(String[] args) {
	
		Date date = new Date();
		System.out.println(date);
		//System.out.println(date.toString().replace(" ", "_"));
		//.toString -> convert to string
		//System.out.println(date.toString().replace(" ", "_").replace(":", "_"));

		//String dateText = date.toString();
		//String dateTextWithoutSpace = dateText.replace(" ", "_");
		//String dateTextWithoutSpaceAndColon = dateTextWithoutSpace.replace(":", "_");
		//System.out.println(dateTextWithoutSpaceAndColon);
		
		//**** direct by method chaining
		date.toString().replace(" ", " ").replace(":", " ");
		
		
		
	}

}
