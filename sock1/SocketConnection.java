
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s
 */
public class SocketConnection
{
	private final static int defaultPortNo = 2222;		// some arbitrary port no. for this example
	private int portNo;
	private SocketCommunicator babbler;
	private BufferedReader inStream;
	private PrintWriter outStream;
	private boolean isConnected;

	public SocketConnection(SocketCommunicator babbler, boolean isServer )
	{	this( babbler, defaultPortNo, isServer );
	}
	public SocketConnection(SocketCommunicator babbler, int portNo, boolean isServer )
	{	this.babbler = babbler;
		this.portNo = portNo;
		isConnected = false;
		if( isServer )
			advertiseAsServer();
		else
			connectAsClient();
	}
	public boolean isConnected()
	{	return isConnected;
	}
	public void write( String msg )
	{	if( isConnected )
			outStream.println(msg);
	}
	private void advertiseAsServer()
	{	new Thread()
		{	public void run()
			{	Socket socket;
				try
				{	ServerSocket server = new ServerSocket(portNo);
					babbler.notify("server advertising");
					socket = server.accept();
					makeComms(socket);
					isConnected = true;
					readerLoop();
				}
				catch (IOException ex)
				{	Logger.getLogger(SocketConnection.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}.start();
	}
	
	private void connectAsClient()
	{	new Thread()
		{	public void run()
			{	Socket socket;
				try
				{	babbler.notify("client attaching");
					socket = new Socket( InetAddress.getLocalHost(), portNo );
					makeComms(socket);
					isConnected = true;
					readerLoop();
				}
				catch (IOException ex)
				{	Logger.getLogger(SocketConnection.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}.start();
	}
	
	private void makeComms(Socket socket) throws IOException
	{	babbler.notify("making IO streams");
		inStream = new BufferedReader(
							new InputStreamReader( socket.getInputStream()) );
		outStream = new PrintWriter( socket.getOutputStream(), true );
		babbler.notify("IO streams ok");
	}
	
	private void readerLoop() throws IOException
	{	while( isConnected )
		{	babbler.inputReceived( inStream.readLine() );
		}
		babbler.notify("socket reader closing");
	}
}
