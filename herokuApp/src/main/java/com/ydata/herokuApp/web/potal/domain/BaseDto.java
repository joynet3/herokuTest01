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
public class BaseDto {

    private String dataCretDt;
    private String dataCretUserId;
    private String dataUpdDt;
    private String dataUpdUserId;
    
    public String getDataCretDt() {
        return dataCretDt;
    }
    public void setDataCretDt(String dataCretDt) {
        this.dataCretDt = dataCretDt;
    }
    public String getDataCretUserId() {
        return dataCretUserId;
    }
    public void setDataCretUserId(String dataCretUserId) {
        this.dataCretUserId = dataCretUserId;
    }
    public String getDataUpdDt() {
        return dataUpdDt;
    }
    public void setDataUpdDt(String dataUpdDt) {
        this.dataUpdDt = dataUpdDt;
    }
    public String getDataUpdUserId() {
        return dataUpdUserId;
    }
    public void setDataUpdUserId(String dataUpdUserId) {
        this.dataUpdUserId = dataUpdUserId;
    }
}
