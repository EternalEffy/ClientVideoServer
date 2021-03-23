import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client {
    private static Socket client;
    private static DataInputStream inStream;
    private static DataOutputStream outStream;
    private static String name;

    public Client(String name) {
        this.name = name;
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

    public void loadClient(int port){
        try {
            client = new Socket("37.77.106.12", port);//37.77.106.12
            inStream= new DataInputStream(client.getInputStream());
            outStream=new DataOutputStream(client.getOutputStream());

            outStream.writeUTF(name);
            outStream.flush();
            System.out.println(inStream.readUTF());

        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_LOAD_ERROR);
        }
    }

    public void request(String requestCode,String request){
        switch (requestCode){
            case Requests.getFile:
                getFile(request);
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

    private void getFile(String request) {
        try {
            outStream.writeUTF(request);
            outStream.flush();
            JSONObject json = new JSONObject(inStream.readUTF());
            if (json.getString("request").equals("OK")) {
                System.out.println(json.getString("request"));
                Files.createFile(Paths.get("Slovenya.mp4"));
                File f = new File("Slovenya.mp4");
                FileOutputStream w = new FileOutputStream(f);
                BufferedOutputStream bos = new BufferedOutputStream(w);
                byte[] buffer = new byte[json.getInt("file")];
                inStream.readFully(buffer, 0, buffer.length);
                bos.write(buffer);
                bos.flush();
            }
            else System.out.println(json.getString("request"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
