package com.ibm.iotf.test.common;

import org.eclipse.paho.mqttv5.common.MqttException;

import com.ibm.iotf.devicemgmt.DeviceAction;
import com.ibm.iotf.devicemgmt.DeviceActionHandler;
import com.ibm.iotf.devicemgmt.DeviceAction.Status;
import com.ibm.iotf.devicemgmt.device.ManagedDevice;
import com.ibm.iotf.util.LoggerUtility;

public class TestDeviceActionHandler extends DeviceActionHandler {
	
	private static final String CLASS_NAME = TestDeviceActionHandler.class.getName();
	private ManagedDevice dmClient = null;
	private boolean reboot = false;
	private boolean factoryReset = false;
	
	public TestDeviceActionHandler(ManagedDevice dmClient) {
		this.dmClient = dmClient;
	}
	
	public boolean rebooted() { return reboot; }
	public boolean factoryReset() { return factoryReset; }
	
	@Override
	public void handleReboot(DeviceAction action) {
		final String METHOD = "handleReboot";
		action.setStatus(Status.ACCEPTED);
		LoggerUtility.info(CLASS_NAME, METHOD, "reboot initiated.");
		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
					boolean status = dmClient.sendManageRequest(0,  true, true);
					LoggerUtility.info(CLASS_NAME, METHOD, "sent a manage request : " + status);
				} catch (MqttException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				reboot = true;
			}
		}.start();
	}

	@Override
	public void handleFactoryReset(DeviceAction action) {
		final String METHOD = "handleFactoryReset";
		LoggerUtility.info(CLASS_NAME, METHOD, "factory reset initiated.");
		action.setStatus(Status.ACCEPTED);
		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
					boolean status = dmClient.sendManageRequest(0,  true, true);
					LoggerUtility.info(CLASS_NAME, METHOD, "sent a manage request : " + status);
				} catch (MqttException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				factoryReset = true;
			}

		}.start();
	}

}
