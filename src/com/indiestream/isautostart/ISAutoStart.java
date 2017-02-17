package com.indiestream.isautostart;

import com.wowza.wms.application.*;
import com.wowza.wms.amf.*;
import com.wowza.wms.client.*;
import com.wowza.wms.module.*;
import com.wowza.wms.request.*;
import com.wowza.wms.stream.*;
import com.wowza.wms.stream.publish.Playlist;
import com.wowza.wms.stream.publish.Stream;
import com.wowza.wms.rtp.model.*;
import com.wowza.wms.httpstreamer.model.*;
import com.wowza.wms.httpstreamer.cupertinostreaming.httpstreamer.*;
import com.wowza.wms.httpstreamer.smoothstreaming.httpstreamer.*;

public class ISAutoStart extends ModuleBase {
	
	private Playlist _playlist;
	private Stream _stream;
	private String _streamName = "feed";
	
	public void onAppStart(IApplicationInstance appInstance) {
		String fullname = appInstance.getApplication().getName() + "/" + appInstance.getName();
		getLogger().info("onAppStart: " + fullname);
		

		// Create the playlist the cameras will cycle through
		_playlist = new Playlist("playlist");
		
		// Set the playlist so it repeats when it gets to the end of the list.
		_playlist.setRepeat(true);
		
		// Add the default stream which is a VOD file stored in the /content folder.
		// This file will play when no cameras are publishing to the app.
		_playlist.addItem("mp4:sample.mp4", 0, 630);

		// Create the stream that will be published out
		_stream = Stream.createInstance(appInstance, _streamName);
		
		// Open the stream using the content in the playlist. To start will be the 'Offline' video.
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
