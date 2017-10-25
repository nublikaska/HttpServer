package lab2;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    /**
     * Создает сервер
     **/
    public static void main(String[] args){ /*Точка входа в программу */
        HttpServer httpServer = null;
        httpServer = new HttpServer(8080);
        httpServer.start();
    }
}
