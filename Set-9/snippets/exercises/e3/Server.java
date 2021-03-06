public class Server{
    public static void main(String[] args) throws IOException{
        ServerSocket listener = new ServerSocket(1337);

        try{
            Socket socket = listener.accept();
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));

            try{
                new Thread(() -> {
                    while(true){
                        String received = null;
                        try{
                            received = input.readLine();
                            System.out.println(received);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }).start();

                while(true){
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
                    out.println(keyboardInput.readLine());
                    out.flush();
                }

            }finally{
                socket.close();
            }
        }finally{
            listener.close();
        }
    }
}
