package org.indigo.cloudproviderranker;

import java.io.BufferedReader
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Doc TODO.
 */
public class SlaNormalizations {

  /**
   * Doc TODO.
   */
  protected float computing_time;
  /**
   * Doc TODO.
   */
  protected float num_cpus;
  /**
   * Doc TODO.
   */
  protected float mem_size;
  /**
   * Doc TODO.
   */
  protected float disk_size;
  /**
   * Doc TODO.
   */
  protected float public_ip;
  /**
   * Doc TODO.
   */
  protected float upload_bandwidth;
  /**
   * Doc TODO.
   */
  protected float download_bandwidth;
  /**
   * Doc TODO.
   */
  protected float upload_aggregated;
  /**
   * Doc TODO.
   */
  protected float download_aggregated;
  /**
   * Doc TODO.
   */
  protected float infinity_value;

  /**
   * Doc TODO.
   */
  public final float get_computing_time() {
    return computing_time;
  }

  /**
   * Doc TODO.
   */
  public static String priority_file = null;

  /**
   * Doc TODO.
   */
  public SlaNormalizations() {
  }

  /**
   * Doc TODO.
   */
  public SlaNormalizations(final float computing_time,
                           final float num_cpus,
                           final float mem_size,
                           final float disk_size,
                           final float public_ip,
                           final float upload_bandwidth,
                           final float download_bandwidth,
                           final float upload_aggregated,
                           final float download_aggregated,
                           final float infinity_value) {
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
  public final void fromDefaultFile() {
    fromFile(priority_file);
  }

  /**
   *
   *
   *
   *
   */
  public final void fromCustomFile() {
    String customPriorityFile = "/usr/share/java/cpr";
    if (priority_file != null) {
      customPriorityFile = (new File(priority_file)).getParent();
      if (customPriorityFile  ==  null || customPriorityFile.compareToIgnoreCase("") == 0) {
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
  private final void fromFile(final String filename) {
    if (filename  ==  null) {
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
    String line = "";
    try {
      is = new FileInputStream(new File(filename));
      InputStreamReader inputReader = new InputStreamReader(is);
      BufferedReader buffReader     = new BufferedReader(inputReader);
      String sline = "";
      while ((sline = buffReader.readLine()) != null) {
        line += sline;
      }
    } catch (Exception e) {
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
    JsonElement element = gson.fromJson(line,  JsonElement.class);
    this.fromJsonObject((SlaNormalizations) gson.fromJson(element.getAsJsonObject(),
                                                         SlaNormalizations.class));
  }

  /**
   * Doc TODO.
   */
  private final void fromJsonObject(final SlaNormalizations aSla) {
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
  public final void toCustomFile() {
    String customPriorityFile = "/usr/share/java/cpr";
    if (priority_file != null) {
      customPriorityFile = (new File(priority_file)).getParent();
      if (customPriorityFile == null || customPriorityFile.compareToIgnoreCase("") == 0) {
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
    } catch (java.io.FileNotFoundException e) {
      String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
      Logger.getLogger("").log(Level.SEVERE, timeStamp
                                             + " - File ["
                                             + customPriorityFile
                                             + "] doesn't exist. Cannot "
                                             + "write custom priority parameters..");
    }
  }

  /**
   * Doc TODO.
   */
  public final String getParams() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
