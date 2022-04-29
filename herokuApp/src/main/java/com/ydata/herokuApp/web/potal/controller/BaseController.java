package com.ydata.herokuApp.web.potal.controller;

import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ydata.herokuApp.web.common.util.PagingUtil;
import com.ydata.herokuApp.web.common.web.service.CodeService;
import com.ydata.herokuApp.web.potal.service.RestTemplateService;

/**
 * <pre>
 * BASE 컨트롤러 
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
@Controller
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    RestTemplateService restTemplateService;

    @Autowired
    CodeService codeService;

    public void makePaging(Map<String, Object> result, JSONObject pageInfo) {

        int totalCnt = Integer.parseInt(pageInfo.get("totalCnt").toString());
        int limit = Integer.parseInt(pageInfo.get("limit").toString());
        int nowPage = Integer.parseInt(pageInfo.get("nowPage").toString());

        PagingUtil page = new PagingUtil(totalCnt, nowPage, limit);
        result.put("page", page);
    }

}
