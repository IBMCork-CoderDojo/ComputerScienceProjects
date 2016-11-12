public class Client{
    public static void main(String[] args) throws IOException{
        Socket s = new Socket("localhost", 1337);
        BufferedReader input =
                new BufferedReader(new InputStreamReader(s.getInputStream()));
        String response = input.readLine();
        System.out.println(response);
    }
}
