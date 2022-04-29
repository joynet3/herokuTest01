package com.ydata.herokuApp.web.common.web.domain;

import com.ydata.herokuApp.web.potal.domain.BaseDto;

/**
 * <pre>
 * 그룹 코드 DTO
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.04.27.    scshin      최초 생성
 * </pre>
 *
 * @author scshin <scshin@ydata.co.kr>
 * @since 2020.04.27.
 * @version 1.0
 */
public class GroupCodeDto extends BaseDto {

    private String groupComnCd;
    private String groupComnCdNm;
    private String useYn;

    public String getGroupComnCd() {
        return groupComnCd;
    }

    public void setGroupComnCd(String groupComnCd) {
        this.groupComnCd = groupComnCd;
    }

    public String getGroupComnCdNm() {
        return groupComnCdNm;
    }

    public void setGroupComnCdNm(String groupComnCdNm) {
        this.groupComnCdNm = groupComnCdNm;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
}
