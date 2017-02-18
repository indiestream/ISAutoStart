/*
* This code and all components Copyright (c) 2017 IndieStream, LLC. All rights reserved.
* This code is licensed pursuant to the agreement in the LICENSE file.
*/

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
 * autoStartVHostName and autoStartApplicationName must be set in the servers conf/Server.xml
 * file. 
 *   
 * @author petercouture
 *
 */
public class ISAutoStartServer implements IServerNotify2 {

	
	public void onServerConfigLoaded(IServer server) {
		WMSLoggerFactory.getLogger(null).info("onServerConfigLoaded");
	}

	public void onServerCreate(IServer server) {
		WMSLoggerFactory.getLogger(null).info("onServerCreate");
	}

	public void onServerInit(IServer server) {
		WMSLoggerFactory.getLogger(null).info("onServerInit");
		
		// Once the server has initialized, tell the default VHost to start the application.
		VHostSingleton.getInstance(server.getProperties().getPropertyStr("autoStartVHostName")).startApplicationInstance(server.getProperties().getPropertyStr("autoStartApplicationName"));
	}

	public void onServerShutdownStart(IServer server) {
		WMSLoggerFactory.getLogger(null).info("onServerShutdownStart");
	}

	public void onServerShutdownComplete(IServer server) {
		WMSLoggerFactory.getLogger(null).info("onServerShutdownComplete");
	}

}
