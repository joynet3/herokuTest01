package com.ydata.herokuApp.web.common.web.domain;

/**
 * <pre>
 * 코드그룹 Record
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

public class CodeGroup {

    private String codeGroup;
    private String codeGroupNm;

    public String getCodeGroup() {
        return codeGroup;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    public String getCodeGroupNm() {
        return codeGroupNm;
    }

    public void setCodeGroupNm(String codeGroupNm) {
        this.codeGroupNm = codeGroupNm;
    }

}
