import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Main {

	public static void main(String[] args)//throws IOException 
	{
		// TODO Auto-generated method stub
		
		JSONObject object = new JSONObject();
		
        String s="";
		File f=new File("input.txt");
		
		JSONArray values=new JSONArray();
    	
	
		int count=0;
		float max=0;
		float total=0;
		float avg=0;
		
		try
		{
		BufferedReader br=new BufferedReader(new FileReader(f));
		
			
		
		while((s=br.readLine())!=null)
		{
		  if(s.contains("TOTAL:"))
		  {
			  count++;
			  s=s.replaceAll("[^0-9]","");
			  float value=Float.parseFloat(s);
			  float mb=value/10000;
			  JSONObject ob = new JSONObject();
			  String cnt=count+"s";
			  ob.put(cnt,mb);
			  if(max<mb)
			  {
				  max=mb;
			  }
			  total=total+mb;
			  
			
			  values.add(ob);
			  
		  }
		}  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		avg=total/count;
		object.put("AverageMemory(MB)",avg);
		object.put("values",values);
		object.put("MaxMemory(MB)",max);
		object.put("Usecasename","HomePage");
		
		
		
		try
		{
		
		FileWriter fw=new FileWriter("output.json");
		fw.write(object.toString());
		fw.close();
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		
		
		//reading file
		
		JSONParser jsonParser = new JSONParser();
		
		
		try
		{
			FileReader reader = new FileReader("output.json");
            Object obj = jsonParser.parse(reader);
            JSONObject jo=(JSONObject)obj;
            

            
            System.out.println(jo.toString());
            
            Double Average=(Double)jo.get("AverageMemory(MB)");
        } 
		catch(Exception e)
		{
			System.out.println(e);
		}
	
		
	}

}
