package com.ydata.herokuApp.web.potal.domain;

/**
 * <pre>
 * 공통 DTO
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.04.22.    scshin      최초 생성
 * </pre>
 *
 * @author scshin <scshin@ydata.co.kr>
 * @since 2020.04.22.
 * @version 1.0
 */
public class MonthPriceDto {

    private String sfid;
    private String termsName;
    private String termlocaCode;
    private String areaCode;
    private String productCode;
    private String areaName;
    private String createDateTime;
    private String productName;
    private String priceExchg;
    private String tempFlag;
    private String ifKey;
    private String stdYmd;
    private String termsPrice;
    private String sourceCode;
    private String sourceName;
    private String stdYmdIf;
    private String createDateTimeIf;

    public String getSfid() {
        return sfid;
    }

    public void setSfid(String sfid) {
        this.sfid = sfid;
    }

    public String getTermsName() {
        return termsName;
    }

    public void setTermsName(String termsName) {
        this.termsName = termsName;
    }

    public String getTermlocaCode() {
        return termlocaCode;
    }

    public void setTermlocaCode(String termlocaCode) {
        this.termlocaCode = termlocaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPriceExchg() {
        return priceExchg;
    }

    public void setPriceExchg(String priceExchg) {
        this.priceExchg = priceExchg;
    }

    public String getTempFlag() {
        return tempFlag;
    }

    public void setTempFlag(String tempFlag) {
        this.tempFlag = tempFlag;
    }

    public String getIfKey() {
        return ifKey;
    }

    public void setIfKey(String ifKey) {
        this.ifKey = ifKey;
    }

    public String getStdYmd() {
        return stdYmd;
    }

    public void setStdYmd(String stdYmd) {
        this.stdYmd = stdYmd;
    }

    public String getTermsPrice() {
        return termsPrice;
    }

    public void setTermsPrice(String termsPrice) {
        this.termsPrice = termsPrice;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getStdYmdIf() {
        return stdYmdIf;
    }

    public void setStdYmdIf(String stdYmdIf) {
        this.stdYmdIf = stdYmdIf;
    }

    public String getCreateDateTimeIf() {
        return createDateTimeIf;
    }

    public void setCreateDateTimeIf(String createDateTimeIf) {
        this.createDateTimeIf = createDateTimeIf;
    }

}
