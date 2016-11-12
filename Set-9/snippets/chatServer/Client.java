public class Client{
    public static void main(String[] args) throws IOException{

        Socket s = new Socket("localhost", 1337);
        BufferedReader input =
                new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(s.getOutputStream());
        
         while(true){             
            String toSend = keyboardInput.readLine();
            out.println(toSend);
            out.flush();
            
            String response = input.readLine();
            System.out.println(response);
        }
    }
}
