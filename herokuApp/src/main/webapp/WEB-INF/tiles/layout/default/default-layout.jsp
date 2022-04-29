<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%
response.setHeader("Pragma", "No-cache"); 
response.setHeader("Cache-Control", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="/WEB-INF/tld/common.tld" prefix="plus" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <title>heroku </title>
    <link rel="stylesheet" type="text/css" href="/resource/css/common.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/jquery-ui.css"> 
    <script type="text/javascript" src="/resource/js/jquery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery/jquery-ui.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery/jquery.tmpl.js"></script>
    <script type="text/javascript" src="/resource/js/jquery/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.form.min.js"></script>
    
    <script type="text/javascript" src="/resource/js/common.js"></script>
    
    <!-- 퓨전 차트 라이브러리 --> 
    <!-- Step 1 - Include the fusioncharts core library -->
    <script type="text/javascript" src="/resource/js/fusionchart/fusioncharts.js"></script> 
    
    <!-- Step 2 - Include the fusion theme -->
    <script type="text/javascript" src="/resource/js/fusionchart/themes/fusioncharts.theme.fusion.js"></script>
    <!-- jQuery-FusionCharts -->
    <script type="text/javascript" src="/resource/js/fusionchart/jquery-fusioncharts.min.js"></script>
    <script type="text/javascript" src="/resource/js/fusionchart/fusioncharts.timeseries.js"></script>
 	<!-- 퓨전 차트 샘플  -->
    
    <script type="text/javascript">

        $(document).ready(function() {
          

        });
        
    </script>
    
    <tiles:insertAttribute name="css" ignore="true" flush="true"/>
    <tiles:insertAttribute name="script" ignore="true" flush="true"/>
    
</head>


    <body>
        <div id="wrap">
            <tiles:insertAttribute name="header" defaultValue="/WEB-INF/tiles/layout/default/header.jsp" flush="true"/>
            <div id="container">        
                <tiles:insertAttribute name="contents"  flush="true"/>
            </div>
            <tiles:insertAttribute name="footer"  defaultValue="/WEB-INF/tiles/layout/default/footer.jsp" flush="true"/>
        </div>
      
<!-- 공통 레이어 팝업 -->        
      <!-- 사이트검색 layerPopup -->
      <div id="layerPopmask"></div>
      <div id="siteSearchPopLayer" class="popupWrap" style="width:480px;height:600px;display:none"><!-- 임시 위치 스타일 삭제바람 -->
          <div class="popupHeader"><h1>사이트검색</h1></div>
          <div class="popupConent">
              <iframe src="" width="100%" height="520px" frameborder="0" scrolling="yes" id="ifrmSiteSearch"></iframe>
          </div>
          <button type="button" class="btnX" title="close" onclick="javascript:popupClose('siteSearchPopLayer');"></button>
      </div>
      
      <!-- 사이트등록 layerPopup -->
      <div id="layerPopmask"></div>
      <div id="siteRegPopLayer" class="popupWrap" style="width:540px;height:420px;display:none"><!-- 임시 위치 스타일 삭제바람 -->
          <div class="popupHeader"><h1>사이트등록</h1></div>
          <div class="popupConent">
              <iframe src="/commonPop/siteRegPop.do" width="100%" height="340px" frameborder="0" scrolling="yes"></iframe>
          </div>
          <button type="button" class="btnX" title="close" onclick="javascript:popupClose('siteRegPopLayer');"></button>
      </div>
<!-- 공통 레이어 팝업 -->

        
    </body>
</html>