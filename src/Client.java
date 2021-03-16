import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private static Socket client;
    private static DataInputStream inStream;
    private static DataOutputStream outStream;
    private static String name = "Alex";

    public void loadClient(){
        try {
            client = new Socket("localhost", 3320);
            inStream= new DataInputStream(client.getInputStream());
            outStream=new DataOutputStream(client.getOutputStream());

            outStream.writeUTF(name);
            outStream.flush();
            System.out.println(inStream.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void request(String request,String user,String index){
        switch (request){
            case Requests.add:
                add(request,user,index);
                break;
            case Requests.edit:
                edit(request,index,user);
                break;
            case Requests.get:
                get(request,index);
                break;
            case  Requests.remove:
                remove(request,index);

        }
    }

    private void add(String request,String user, String index){
        try {
            System.out.println("Отправляю запрос на добалвение пользователя");
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(user);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            System.out.println(inStream.readUTF());
        } catch (IOException e) {
            System.out.println("Не удалось добавить пользователя");
        }
    }

    private void get(String request,String index){
        try {
            System.out.println("Отправляю запрос на получение данных о пользователе с индексом: "+index);
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            System.out.println(inStream.readUTF());
        } catch (IOException e) {
            System.out.println("Не удалось получить данные");
        }
    }

    private void edit(String request,String index,String user){
        try {
            System.out.println("Отправляю запрос на редактирование пользователя с индексом: "+ index);
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            outStream.writeUTF(user);
            outStream.flush();
            System.out.println(inStream.readUTF());

        } catch (IOException e) {
            System.out.println("Не удалось отредактировать пользователя");
        }

    }

    private void remove(String request,String index){
        try {
            System.out.println("Отправляю запрос на удаление данных о пользователе с индексом: "+index);
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            System.out.println(inStream.readUTF());
        } catch (IOException e) {
            System.out.println("Не удалось удалить данные");
        }

    }
}
