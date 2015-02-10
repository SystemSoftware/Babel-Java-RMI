package comtec.services;

import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The client, that receives the json ball and pushes it to the rmi service.
 * @author Blank
 *
 */
public class BallClient {

	public static final String rmiServerName = "rmi://localhost/comtec/BallHolderImpl";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BallHolder ballHolder;		// Rmi service object, where the ball is sent
		JsonServer jsonServer;		// JSON server from where the ball is received
		boolean  useSecurityManager = false;
		
		try {
			
			if(useSecurityManager) {
				System.setSecurityManager(new RMISecurityManager());
				ballHolder = (BallHolder)Naming.lookup(rmiServerName);
			}
			else {
				Registry registry = LocateRegistry.getRegistry();
				ballHolder = (BallHolder) registry.lookup( rmiServerName );
			}
			
			jsonServer = new JsonServerImpl();

			String jsonString;
			while(true) {
				jsonString = jsonServer.receiveBall();
				if(jsonString != null) {
					String jsonBall = jsonServer.receiveBall();
					BallImpl javaBall = BallConversion.ConvertJsonToJava(jsonBall);
					
					// TODO update ball stats here
					javaBall.hopCount++;
					Thread.sleep(javaBall.holdTime * 1000);
					javaBall.payLoad.put("Super RMI", "Timestamp");
					
					// send to RMI Service
					ballHolder.sendBall(javaBall);
				}
			}
		 
		}catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
	}

	public static String nameBuilder(String host, int port, String name) {
		return String.format("rmi://%s:%d/%s", host, port, name);
	}
	
	static String rmiServerHost;
	static int rmiServerPort;
	static String rmiServerObject;
	
	static String jsonServerHost;
	static int jsonServerPort;
	static String jsonServerObject;
	
	// TODO load settings from XML file;
	/**
	 * Possibility to load the java and json service configuration from external xml file to be more flexible.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void loadSettings() throws ParserConfigurationException, SAXException, IOException {

		File settingsXmlFile = new File("BallClient.settings.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(settingsXmlFile);
		
		Element root = doc.getDocumentElement();
		NodeList list = root.getElementsByTagName("RmiServiceSettings");
		rmiServerPort = Integer.parseInt(list.item(0).getAttributes().getNamedItem("Port").getTextContent());
		rmiServerHost = list.item(0).getAttributes().getNamedItem("Host").getTextContent();
		rmiServerObject = list.item(0).getAttributes().getNamedItem("Object").getTextContent();
	}
}
