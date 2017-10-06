
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;


public final class EchoClient {

    public static void main(String[] args) throws Exception {
    	System.out.print("Enter Username>");
    	
        try (Socket socket = new Socket("18.221.102.182", 38001)) {
        	
        	
            Runnable serverMessageThread = () -> {
            	In in = null;
	            try{
	                in = new In(socket);
	                while(true){
	            		String s = in.readLine();
	            		if(s.equalsIgnoreCase("null") || s == null)
	            			break;
	            		else {
	            			System.out.println(s);
	            		}
	                }
	                socket.close();
	        		
	            }catch(Exception e){
	                System.out.println("Error recieving message from server");
	                in.close();
	            }   
            };
	        Runnable outThread = () -> {
	        	OutputStream os = null;
	        	PrintStream out = null;
	        	int x = 0;
	        	String userName = null;
	            try{
	            	In userInput = new In();
	
		            //Output to server
		            os = socket.getOutputStream();
		    		out = new PrintStream(os, true, "UTF-8");
	                while(true){
	                	if(x == 1){
	                		System.out.println("Connected as " + userName);
	                		x++;
	                	}
	                	String s = userInput.readLine();
	                	if(s.equalsIgnoreCase("exit") || socket.isClosed()) break;
	                	if(x == 0) {
	                		userName = s;
	                		System.out.println("Attempting connection as " + s + " to " + socket.getInetAddress() + ":"+ socket.getPort());
	                	}
	                	out.println(s);
	                	x++;
	                }
	                userInput.close();
	                out.close();
	                socket.close();
	            }catch(Exception e){
	                System.out.println("Error with server;");
	                out.close();
	            }   
	        };
        	//Declare Thread
			Thread serverM = new Thread(serverMessageThread);
			Thread clientM = new Thread(outThread);
			clientM.start();
			serverM.start();
			int x = 0;
			while(!socket.isClosed()) {
				if(socket.isClosed());
				Thread.sleep(1000);
			}
			serverM.stop();
			clientM.stop();
			System.out.println("Closed Communications with server\nPress Enter to exit..");
			clientM = null;
			serverM = null;
        }catch (IOException e) {
        	System.out.println(e);
        }

    }
}