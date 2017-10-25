package lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Класс, наследуемый от класса HttpRequest
 * Читает запрос, после прочтения заголовка, строит запрос с помощью HttpRequest.Сreate
 */
public class HttpRequestRead extends HttpRequest {
    private Socket socket;
    private InputStream is;
    private String request;

    public HttpRequestRead(Socket socket) {
        super();
        this.socket = socket;
        try {
            ConnectToInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ConnectToInputStream() throws IOException{
        is = socket.getInputStream();
        ReadRequest();
    }

    private void ReadRequest() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        while (true) {
            request = bufferedReader.readLine();
            Create(request);
            if (request == null || request.trim().length() == 0) {
                break;
            }
        }
    }
}
