
/*
 * this uses the SocketCommunicator classes because I have them from other work,
 * because of this I use queue for the input read from the socket rather than
 * allowing input to queue on the sockets input stream
 */
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author s
 */
class NlogoSocketCommunicator implements SocketCommunicator
{
	private SocketConnection cnx;
	private ConcurrentLinkedQueue<String> inputQ;

	public NlogoSocketCommunicator(int port)
	{	// connect as client
		cnx = new SocketConnection( this, port, false );
		inputQ = new ConcurrentLinkedQueue();
	}

	@Override
	public void inputReceived(String text)
	{	inputQ.add(text);
	}

	@Override
	public void notify(String msg)
	{	System.err.println(msg);
	}
	public void flush()
	{	inputQ.clear();
	}
	public String read()
	{	return inputQ.poll();
	}
	public boolean isInputWaiting()
	{	return !inputQ.isEmpty();
	}
	public boolean isConnected()
	{	return cnx.isConnected();
	}
	public void write(String msg)
	{	cnx.write(msg);
	}
	
}
