package com.ydata.herokuApp.web.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * 공통 에러 처리
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
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired(required = false)
    MessageSourceAccessor messageSourceAccessor;

    private String errorVeiw = "/error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        int errorReason = 500;
        String errTitle = "Server Error";
        String errText = "An internal server error occurred";
        if (ex instanceof UnauthenticationException) {
            ModelAndView mav = new ModelAndView("redirect:/login/login.do");
            return mav;
        }
        ModelAndView mav = new ModelAndView(errorVeiw);
        if (messageSourceAccessor != null && ex instanceof AppMessageException) {
            String code = ((AppMessageException) ex).getErrorCode();
            Object[] args = ((AppMessageException) ex).getErrorArgs();
            if (StringUtils.isBlank(code)) {
                try {
                    errText = messageSourceAccessor.getMessage(code, args);
                } catch (NoSuchMessageException e) {
                    logger.warn(code, e);
                }

            } else {
                errText = ex.getMessage();
            }
            if (StringUtils.isBlank(errText)) {
                errText = ex.getMessage();
            }
        } else {
            errText = ex.getMessage();
        }
        mav.addObject("errorReason", errorReason);
        mav.addObject("errorTitle", errTitle);
        mav.addObject("errorText", errText);

        return mav;
    }

}
