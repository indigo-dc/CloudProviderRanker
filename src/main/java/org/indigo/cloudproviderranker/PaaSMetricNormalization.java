package org.indigo.cloudproviderranker;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class PaaSMetricNormalization {

  public static String normalization_file = null;

  protected int   occi_create_vm_availability 	 = 1;
  protected float occi_createvm_Response_Time 	 = (float) 0.001;
  protected int   occi_createvm_Result 		 = 1;
  protected int   occi_delete_vm_Availability 	 = 1;
  protected float occi_deletevm_Response_Time 	 = (float) 0.001;
  protected int   occi_deletevm_Result 		 = 1;
  protected int   general_occi_api_Availability	 = 1;
  protected float general_occi_api_Response_Time = (float) 0.001;
  protected int   general_occi_api_Result 	 = 1;
  protected int   occi_inspect_vm_availability 	 = 1;
  protected float occi_inspectvm_Response_Time 	 = (float) 0.001;
  protected int   occi_inspectvm_Result 	 = 1;

  public int getocci_create_vm_availability() {
    return occi_create_vm_availability;
  }
  public float getocci_createvm_Response_Time() {
    return occi_createvm_Response_Time;
  }
  public int getocci_createvm_Result() {
    return occi_createvm_Result;
  }
  public int getocci_delete_vm_Availability() {
    return occi_delete_vm_Availability;
  }
  public float getocci_deletevm_Response_Time() {
    return occi_deletevm_Response_Time;
  }
  public int getocci_deletevm_Result() {
    return occi_deletevm_Result;
  }
  public int getgeneral_occi_api_Availability() {
    return general_occi_api_Availability;
  }
  public float getgeneral_occi_api_Response_Time() {
    return general_occi_api_Response_Time;
  }
  public int getgeneral_occi_api_Result() {
    return general_occi_api_Result;
  }
  public int getocci_inspect_vm_availability() {
    return occi_inspect_vm_availability;
  }
  public float getocci_inspectvm_Response_Time() {
    return occi_inspectvm_Response_Time;
  }
  public int getocci_inspectvm_Result() {
    return occi_inspectvm_Result;
  }
  public void setocci_create_vm_availability(final int occi_create_vm_availability) {
    this.occi_create_vm_availability = occi_create_vm_availability;
  }
  public void setocci_createvm_Response_Time(final float occi_createvm_Response_Time) {
    this.occi_createvm_Response_Time = occi_createvm_Response_Time;
  }
  public void setocci_createvm_Result(final int occi_createvm_Result) {
    this.occi_createvm_Result = occi_createvm_Result;
  }
  public void setocci_delete_vm_Availability(final int occi_delete_vm_Availability) {
    this.occi_delete_vm_Availability = occi_delete_vm_Availability;
  }
  public void setocci_deletevm_Response_Time(final float occi_deletevm_Response_Time) {
    this.occi_deletevm_Response_Time = occi_deletevm_Response_Time;
  }
  public void setocci_deletevm_Result(final int occi_deletevm_Result) {
    this.occi_deletevm_Result = occi_deletevm_Result;
  }
  public void setgeneral_occi_api_Availability(final int general_occi_api_Availability) {
    this.general_occi_api_Availability = general_occi_api_Availability;
  }
  public void setgeneral_occi_api_Response_Time(final float general_occi_api_Response_Time) {
    this.general_occi_api_Response_Time = general_occi_api_Response_Time;
  }
  public void setgeneral_occi_api_Result(final int general_occi_api_Result) {
    this.general_occi_api_Result = general_occi_api_Result;
  }
  public void setocci_inspect_vm_availability(final int occi_inspect_vm_availability) {
    this.occi_inspect_vm_availability = occi_inspect_vm_availability;
  }
  public void setocci_inspectvm_Response_Time(final float occi_inspectvm_Response_Time) {
    this.occi_inspectvm_Response_Time = occi_inspectvm_Response_Time;
  }
  public void setocci_inspectvm_Result(final int occi_inspectvm_Result) {
    this.occi_inspectvm_Result = occi_inspectvm_Result;
  }

  private PaaSMetricNormalization() { }

  //----------------------------------------------------------------------------------------
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

  //----------------------------------------------------------------------------------------
  @Override
  public final String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  //----------------------------------------------------------------------------------------
  private void updateFromDefaultFile() {
    updateFromFile(normalization_file);
  }

  //----------------------------------------------------------------------------------------
  private void updateFromCustomFile() {
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

  //----------------------------------------------------------------------------------------
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
        (PaaSMetricNormalization)gson.fromJson(element.getAsJsonObject(),
					       PaaSMetricNormalization.class);
    updateFromObject(paaSMetricNormalization);
  }

  //----------------------------------------------------------------------------------------
  private void update(final int   occi_create_vm_availability,
		      final float occi_createvm_Response_Time,
		      final int   occi_createvm_Result,
		      final int   occi_delete_vm_Availability,
		      final float occi_deletevm_Response_Time,
		      final int   occi_deletevm_Result,
		      final int   general_occi_api_Availability,
		      final float general_occi_api_Response_Time,
		      final int   general_occi_api_Result,
		      final int   occi_inspect_vm_availability,
		      final float occi_inspectvm_Response_Time,
		      final int   occi_inspectvm_Result) {
    this.occi_create_vm_availability    = occi_create_vm_availability;
    this.occi_createvm_Response_Time    = occi_createvm_Response_Time;
    this.occi_createvm_Result 		= occi_createvm_Result;
    this.occi_delete_vm_Availability    = occi_delete_vm_Availability;
    this.occi_deletevm_Response_Time    = occi_deletevm_Response_Time;
    this.occi_deletevm_Result 		= occi_deletevm_Result;
    this.general_occi_api_Availability  = general_occi_api_Availability;
    this.general_occi_api_Response_Time = general_occi_api_Response_Time;
    this.general_occi_api_Result 	= general_occi_api_Result;
    this.occi_inspect_vm_availability   = occi_inspect_vm_availability;
    this.occi_inspectvm_Response_Time   = occi_inspectvm_Response_Time;
    this.occi_inspectvm_Result 		= occi_inspectvm_Result;
  }

  //----------------------------------------------------------------------------------------
  private void updateFromObject(final PaaSMetricNormalization obj) {
    this.occi_create_vm_availability    = obj.occi_create_vm_availability;
    this.occi_createvm_Response_Time    = obj.occi_createvm_Response_Time;
    this.occi_createvm_Result 		= obj.occi_createvm_Result;
    this.occi_delete_vm_Availability    = obj.occi_delete_vm_Availability;
    this.occi_deletevm_Response_Time    = obj.occi_deletevm_Response_Time;
    this.occi_deletevm_Result 		= obj.occi_deletevm_Result;
    this.general_occi_api_Availability  = obj.general_occi_api_Availability;
    this.general_occi_api_Response_Time = obj.general_occi_api_Response_Time;
    this.general_occi_api_Result 	= obj.general_occi_api_Result;
    this.occi_inspect_vm_availability   = obj.occi_inspect_vm_availability;
    this.occi_inspectvm_Response_Time   = obj.occi_inspectvm_Response_Time;
    this.occi_inspectvm_Result 		= obj.occi_inspectvm_Result;
  }

  //----------------------------------------------------------------------------------------
  public final void toCustomFile() {
    String customNormalizationFile = "/usr/share/java/cpr";
    if (normalization_file != null) {
      customNormalizationFile = (new File(normalization_file)).getParent();
      if (customNormalizationFile  ==  null || customNormalizationFile.compareToIgnoreCase("")  ==  0) {
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

  //----------------------------------------------------------------------------------------
  public final void printParams() {
    Gson gson = new Gson();
    String params = gson.toJson(this);
    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
    Logger.getLogger("").log(Level.INFO,  timeStamp + " - Default Params=[" + params + "]");
  }

  //----------------------------------------------------------------------------------------
  public final String getParams() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
