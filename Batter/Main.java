import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		File f=new File("Battery.txt");
		
		JSONObject object = new JSONObject();
		
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(f));
		
			String s="";
			String activity="";
		
			boolean t1=true;
			boolean t2=true;
			while((s=br.readLine())!=null)
			{
			   if(t1||t2)
			   {
				   if(s.contains("Foreground activities:"))
				   {
					   s=s.trim();
					   //String h=s.substring()
					   String s1[]=s.split(":");
					   
					   String s2=s1[1].substring(1,33);
					   
					   System.out.println(s2);
					   object.put("Foreground_time",s2);
					   t1=false;
				   }
				   if(s.contains("Uid u0a202"))
				   {
					   s=s.trim();
					   s=s.substring(12,18);
					   System.out.println(s);
					   double d=Double.parseDouble(s);
					   double d1=d/1000;
					   //double av = Math.round(d1 * 100.0) / 100.0;
					   object.put("Battery_percentage",d1);
					   
					   object.put("Battery_drain",d);
					   t2=false;
				   }
			   }
			   else
			   {
				   break;
			   }
			}
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
		
		FileWriter fw=new FileWriter("Battery_output.json");
		
		fw.write(object.toString());
		fw.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		JSONParser jsonParser = new JSONParser();
		
		
		try
		{
			FileReader reader = new FileReader("Battery_output.json");
            Object obj = jsonParser.parse(reader);
            JSONObject jo=(JSONObject)obj;
            
            System.out.println(jo.toString());
        } 
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

}
