package lab2;

/**
 * Класс, реализующий паттер билдер
 * Содержит в себе класс, который строит объект класса HttpResponse
 */
public class HttpResponse {

    //required
    private final String response;

    //optional
    private final String CLEAR_STRING = "";
    //responseLine
    //server
    //date
    //connection
    //cacheControl
    //contentLength
    //contentType
    //contentEncoding

    private HttpResponse(Builder builder) {
        response = builder.response
                + "\r\n"  + CLEAR_STRING
                + "\r\n" + builder.entityBody;
    }

    public String getResponse() {
        return response;

    }

    public static class Builder {
        private String response; //required
        private final String entityBody; //required

        public Builder(String responseLine, String entityBody) {
            this.response = responseLine;
            this.entityBody = entityBody;
        }

        public Builder setEntityBody(String entityBody) {
            this.response += "\r\n" + entityBody;
            return this;
        }

        public Builder setServer(String server) {
            this.response += "\r\nServer: " + server;
            return this;
        }

        public Builder setDate(String date) {
            this.response += "\r\nDate: " + date;
            return this;
        }

        public Builder setConnection(String connection) {
            this.response += "\r\nConnection: " + connection;
            return this;
        }

        public Builder setCacheControl(String cacheControl) {
            this.response += "\r\nCache-Control: " + cacheControl;
            return this;
        }

        public Builder setContentLenght(String contentLenght) {
            this.response += "\r\nContent-Length: " + contentLenght;
            return this;
        }

        public Builder setContentType(String contentType) {
            this.response += "\r\nContent-Type: " + contentType;
            return this;
        }

        public Builder setContentEncoding(String contentEncoding) {
            this.response += "\r\nContent-Encoding: " + contentEncoding;
            return this;
        }

        public HttpResponse Build() {
            return new HttpResponse(this);
        }
    }
}
