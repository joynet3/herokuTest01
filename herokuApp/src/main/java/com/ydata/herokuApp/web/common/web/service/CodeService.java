package com.ydata.herokuApp.web.common.web.service;

import java.util.List;

import com.ydata.herokuApp.web.common.web.domain.GroupCodeDto;
import com.ydata.herokuApp.web.potal.domain.CodeDto;

/**
 * <pre>
 * 공통코드 조회 SERVICE
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

public interface CodeService {

    public int setCodeListAll();

    public List<GroupCodeDto> getCodeGroupList();

    public List<CodeDto> getCodeList(String codeGroup);

    public CodeDto getCode(String codeGroup, String code);

}