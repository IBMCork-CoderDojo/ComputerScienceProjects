public class MultiChatClient{

    public static void main(String[] args) throws IOException{
        Socket s = new Socket("localhost", 1344);
        BufferedReader input =
                new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(s.getOutputStream());


        new Thread(() -> {
            while(true){
                try{
                    String response = input.readLine();
                    System.out.println(response);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }).start();

        while(true){
            out.println(keyboardInput.readLine());
            out.flush();
        }
    }
}
