package com.indiestream.isautostart;

import com.wowza.wms.logging.WMSLoggerFactory;
import com.wowza.wms.server.IServer;
import com.wowza.wms.server.IServerNotify2;
import com.wowza.wms.vhost.VHostSingleton;


/**
 * 
 * ISAutoStartServer - This is the server listener class. It 
 * tells the VHost to start the application once the server 
 * has initialized.
 * 
 * You need to add it to the ServerListeners node at the 
 * bottom of /conf/server.xml
 *   
 * @author petercouture
 *
 */
public class ISAutoStartServer implements IServerNotify2 {

	// The listener for the VHost events.
	ISAutoStartVHost listenerVHost;
	
	public void onServerConfigLoaded(IServer server) {
		WMSLoggerFactory.getLogger(null).info("onServerConfigLoaded");
	}

	public void onServerCreate(IServer server) {
		
		WMSLoggerFactory.getLogger(null).info("onServerCreate");

		// Create a new event listener for the default VHost.
		//listenerVHost = new ISAutoStartVHost();
		//VHostSingleton.addVHostListener(listenerVHost);
	}

	public void onServerInit(IServer server) {
		WMSLoggerFactory.getLogger(null).info("onServerInit");
		// Once the server has initialized, tell the default VHost to start the application.
		//listenerVHost.startApplication();
		VHostSingleton.getInstance(server.getProperties().getPropertyStr("autoStartVHostName")).startApplicationInstance(server.getProperties().getPropertyStr("autoStartApplicationName"));
	}

	public void onServerShutdownStart(IServer server) {
		WMSLoggerFactory.getLogger(null).info("onServerShutdownStart");
		
		// Remove the listener and set it to null.
		//VHostSingleton.removeApplicationListener(listenerVHost);
		//listenerVHost = null;
	}

	public void onServerShutdownComplete(IServer server) {
		WMSLoggerFactory.getLogger(null).info("onServerShutdownComplete");
	}

}
