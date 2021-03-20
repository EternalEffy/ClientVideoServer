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
            client = new Socket("localhost", port);
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
            case Requests.getVideo:
                getVideo(request);
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

    private void getVideo(String request) {
        try {
            outStream.writeUTF(request);
            outStream.flush();
            JSONObject json = new JSONObject(inStream.readUTF());
            System.out.println(json.getString("request"));
            Files.createFile(Paths.get("pornhub.mp4"));
            File f = new File("pornhub.mp4");
            int count,total=0;
            FileOutputStream w = new FileOutputStream(f);
            byte[] buffer = new byte[json.getInt("video")*1024];
            while ((count = inStream.read(buffer)) > 0) {
                total += count;
                w.write(buffer, 0, count);
                if(total == count){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
