public class ChatClient{
    public static void main(String[] args){
        try(Socket socket = new Socket("localhost",22222)){
            //Send Server Request
            //Server Response
            //Client Print
            
            //While connected
            while(true) {
            	//read from client
            	String s = userInput.readLine();
            	if(s.equalsIgnoreCase("exit")) break;
            	//Send over to socket
            	out.println(s);
            	
            	//get reply from server and print it out
            	System.out.println("Server: " + in.readLine());
            }
        }catch (IOException e) {
        	System.out.println(e);
        }
    }
}