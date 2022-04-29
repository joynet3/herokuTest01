package com.ydata.herokuApp.web.potal.form;

/**
 * <pre>
 * 프로토콜 검색 DTO
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.06.15.    scshin      최초 생성
 * </pre>
 *
 * @author scshin <scshin@ydata.co.kr>
 * @since  2020.06.15.
 * @version 1.0
 */
public class ProtSearchFrom extends SearchForm {

    private String protNm;
    private String protNo;

    public String getProtNm() {
        return protNm;
    }

    public void setProtNm(String protNm) {
        this.protNm = protNm;
    }

    public String getProtNo() {
        return protNo;
    }

    public void setProtNo(String protNo) {
        this.protNo = protNo;
    }

}
