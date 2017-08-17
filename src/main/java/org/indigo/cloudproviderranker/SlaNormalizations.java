package org.indigo.cloudproviderranker;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Doc TODO.
 */
public class SlaNormalizations {

  /**
   * Doc TODO.
   */
  protected float computingTime;
  /**
   * Doc TODO.
   */
  protected float numCpus;
  /**
   * Doc TODO.
   */
  protected float memSize;
  /**
   * Doc TODO.
   */
  protected float diskSize;
  /**
   * Doc TODO.
   */
  protected float publicIp;
  /**
   * Doc TODO.
   */
  protected float uploadBandwidth;
  /**
   * Doc TODO.
   */
  protected float downloadBandwidth;
  /**
   * Doc TODO.
   */
  protected float uploadAggregated;
  /**
   * Doc TODO.
   */
  protected float downloadAggregated;
  /**
   * Doc TODO.
   */
  protected float infinityValue;

  /**
   * Doc TODO.
   */
  public final float get_computing_time() {
    return computingTime;
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
  public SlaNormalizations(final float computingtime,
                           final float numcpus,
                           final float memsize,
                           final float disksize,
                           final float publicip,
                           final float uploadbandwidth,
                           final float downloadbandwidth,
                           final float uploadaggregated,
                           final float downloadaggregated,
                           final float infinityvalue) {
    this.computingTime      = computingtime;
    this.numCpus            = numcpus;
    this.memSize            = memsize;
    this.diskSize           = disksize;
    this.publicIp           = publicip;
    this.uploadBandwidth    = uploadbandwidth;
    this.downloadBandwidth  = downloadbandwidth;
    this.uploadAggregated   = uploadaggregated;
    this.downloadAggregated = downloadaggregated;
    this.infinityValue      = infinityvalue;
  }

  public float getByName(String name) {
    if (null == name) {
      return 0.0f;
    }

    if (0  ==  name.compareTo("public_ip")) {
      return publicIp;
    } else if (0  ==  name.compareTo("computing_time")) {
      return computingTime;
    } else if (0  ==  name.compareTo("num_cpus")) {
      return numCpus;
    } else if (0  ==  name.compareTo("mem_size")) {
      return memSize;
    } else if (0  ==  name.compareTo("disk_size")) {
      return diskSize;
    } else if (0  ==  name.compareTo("upload_bandwidth")) {
      return uploadBandwidth;
    } else if (0  ==  name.compareTo("download_bandwidth")) {
      return downloadBandwidth;
    } else if (0  ==  name.compareTo("upload_aggregated")) {
      return  uploadAggregated;
    } else if (0  ==  name.compareTo("download_aggregated")) {
      return downloadAggregated;
    } else {
      return 0.0f;
    }
  }

  /**
   * Doc TODO.
   */
  public final void fromDefaultFile() {
    fromFile(priority_file);
  }

  /**
   * Doc TODO.
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
   * Doc TODO.
   */
  private final void fromFile(final String filename) {
    if (filename  ==  null) {
      computingTime      = 0.0166f;
      numCpus            = 1.0f;
      memSize            = 1.0f;
      diskSize           = 1.0f;
      publicIp           = 1.0f;
      uploadBandwidth    = 1.0f;
      downloadBandwidth  = 1.0f;
      uploadAggregated   = 1.0f;
      downloadAggregated = 1.0f;
      infinityValue      = 1000.0f;
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
      computingTime      = 0.0166f;
      numCpus            = 1.0f;
      memSize            = 1.0f;
      diskSize           = 1.0f;
      publicIp           = 1.0f;
      uploadBandwidth    = 1.0f;
      downloadBandwidth  = 1.0f;
      uploadAggregated   = 1.0f;
      downloadAggregated = 1.0f;
      infinityValue      = 1000.0f;
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
  private final void fromJsonObject(final SlaNormalizations asla) {
    this.computingTime      = asla.computingTime;
    this.numCpus            = asla.numCpus;
    this.memSize            = asla.memSize;
    this.diskSize           = asla.diskSize;
    this.publicIp           = asla.publicIp;
    this.uploadBandwidth    = asla.uploadBandwidth;
    this.downloadBandwidth  = asla.downloadBandwidth;
    this.uploadAggregated   = asla.uploadAggregated;
    this.downloadAggregated = asla.downloadAggregated;
    this.infinityValue      = asla.infinityValue;
  }


  /**
   * Doc TODO.
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
