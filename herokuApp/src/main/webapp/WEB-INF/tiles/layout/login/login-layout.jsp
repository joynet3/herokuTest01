<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8" %>
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
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <title>heroku</title>
    <link rel="stylesheet" type="text/css" href="/resource/css/common.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/jquery-ui.css">
    
    <script type="text/javascript" src="/resource/js/jquery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery/jquery-ui.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery/jquery.tmpl.js"></script>
    <script type="text/javascript" src="/resource/js/jquery/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/resource/js/common.js"></script>
    <tiles:insertAttribute name="css" ignore="true" flush="true"/>
    <tiles:insertAttribute name="script" ignore="true" flush="true"/>
    <script type="text/javascript">
        $(document).ready(function() {
          
        });
        
    </script>
</head>
    <body>
        <div class="loginWrap">
            <tiles:insertAttribute name="contents"  flush="true"/>
        </div>
    </body>
</html>