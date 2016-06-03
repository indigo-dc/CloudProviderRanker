package org.indigo.cloudproviderruleengine;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import com.google.gson.*;

public class SlaNormalizations {

  public float computing_time;
  public float num_cpus;
  public float mem_size;
  public float disk_size;
  public float public_ip;
  public float upload_bandwidth;
  public float download_bandwidth;
  public float upload_aggregated;
  public float download_aggregated;
  public float infinity_value;
  
  public static String priority_file = null;
  
  /**
   *
   *
   * 
   *
   */
  public SlaNormalizations( float computing_time,
  			    float num_cpus,
			    float mem_size,
			    float disk_size,
			    float public_ip,
			    float upload_bandwidth,
			    float download_bandwidth,
			    float upload_aggregated,
			    float download_aggregated,
			    float infinity_value )
  {
	this.computing_time	 = computing_time;
	this.num_cpus		 = num_cpus;
	this.mem_size		 = mem_size;
	this.disk_size		 = disk_size;
	this.public_ip		 = public_ip;
	this.upload_bandwidth    = upload_bandwidth;
	this.download_bandwidth  = download_bandwidth;
	this.upload_aggregated   = upload_aggregated;
	this.download_aggregated = download_aggregated; 
	this.infinity_value      = infinity_value; 
  }
  
  /**
   *
   *
   * 
   *
   */
  public static SlaNormalizations fromFile( ) {
    if(priority_file==null) return null;
    InputStream is = null;
    String Line = "";
    try {
      is = new FileInputStream(new File(priority_file));
    
    
      InputStreamReader inputReader = new InputStreamReader(is);
      BufferedReader buffReader     = new BufferedReader(inputReader);
      //String Line = "";
      String line = "";
      while( (line = buffReader.readLine()) != null) {
        Line += line;
      }
    } catch(Exception e) {
	return new SlaNormalizations(1f,2f,3f,4f,5f,6f,7f,8f,9f,1000f);
    } catch(Throwable t) {
        return new SlaNormalizations(1f,2f,3f,4f,5f,6f,7f,8f,9f,1000f);  
    }
    
    Gson gson = new Gson();
    
    JsonElement E = gson.fromJson(Line, JsonElement.class);
    
    return (SlaNormalizations)gson.fromJson(E.getAsJsonObject( ), SlaNormalizations.class);
  
  }
  
}
