package learn.tassel.community.Redis;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TestIPPort {
    public static void main(String[] args){
        Socket connect = new Socket();
        try {
            connect.connect(new InetSocketAddress("192.168.30.128", 6379),100);
            boolean res = connect.isConnected();
            System.out.println("" + res);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                connect.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
