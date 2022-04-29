package com.ydata.herokuApp.web.common.taglib;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.jasper.el.JspELException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 커스텀 tag  Abstract
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

public abstract class AbstractTag implements Tag {

    private static final Logger logger = LoggerFactory.getLogger(AbstractTag.class);

    protected PageContext pageContext;

    protected Tag parent;

    public AbstractTag() {
    }

    public void release() {
        this.pageContext = null;
    }

    public void setPageContext(PageContext context) {
        this.pageContext = context;
    }

    public Tag getParent() {
        return parent;
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }

    public int doEndTag() throws JspELException {
        this.parent = null;
        return EVAL_PAGE;
    }
}
