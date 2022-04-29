package com.ydata.herokuApp.web.common.web.service.impl;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.ydata.herokuApp.web.common.RestCallConstrains;
import com.ydata.herokuApp.web.common.web.domain.GroupCodeDto;
import com.ydata.herokuApp.web.common.web.service.CodeService;
import com.ydata.herokuApp.web.potal.domain.CodeDto;
import com.ydata.herokuApp.web.potal.form.SearchForm;
import com.ydata.herokuApp.web.potal.service.RestTemplateService;

/**
 * <pre>
 * 공통코드 조회 SERVICE 구현체
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

@Service("codeService")
public class CodeServiceImpl implements CodeService {

    private static List<GroupCodeDto> gcodeGroup = new ArrayList<>();
    private static List<CodeDto> gcodeDet = new ArrayList<>();

    @Autowired
    RestTemplateService restTemplateService;

    @Autowired
    ServletContext servletContext;

    @Override
    public int setCodeListAll() {

        int res = 0;

        // 1. 코드 그룹 가져오기
        try {

            /* 현재 전체 조회하는 api가 없어서 무작위로 하나 조회후 totalCount를 얻어와 그 갯수만큼 limit 세팅해서 다시 조회 */
            SearchForm param = new SearchForm();
            param.setLimit(1);
            param.setStartRow(0);

            Map<String, Object> apires = restTemplateService.getResponseEntity(RestCallConstrains.GROUPCODELIST_API_URL,
                    param);

            JSONObject temp = (JSONObject) apires.get("result");
            int totalCnt = Integer.parseInt(temp.get("totalCnt").toString());

            param.setLimit(totalCnt);
            apires = restTemplateService.getResponseEntity(RestCallConstrains.GROUPCODELIST_API_URL, param);

            JSONObject result = (JSONObject) apires.get("result");

            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new StringReader(result.get("resultList").toString()));
            reader.setLenient(true);
            Type type = new TypeToken<List<GroupCodeDto>>() {
            }.getType();
            gcodeGroup = new ArrayList<>();
            gcodeGroup = gson.fromJson(reader, type);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

        // 2. 코드 가져오기
        try {

            /* 현재 전체 조회하는 api가 없어서 무작위로 하나 조회후 totalCount를 얻어와 그 갯수만큼 limit 세팅해서 다시 조회 */
            SearchForm param = new SearchForm();
            param.setLimit(1);
            param.setStartRow(0);

            Map<String, Object> apires = restTemplateService.getResponseEntity(RestCallConstrains.CODELIST_API_URL,
                    param);

            JSONObject temp = (JSONObject) apires.get("result");
            int totalCnt = Integer.parseInt(temp.get("totalCnt").toString());

            param.setLimit(totalCnt);
            apires = restTemplateService.getResponseEntity(RestCallConstrains.CODELIST_API_URL, param);

            JSONObject result = (JSONObject) apires.get("result");

            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new StringReader(result.get("resultList").toString()));
            reader.setLenient(true);
            Type type = new TypeToken<List<CodeDto>>() {
            }.getType();
            gcodeDet = new ArrayList<>();
            gcodeDet = gson.fromJson(reader, type);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

        return res;

    }

    @Override
    public List<GroupCodeDto> getCodeGroupList() {
        List<GroupCodeDto> res = new ArrayList<>();

        // 1. 코드 그룹 가져오기
        try {

            SearchForm param = new SearchForm();
            param.setStartRow(0);
            param.setLimit(999);

            Map<String, Object> apires = restTemplateService.getResponseEntity(RestCallConstrains.GROUPCODELIST_API_URL,
                    param);

            JSONObject result = (JSONObject) apires.get("result");

            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new StringReader(result.get("resultList").toString()));
            reader.setLenient(true);
            Type type = new TypeToken<List<GroupCodeDto>>() {
            }.getType();
            res = gson.fromJson(reader, type);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

        return res;
    }

    @Override
    public List<CodeDto> getCodeList(String codeGroup) {
        ArrayList<CodeDto> res = new ArrayList<CodeDto>();

        try {

            Map<String, Object> param = new HashMap<>();

            param.put("startRow", 0);
            param.put("limit", 999);
            param.put("groupComnCd", codeGroup);

            Map<String, Object> apires = restTemplateService.getResponseEntity(RestCallConstrains.CODELIST_API_URL,
                    param);

            JSONObject result = (JSONObject) apires.get("result");

            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new StringReader(result.get("resultList").toString()));
            reader.setLenient(true);
            Type type = new TypeToken<List<CodeDto>>() {
            }.getType();
            res = gson.fromJson(reader, type);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

        return res;
    }

    @Override
    public CodeDto getCode(String codeGroup, String code) {

        CodeDto dto = new CodeDto();
        ArrayList<CodeDto> res = new ArrayList<CodeDto>();
        try {

            Map<String, Object> param = new HashMap<>();

            param.put("startRow", 0);
            param.put("limit", 999);
            param.put("groupComnCd", codeGroup);

            Map<String, Object> apires = restTemplateService.getResponseEntity(RestCallConstrains.CODELIST_API_URL,
                    param);

            JSONObject result = (JSONObject) apires.get("result");

            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new StringReader(result.get("resultList").toString()));
            reader.setLenient(true);
            Type type = new TypeToken<List<CodeDto>>() {
            }.getType();
            res = gson.fromJson(reader, type);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

        if (res.size() > 0) {

            for (CodeDto bean : res) {

                if (StringUtils.equals(codeGroup, bean.getGroupComnCd())
                        && StringUtils.equals(code, bean.getComnCd())) {
                    dto = bean;
                    break;
                }
            }
        }

        return dto;
    }

}