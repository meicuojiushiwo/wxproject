package exception;

import lombok.Data;

/**
 * 异常信息类
 */
@Data
public class Error {

    private String code;
    private String msg;
}
