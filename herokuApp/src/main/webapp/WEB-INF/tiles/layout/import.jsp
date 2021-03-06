<%@ page pageEncoding="utf-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<spring:eval var ="defaultHeaderPath" expression="T(com.ydata.herokuApp.web.common.LayoutConstrains).DEFAULT_HEADER_PATH" scope="request" />
<spring:eval var ="defaultLocationPath" expression="T(com.ydata.herokuApp.web.common.LayoutConstrains).DEFAULT_LOCATION_PATH" scope="request" />
<spring:eval var ="defaultFooterPath" expression="T(com.ydata.herokuApp.web.common.LayoutConstrains).DEFAULT_FOOTER_PATH" scope="request" />
<spring:eval var ="defaultLayout" expression="T(com.ydata.herokuApp.web.common.LayoutConstrains).LAYOUT_DEFAUL_TEMPLET_URL" scope="request" />


<spring:eval var ="logintHeaderPath" expression="T(com.ydata.herokuApp.web.common.LayoutConstrains).LOGIN_HEADER_PATH" scope="request" />
<spring:eval var ="loginFooterPath" expression="T(com.ydata.herokuApp.web.common.LayoutConstrains).LOGIN_FOOTER_PATH" scope="request" />
<spring:eval var ="loginLayout" expression="T(com.ydata.herokuApp.web.common.LayoutConstrains).LAYOUT_LOGIN_TEMPLET_URL" scope="request" />


<spring:eval var ="popupLayout" expression="T(com.ydata.herokuApp.web.common.LayoutConstrains).LAYOUT_POPUP_TEMPLET_URL" scope="request" />

