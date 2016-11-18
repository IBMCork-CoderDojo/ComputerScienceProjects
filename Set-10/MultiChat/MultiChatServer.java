public class MultiChatServer{
    static ArrayList<Socket> allConnections = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        ServerSocket listener = new ServerSocket(1344);

        try{
            while(true){
                Socket socket = listener.accept();
                allConnections.add(socket);
                BufferedReader input =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));

                new Thread(() -> {
                    while(true){
                        try{
                            String received = input.readLine();
                            System.out.println(received);

                            broadcastMessage(received);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }finally{
            listener.close();
        }
    }

    /**
     * Broadcasts a message to every connected client.
     * @param message, the message the send.
     */
    public static void broadcastMessage(String message) throws IOException{
        //Iterate over each connection

        for(Socket connection : allConnections){
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.println(message);
            out.flush();
        }
    }
}
