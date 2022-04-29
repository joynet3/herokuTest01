package com.ydata.herokuApp.web.common.taglib;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.jasper.el.JspELException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ydata.herokuApp.web.common.web.service.CodeService;
import com.ydata.herokuApp.web.potal.domain.CodeDto;

/**
 * <pre>
 * 커스텀 코드 조회 tag 정의
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
public class CodeTag extends AbstractTag {

    private static final Logger logger = LoggerFactory.getLogger(CodeTag.class);

    protected CodeService codeService;

    protected String codeGroup;
    protected String code;

    public CodeTag() {
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
            CodeDto record = codeService.getCode(codeGroup, code);
            pageContext.getOut().write(record.getComnCdNm());
        } catch (Exception e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspELException {
        this.codeGroup = null;
        this.code = null;
        this.parent = null;
        return EVAL_PAGE;
    }

    public String getCodeGroup() {
        return codeGroup;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
