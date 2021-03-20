import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       System.out.println(ClientMessages.MESSAGE_SET_NAME);
        Client client = new Client(new Scanner(System.in).nextLine());
        client.loadClient(3310);

        client.request(Requests.getVideo,"{\"request\":\""+Requests.getVideo+"\"," +
                        "\"name\":\"pornhub.mp4\"}");
        client.close();

    }
}
