package lab2;

import java.util.HashMap;
import java.util.Map;

/**
 * класс, который строит запрос по типу (ключ, значение) по уже принятому запросу
 */
public class HttpRequest {
    public final Map request;

    public static enum RequestStrings {
        REQUEST_LINE,
        HOST,
        CONNECTION,
        CACHE_CONTROL,
        ACCEPT,
        USER_AGENT,
        REFERER,
        ACCEPT_ENCODING,
        ACCEPT_LANGUAGE,
        CLEAR_STRING,
        ENTITY_BODY
    }

    public HttpRequest() {
        request = new HashMap<String, String>();
    }

    protected void Create(String request) {

        if (request.contains("GET ") || request.contains("POST ")) {
            this.request.put(RequestStrings.REQUEST_LINE.name(), request);
        } else if (request.contains("Host: ")) {
            this.request.put(RequestStrings.HOST.name(), request);
        } else if (request.contains("Connection: ")) {
            this.request.put(RequestStrings.CONNECTION.name(), request);
        } else if (request.contains("Cache-Control: ")) {
            this.request.put(RequestStrings.CACHE_CONTROL.name(), request);
        } else if (request.contains("Accept: ")) {
            this.request.put(RequestStrings.ACCEPT.name(), request);
        } else if (request.contains("User-Agent: ")) {
            this.request.put(RequestStrings.USER_AGENT.name(), request);
        } else if (request.contains("Referer: ")) {
            this.request.put(RequestStrings.REFERER.name(), request);
        } else if (request.contains("Accept-Encoding: ")) {
            this.request.put(RequestStrings.ACCEPT_ENCODING.name(), request);
        } else if (request.contains("Accept-Language: ")) {
            this.request.put(RequestStrings.ACCEPT_LANGUAGE.name(), request);
        }
    }
}
