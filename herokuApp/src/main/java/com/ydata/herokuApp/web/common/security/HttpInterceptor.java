package com.ydata.herokuApp.web.common.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <pre>
 * 공통 인터셉터 처리
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

@Component
@Qualifier("httpInterceptor")
public class HttpInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        logger.info("preHandle >>>>>>>>>>>>>>>>>>>>>>>>>>" + request.getRequestURI());
        HttpSession session = request.getSession();
        String _requestUrl = request.getRequestURI();

        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

//        if (isAjax) {
//            return true;
//        }

//        if (session.getAttribute("API_TOKEN") == null || session.getAttribute("USER_INFO") == null) {
//            // 로그인 전
//            if (_requestUrl != null) {
////                List<String> excludeList = new ArrayList<String>();
////                excludeList.add("/login/login.do");
////                excludeList.add("/login/loginProc.do");
////                excludeList.add("/authErr.do");
////                if (!excludeList.contains(_requestUrl)) {
////                    response.sendRedirect("/login/login.do");
////                    return false;
////                }
//                response.sendRedirect("/login/login.do");
//                return false;
//
//            }
//
//        } else {
//            // 로그인 후
//            if (_requestUrl != null && _requestUrl.indexOf("login.do") > -1) {
//                response.sendRedirect("/dashboard/dashboard.do");
//            }
//
//        }

        return true;
    }

    /*
     * @Override public void postHandle(HttpServletRequest request,
     * HttpServletResponse response, Object handler, ModelAndView modelAndView)
     * throws Exception { logger.info("Interceptor > postHandle"); }
     * 
     * @Override public void afterCompletion(HttpServletRequest request,
     * HttpServletResponse response, Object object, Exception arg3) throws Exception
     * { logger.info("Interceptor > afterCompletion"); }
     */

}