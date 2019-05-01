/*
 * IBM Watson IoT Platform Organization Administration REST APIs
 * The Organization Adminstration APIs can be used to configure an organization (including  creating and deleting devices), checking usage, service status and diagnosing device  connection problems. For information on this API, and how to use Watson IoT Platform APIs generally see  [the API documentation](https://console.ng.bluemix.net/docs/services/IoT/reference/api.html). 
 *
 * OpenAPI spec version: 0002
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.ibm.wiotp.swagger.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.ibm.wiotp.swagger.model.DataTrafficDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DataTraffic
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-04-30T18:06:21.121+01:00")
public class DataTraffic {
  @SerializedName("start")
  private String start = null;

  @SerializedName("end")
  private String end = null;

  @SerializedName("average")
  private Integer average = null;

  @SerializedName("total")
  private Integer total = null;

  @SerializedName("days")
  private List<DataTrafficDetail> days = null;

  public DataTraffic start(String start) {
    this.start = start;
    return this;
  }

   /**
   * Get start
   * @return start
  **/
  @ApiModelProperty(required = true, value = "")
  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public DataTraffic end(String end) {
    this.end = end;
    return this;
  }

   /**
   * Get end
   * @return end
  **/
  @ApiModelProperty(required = true, value = "")
  public String getEnd() {
    return end;
  }

  public void setEnd(String end) {
    this.end = end;
  }

  public DataTraffic average(Integer average) {
    this.average = average;
    return this;
  }

   /**
   * The average amount of data used per day over the time period
   * @return average
  **/
  @ApiModelProperty(required = true, value = "The average amount of data used per day over the time period")
  public Integer getAverage() {
    return average;
  }

  public void setAverage(Integer average) {
    this.average = average;
  }

  public DataTraffic total(Integer total) {
    this.total = total;
    return this;
  }

   /**
   * The total amount of data used over the time period
   * @return total
  **/
  @ApiModelProperty(required = true, value = "The total amount of data used over the time period")
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public DataTraffic days(List<DataTrafficDetail> days) {
    this.days = days;
    return this;
  }

  public DataTraffic addDaysItem(DataTrafficDetail daysItem) {
    if (this.days == null) {
      this.days = new ArrayList<DataTrafficDetail>();
    }
    this.days.add(daysItem);
    return this;
  }

   /**
   * Get days
   * @return days
  **/
  @ApiModelProperty(value = "")
  public List<DataTrafficDetail> getDays() {
    return days;
  }

  public void setDays(List<DataTrafficDetail> days) {
    this.days = days;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DataTraffic dataTraffic = (DataTraffic) o;
    return Objects.equals(this.start, dataTraffic.start) &&
        Objects.equals(this.end, dataTraffic.end) &&
        Objects.equals(this.average, dataTraffic.average) &&
        Objects.equals(this.total, dataTraffic.total) &&
        Objects.equals(this.days, dataTraffic.days);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end, average, total, days);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataTraffic {\n");
    
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
    sb.append("    average: ").append(toIndentedString(average)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    days: ").append(toIndentedString(days)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

