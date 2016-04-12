# Introduction #

The unitt-websocket project is an API that allows you to use web sockets in your Java/Android projects. It conforms to the initial & latest versions of web sockets. There are many different web socket servers out there and many to do not conform to the more recent versions of the specification. Please make sure you are using the correct client version for your server version. There are rev07, rev08, and rev10 versions for this client. The client API tries to follow the client specification from javascript using Java constructs. Web sockets provide a low latency, bi-directional, full-duplex communication channel over TCP. The new draft version of the specification allows for text messages (must be UTF-8), as well as, binary messages. For more information, see [Wikipedia](http://en.wikipedia.org/wiki/WebSockets).

# Setup Your Project #

  1. Include the slf4j and Apache Java commons-codec libraries. If you want to use the Java NIO version, you also need Netty. If you are using Android, consider using [SLF4J Android](http://www.slf4j.org/android/). It gives you the full power of SLF4J (package filtering, priority filtering, logger pluggability and portability) and logs to the built-in Android logger.
  1. Include the unitt-websocket library in your project.

If you already use Maven, then adding the dependency to your project is as simple as adding the web socket dependency to your project
  1. Add the dependency & repository:
```
<repositories>
    <repository>
        <id>unitt</id>
        <name>UnitT Repository</name>
        <url>http://unitt.googlecode.com/svn/repository</url>
    </repository>
</repositories>
```
  1. Add the dependency:
```
<dependencies>
    <dependency>
        <groupId>com.unitt.framework</groupId>
        <artifactId>websocket</artifactId>
        <version>0.9.2</version>
        <type>jar</type>
    </dependency>
</dependencies>
```

# Using a Web Socket #

> ### 1. The first step is to create your WebSocketObserver. This is the class that will receive the callbacks from the web socket. ###
<p>
<b>MyWebSocket.java</b>
<pre><code><br>
public class MyWebSocket implements WebSocketObserver<br>
{<br>
//Observer implementation<br>
// ---------------------------------------------------------------------------<br>
/**<br>
 * Called when the web socket connects and is ready for reading and writing.<br>
 **/<br>
public void onOpen( String aProtocol, List&lt;String&gt; aExtensions )<br>
{<br>
    System.out.println("Socket is open for business.");<br>
}<br>
<br>
/**<br>
 * Called when the web socket closes. aError will be nil if it closes cleanly.<br>
 **/<br>
public void onClose( int aStatusCode, String aMessage, Exception aException )<br>
{<br>
    System.out.println("Oops. It closed.");<br>
}<br>
<br>
/**<br>
 * Called when the web socket receives an error. Such an error can result in the<br>
 socket being closed.<br>
 **/<br>
public void onError( Exception aException )<br>
{<br>
    System.out.println("Oops. An error occurred.");<br>
}<br>
<br>
/**<br>
 * Called when the web socket receives a message.<br>
 **/<br>
public void onTextMessage( String aMessage )<br>
{<br>
    //Hooray! I got a message to print.<br>
    System.out.println("Did receive message: %@", aMessage);<br>
}<br>
<br>
/**<br>
 * Called when the web socket receives a message.<br>
 **/<br>
public void onBinaryMessage( byte[] aMessage )<br>
{<br>
    //Hooray! I got a binary message.<br>
}<br>
<br>
/**<br>
 * Called when pong is sent... For keep-alive optimization.<br>
 **/<br>
public void onPong( String aMessage )<br>
{<br>
    System.out.println("Yay! Pong was sent!");<br>
}<br>
}<br>
</code></pre>
</p>

> ### 2. The next step is to actually create and use our web socket. ###
<p>
<b>MyWebSocket.java</b>
<pre><code>//...<br>
<br>
private WebSocket ws;<br>
<br>
//WebSocket logic<br>
// ---------------------------------------------------------------------------<br>
public void startMyWebSocket()<br>
{<br>
    ws.open()<br>
    <br>
    //continue processing other stuff<br>
    //...<br>
}<br>
<br>
//Constructors<br>
// ---------------------------------------------------------------------------<br>
public MyWebSocket()<br>
{<br>
    WebSocketConnectConfig config = new WebSocketConnectConfig();<br>
    config.setUrl( new URI("ws://localhost:8080/testws/ws/test") );<br>
    config.setWebSocketVersion(WebSocketVersion.Version08); //uses WebSocketVersion.Version07 by default<br>
    ws =  SimpleSocketFactory.create( config, this );<br>
}<br>
<br>
//...<br>
</code></pre>
</p>

> ### 3. The final step is to call our code. ###
<p>
<pre><code>MyWebSocket myWS = new MyWebSocket();<br>
myWS.startMyWebSocket();<br>
<br>
//continue processing<br>
//...<br>
</code></pre>
</p>

# Sample Code #
You can check out the [NetworkSocketTest](http://code.google.com/p/unitt/source/browse/projects/unitt-websocket/tags/0.9.1.1/src/test/java/com/unitt/framework/websocket/simple/NetworkSocketTest.java) class for sample code on connecting and talking to a web socket. You can also run your own server for the test using the [testws](http://code.google.com/p/unitt/source/browse/projects#projects%2FiOS%2Ftestws%2Ftrunk) project. To start the server using maven type "mvn jetty:run" at the command line.


# More Info #
  * Check out the [ClientWebsocketFactory.java](http://code.google.com/p/unitt/source/browse/projects/unitt-websocket/tags/0.9.2/src/main/java/com/unitt/framework/websocket/netty/ClientWebsocketFactory.java), [SimpleSocketFactory.java](http://code.google.com/p/unitt/source/browse/projects/unitt-websocket/tags/0.9.2/src/main/java/com/unitt/framework/websocket/simple/SimpleSocketFactory.java) for more information on how to use these classes.
  * For more information on web sockets, see [Wikipedia](http://en.wikipedia.org/wiki/WebSockets)