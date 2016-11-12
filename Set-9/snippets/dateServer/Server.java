public class Server{
    public static void main(String[] args) throws IOException{
        ServerSocket listener = new ServerSocket(1337);
        try{
            while(true){
                Socket socket = listener.accept();
                try{
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    out.println(new Date().toString());
                    out.flush();
                }finally{
                    socket.close();
                }
            }
        }finally{
            listener.close();
        }
    }
}
