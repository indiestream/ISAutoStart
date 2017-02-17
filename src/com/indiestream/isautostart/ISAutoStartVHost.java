package com.indiestream.isautostart;

import com.wowza.wms.amf.AMFDataList;
import com.wowza.wms.client.IClient;
import com.wowza.wms.logging.WMSLoggerFactory;
import com.wowza.wms.request.RequestFunction;
import com.wowza.wms.vhost.IVHost;
import com.wowza.wms.vhost.IVHostNotify;

public class ISAutoStartVHost implements IVHostNotify {

	IVHost _vhost;
	
	@Override
	public void onVHostClientConnect(IVHost vhost, IClient inClient, RequestFunction function, AMFDataList params) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onVHostCreate(IVHost vhost) {
		// TODO Auto-generated method stub
		WMSLoggerFactory.getLogger(null).info("onVHostCreate");

	}

	@Override
	public void onVHostInit(IVHost vhost) {
		// TODO Auto-generated method stub
		WMSLoggerFactory.getLogger(null).info("onVHostInit");
		// Save the vhost 
		_vhost = vhost;
	}

	@Override
	public void onVHostShutdownComplete(IVHost vhost) {
		// TODO Auto-generated method stub
		WMSLoggerFactory.getLogger(null).info("onVHostShutdownComplete");

	}

	@Override
	public void onVHostShutdownStart(IVHost vhost) {
		// TODO Auto-generated method stub
		WMSLoggerFactory.getLogger(null).info("onVHostShutdownStart");

	}

	/**
	 * The function call that 
	 */
	public void startApplication(){
		WMSLoggerFactory.getLogger(null).info("startApplication");
		
		// Set the 
		_vhost.startApplicationInstance("autostart");
	}
	
}
