package com.ydata.herokuApp.web.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 전체 레이아웃 정의용 상수 정의
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

public interface RestCallConstrains {

    public static List<String> getTonkenKeyExcludeList() {
        List<String> tonkenKeyNoChkeckList = new ArrayList<String>();
        tonkenKeyNoChkeckList.add(LOGIN_API_URL);
        return tonkenKeyNoChkeckList;
    }

    /**
     * 로그인 Url
     */
    public static final String LOGIN_API_URL = "intuser/oauth2/token";

    public static final String LOGOUT_API_URL = "api/logout:DELETE";


    // ========================= 사이트 현황
    /** 전국 사이트 리스트 */
    public static final String TOT_SITELIST_API_URL = "api/site/allSite:GET";
    /** 전국 실시간 알람/프로텍션 현황 */
    public static final String TOT_ALMPRO_LST_API_URL = "api/ocrrStats/alrmPtt/rtime:GET";
    /** 전국 알람/프로텍션 발생이력 */
    public static final String TOT_ALMPRO_LST_HIS_API_URL = "api/ocrrStats/alrmPtt/hst:GET";
    /** 전국 알람/프로텍션 발생이력 Excel */
    public static final String TOT_ALMPRO_LST_FILE_API_URL = "/api/ocrrStats/alrmPtt/download:POST";
    /** 전국 알람/프로텍션 발생이력 RAck */
    public static final String TOT_ALMPRO_LST_HIS_RACK_API_URL = "api/ocrrStats/alrmPttR/hst:GET";

    /** 전국 실시간 통신불가 발생 현황 */
    public static final String TOT_OFFLINE_LST_API_URL = "api/ocrrStats/cmncFalu/rtime:GET";
    /** 전국 통신불가 발생 이력 */
    public static final String TOT_OFFLINE_HIS_LST_API_URL = "api/ocrrStats/cmncFalu/hst:GET";
    /** 전국 통신불가 발생 이력 Excel */
    public static final String TOT_OFFLINE_LST_FILE_API_URL = "api/ocrrStats/cmncFalu/download:POST";



    /**
     * 공통코드 그룹 코드 리스트 조회
     */
    public static final String GROUPCODELIST_API_URL = "api/groupCd:GET";
    /** 그룹공통코드 등록 */
    public static final String GROUPCODEREG_API_URL = "api/groupCd:POST";
    /** 그룹공통코드 수정 */
    public static final String GROUPCODEMOD_API_URL = "api/groupCd/{groupCd}:PUT";

    /** 공통코드 리스트 조회 */
    public static final String CODELIST_API_URL = "api/comnCd:GET";
    /** 공통코드 등록 */
    public static final String CODEREG_API_URL = "api/comnCd:POST";
    /** 공통코드 수정 */
    public static final String CODEMOD_API_URL = "api/comnCd/{comnCd}:PUT";

}
