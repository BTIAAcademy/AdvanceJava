
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	
	public static void main (String args[]){
		
		String pattern = "[a-zA-Z0-9]+@[a-zA-Z]+.[a-z]{3,5}";
		Pattern p = Pattern.compile(pattern);
			
		Matcher match = p.matcher("babum.m@testleaf.com");
		System.out.println(match.matches());
		
	}
	}


