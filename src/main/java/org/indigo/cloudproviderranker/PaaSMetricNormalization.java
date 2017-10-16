package org.indigo.cloudproviderranker;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

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
public class PaaSMetricNormalization {
  /**
   * Doc TODO.
   */
  public static String normalization_file = null;
  /**
   * Doc TODO.
   */
  protected int   occiCreatevmAvailability = 1;
  /**
   * Doc TODO.
   */
  protected float occiCreatevmResponseTime = (float) 0.001;
  /**
   * Doc TODO.
   */
  protected int   occiCreatevmResult = 1;
  /**
   * Doc TODO.
   */
  protected int   occiDeletevmAvailability = 1;
  /**
   * Doc TODO.
   */
  protected float occiDeletevmResponseTime = (float) 0.001;
  /**
   * Doc TODO.
   */
  protected int   occiDeletevmResult = 1;
  /**
   * Doc TODO.
   */
  protected int   generalOcciApiAvailability = 1;
  /**
   * Doc TODO.
   */
  protected float generalOcciApiResponseTime = (float) 0.001;
  /**
   * Doc TODO.
   */
  protected int   generalOcciApiResult = 1;
  /**
   * Doc TODO.
   */
  protected int   occiInspectVmAvailability = 1;
  /**
   * Doc TODO.
   */
  protected float occiInspectVmResponseTime = (float) 0.001;
  /**
   * Doc TODO.
   */
  protected int   occiInspectVmResult = 1;
  /**
   * Doc TODO.
   */
  public final int getocci_create_vm_availability() {
    return occiCreatevmAvailability;
  }
  /**
   * Doc TODO.
   */
  public final float getocciCreatevmResponseTime() {
    return occiCreatevmResponseTime;
  }
  /**
   * Doc TODO.
   */
  public final int getocciCreatevmResult() {
    return occiCreatevmResult;
  }
  /**
   * Doc TODO.
   */
  public final int getocciDeletevmAvailability() {
    return occiDeletevmAvailability;
  }
  /**
   * Doc TODO.
   */
  public final float getocciDeletevmResponseTime() {
    return occiDeletevmResponseTime;
  }
  /**
   * Doc TODO.
   */
  public final int getocciDeletevmResult() {
    return occiDeletevmResult;
  }
  /**
   * Doc TODO.
   */
  public final int getgeneralOcciApiAvailability() {
    return generalOcciApiAvailability;
  }
  /**
   * Doc TODO.
   */
  public final float getgeneralOcciApiResponseTime() {
    return generalOcciApiResponseTime;
  }
  /**
   * Doc TODO.
   */
  public final int getgeneralOcciApiResult() {
    return generalOcciApiResult;
  }
  /**
   * Doc TODO.
   */
  public final int getocciInspectVmAvailability() {
    return occiInspectVmAvailability;
  }
  /**
   * Doc TODO.
   */
  public final float getocciInspectVmResponseTime() {
    return occiInspectVmResponseTime;
  }
  /**
   * Doc TODO.
   */
  public final int getocciInspectVmResult() {
    return occiInspectVmResult;
  }
  /**
   * Doc TODO.
   */
  public final void setocciCreatevmAvailability(final int occicreatevmavailability) {
    this.occiCreatevmAvailability = occicreatevmavailability;
  }
  /**
   * Doc TODO.
   */
  public final void setocciCreatevmResponseTime(final float occiCreatevmResponseTime) {
    this.occiCreatevmResponseTime = occiCreatevmResponseTime;
  }
  /**
   * Doc TODO.
   */
  public final void setocciCreatevmResult(final int occiCreatevmResult) {
    this.occiCreatevmResult = occiCreatevmResult;
  }
  /**
   * Doc TODO.
   */
  public final void setocciDeletevmAvailability(final int occiDeletevmAvailability) {
    this.occiDeletevmAvailability = occiDeletevmAvailability;
  }
  /**
   * Doc TODO.
   */
  public final void setocciDeletevmResponseTime(final float occiDeletevmResponseTime) {
    this.occiDeletevmResponseTime = occiDeletevmResponseTime;
  }
  /**
   * Doc TODO.
   */
  public final void setocciDeletevmResult(final int occiDeletevmResult) {
    this.occiDeletevmResult = occiDeletevmResult;
  }
  /**
   * Doc TODO.
   */
  public final void setgeneralOcciApiAvailability(final int generalOcciApiAvailability) {
    this.generalOcciApiAvailability = generalOcciApiAvailability;
  }
  /**
   * Doc TODO.
   */
  public final void setgeneralOcciApiResponseTime(final float generalOcciApiResponseTime) {
    this.generalOcciApiResponseTime = generalOcciApiResponseTime;
  }
  /**
   * Doc TODO.
   */
  public final void setgeneralOcciApiResult(final int generalOcciApiResult) {
    this.generalOcciApiResult = generalOcciApiResult;
  }
  /**
   * Doc TODO.
   */
  public final void setocciInspectVmAvailability(final int occiInspectVmAvailability) {
    this.occiInspectVmAvailability = occiInspectVmAvailability;
  }
  /**
   * Doc TODO.
   */
  public final void setocciInspectVmResponseTime(final float occiInspectVmResponseTime) {
    this.occiInspectVmResponseTime = occiInspectVmResponseTime;
  }
  /**
   * Doc TODO.
   */
  public final void setocciInspectVmResult(final int occiInspectVmResult) {
    this.occiInspectVmResult = occiInspectVmResult;
  }
  /**
   * Doc TODO.
   */
  private PaaSMetricNormalization() { }

  /**
   * Doc TODO.
   */
  public PaaSMetricNormalization(final boolean update) {
    if (update) {
      try {
        updateFromDefaultFile();
      } catch (Exception e) {
        Logger.getLogger("").log(Level.SEVERE,  " - updateFromDefaultFile failed: " + e);
      }
      try {
        updateFromCustomFile();
      } catch (Exception e) {
        Logger.getLogger("").log(Level.SEVERE,  " - updateFromCustomFile failed: " + e);
      }
    }
  }


  /**
   * Doc TODO.
   */
  @Override
  public final String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  /**
   * Doc TODO.
   */
  private final void updateFromDefaultFile() {
    updateFromFile(normalization_file);
  }

  /**
   * Doc TODO.
   */
  private final void updateFromCustomFile() {
    String customNormalizationFile = "/usr/share/java/cpr";
    if (normalization_file != null) {
      customNormalizationFile = (new File(normalization_file)).getParent();
      if (customNormalizationFile  ==  null || customNormalizationFile.equalsIgnoreCase("")) {
        customNormalizationFile = ".";
      }
    }
    customNormalizationFile = customNormalizationFile
        + "/paasmetric_normalization-custom.json";
    updateFromFile(customNormalizationFile);
  }

  /**
   * Doc TODO.
   */
  private void updateFromFile(final String filename) {
    String line = "";

    InputStream is = null;
    try {
      is = new FileInputStream(new File(filename));
      InputStreamReader inputReader = new InputStreamReader(is);
      BufferedReader buffReader     = new BufferedReader(inputReader);
      String sline = "";
      while ((sline = buffReader.readLine()) != null) {
        line += sline;
      }
    } catch (Exception e) {
      Logger.getLogger("").log(Level.SEVERE,  "updateFromFile Exception: " + e);
      this.update(1, 0.001f, 1, 1, 0.001f, 1, 1, 0.001f, 1, 1, 0.001f, 1);
    } catch (Throwable t) {
      Logger.getLogger("").log(Level.SEVERE,  "updateFromFile Throwable: " + t);
      this.update(1, 0.001f, 1, 1, 0.001f, 1, 1, 0.001f, 1, 1, 0.001f, 1);
    }
    Gson gson = new Gson();
    JsonElement element = gson.fromJson(line,  JsonElement.class);
    PaaSMetricNormalization paaSMetricNormalization =
        (PaaSMetricNormalization) gson.fromJson(element.getAsJsonObject(),
                                               PaaSMetricNormalization.class);
    updateFromObject(paaSMetricNormalization);
  }

  /**
   * Doc TODO.
   */
  public void update(final int   occicreatevmavailability,
                      final float occiCreatevmResponseTime,
                      final int   occiCreatevmResult,
                      final int   occiDeletevmAvailability,
                      final float occiDeletevmResponseTime,
                      final int   occiDeletevmResult,
                      final int   generalOcciApiAvailability,
                      final float generalOcciApiResponseTime,
                      final int   generalOcciApiResult,
                      final int   occiInspectVmAvailability,
                      final float occiInspectVmResponseTime,
                      final int   occiInspectVmResult) {
    this.occiCreatevmAvailability   = occicreatevmavailability;
    this.occiCreatevmResponseTime   = occiCreatevmResponseTime;
    this.occiCreatevmResult         = occiCreatevmResult;
    this.occiDeletevmAvailability   = occiDeletevmAvailability;
    this.occiDeletevmResponseTime   = occiDeletevmResponseTime;
    this.occiDeletevmResult         = occiDeletevmResult;
    this.generalOcciApiAvailability = generalOcciApiAvailability;
    this.generalOcciApiResponseTime = generalOcciApiResponseTime;
    this.generalOcciApiResult       = generalOcciApiResult;
    this.occiInspectVmAvailability  = occiInspectVmAvailability;
    this.occiInspectVmResponseTime  = occiInspectVmResponseTime;
    this.occiInspectVmResult        = occiInspectVmResult;
  }

  /**
   * Doc TODO.
   */
  private void updateFromObject(final PaaSMetricNormalization obj) {
    this.occiCreatevmAvailability    = obj.occiCreatevmAvailability;
    this.occiCreatevmResponseTime    = obj.occiCreatevmResponseTime;
    this.occiCreatevmResult          = obj.occiCreatevmResult;
    this.occiDeletevmAvailability    = obj.occiDeletevmAvailability;
    this.occiDeletevmResponseTime    = obj.occiDeletevmResponseTime;
    this.occiDeletevmResult          = obj.occiDeletevmResult;
    this.generalOcciApiAvailability  = obj.generalOcciApiAvailability;
    this.generalOcciApiResponseTime  = obj.generalOcciApiResponseTime;
    this.generalOcciApiResult        = obj.generalOcciApiResult;
    this.occiInspectVmAvailability   = obj.occiInspectVmAvailability;
    this.occiInspectVmResponseTime   = obj.occiInspectVmResponseTime;
    this.occiInspectVmResult         = obj.occiInspectVmResult;
  }

  
  /**
   * Doc TODO.
   */
  public final void toCustomFile() {
    String customNormalizationFile = "/usr/share/java/cpr";
    if (normalization_file != null) {
      customNormalizationFile = (new File(normalization_file)).getParent();
      if (customNormalizationFile  ==  null 
          || customNormalizationFile.compareToIgnoreCase("") == 0) {
        customNormalizationFile = ".";
      }
    }
    customNormalizationFile = customNormalizationFile + "/paasmetric_normalization-custom.json";
    Gson gson = new Gson();
    String params = gson.toJson(this);
    try {
      PrintWriter out = new PrintWriter(customNormalizationFile);
      out.println(params);
      out.close();
    } catch (java.io.FileNotFoundException e) {
      String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
      Logger.getLogger("").log(Level.SEVERE,  timeStamp
                               + " - File [" + customNormalizationFile
                               + "] doesn't exist. Cannot write custom normalization parameters..");
    }
  }

  
  /**
   * Doc TODO.
   */
  public final void printParams() {
    Gson gson = new Gson();
    String params = gson.toJson(this);
    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
    Logger.getLogger("").log(Level.INFO,  timeStamp + " - Default Params=[" + params + "]");
  }

  
  /**
   * Doc TODO.
   */
  public final String getParams() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
