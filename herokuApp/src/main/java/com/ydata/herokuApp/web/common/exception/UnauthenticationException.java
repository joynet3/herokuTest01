package com.ydata.herokuApp.web.common.exception;

/**
 * <pre>
 * 권한 에러 메세지 처리
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.04.15.    조일근      최초 생성
 * </pre>
 *
 * @author ikcho <joynet9478@gmail.com>
 * @since 2020.04.15.
 * @version 1.0
 */
public class UnauthenticationException extends AppRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -6311186194810022179L;

    public UnauthenticationException() {
        super();
    }

    public UnauthenticationException(String msg) {
        super(msg);
    }

    public UnauthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public UnauthenticationException(Throwable t) {
        super(t);
    }

}
