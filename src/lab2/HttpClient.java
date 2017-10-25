package lab2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;

public class HttpClient implements Runnable{

    Socket socket;
    HttpRequest httpRequestRead;
    HttpResponseWrite httpResponseWrite;
    String resultStr;

    public HttpClient(Socket socket) {
        this.socket = socket;
    }

    /**
     * Читает запрос от клиента
     * Если прочтен запрос GET, то отправляется ответ
     **/
    @Override
    public void run() {
        if (RequestRead()) {
            ResponseWrite(new Number15(resultStr).getResult());
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Инициализирует объект класса HttpRequestRead, который читает запрос и кладет заголовки в переменную request(ключ, значение)
     **/
    private boolean RequestRead() {
        String requestGet;
        boolean isGet = false;
        httpRequestRead = new HttpRequestRead(socket);
        if (httpRequestRead.request.containsKey(HttpRequest.RequestStrings.REQUEST_LINE.name())) { // если есть запрос
            if (((String) httpRequestRead.request.get(HttpRequest.RequestStrings.REQUEST_LINE.name())).contains("GET ")) { // если запрос GET
                isGet = true;
                requestGet = (String) httpRequestRead.request.get(HttpRequest.RequestStrings.REQUEST_LINE.name());
                resultStr = requestGet.substring(5, requestGet.length() - 9);
                try {
                    resultStr = URLDecoder.decode(resultStr, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return isGet;
    }

    /**
     * Инициализирует объект класса HttpResponseWrite, который принимает Уже созданный ответ(объект класса HttpResponse)
     **/

    private void ResponseWrite(String result) {
        httpResponseWrite = new HttpResponseWrite(socket, new HttpResponse.Builder("HTTP/1.1 200 OK",
                "<!DOCTYPE html>\r\n" +
                        "<html>\r\n" +
                        "<head>\r\n" +
                        "<title>HTML</title>\r\n" +
                        "\t<meta charset=\"utf-8\">\r\n" +
                        "</head>\r\n" +
                        "<body>\r\n" +
                        result +
                        "<p>\r\n" +
                        "Работу выполнил: Васянович Денис Максимович <br/>\r\n" +
                        "Студент группы: РИ-350002 <br/>\r\n" +
                        "Номер индивидуального задания: 15 <br/>\r\n" +
                        "Текст индивидуального задания: \"Исследовать Последовательность\" <br/>\r\n" +
                        "</p>\r\n" +
                        "</body>\r\n" +
                        "</html>")
                .setConnection("close")
                .setContentType("text/html")
                .Build());
        httpResponseWrite.WriteResponse();
    }
}
