package com.ydata.herokuApp.web.common.taglib;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.jasper.el.JspELException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydata.herokuApp.web.common.web.service.CodeService;
import com.ydata.herokuApp.web.potal.domain.CodeDto;

/**
 * <pre>
 * 커스텀 코드 리스트 조회 tag 정의
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

public class CodeListTagJSON extends AbstractTag {

    private static final Logger logger = LoggerFactory.getLogger(CodeListTagJSON.class);

    protected CodeService codeService;

    protected String var;

    protected String codeGroup;

    public CodeListTagJSON() {
        super();
    }

    @Override
    public void setPageContext(PageContext context) {
        super.setPageContext(context);
        if (context != null) {
            ServletContext ctx = pageContext.getServletContext();
            WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
            codeService = wac.getBean("codeService", CodeService.class);
        }
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            List<CodeDto> codeList = null;
            if (codeGroup != null) {
                codeList = codeService.getCodeList(codeGroup);
            }
            if (CollectionUtils.isNotEmpty(codeList)) {
                List<CodeDto> filterList = new ArrayList<CodeDto>();
                filterList.addAll(codeList);
                ObjectMapper objectMapper = new ObjectMapper();
                String paramJsonString = objectMapper.writeValueAsString(filterList);
                pageContext.getOut().write(paramJsonString);
            }

        } catch (Exception e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspELException {
        this.var = null;
        this.codeGroup = null;
        this.parent = null;
        return EVAL_PAGE;
    }

    public String getCodeGroup() {
        return codeGroup;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
