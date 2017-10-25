package lab2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Отправляет уже созданный ответ в классе HttpResponse
 */
public class HttpResponseWrite {
    private OutputStream os;
    private Socket socket;
    private String response;

    public HttpResponseWrite(Socket socket, HttpResponse httpResponse) {
        this.socket = socket;
        this.response = httpResponse.getResponse();
        System.out.println(response);
        ConnectToOutputStream();
    }

    private void ConnectToOutputStream() {
        try {
            os = socket.getOutputStream();
        } catch (Throwable t) {
            System.out.println("Не удалось получить поток вывода");
        }
    }

    public void WriteResponse() {
        try {
            os.write(response.getBytes());
        } catch (IOException e) {
            System.out.println("Не удалось отправить ответ");
        }
    }
}
