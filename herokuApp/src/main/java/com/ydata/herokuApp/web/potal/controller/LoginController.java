package com.ydata.herokuApp.web.potal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ydata.herokuApp.web.common.RestCallConstrains;

/**
 * <pre>
 * 설명
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.04.07.    조일근      최초 생성
 * </pre>
 *
 * @author ikcho <joynet9478@gmail.com>
 * @since 2020.04.07.
 * @version 1.0
 */
@RequestMapping("/login")
@Controller
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    protected String viewPrefix = "/login";

    /**
     * <pre>
     * 로그인 화면 호출
     * </pre>
     *
     * @method login
     * @param request
     * @param response
     * @param model
     * @return
     */
    @GetMapping(path = "/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {

        return viewPrefix + "/login";
    }

    /**
     * <pre>
     * 로그인 프로세스
     * </pre>
     *
     * @method loginProc
     * @param request
     * @param response
     * @param model
     * @param userId
     * @param userPwd
     * @param redirectAttributes
     * @return
     */
    @PostMapping(path = "/loginProc.do")
    public String loginProc(HttpServletRequest request, HttpServletResponse response, Model model, String userId,
            String userPwd, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        try {
//            Map<String, Object> result = restTemplateService.requestLoginApi(userId, userPwd);
//
//            String tokenKey = "";
//            if (result.get("errCode").equals("00") && result.get("access_token") != null) {
//                tokenKey = result.get("access_token").toString();
//
//                session.setAttribute("API_TOKEN", tokenKey);
//
//                Map<String, Object> valueParam = new HashMap<String, Object>();
//
//                Map<String, Object> apires = restTemplateService.getResponseEntityPath(RestCallConstrains.test_url,
//                        null, valueParam);
//
//                System.out.println("asdasdasdasda");
//                System.out.println(apires);
//
//            } else {
//                redirectAttributes.addFlashAttribute("loginError", "Y");
//                redirectAttributes.addFlashAttribute("errMsg", result.get("errMsg"));
//            }

        } catch (Exception e) {
            logger.debug("로그인 실패");
            redirectAttributes.addFlashAttribute("loginError", "Y");
            e.printStackTrace();
        }

        return "redirect:/dashboard/dashboard.do";
    }

    /**
     * <pre>
     * 로그아웃 프로세스
     * </pre>
     *
     * @method logout
     * @param request
     * @param response
     * @param model
     * @return
     */
    @GetMapping(path = "/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {

        /* API Token 삭제 (현재 서버 NullPointerException 동작안됨) */
        Map<String, Object> form = new HashMap<>();
        Map<String, Object> apires = restTemplateService.getResponseEntity(RestCallConstrains.LOGOUT_API_URL, form);

        /* session 삭제 */
        HttpSession session = request.getSession(false);
        session.removeAttribute("API_TOKEN");
        session.removeAttribute("USER_INFO");
        session.invalidate();
        return "redirect:/login/login.do";
    }

}
