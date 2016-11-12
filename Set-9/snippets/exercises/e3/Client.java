public class Client{
    public static void main(String[] args) throws IOException{
        Socket s = new Socket("localhost", 1337);
        BufferedReader input =
                new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(s.getOutputStream());


        new Thread(() -> {
            while(true){
                String response = null;
                try{
                    response = input.readLine();
                    System.out.println(response);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }).start();

        while(true){
            String toSend = keyboardInput.readLine();
            out.println(toSend);
            out.flush();
        }
    }
}
