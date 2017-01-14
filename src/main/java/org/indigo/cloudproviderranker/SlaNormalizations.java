package org.indigo.cloudproviderranker;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.PrintWriter;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.text.SimpleDateFormat;

public class SlaNormalizations {

  protected float computing_time;
  protected float num_cpus;
  protected float mem_size;
  protected float disk_size;
  protected float public_ip;
  protected float upload_bandwidth;
  protected float download_bandwidth;
  protected float upload_aggregated;
  protected float download_aggregated;
  protected float infinity_value;

  public float get_computing_time() {
    return computing_time;
  }
  
  public static String priority_file = null;
  
  public SlaNormalizations() {}

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
			     float infinity_value)
  {
    this.computing_time	     = computing_time;
    this.num_cpus	     = num_cpus;
    this.mem_size	     = mem_size;
    this.disk_size	     = disk_size;
    this.public_ip	     = public_ip;
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
  public void fromDefaultFile() {
    fromFile(priority_file);
  }

  /**
   *
   *
   * 
   *
   */
  public void fromCustomFile() {
    String customPriorityFile = "/usr/share/java/cpr";
    if(priority_file!=null) {
      customPriorityFile = (new File(priority_file)).getParent();
      if(customPriorityFile==null || customPriorityFile.compareToIgnoreCase("")==0) {
        customPriorityFile = ".";
      }
    }
    customPriorityFile = customPriorityFile + "/sla_priorities-custom.json";
    fromFile(customPriorityFile);
  }


  /**
   *
   *
   * 
   *
   */
  private void fromFile(String filename) {
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
      is = new FileInputStream(new File(filename));
      InputStreamReader inputReader = new InputStreamReader(is);
      BufferedReader buffReader     = new BufferedReader(inputReader);
      String line = "";
      while((line = buffReader.readLine()) != null) {
        Line += line;
      }
      //Logger.getLogger("").log(Level.INFO, "loaded from [" + filename + "] params " + Line); 
    } catch(Exception e) {
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
    this.fromJsonObject((SlaNormalizations)gson.fromJson(E.getAsJsonObject(), SlaNormalizations.class) );  
  }

  private void fromJsonObject(SlaNormalizations aSla) {
    this.computing_time	     = aSla.computing_time;
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
  public void toCustomFile() {
    String customPriorityFile = "/usr/share/java/cpr";
    if(priority_file!=null) {
      customPriorityFile = (new File(priority_file)).getParent();
      if(customPriorityFile==null || customPriorityFile.compareToIgnoreCase("")==0) {
        customPriorityFile = ".";
      }
    }
    customPriorityFile = customPriorityFile + "/sla_priorities-custom.json";
    Gson gson = new Gson();
    String params = gson.toJson(this);
    try {
      PrintWriter out = new PrintWriter(customPriorityFile);
      out.println(params);
      out.close();
    } catch(java.io.FileNotFoundException e) {
      String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
      Logger.getLogger("").log(Level.SEVERE, timeStamp + " - File [" + customPriorityFile + "] doesn't exist. Cannot write custom priority parameters.."); 
    }
  }

  //----------------------------------------------------------------------------------------
  public String getParams() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
