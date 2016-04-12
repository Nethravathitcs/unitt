# Introduction #

UnittWebSocketClient is an API that allows you to use web sockets in your iOS Objective-C projects. It conforms to the initial & latest versions of web sockets. Simply import WebSocket.h.

There are many different web socket servers out there and many to do not conform to the more recent versions of the specification. Please make sure you are using the correct client version for your server version. If you need a version prior to the hybi drafts, import WebSocket00.h.

The client API tries to follow the client specification from javascript using Objective-C constructs. Web sockets provide a low latency, bi-directional, full-duplex communication channel over TCP. The new draft version of the specification allows for text messages (must be UTF-8), as well as, binary messages. For more information, see [Wikipedia](http://en.wikipedia.org/wiki/WebSockets).

# Setup Your Project #

  1. Include the CFNetwork and Security frameworks
  1. Add the "-all\_load" flag to your "Other Linker Flags" in the "Build Settings" of your project
  1. Include the UnittWebSocketClient library in your project. For more information on this, see [Including a LIbrary in Your Xcode Project](xCodeIncludes.md).

# Using a Web Socket #

> ### 1. The first step is to create your WebSocketDelegate. This is the class that will receive the callbacks from the web socket. ###
<p>
<b>MyWebSocket.h</b>
<pre><code>#import "WebSocket.h"<br>
<br>
<br>
@interface MyWebSocket : NSObject &lt;WebSocketDelegate&gt;<br>
{<br>
}<br>
<br>
@end<br>
</code></pre>
</p>
<p>
<b>MyWebSocket.m</b>
<pre><code>#import "MyWebSocket.h"<br>
<br>
<br>
@implementation MyWebSocket<br>
<br>
<br>
#pragma mark Web Socket<br>
/**<br>
 * Called when the web socket connects and is ready for reading and writing.<br>
 **/<br>
- (void) didOpen<br>
{<br>
    NSLog(@"Socket is open for business.");<br>
}<br>
<br>
/**<br>
 * Called when the web socket closes. aError will be nil if it closes cleanly.<br>
 **/<br>
- (void) didClose:(NSUInteger) aStatusCode message:(NSString*) aMessage error:(NSError*) aError<br>
{<br>
    NSLog(@"Oops. It closed.");<br>
}<br>
<br>
/**<br>
 * Called when the web socket receives an error. Such an error can result in the<br>
 socket being closed.<br>
 **/<br>
- (void) didReceiveError:(NSError*) aError<br>
{<br>
    NSLog(@"Oops. An error occurred.");<br>
}<br>
<br>
/**<br>
 * Called when the web socket receives a message.<br>
 **/<br>
- (void) didReceiveTextMessage:(NSString*) aMessage<br>
{<br>
    //Hooray! I got a message to print.<br>
    NSLog(@"Did receive message: %@", aMessage);<br>
}<br>
<br>
/**<br>
 * Called when the web socket receives a message.<br>
 **/<br>
- (void) didReceiveBinaryMessage:(NSData*) aMessage<br>
{<br>
    //Hooray! I got a binary message.<br>
}<br>
<br>
/**<br>
 * Called when pong is sent... For keep-alive optimization.<br>
 **/<br>
- (void) didSendPong:(NSData*) aMessage<br>
{<br>
    NSLog(@"Yay! Pong was sent!");<br>
}<br>
<br>
<br>
@end<br>
</code></pre>
</p>

> ### 2. The next step is to actually create and use our web socket. ###
<p>
<b>MyWebSocket.h</b>
<pre><code>#import "WebSocket.h"<br>
<br>
<br>
@interface MyWebSocket : NSObject &lt;WebSocketDelegate&gt;<br>
{<br>
@private<br>
    WebSocket* ws;<br>
}<br>
<br>
@property (nonatomic, readonly) WebSocket* ws;<br>
<br>
- (void) startMyWebSocket;<br>
<br>
@end<br>
</code></pre>
</p>
<p>
<b>MyWebSocket.m</b>
<pre><code>//...<br>
<br>
@synthesize ws;<br>
<br>
#pragma mark Web Socket<br>
- (void) startMyWebSocket<br>
{<br>
    [self.ws open];<br>
    <br>
    //continue processing other stuff<br>
    //...<br>
}<br>
<br>
#pragma mark Lifecycle<br>
- (id)init<br>
{<br>
    self = [super init];<br>
    if (self) <br>
    {<br>
        //make sure to use the right url, it must point to your specific web socket endpoint or the handshake will fail<br>
        //create a connect config and set all our info here<br>
        WebSocketConnectConfig* config = [WebSocketConnectConfig configWithURLString:@"ws://localhost:8080/testws/ws/test" origin:nil protocols:nil tlsSettings:nil headers:nil verifySecurityKey:YES extensions:nil ];<br>
        config.closeTimeout = 15.0;<br>
        config.keepAlive = 15.0; //sends a ws ping every 15s to keep socket alive<br>
<br>
        //setup dispatch queue for delegate logic (not required, the websocket will create its own if not supplied)<br>
        dispatch_queue_t delegateQueue = dispatch_queue_create("myWebSocketQueue", NULL);<br>
<br>
        //open using the connect config, it will be populated with server info, such as selected protocol/etc<br>
        ws = [[WebSocket webSocketWithConfig:config queue:delegateQueue delegate:self] retain];<br>
<br>
        //release queue, it is retained by the web socket<br>
        dispatch_release(delegateQueue);<br>
    }<br>
    return self;    <br>
}<br>
<br>
- (void)dealloc <br>
{<br>
    [ws release];<br>
    [super dealloc];<br>
}<br>
<br>
//...<br>
</code></pre>
</p>

> ### 3. The final step is to call our code. ###
<p>
<pre><code>MyWebSocket* myWS = [[MyWebSocket alloc] init];<br>
[myWS startMyWebSocket];<br>
<br>
//continue processing<br>
//...<br>
</code></pre>
</p>


# More Info #
  * Check out the [WebSocket.h](http://code.google.com/p/unitt/source/browse/projects/iOS/UnittWebSocketClient/tags/1.0.0/UnittWebSocketClient/WebSocket.h) for more information on how to use these classes.
  * For more information on web sockets, see [Wikipedia](http://en.wikipedia.org/wiki/WebSockets)

# Sample Code #
You can check out the [UnittWebSocketClientTests](http://code.google.com/p/unitt/source/browse/projects/iOS/UnittWebSocketClient/tags/1.0.0/UnittWebSocketClientTests/UnittWebSocketClientTests.m) class for sample code on connecting and talking to a web socket. You can also run your own server for the test using the [testws](http://code.google.com/p/unitt/source/browse/projects#projects%2FiOS%2Ftestws%2Ftrunk) project. To start the server using maven type "mvn jetty:run" at the command line.

## WebSocket v00 ##
WebSocket00.h is based on an earlier specification that was not as clear on how binary messages were handled. Therefore, this class only supports UTF8 text messages, so there is only send: and didReceiveMessage: instead of the text/binary variants. If you need the hixie76 keys, you can set the useKeys property to true. Otherwise, everything should just work. You can check out the [UnittWebSocketClient00Tests](http://code.google.com/p/unitt/source/browse/projects/iOS/UnittWebSocketClient/tags/1.0.0-RC/UnittWebSocketClientTests/UnittWebSocketClient00Tests.m) class for sample code for more info on using this version. This version is deprecated in the library and will not be updated as the specification is now 13 versions ahead of this. Contributions to the code will be reviewed and accepted, but there is no planned development due to its incompatibility with the current version of the specification.