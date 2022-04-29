package com.ydata.herokuApp.web.potal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydata.herokuApp.web.potal.domain.MonthPriceDto;
import com.ydata.herokuApp.web.potal.domain.SiteBasDto;
import com.ydata.herokuApp.web.potal.service.MonthPriceService;

/**
 * <pre>
 * 메인 대시보드 컨트롤러
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.04.08.    scshin      최초 생성
 * </pre>
 *
 * @author scshin <scshin@ydata.co.kr>
 * @since 2020.04.08.
 * @version 1.0
 */
@RequestMapping("/dashboard")
@Controller
public class DashboardController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
    protected String viewPrefix = "/dashboard";
    
    @Autowired MonthPriceService service;

    /**
     * 
     * <pre>
     * 메인 대시보드
     * </pre>
     *
     * @method dashboard
     * @param request
     * @param response
     * @param model
     * @return
     */
    @GetMapping(path = "/dashboard.do")
    public String dashboard(HttpServletRequest request, HttpServletResponse response, Model model) {

        return viewPrefix + "/dashboard";
    }

    /**
     * <pre>
     * Dashboard 데이터 - 장비 설치일정
     * </pre>
     *
     * @method dashboardData
     * @param request
     * @param paramMap
     * @return
     */
    @PostMapping(path = "/getList.do")
    @ResponseBody
    public Map<String, Object> vndrInstallSchList(HttpServletRequest request,
            @RequestParam Map<String, Object> paramMap) {

        logger.info("url : /dashboard/vndrInstallSchList.do");

        Map<String, Object> res = new HashMap<>();

        try {

            List<MonthPriceDto> list = service.getAll();
            
            logger.info("get list =================>" + list);
            
            res.put("dataList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

}
