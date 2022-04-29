package com.ydata.herokuApp.web.common.security;

import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 세션 리스너 인터셉터
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

@WebListener
public class SessionListener implements HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);
    private HttpSession session = null;
    static private int activeSessions = 0;

    public static int getActiveSessions() {
        return activeSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        session = se.getSession();
        session.setMaxInactiveInterval(60 * 60); // 세션만료60분
        activeSessions++;
        logger.debug("SessionCnt:" + activeSessions
                + " Session ID ".concat(se.getSession().getId()).concat(" created at ").concat(new Date().toString()));

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        activeSessions--;
        logger.debug("session sessionDestroyed.. ");
        logger.debug("SessionCnt:" + activeSessions
                + " Session ID ".concat(se.getSession().getId()).concat(" created at ").concat(new Date().toString()));
    }

}
