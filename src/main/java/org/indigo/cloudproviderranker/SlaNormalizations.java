 package org.indigo.cloudproviderranker;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import com.google.gson.*;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.PrintWriter;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.text.SimpleDateFormat;

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
  
  public SlaNormalizations( ) {}

  /**
   *
   *
   * 
   *
   */
  private SlaNormalizations( float computing_time,
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
  public void fromDefaultFile( ) {
    fromFile(priority_file);
  }

  /**
   *
   *
   * 
   *
   */
  public void fromCustomFile( ) {
    String customPriorityFile = "/usr/share/java/cpr";
    if(priority_file!=null) {
      //Logger.getLogger("").log(Level.INFO, "normalization_file=[" + normalization_file + "]"); 
      customPriorityFile = (new File(priority_file)).getParent();
      //Logger.getLogger("").log(Level.INFO, "normalization_file parent=[" + customNormalizationFile + "]"); 
      if(customPriorityFile==null || customPriorityFile.compareToIgnoreCase("")==0) {
        customPriorityFile = ".";
      }

      //Logger.getLogger("").log(Level.INFO, "normalization_file parent=[" + customNormalizationFile + "]"); 
    }
    customPriorityFile = customPriorityFile + "/sla_priorities-custom.json";
    fromFile( customPriorityFile );
  }


  /**
   *
   *
   * 
   *
   */
  private void fromFile( String filename ) {
    if(filename==null) {
      computing_time 	    = 0.0166f;
      num_cpus	    	    = 1.0f;
      mem_size	    	    = 1.0f;
      disk_size	    	    = 1.0f;
      public_ip	    	    = 1.0f;
      upload_bandwidth      = 1.0f;
      download_bandwidth    = 1.0f;
      upload_aggregated     = 1.0f;
      download_aggregated   = 1.0f;
      infinity_value	    = 1000.0f;
      return;
    }
    InputStream is = null;
    String Line = "";
    try {
      is = new FileInputStream(new File(priority_file));
      InputStreamReader inputReader = new InputStreamReader(is);
      BufferedReader buffReader     = new BufferedReader(inputReader);
      String line = "";
      while( (line = buffReader.readLine()) != null) {
        Line += line;
      }
    } catch(Exception e) {
	//return new SlaNormalizations(1f,2f,3f,4f,5f,6f,7f,8f,9f,1000f);
	computing_time 	    = 0.0166f;
  	num_cpus	    = 1.0f;
  	mem_size	    = 1.0f;
  	disk_size	    = 1.0f;
  	public_ip	    = 1.0f;
  	upload_bandwidth    = 1.0f;
  	download_bandwidth  = 1.0f;
  	upload_aggregated   = 1.0f;
  	download_aggregated = 1.0f;
  	infinity_value	    = 1000.0f;
        return;
    }
    Gson gson = new Gson();
    JsonElement E = gson.fromJson(Line, JsonElement.class);
    this.fromJsonObject( (SlaNormalizations)gson.fromJson(E.getAsJsonObject( ), SlaNormalizations.class) );  
  }

  private void fromJsonObject( SlaNormalizations aSla ) {
    this.computing_time	 = aSla.computing_time;
    this.num_cpus	     = aSla.num_cpus;
    this.mem_size	     = aSla.mem_size;
    this.disk_size	     = aSla.disk_size;
    this.public_ip	     = aSla.public_ip;
    this.upload_bandwidth    = aSla.upload_bandwidth;
    this.download_bandwidth  = aSla.download_bandwidth;
    this.upload_aggregated   = aSla.upload_aggregated;
    this.download_aggregated = aSla.download_aggregated; 
    this.infinity_value      = aSla.infinity_value; 
  }

  /**
   *
   *
   * 
   *
   */
  public void toCustomFile( ) {
    String customPriorityFile = "/usr/share/java/cpr";
    if(priority_file!=null) {
      //Logger.getLogger("").log(Level.INFO, "normalization_file=[" + normalization_file + "]"); 
      customPriorityFile = (new File(priority_file)).getParent();
      //Logger.getLogger("").log(Level.INFO, "normalization_file parent=[" + customNormalizationFile + "]"); 
      if(customPriorityFile==null || customPriorityFile.compareToIgnoreCase("")==0) {
        customPriorityFile = ".";
      }

      //Logger.getLogger("").log(Level.INFO, "normalization_file parent=[" + customNormalizationFile + "]"); 
    }
    customPriorityFile = customPriorityFile + "/sla_priorities-custom.json";
    Gson gson = new Gson();
    String params = gson.toJson( this );
    try {
      PrintWriter out = new PrintWriter(customPriorityFile);
      out.println(params);
      out.close( );
    } catch(java.io.FileNotFoundException e) {
      String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( new java.util.Date() );
      Logger.getLogger("").log(Level.SEVERE, timeStamp + " - File ["+customPriorityFile+"] doesn't exist. Cannot write custom priority parameters.."); 
    }
  }

  //----------------------------------------------------------------------------------------
  public String getParams( ) {
    Gson gson = new Gson();
    return gson.toJson( this );
  }
}
