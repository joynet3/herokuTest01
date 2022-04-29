package com.ydata.herokuApp.web.potal.domain;

/**
 * <pre>
 * 공통 코드 DTO
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.04.27.    scshin      최초 생성
 * </pre>
 *
 * @author scshin <scshin@ydata.co.kr>
 * @since  2020.04.27.
 * @version 1.0
 */
public class CodeDto extends BaseDto {

    private String comnCd;
    private String comnCdNm;
    private String groupComnCd;
    private String useYn;
    private String relComnCd;
    
    public String getComnCd() {
        return comnCd;
    }
    public void setComnCd(String comnCd) {
        this.comnCd = comnCd;
    }
    public String getComnCdNm() {
        return comnCdNm;
    }
    public void setComnCdNm(String comnCdNm) {
        this.comnCdNm = comnCdNm;
    }
    public String getGroupComnCd() {
        return groupComnCd;
    }
    public void setGroupComnCd(String groupComnCd) {
        this.groupComnCd = groupComnCd;
    }
    public String getUseYn() {
        return useYn;
    }
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
    public String getRelComnCd() {
        return relComnCd;
    }
    public void setRelComnCd(String relComnCd) {
        this.relComnCd = relComnCd;
    }
}
