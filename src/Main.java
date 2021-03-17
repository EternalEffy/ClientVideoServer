import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(ClientMessages.MESSAGE_SET_NAME);
        Client client = new Client(new Scanner(System.in).nextLine());
        client.setPort(3310);
        client.loadClient();
        Scanner console = new Scanner(System.in);

    client.request(Requests.add,"{\"user name\":\""+console.nextLine()+"\",\"age\":\""+console.nextLine()+"\"," +
            "\"score\":\""+console.nextLine()+"\",\"level\":\""+console.nextLine()+"\"}","0");

    client.request(Requests.add,"{\"user name\":\""+"Linda"+"\",\"age\":\""+"19"+"\"," +
            "\"score\":\""+"120"+"\",\"level\":\""+"12"+"\"}","1");

    client.request(Requests.get,null,"0");

    client.request(Requests.edit,"{\"user name\":\""+"Linda"+"\",\"age\":\""+"19"+"\"," +
            "\"score\":\""+"120"+"\",\"level\":\""+"12"+"\"}","0");
    client.request(Requests.get,null,"0");

    client.request(Requests.remove,null,"1");

    client.close();

    }
}
