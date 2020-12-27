package plugin.asyncHttp;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class HttpEnum {

    @Getter
    @AllArgsConstructor
    public enum Method {

        PUT("PUT", "PUT"),
        POST("POST", "POST"),
        DELETE("DELETE", "DELETE"),
        GET("GET", "GET"),
        ;

        private String code;
        private String msg;
    }
}
