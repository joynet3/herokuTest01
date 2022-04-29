package com.ydata.herokuApp.web.potal.service;

import java.util.Map;

/**
 * <pre>
 * API 호출 Service
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
public interface RestTemplateService {

    public Map<String, Object> getResponseEntity(String sendUrl, Object param);

    public Map<String, Object> getResponseEntityPath(String sendUrl, Object param, Map<String, Object> valueParam);

}