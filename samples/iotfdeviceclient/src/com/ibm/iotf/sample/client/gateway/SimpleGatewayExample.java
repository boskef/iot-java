/**
 *****************************************************************************
 * Copyright (c) 2016 IBM Corporation and other Contributors.

 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sathiskumar Palaniappan - Initial Contribution
 *****************************************************************************
 */

package com.ibm.iotf.sample.client.gateway;

import java.util.Properties;

import javax.management.InstanceNotFoundException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.iotf.client.IoTFCReSTException;
import com.ibm.iotf.client.api.APIClient;
import com.ibm.iotf.client.app.ApplicationClient;
import com.ibm.iotf.client.gateway.GatewayClient;
import com.ibm.iotf.sample.client.SystemObject;
import com.ibm.iotf.sample.util.Utility;

/**
 * <p>The Gateway sample uses the com.ibm.iotf.client.gateway.GatewayClient class 
 * from the Watson IoT Platform Java Client Library 
 * that simplifies the Gateway interactions with IBM Watson IoT Platform. </p>
 * 
 */
public class SimpleGatewayExample {
	
	private final static String PROPERTIES_FILE_NAME = "gateway.prop";
	private final static String DEFAULT_PATH = "samples/iotfdeviceclient/src";
	
	private final static String DEVICE_TYPE = "iotsampleType";
	private final static String SIMULATOR_DEVICE_ID = "Arduino02";
	
	private GatewayClient gwClient = null;
	SystemObject obj = new SystemObject();
	private String gwDeviceId;
	private String gwDeviceType;
	private APIClient apiClient;	
	
	public SimpleGatewayExample() {
		
	}
	
	/**
	 * This method creates a GatewayClient instance by passing the required properties 
	 * and connects the Gateway to the Watson IoT Platform by calling the connect function.
	 * 
	 * After the successful connection to the Watson IoT Platform, the Gateway can perform the following operations,
	 *   1. Publish events for itself and on behalf of devices connected behind the Gateway
	 *   2. Subscribe to commands for itself and on behalf of devices behind the Gateway
	 */
	private void createGatewayClient(String fileName) {
		/**
		 * Load properties file "gateway.prop"
		 */
		Properties props = Utility.loadPropertiesFile(PROPERTIES_FILE_NAME, fileName);
		
		try {
			//Instantiate & connect the Gateway by passing the properties file
			gwClient = new GatewayClient(props);
			this.gwDeviceId = props.getProperty("Gateway-ID");
			this.gwDeviceType = props.getProperty("Gateway-Type");
			gwClient.connect();
			
			Properties options = new Properties();
			options.put("Organization-ID", props.getProperty("Organization-ID"));
			options.put("id", "app" + (Math.random() * 10000));		
			options.put("Authentication-Method","apikey");
			options.put("API-Key", props.getProperty("API-Key"));		
			options.put("Authentication-Token", props.getProperty("API-Token"));
			
			this.apiClient = new APIClient(options);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * While the Gateway publishes events on behalf of the devices connected behind, the Gateway 
	 * can publish its own events as well. 
	 * 
	 * The sample publishes a blink event every second, that has the CPU and memory utilization of 
	 * this sample Gateway process.
	 */
	private void publishGatewayEvent() {
		//Generate a JSON object of the event to be published
		JsonObject event = new JsonObject();
		event.addProperty("name", SystemObject.getName());
		try {
			event.addProperty("cpu",  obj.getProcessCpuLoad());
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (ReflectionException e) {
			e.printStackTrace();
		}
		event.addProperty("mem",  obj.getMemoryUsed());
			
		gwClient.publishGatewayEvent("blink", event, 2);
		System.out.println("Publish Gateway event:: "+event);
	}
	
	/**
	 * The method publishes a blink event every second, that has the CPU and memory utilization of 
	 * this sample Gateway process.
	 */
	private void publishDeviceEvent() {
		//Generate a JSON object of the event to be published
		JsonObject event = new JsonObject();
		event.addProperty("name", SystemObject.getName() + "simulator");
		try {
			event.addProperty("cpu",  obj.getProcessCpuLoad());
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (ReflectionException e) {
			e.printStackTrace();
		}
		event.addProperty("mem",  obj.getMemoryUsed());
			
		gwClient.publishDeviceEvent(DEVICE_TYPE, SIMULATOR_DEVICE_ID, "blink", event, 2);
		System.out.println("Publish Device event:: "+event);
	}
	
	private void disconnect() {
		//Disconnect cleanly
		gwClient.disconnect();
	}
	
	/**
	 * This sample showcases how to Create a device type using the Java Client Library. 
	 * @throws IoTFCReSTException
	 */
	private void addDeviceType() throws IoTFCReSTException {
		try {
			JsonObject response = this.apiClient.addDeviceType(DEVICE_TYPE, null, null, null);
			System.out.println(response);
		} catch(IoTFCReSTException e) {
			System.out.println("HttpCode :" + e.getHttpCode() +" ErrorMessage :: "+ e.getMessage());
			// Print if there is a partial response
			System.out.println(e.getResponse());
		}
	}
	
	/**
	 * Add a device under the given gateway using the Java Client Library.
	 * @throws IoTFCReSTException
	 */
	private void addDevice(String deviceId) throws IoTFCReSTException {
		try{
			
			String deviceToBeAdded = "{\"deviceId\": \"" + deviceId +
						"\",\"authToken\": \"qwert123\"}";

			System.out.println(deviceToBeAdded);
			JsonParser parser = new JsonParser();
			JsonElement input = parser.parse(deviceToBeAdded);
			JsonObject response = this.gwClient.api().
					registerDeviceUnderGateway(DEVICE_TYPE, this.gwDeviceId, this.gwDeviceType, input);
			System.out.println(response);
			
		} catch(IoTFCReSTException e) {
			System.out.println("HttpCode :" + e.getHttpCode() +" ErrorMessage :: "+ e.getMessage());
			// Print if there is a partial response
			System.out.println(e.getResponse());
		}
	}

	public static void main(String[] args) throws IoTFCReSTException {
		
		SimpleGatewayExample sample = new SimpleGatewayExample();
		
		String fileName = Utility.getDefaultFilePath(PROPERTIES_FILE_NAME, DEFAULT_PATH);
		sample.createGatewayClient(fileName);
		sample.addDeviceType();
		sample.addDevice(SIMULATOR_DEVICE_ID);

		System.out.println("Gateway Started");
		
		/**
		 * Try to publish a Gateway Event for every second. As like devices, the Gateway
		 * also can have attached sensors and publish events.
		 */
		while(true) {
			sample.publishGatewayEvent();
			sample.publishDeviceEvent();
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ie) {}
		}
		
		//sample.disconnect();
		
	}
}