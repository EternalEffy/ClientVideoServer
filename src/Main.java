public class Main {
    public static void main(String[] args) {
    Client client = new Client();
    client.loadClient();
    client.request(Requests.add,"{\"user name\":\""+"Alex"+"\",\"age\":\""+"22"+"\"," +
            "\"score\":\""+"100"+"\",\"level\":\""+"10"+"\"}","0");

    client.request(Requests.add,"{\"user name\":\""+"Linda"+"\",\"age\":\""+"19"+"\"," +
            "\"score\":\""+"120"+"\",\"level\":\""+"12"+"\"}","1");

    client.request(Requests.get,null,"0");

    client.request(Requests.edit,"{\"user name\":\""+"Linda"+"\",\"age\":\""+"19"+"\"," +
            "\"score\":\""+"120"+"\",\"level\":\""+"12"+"\"}","0");
    client.request(Requests.get,null,"0");

    client.request(Requests.remove,null,"1");

    }
}
