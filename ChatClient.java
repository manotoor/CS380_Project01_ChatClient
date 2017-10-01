import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient implements Runnable{
	private Socket socket = null;
	private DataOutputStream out = null;
	private DataInputStream  console   = null;
	
	public ChatClient(String servername, int port) {
		try {
			socket = new Socket(servername, port);
			System.out.println("Connected to " + socket);
			start();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
    public static void main(String[] args){
        try{
        	
        }catch (IOException e) {
        	System.out.println(e);
        }
    }

	@Override
	public void run() {
		while(true) {
        	//read from client
        	String s = userInput.readLine();
        	if(s.equalsIgnoreCase("exit")) break;
        	//Send over to socket
        	out.println(s);
        	
        	//get reply from server and print it out
        	System.out.println("Server: " + in.readLine());
        }
		
	}
	public void start() throws IOException {
		
	}
	}
}