package com.ydata.herokuApp.web.potal.domain;

/**
 * <pre>
 * SITE_BAS DTO
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.04.28.    scshin      최초 생성
 * </pre>
 *
 * @author scshin <scshin@ydata.co.kr>
 * @since 2020.04.28.
 * @version 1.0
 */
public class SiteBasDto extends BaseDto {

    private String siteId;
    private String siteNm;
    private String siteAdr;
    private String siteSidoDivCd;
    private String siteFirstRegDt;
    private String siteExpDate;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteNm() {
        return siteNm;
    }

    public void setSiteNm(String siteNm) {
        this.siteNm = siteNm;
    }

    public String getSiteAdr() {
        return siteAdr;
    }

    public void setSiteAdr(String siteAdr) {
        this.siteAdr = siteAdr;
    }

    public String getSiteSidoDivCd() {
        return siteSidoDivCd;
    }

    public void setSiteSidoDivCd(String siteSidoDivCd) {
        this.siteSidoDivCd = siteSidoDivCd;
    }

    public String getSiteFirstRegDt() {
        return siteFirstRegDt;
    }

    public void setSiteFirstRegDt(String siteFirstRegDt) {
        this.siteFirstRegDt = siteFirstRegDt;
    }

    public String getSiteExpDate() {
        return siteExpDate;
    }

    public void setSiteExpDate(String siteExpDate) {
        this.siteExpDate = siteExpDate;

    }

    public void setSiteBasData(String siteId, String siteNm, String siteAdr, String siteSidoDivCd,
            String siteFirstRegDt, String siteExpDate) {
        this.siteId = siteId;
        this.siteNm = siteNm;
        this.siteAdr = siteAdr;
        this.siteSidoDivCd = siteSidoDivCd;
        this.siteFirstRegDt = siteFirstRegDt;
        this.siteExpDate = siteExpDate;

    }

}
