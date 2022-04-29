package com.ydata.herokuApp.web.common.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydata.herokuApp.web.common.RestCallConstrains;
import com.ydata.herokuApp.web.common.exception.UnauthenticationException;

/**
 * <pre>
 * rest Api 호출용 함수
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.04.09.    조일근      최초 생성
 * </pre>
 *
 * @author ikcho <joynet9478@gmail.com>
 * @since 2020.04.09.
 * @version 1.0
 */
@Component
public class RestTemplateUtil {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateUtil.class);

    public static String RESTAPI_BASE_URL;

    public static String OAUTH2_CLIENT_ID;

    public static String OAUTH2_CLIENT_PWD;

    @Value("${restapi.base.url}")
    public void setRestAPIBaseURL(String baseUrl) {
        this.RESTAPI_BASE_URL = baseUrl;
    }

    @Value("${security.oauth2.client.client-id}")
    public void setOauth2ClientId(String clientId) {
        this.OAUTH2_CLIENT_ID = clientId;
    }

    @Value("${security.oauth2.client.client-secret}")
    public void setOauth2ClientPed(String clientPwd) {
        this.OAUTH2_CLIENT_PWD = clientPwd;
    }

    private static RestTemplate restTemplate;

    @Autowired
    public RestTemplateUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * <pre>
     * HTTP 메소드 구분
     * </pre>
     *
     * @method callMethod
     * @param methodParam
     * @return
     */
    private static HttpMethod callMethod(String methodParam) {

        HttpMethod callMethod = null;

        switch (methodParam) {
        case "GET":
            callMethod = HttpMethod.GET;
            break;
        case "POST":
            callMethod = HttpMethod.POST;
            break;
        case "PUT":
            callMethod = HttpMethod.PUT;
            break;
        case "DELETE":
            callMethod = HttpMethod.DELETE;
            break;
        default:
            callMethod = HttpMethod.GET;
        }

        return callMethod;
    }

    /**
     * <pre>
     * HttpEntity 설정
     * </pre>
     *
     * @method httpEntity
     * @param tokenKey
     * @param param
     * @return
     */
    private static HttpEntity httpEntity(String tokenKey, Object param) {

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();

        if (!StringUtils.isBlank(tokenKey)) {
            headers.add("Authorization", "Bearer " + tokenKey);
        }

        Charset utf8 = Charset.forName("UTF-8");
        MediaType mediaType = new MediaType("application", "json", utf8);
        headers.setContentType(mediaType);
        headers.set("Connection", "keep-alive");
        headers.set("X-IBM-Client-Id", "4a019ff4-fbfd-4a1a-b464-73f981e22911");
        headers.set("X-IBM-Client-Secret", "M1fP3wL1eM3wW6yD8vH2dW3qF2qO2nY3rT2tA4aG5hF3xH1cI8");
        headers.set("X-forwarded-For", "81.21.91.221");
        headers.set("billAcntId", "NTEyMTA2OTI0NzA5");
        headers.set("entrId", "NTAwMjI4NDYxNzk5");
        headers.set("entrCntcId", "entrCntcId");
        String paramJsonString = "";

        try {
            paramJsonString = objectMapper.writeValueAsString(param);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpEntity requestEntity = new HttpEntity(paramJsonString, headers);

        return requestEntity;
    }

    /**
     * 
     * <pre>
     * 일반 Api 호출
     * </pre>
     *
     * @method getResponseEntity
     * @param _sendUrl
     * @param paramJsonString
     * @param _tokenKey
     * @return
     */
    public static Map<String, Object> getResponseEntity(String sendUrl, Object param) {
        Map<String, Object> result = new HashMap<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        String reqUrl = RESTAPI_BASE_URL + sendUrl.split(":")[0];
        String tokenKey = "";
        String errCode = "00";
        String errMsg = "";

        try {

            if (request != null) {
                HttpSession session = request.getSession();
                tokenKey = session.getAttribute("API_TOKEN") != null ? session.getAttribute("API_TOKEN").toString()
                        : "";
            }

            if (StringUtils.isEmpty(tokenKey)) {

                List<String> excludeList = RestCallConstrains.getTonkenKeyExcludeList();
                // 토큰코드를 조회 해야 하는 url의 경우 에러 처리
                if (!excludeList.contains(sendUrl)) {
                    throw new UnauthenticationException("로그인 후 사용 가능합니다.");
                }
            }

            HttpMethod callMethod = callMethod(sendUrl.split(":")[1]);
            HttpEntity requestEntity = httpEntity(tokenKey, param);

            if (StringUtils.equals("GET", sendUrl.split(":")[1])) {

                ObjectMapper objectMapper = new ObjectMapper();
                String queryParam = objectMapper.writeValueAsString(param);

                Map<String, Object> tempMap = objectMapper.readValue(queryParam,
                        new TypeReference<Map<String, Object>>() {
                        });

                UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(reqUrl);

                for (String mapkey : tempMap.keySet()) {
                    urlBuilder.queryParam(mapkey, tempMap.get(mapkey));
                }

                reqUrl = urlBuilder.build(false).toUriString();
            }

            ResponseEntity<JSONObject> apiRes = restTemplate.exchange(reqUrl, callMethod, requestEntity,
                    JSONObject.class);

            int status = apiRes.getStatusCodeValue();
            if (status == 200) {

                ObjectMapper objectMapper = new ObjectMapper();

                JSONObject obj = apiRes.getBody();
                String resultStr = objectMapper.writeValueAsString(obj.get("result"));
                String reserrCode = objectMapper.writeValueAsString(obj.get("error"));
                String reserrMsg = objectMapper.writeValueAsString(obj.get("error_description"));

                if (StringUtils.isBlank(reserrCode) || "null".equals(reserrCode)) {
                    JSONParser parser = new JSONParser();
                    Object resultObj = parser.parse(resultStr);

                    result.put("result", resultObj);
                } else {
                    errCode = reserrCode;
                    errMsg = reserrMsg;
                }

            } else {

                errCode = "9997";
                errMsg = "response error";
            }

        } catch (UnauthenticationException ue) {
            errCode = "9998";
            errMsg = "로그인 필요";
        } catch (HttpStatusCodeException he) {
            String resposeMessage = he.getResponseBodyAsString();
            JSONParser parser = new JSONParser();
            JSONObject element = null;
            try {
                element = (JSONObject) parser.parse(resposeMessage);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String message = (String) element.get("message");

            errCode = message;
            errMsg = message;

        } catch (Exception e) {
            errCode = "9999";
            errMsg = e.getMessage();
        }
        result.put("errCode", errCode);
        result.put("errMsg", errMsg);

        return result;
    }

    /**
     * <pre>
     * file 다운로드
     * </pre>
     *
     * @method getExcelFile
     * @param sendUrl
     * @param param
     * @return
     */
    public static Map<String, Object> getExcelFile(String sendUrl, Object param) {
        Map<String, Object> result = new HashMap<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        String reqUrl = RESTAPI_BASE_URL + sendUrl.split(":")[0];
        String tokenKey = "";
        String errCode = "00";
        String errMsg = "";

        try {

            if (request != null) {
                HttpSession session = request.getSession();
                tokenKey = session.getAttribute("API_TOKEN") != null ? session.getAttribute("API_TOKEN").toString()
                        : "";
            }

            if (StringUtils.isEmpty(tokenKey)) {

                List<String> excludeList = RestCallConstrains.getTonkenKeyExcludeList();
                // 토큰코드를 조회 해야 하는 url의 경우 에러 처리
                if (!excludeList.contains(sendUrl)) {
                    throw new UnauthenticationException("로그인 후 사용 가능합니다.");
                }
            }

            HttpMethod callMethod = callMethod(sendUrl.split(":")[1]);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept-Encoding", "gzip, deflate, sdch");

            if (!StringUtils.isBlank(tokenKey)) {
                headers.add("Authorization", "Bearer " + tokenKey);
            }

            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> maps = objectMapper.convertValue(param, new TypeReference<Map<String, String>>() {
                });
                parameters.setAll(maps);
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestCallback requestCallback = req -> {
                req.getHeaders().addAll(headers);
                req.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));
                FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
                formHttpMessageConverter.setCharset(Charset.forName("UTF-8"));
                formHttpMessageConverter.write(parameters, MediaType.APPLICATION_FORM_URLENCODED, req);
            };

            ResponseExtractor<byte[]> responseExtractor = response -> {
                InputStream is = response.getBody();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                int nRead;
                byte[] data = new byte[16384];

                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }

                return buffer.toByteArray();
            };

            byte[] resultByte = restTemplate.execute(reqUrl, callMethod, requestCallback, responseExtractor);

            result.put("byteArr", resultByte);

        } catch (UnauthenticationException ue) {
            errCode = "9998";
            errMsg = "로그인 필요";
        } catch (HttpStatusCodeException he) {
            String resposeMessage = he.getResponseBodyAsString();
            JSONParser parser = new JSONParser();
            JSONObject element = null;
            try {
                element = (JSONObject) parser.parse(resposeMessage);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String message = (String) element.get("message");

            errCode = message;
            errMsg = message;

        } catch (Exception e) {
            errCode = "9999";
            errMsg = e.getMessage();
        }
        result.put("errCode", errCode);
        result.put("errMsg", errMsg);

        return result;
    }

    public static Map<String, Object> getFileUpload(String sendUrl, MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        String reqUrl = RESTAPI_BASE_URL + sendUrl.split(":")[0];
        String tokenKey = "";
        String errCode = "00";
        String errMsg = "";

        try {

            if (request != null) {
                HttpSession session = request.getSession();
                tokenKey = session.getAttribute("API_TOKEN") != null ? session.getAttribute("API_TOKEN").toString()
                        : "";
            }

            if (StringUtils.isEmpty(tokenKey)) {

                List<String> excludeList = RestCallConstrains.getTonkenKeyExcludeList();
                // 토큰코드를 조회 해야 하는 url의 경우 에러 처리
                if (!excludeList.contains(sendUrl)) {
                    throw new UnauthenticationException("로그인 후 사용 가능합니다.");
                }
            }

            HttpMethod callMethod = callMethod(sendUrl.split(":")[1]);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            if (!StringUtils.isBlank(tokenKey)) {
                headers.add("Authorization", "Bearer " + tokenKey);
            }

            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
            parts.add("file", file.getResource());

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(
                    parts, headers);

            ResponseEntity<JSONObject> response = restTemplate.exchange(reqUrl, callMethod, requestEntity,
                    JSONObject.class);

            int status = response.getStatusCodeValue();
            if (status == 200) {

                ObjectMapper objectMapper = new ObjectMapper();

                JSONObject obj = response.getBody();
                String resultStr = objectMapper.writeValueAsString(obj.get("result"));

                if (resultStr.equals("null")) {
                    errCode = "9995";
                    errMsg = objectMapper.writeValueAsString(obj.get("error_description"));

                } else {
                    result.put("result", obj);
                }

            } else {

                errCode = "9997";
                errMsg = "response error";
            }

        } catch (UnauthenticationException ue) {
            errCode = "9998";
            errMsg = "로그인 필요";
        } catch (HttpStatusCodeException he) {
            String resposeMessage = he.getResponseBodyAsString();
            JSONParser parser = new JSONParser();
            JSONObject element = null;
            try {
                element = (JSONObject) parser.parse(resposeMessage);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String message = (String) element.get("message");

            errCode = message;
            errMsg = message;

        } catch (Exception e) {
            errCode = "9999";
            errMsg = e.getMessage();
        }
        result.put("errCode", errCode);
        result.put("errMsg", errMsg);

        return result;
    }

    /**
     * <pre>
     * 일반 Api 호출(Path)
     * </pre>
     *
     * @method getResponseEntityPath
     * @param sendUrl
     * @param param
     * @param valueParam
     * @return
     */
    public static Map<String, Object> getResponseEntityPath(String sendUrl, Object param,
            Map<String, Object> valueParam) {

        Map<String, Object> result = new HashMap<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        String reqUrl = RESTAPI_BASE_URL + sendUrl.split(":")[0];
        String tokenKey = "";
        String errCode = "00";
        String errMsg = "";
        ResponseEntity<JSONObject> apiRes = null;
        try {

            if (request != null) {
                HttpSession session = request.getSession();
                tokenKey = session.getAttribute("API_TOKEN") != null ? session.getAttribute("API_TOKEN").toString()
                        : "";
            }

            if (StringUtils.isEmpty(tokenKey)) {

                List<String> excludeList = RestCallConstrains.getTonkenKeyExcludeList();
                // 토큰코드를 조회 해야 하는 url의 경우 에러 처리
                if (!excludeList.contains(sendUrl)) {
                    throw new UnauthenticationException("로그인 후 사용 가능합니다.");
                }
            }

            HttpMethod callMethod = callMethod(sendUrl.split(":")[1]);
            HttpEntity requestEntity = httpEntity(tokenKey, param);

//            if (StringUtils.equals("GET", sendUrl.split(":")[1])) {
//
//                ObjectMapper objectMapper = new ObjectMapper();
//                String queryParam = objectMapper.writeValueAsString(param);
//
//                Map<String, Object> tempMap = objectMapper.readValue(queryParam,
//                        new TypeReference<Map<String, Object>>() {
//                        });
//
//                UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(reqUrl);
//
//                for (String mapkey : tempMap.keySet()) {
//                    urlBuilder.queryParam(mapkey, tempMap.get(mapkey));
//                }
//
//                reqUrl = urlBuilder.build(false).toUriString();
//            }

            apiRes = restTemplate.exchange(reqUrl, callMethod, requestEntity, JSONObject.class, valueParam);

            int status = apiRes.getStatusCodeValue();
            if (status == 200) {

                ObjectMapper objectMapper = new ObjectMapper();

                JSONObject obj = apiRes.getBody();
                String resultStr = objectMapper.writeValueAsString(obj.get("result"));

                String reserrCode = objectMapper.writeValueAsString(obj.get("error"));
                String reserrMsg = objectMapper.writeValueAsString(obj.get("error_description"));

                if (StringUtils.isBlank(reserrCode) || "null".equals(reserrCode)) {
                    JSONParser parser = new JSONParser();
                    Object resultObj = parser.parse(resultStr);

                    result.put("result", resultObj);
                } else {
                    errCode = reserrCode;
                    errMsg = reserrMsg;
                }

            } else {

                errCode = "9997";
                errMsg = "response error";
            }

        } catch (UnauthenticationException ue) {
            errCode = "9998";
            errMsg = "로그인 필요";
        } catch (HttpStatusCodeException he) {
            String resposeMessage = he.getResponseBodyAsString();
            JSONParser parser = new JSONParser();
            JSONObject element = null;
            try {
                element = (JSONObject) parser.parse(resposeMessage);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String message = (String) element.get("message");

            errCode = message;
            errMsg = message;

        } catch (Exception e) {
            errCode = "9999";
            errMsg = e.getMessage();
        }
        result.put("errCode", errCode);
        result.put("errMsg", errMsg);

        return result;
    }

    /**
     * 
     * <pre>
     * 로그인 Api 호출
     * </pre>
     *
     * @method requestLoginApi
     * @param userName
     * @param passwd
     * @return
     */
    public static Map<String, Object> requestLoginApi(String userName, String passwd) {

        String reqUrl = RESTAPI_BASE_URL + RestCallConstrains.LOGIN_API_URL;
        String clientCredition = OAUTH2_CLIENT_ID + ":" + OAUTH2_CLIENT_PWD;
        String base64ClientCredit = new String(Base64.encodeBase64(clientCredition.getBytes()));
        Map<String, Object> result = new HashMap<String, Object>();

        ResponseEntity<JSONObject> res = null;
        String errCode = "00";
        String errMsg = "";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            // headers.add("Authorization", "Basic " + base64ClientCredit);

            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
            parameters.add("grant_type", "client_credentials");
            parameters.add("client_id", "4a019ff4-fbfd-4a1a-b464-73f981e22911");
            parameters.add("client_secret", "M1fP3wL1eM3wW6yD8vH2dW3qF2qO2nY3rT2tA4aG5hF3xH1cI8");
            parameters.add("scope", "BL");
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);

            res = restTemplate.exchange(reqUrl, HttpMethod.POST, requestEntity, JSONObject.class);
            JSONObject body = res.getBody();
            System.out.println(body);
            if (body.get("access_token") != null) {
                String tokenKey = body.get("access_token").toString();
                System.out.println("-------------------------------------------------");
                System.out.println(tokenKey);
                result.put("access_token", tokenKey);
            }

        } catch (HttpStatusCodeException he) {
            String resposeMessage = he.getResponseBodyAsString();
            JSONParser parser = new JSONParser();
            JSONObject element = null;
            try {
                element = (JSONObject) parser.parse(resposeMessage);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String message = (String) element.get("error_description");
            if (message.indexOf("Invalid username") > -1) {
                message = "아이디 또는 패스워드가 틀립니다. 확인 후 시도해주세요";
            } else if (message.indexOf("3회") > -1) {
                message = "로그인 3회 실패시 접속이 차단됩니다. <br>관리자에게 문의 바랍니다.";
            }
            errCode = message;
            errMsg = message;

        } catch (Exception e) {
            errCode = "9999";
            errMsg = e.getMessage();
        }

        result.put("errCode", errCode);
        result.put("errMsg", errMsg);
        return result;

    }

}