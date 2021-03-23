import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       System.out.println(ClientMessages.MESSAGE_SET_NAME);
        Client client = new Client(new Scanner(System.in).nextLine());
        client.loadClient(3311);

        /*client.request(Requests.getFile,"{\"request\":\""+Requests.getFile+"\"," +
                        "\"name\":\"data_weapon.zip\"}");
                        client.request(Requests.stop,"{\"request\":\""+Requests.stop+"\"," +
                "\"name\":\"data_weapon.zip\"}");
                client.request(Requests.stop,"{\"request\":\""+Requests.stop+"\"," +
                "\"name\":\"111.txt\"}");

         */
        client.request(Requests.getFile,"{\"request\":\""+Requests.getFile+"\"," +
                "\"name\":\"Slovenya.mp4\"}");

        client.close();

    }
}
