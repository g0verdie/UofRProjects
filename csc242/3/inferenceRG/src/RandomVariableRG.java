import java.util.*;
/*
 * the basic class for random variables, contains basic information about the variables
 * 
 */
public class RandomVariableRG {

	private String name;
	private ArrayList<String> domain;
	//private ArrayList<Integer> domainVals;
	private String value = null;
	
	public RandomVariableRG(String name) 
	{this.name = name;}
	public RandomVariableRG(String name, ArrayList<String> domain, String value)
	{
		this.name = name;
		this.domain = domain;
		
		if(domain.contains(value))
			this.value = value;
		else
			System.out.println("error, attempted assignment outside of domain on "+name);
		
	}
	
	public RandomVariableRG(String name, ArrayList<String> domain)
	{
		this.name = name;
		this.domain = domain;
	}
	
	public String getName() 
	{return name;}
	
	public ArrayList<String> getDomain() 
	{return domain;}
	public void setValue(String val) 
	{this.value = val;}

	public String getValue() 
		{return this.value;}
	
	public void print() {
		System.out.println("name- "+name);
		System.out.println("domain:");
		for(String d : domain)
			System.out.println("\t "+d+", ");
		System.out.println("value:");
		if(value != null)
			System.out.println("\t" +value);
		else
			System.out.println("\t none");
			
	}
	
}
