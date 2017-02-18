# ISAutoStart

As a streaming engineer you often need to have a Live stream running to test on. Especially if you're working on a player. Here's a useful library to get one going in Wowza with the need of an encoder or webcam. You can learn more about how it works [here](http://www.indiestre.am/index.php/2017/02/16/auto-starting-an-application-using-wowza/).

---

## Setup

1. Copy ISAutoStart.jar to your Wowza lib/ folder.

2. Edit the Wowza configuration file located at conf/Server.xml Scroll down to the bottom and add the following in the ```<ServerListeners>``` node.

```
<ServerListener>
  <BaseClass>com.indiestream.ISAutoStart</BaseClass>
</ServerListener>
```

3. Further down in the same file add the following two properties in the ```<Properties>``` node. You can update the values to represet a different VHost then the default one and the application name. By default the program runs on the application name ```autostart```

```
<Property>
  <Name>autoStartVHostName</Name>
  <Value>_defaultVHost_</Value>
  <Type>String</Type>
</Property>
<Property>
  <Name>autoStartApplicationName</Name>
  <Value>autostart</Value>
  <Type>String</Type>
</Property>
```

4. Now create the application by adding the ```autostart``` folder in both the ```applications``` and ```conf```. Copy the application configuration file ```Application.xml``` from the ```live``` application into the new folder.

---
