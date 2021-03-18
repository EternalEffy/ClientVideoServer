import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private static Socket client;
    private static DataInputStream inStream;
    private static DataOutputStream outStream;
    private static String name;

    public Client(String name) {
        this.name = name;
    }

    public void setPort(int port){
        try {
            client = new Socket("localhost", port);
        } catch (IOException e) {
            setPort(port+1);
        }
    }

    public void close(){
        try {
            client.close();
            inStream.close();
            outStream.close();
        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_CLOSE_ERROR);
        }
    }

    public void loadClient(){
        try {
            inStream= new DataInputStream(client.getInputStream());
            outStream=new DataOutputStream(client.getOutputStream());

            outStream.writeUTF(name);
            outStream.flush();
            System.out.println(inStream.readUTF());

        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_LOAD_ERROR);
        }
    }

    public void request(String requestCode,String request,String index){
        switch (requestCode){
            case Requests.add:
                add(request,index);
                break;
            case Requests.edit:
                edit(request,index);
                break;
            case Requests.get:
                get(request,index);
                break;
            case  Requests.remove:
                remove(request,index);
            default:
                try {
                    outStream.writeUTF(request);
                    outStream.flush();
                } catch (IOException e) {
                    try {
                        System.out.println(inStream.readUTF());
                    } catch (IOException ioException) {
                        System.out.println(ClientMessages.MESSAGE_ERROR);
                    }
                }
        }
    }

    private void add(String request,String index){
        try {
            System.out.println(ClientMessages.MESSAGE_ADD);
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            System.out.println(inStream.readUTF());
        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_ADD_ERROR);
        }
    }

    private void get(String request,String index){
        try {
            System.out.println(ClientMessages.MESSAGE_GET+index);
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            System.out.println(inStream.readUTF());
        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_GET_ERROR);
        }
    }

    private void edit(String request,String index){
        try {
            System.out.println(ClientMessages.MESSAGE_EDIT+ index);
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            System.out.println(inStream.readUTF());

        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_EDIT_ERROR);
        }

    }

    private void remove(String request,String index){
        try {
            System.out.println(ClientMessages.MESSAGE_REMOVE+index);
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            System.out.println(inStream.readUTF());
        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_REMOVE_ERROR);
        }

    }


}
