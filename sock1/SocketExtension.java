

import org.nlogo.api.*;


/**
 * this extension contains a hook-up to some of the methods provided by
 * Java's String class. For further documentation check the Java API docs.
 * 
 * @author s
 */
public class SocketExtension
    extends org.nlogo.api.DefaultClassManager {
	
	private static NlogoSocketCommunicator comm;

  public void load(org.nlogo.api.PrimitiveManager primManager) {
    primManager.addPrimitive("connect",	new Connect());
	primManager.addPrimitive("write",	new Write());
	primManager.addPrimitive("read",	new Read());
	primManager.addPrimitive("flush",	new Flush());
  }


  //===========================================================================
  // extension requirements
  //===========================================================================

    public String getExtensionName() {
      return "sock1";
    }

    public String getNLTypeName() {
      // following comment plagarised from NetLogo table extension...
	  // since this extension only defines one type, we don't
      // need to give it a name; "table:" is enough,
      // "table:table" would be redundant
      return "";
    }

   // exportWorld & importWorld not specified because there is no persistent data



//===========================================================================
// extension primitives
//===========================================================================

//--- Boolean connect( int portNo ) ---------------------------------------

/** connect to a socket via a served port number, return value indicates success/failure */
public static class Connect extends DefaultCommand
{
    public Syntax getSyntax() {
      return Syntax.commandSyntax
          (new int[]{Syntax.NumberType()});
    }

    public String getAgentClassString() { return "OTPL"; }

    public void perform(Argument args[], Context context)
        throws ExtensionException, LogoException {
      int port = (int)args[0].getIntValue();
	  comm = new NlogoSocketCommunicator(port);
    }
}



//--- void write( String msg ) --------------------------------

/** write a line of text onto the socket */
public static class Write extends DefaultCommand
{
    public Syntax getSyntax() {
      return Syntax.commandSyntax
          (new int[]{Syntax.StringType()});
    }

    public String getAgentClassString() { return "OTPL"; }

    public void perform(Argument args[], Context context)
        throws ExtensionException, LogoException {
      String str = (String)args[0].get();
	  comm.write(str);
    }
}


//--- String read() --------------------------------

/**
 * gets the next input line from the socket
 */
public static class Read extends DefaultReporter
{
    public Syntax getSyntax() {
      return Syntax.reporterSyntax
          (new int[]{},
              Syntax.StringType());
    }

    public String getAgentClassString() { return "OTPL"; }

    public Object report(Argument args[], Context context)
        throws ExtensionException, LogoException {
      return comm.read();
    }
}


//--- void flush() -------------------------------------

/** flushes the input queue */
public static class Flush extends DefaultCommand
{
    public Syntax getSyntax() {
      return Syntax.commandSyntax
          (new int[]{});
    }

    public String getAgentClassString() { return "OTPL"; }

    public void perform(Argument args[], Context context)
        throws ExtensionException, LogoException {
      comm.flush();
    }
}



}
