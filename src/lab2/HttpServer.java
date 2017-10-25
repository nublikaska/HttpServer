package lab2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by User245.group on 23.10.2017.
 */

public class HttpServer {

    private int port;
    private int blackLog;
    private InetAddress inetAddress;

    public HttpServer(int port/*, int blakLog, InetAddress inetAddress*/) {
        this.port = port;
        this.blackLog = blackLog;
        this.inetAddress = inetAddress;
    }

    /**
     * Ожидает подключения клиента
     * Создает для каждого клиента свой поток
     **/
    public void start() {


        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Http-server создан");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new HttpClient(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
