package org.indigo.cloudproviderranker;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class PaaSMetricNormalization {

  public static String normalization_file = null;

  protected int   OCCI_Create_VM_availability 	 = 1;
  protected float OCCI_CreateVM_Response_Time 	 = (float) 0.001;
  protected int   OCCI_CreateVM_Result 		 = 1;
  protected int   OCCI_Delete_VM_Availability 	 = 1;
  protected float OCCI_DeleteVM_Response_Time 	 = (float) 0.001;
  protected int   OCCI_DeleteVM_Result 		 = 1;
  protected int   General_OCCI_API_Availability	 = 1;
  protected float General_OCCI_API_Response_Time = (float) 0.001;
  protected int   General_OCCI_API_Result 	 = 1;
  protected int   OCCI_Inspect_VM_availability 	 = 1;
  protected float OCCI_InspectVM_Response_Time 	 = (float) 0.001;
  protected int   OCCI_InspectVM_Result 	 = 1;

  public int getOCCI_Create_VM_availability() {
    return OCCI_Create_VM_availability;
  }
  public float getOCCI_CreateVM_Response_Time() {
    return OCCI_CreateVM_Response_Time;
  }
  public int getOCCI_CreateVM_Result() {
    return OCCI_CreateVM_Result;
  }
  public int getOCCI_Delete_VM_Availability() {
    return OCCI_Delete_VM_Availability;
  }
  public float getOCCI_DeleteVM_Response_Time() {
    return OCCI_DeleteVM_Response_Time;
  }
  public int getOCCI_DeleteVM_Result() {
    return OCCI_DeleteVM_Result;
  }
  public int getGeneral_OCCI_API_Availability() {
    return General_OCCI_API_Availability;
  }
  public float getGeneral_OCCI_API_Response_Time() {
    return General_OCCI_API_Response_Time;
  }
  public int getGeneral_OCCI_API_Result() {
    return General_OCCI_API_Result;
  }
  public int getOCCI_Inspect_VM_availability() {
    return OCCI_Inspect_VM_availability;
  }
  public float getOCCI_InspectVM_Response_Time() {
    return OCCI_InspectVM_Response_Time;
  }
  public int getOCCI_InspectVM_Result() {
    return OCCI_InspectVM_Result;
  }
  public void setOCCI_Create_VM_availability(final int OCCI_Create_VM_availability) {
    this.OCCI_Create_VM_availability = OCCI_Create_VM_availability;
  }
  public void setOCCI_CreateVM_Response_Time(final float OCCI_CreateVM_Response_Time) {
    this.OCCI_CreateVM_Response_Time = OCCI_CreateVM_Response_Time;
  }
  public void setOCCI_CreateVM_Result(final int OCCI_CreateVM_Result) {
    this.OCCI_CreateVM_Result = OCCI_CreateVM_Result;
  }
  public void setOCCI_Delete_VM_Availability(final int OCCI_Delete_VM_Availability) {
    this.OCCI_Delete_VM_Availability = OCCI_Delete_VM_Availability;
  }
  public void setOCCI_DeleteVM_Response_Time(final float OCCI_DeleteVM_Response_Time) {
    this.OCCI_DeleteVM_Response_Time = OCCI_DeleteVM_Response_Time;
  }
  public void setOCCI_DeleteVM_Result(final int OCCI_DeleteVM_Result) {
    this.OCCI_DeleteVM_Result = OCCI_DeleteVM_Result;
  }
  public void setGeneral_OCCI_API_Availability(final int General_OCCI_API_Availability) {
    this.General_OCCI_API_Availability = General_OCCI_API_Availability;
  }
  public void setGeneral_OCCI_API_Response_Time(final float General_OCCI_API_Response_Time) {
    this.General_OCCI_API_Response_Time = General_OCCI_API_Response_Time;
  }
  public void setGeneral_OCCI_API_Result(final int General_OCCI_API_Result) {
    this.General_OCCI_API_Result = General_OCCI_API_Result;
  }
  public void setOCCI_Inspect_VM_availability(final int OCCI_Inspect_VM_availability) {
    this.OCCI_Inspect_VM_availability = OCCI_Inspect_VM_availability;
  }
  public void setOCCI_InspectVM_Response_Time(final float OCCI_InspectVM_Response_Time) {
    this.OCCI_InspectVM_Response_Time = OCCI_InspectVM_Response_Time;
  }
  public void setOCCI_InspectVM_Result(final int OCCI_InspectVM_Result) {
    this.OCCI_InspectVM_Result = OCCI_InspectVM_Result;
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
  private void update(final int   OCCI_Create_VM_availability,
		      final float OCCI_CreateVM_Response_Time,
		      final int   OCCI_CreateVM_Result,
		      final int   OCCI_Delete_VM_Availability,
		      final float OCCI_DeleteVM_Response_Time,
		      final int   OCCI_DeleteVM_Result,
		      final int   General_OCCI_API_Availability,
		      final float General_OCCI_API_Response_Time,
		      final int   General_OCCI_API_Result,
		      final int   OCCI_Inspect_VM_availability,
		      final float OCCI_InspectVM_Response_Time,
		      final int   OCCI_InspectVM_Result) {
    this.OCCI_Create_VM_availability    = OCCI_Create_VM_availability;
    this.OCCI_CreateVM_Response_Time    = OCCI_CreateVM_Response_Time;
    this.OCCI_CreateVM_Result 		= OCCI_CreateVM_Result;
    this.OCCI_Delete_VM_Availability    = OCCI_Delete_VM_Availability;
    this.OCCI_DeleteVM_Response_Time    = OCCI_DeleteVM_Response_Time;
    this.OCCI_DeleteVM_Result 		= OCCI_DeleteVM_Result;
    this.General_OCCI_API_Availability  = General_OCCI_API_Availability;
    this.General_OCCI_API_Response_Time = General_OCCI_API_Response_Time;
    this.General_OCCI_API_Result 	= General_OCCI_API_Result;
    this.OCCI_Inspect_VM_availability   = OCCI_Inspect_VM_availability;
    this.OCCI_InspectVM_Response_Time   = OCCI_InspectVM_Response_Time;
    this.OCCI_InspectVM_Result 		= OCCI_InspectVM_Result;
  }

  //----------------------------------------------------------------------------------------
  private void updateFromObject(final PaaSMetricNormalization obj) {
    this.OCCI_Create_VM_availability    = obj.OCCI_Create_VM_availability;
    this.OCCI_CreateVM_Response_Time    = obj.OCCI_CreateVM_Response_Time;
    this.OCCI_CreateVM_Result 		= obj.OCCI_CreateVM_Result;
    this.OCCI_Delete_VM_Availability    = obj.OCCI_Delete_VM_Availability;
    this.OCCI_DeleteVM_Response_Time    = obj.OCCI_DeleteVM_Response_Time;
    this.OCCI_DeleteVM_Result 		= obj.OCCI_DeleteVM_Result;
    this.General_OCCI_API_Availability  = obj.General_OCCI_API_Availability;
    this.General_OCCI_API_Response_Time = obj.General_OCCI_API_Response_Time;
    this.General_OCCI_API_Result 	= obj.General_OCCI_API_Result;
    this.OCCI_Inspect_VM_availability   = obj.OCCI_Inspect_VM_availability;
    this.OCCI_InspectVM_Response_Time   = obj.OCCI_InspectVM_Response_Time;
    this.OCCI_InspectVM_Result 		= obj.OCCI_InspectVM_Result;
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
