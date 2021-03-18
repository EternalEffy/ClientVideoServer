import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       System.out.println(ClientMessages.MESSAGE_SET_NAME);
        Client client = new Client(new Scanner(System.in).nextLine());
        client.setPort(3310);
        client.loadClient();
        //Scanner console = new Scanner(System.in);

    /*client.request(Requests.add,"{\"user name\":\""+console.nextLine()+"\",\"age\":\""+console.nextLine()+"\"," +
            "\"score\":\""+console.nextLine()+"\",\"level\":\""+console.nextLine()+"\"}","0");

    client.request(Requests.add,"{\"user name\":\""+"Linda"+"\",\"age\":\""+"19"+"\"," +
            "\"score\":\""+"120"+"\",\"level\":\""+"12"+"\"}","1");

    client.request(Requests.get,null,"0");

    client.request(Requests.edit,"{\"user name\":\""+"Linda"+"\",\"age\":\""+"19"+"\"," +
            "\"score\":\""+"120"+"\",\"level\":\""+"12"+"\"}","0");
    client.request(Requests.get,null,"0");

    client.request(Requests.remove,null,"1");

     */
        client.request(Requests.add,"{\"request\":\""+Requests.add+"\"," +
                "\"userData\":"+"[\"Linda\",\"21\",\"58\",\"10\"]}","0");
        client.request(Requests.add,"{\"request\":\""+Requests.add+"\"," +
                "\"userData\":"+"[\"Max\",\"18\",\"120\",\"15\"]}","1");
        client.request(Requests.edit,"{\"request\":\""+Requests.edit+"\"," +
                "\"userData\":"+"[\"Alex\",\"100\",\"100\",\"200\"]}","0");
        client.request(Requests.get,"{\"request\":\""+Requests.get+"\"," +
                "\"userData\":"+"[\"Linda\",\"21\",\"58\",\"10\"]}","0");
        client.request(Requests.remove,"{\"request\":\""+Requests.remove+"\"," +
                "\"userData\":"+"[\"Linda\",\"21\",\"58\",\"10\"]}","1");
    client.close();

    }
}
