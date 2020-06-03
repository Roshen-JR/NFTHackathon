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
        
		JSONObject object = new JSONObject();
		JSONArray values=new JSONArray();
		
		File f=new File("CPU.txt");
		
		float max=0;
		float total=0;
		float avg=0;
		
		int count=1;
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(f));
		
			String s="";
		
			String s1="";
			while((s=br.readLine())!=null)
			{
				
				StringTokenizer token=new StringTokenizer(s," ");
				while(token.hasMoreTokens())
				{
					String nxt=token.nextToken();
					if(nxt.equals("R")||nxt.equals("S")||nxt.equals("C"))
					{
						s1=token.nextToken();
						break;
					}
				}
				//String s1=s.substring(42,46);
				
				s1=s1.trim();
				
				float val=Float.parseFloat(s1);
				
				if(max<val)
				{
					max=val;
				}
				
				total=total+val;
			
				//System.out.println(count+"s:"+s1);
				String cnt=count+"s";
				
				JSONObject ob = new JSONObject();
				ob.put(cnt,val);
				values.add(ob);
				
				count++;
			}
		}
		
		catch(Exception e)
		{
			
		}
		//System.out.println("count:"+count);
		
		avg=total/(count-1);
		
		double avg1 = Math.round(avg * 100.0) / 100.0;
		
		//System.out.println("max :"+max);
		//System.out.println("avg:"+avg1);
		
		
		object.put("values",values);
		object.put("maxcpu",max);
		object.put("Avgcpu",avg1);
		
		JSONObject report=new JSONObject();
		
		report.put("sampletransaction", object);
		
		try
		{
		
		FileWriter fw=new FileWriter("output.json");
		
		fw.write(report.toString());
		fw.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		//reading file
		
		
		System.out.print(values.toJSONString());
		
				JSONParser jsonParser = new JSONParser();
				
				
				try
				{
					FileReader reader = new FileReader("output.json");
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
