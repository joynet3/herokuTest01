package com.ydata.herokuApp.web.common.exception;

/**
 * <pre>
 * AppRuntimeException
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

public class AppRuntimeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 2766114413347437053L;

    public AppRuntimeException() {
    }

    public AppRuntimeException(String msg) {
        super(msg);
    }

    public AppRuntimeException(String msg, Throwable t) {
        super(msg, t);
    }

    public AppRuntimeException(Throwable t) {
        super(t);
    }

}
