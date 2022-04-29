package com.ydata.herokuApp.web.common.exception;

/**
 * <pre>
 * AppMessageException
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

public class AppMessageException extends AppRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 970416945069959311L;

    private String errorCode;
    private Object[] errorArgs;

    public AppMessageException() {
        super();
    }

    public AppMessageException(String msg) {
        super(msg);
    }

    public AppMessageException(String msg, Throwable t) {
        super(msg, t);
    }

    public AppMessageException(Throwable t) {
        super(t);
    }

    public AppMessageException(String errorCode, String defaultMessage) {
        this(defaultMessage);
        this.errorCode = errorCode;
        this.errorArgs = null;
    }

    public AppMessageException(String errorCode, Object[] errorArgs, String defaultMessage) {
        this(defaultMessage);
        this.errorCode = errorCode;
        this.errorArgs = errorArgs;
    }

    public AppMessageException(String errorCode, Object[] errorArgs, String defaultMessage, Throwable t) {
        this(defaultMessage, t);
        this.errorCode = errorCode;
        this.errorArgs = errorArgs;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object[] getErrorArgs() {
        return errorArgs;
    }

    public void setErrorArgs(Object[] errorArgs) {
        this.errorArgs = errorArgs;
    }
}
