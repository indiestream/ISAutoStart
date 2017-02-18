/*
* This code and all components Copyright (c) 2017 IndieStream, LLC. All rights reserved.
* This code is licensed pursuant to the agreement in the LICENSE file.
*/

package com.indiestream.isautostart;

import com.wowza.wms.amf.AMFDataList;
import com.wowza.wms.application.IApplicationInstance;
import com.wowza.wms.client.IClient;
import com.wowza.wms.module.ModuleBase;
import com.wowza.wms.request.RequestFunction;
import com.wowza.wms.stream.publish.Playlist;
import com.wowza.wms.stream.publish.Stream;

/**
 * 
 * ISAutoStart - This is a Wowza module that will start streaming a 
 * VOD file live. 
 * 
 * Find  https://github.com/indiestream/ISAutoStart
 * 
 * The following properties must be set in the application's configuration file.
 *  
 * autoStartFileName - The name of the file to be streamed. Loacted in the content/ folder by default.
 * autoStartStreamName - The name of the output stream.
 * autoStartStartTime - The start time to begin streaming at.
 * autoStartDuration -  The duration the file will be played till.
 *  
 * @author petercouture
 *
 */
public class ISAutoStart extends ModuleBase {
	
	private Playlist _playlist;
	private Stream _stream;
	
	public void onAppStart(IApplicationInstance appInstance) {
		
		String fullname = appInstance.getApplication().getName() + "/" + appInstance.getName();
		getLogger().info("onAppStart: " + fullname);

		// Create the playlist that will contain the VOD item to be streamed out.
		_playlist = new Playlist("playlist");
		
		// Set the playlist so it repeats when it gets to the end of the list.
		_playlist.setRepeat(true);
		
		// Pull the configuration properties from the Application.xml
		String fileName = appInstance.getProperties().getPropertyStr("autoStartFileName");
		String streamName = appInstance.getProperties().getPropertyStr("autoStartStreamName");
		int startTime = appInstance.getProperties().getPropertyInt("autoStartStartTime", 0);
		int duration = appInstance.getProperties().getPropertyInt("autoStartDuration", 10);
		
		getLogger().info("onAppStart: ISAutoStart: Autostarting file : " + fileName + " at " + startTime + " till " + duration + " on stream: " + streamName);
		
		// Add the VOD file to the playlist which will be streamed out.
		_playlist.addItem(fileName, startTime, duration);

		// Create the stream that will be published out.
		_stream = Stream.createInstance(appInstance, streamName);
		
		// Start the live stream.
		_playlist.open(_stream);
		
	}

	public void onAppStop(IApplicationInstance appInstance) {
		String fullname = appInstance.getApplication().getName() + "/" + appInstance.getName();
		getLogger().info("onAppStop: " + fullname);
	}

	public void onConnect(IClient client, RequestFunction function, AMFDataList params) {
		getLogger().info("onConnect: " + client.getClientId());
	}

	public void onConnectAccept(IClient client) {
		getLogger().info("onConnectAccept: " + client.getClientId());
	}

	public void onConnectReject(IClient client) {
		getLogger().info("onConnectReject: " + client.getClientId());
	}

	public void onDisconnect(IClient client) {
		getLogger().info("onDisconnect: " + client.getClientId());
	}

}
