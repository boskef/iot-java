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
import io.swagger.annotations.ApiModel;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * The status of the service. \\\&quot;green\\\&quot; - the service is functioning properly, \\\&quot;amber\\\&quot; - the service is impacted, but still functioning, or \\\&quot;red\\\&quot; - the service is down. The specific meaning of the status is communicated in the status blog.
 */
@JsonAdapter(ServiceStatus.Adapter.class)
public enum ServiceStatus {
  
  GREEN("green"),
  
  AMBER("amber"),
  
  RED("red");

  private String value;

  ServiceStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static ServiceStatus fromValue(String text) {
    for (ServiceStatus b : ServiceStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<ServiceStatus> {
    @Override
    public void write(final JsonWriter jsonWriter, final ServiceStatus enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public ServiceStatus read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return ServiceStatus.fromValue(String.valueOf(value));
    }
  }
}

