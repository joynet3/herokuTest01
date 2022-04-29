package com.ydata.herokuApp.web.potal.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ydata.herokuApp.web.common.util.RestTemplateUtil;
import com.ydata.herokuApp.web.potal.service.RestTemplateService;

/**
 * <pre>
 * API 호출 Service Impl
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.06.15.    scshin      최초 생성
 * </pre>
 *
 * @author scshin <scshin@ydata.co.kr>
 * @since 2020.06.15.
 * @version 1.0
 */
@Service("RestTemplateService")
public class RestTemplateServiceImpl implements RestTemplateService {
    @Override
    public Map<String, Object> getResponseEntity(String sendUrl, Object param) {
        return RestTemplateUtil.getResponseEntity(sendUrl, param);
    }

    @Override
    public Map<String, Object> getResponseEntityPath(String sendUrl, Object param, Map<String, Object> valueParam) {
        return RestTemplateUtil.getResponseEntityPath(sendUrl, param, valueParam);
    }

}