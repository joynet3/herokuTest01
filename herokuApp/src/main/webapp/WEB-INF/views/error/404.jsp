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

<%@ page import="com.ydata.herokuApp.web.common.AppConstrains"%>
<c:set var="RoleAdmin" value="<%= AppConstrains.RoleAdmin %>"/>
<c:set var="RoleSdi" value="<%= AppConstrains.RoleSdi %>"/>
<c:set var="RoleGen" value="<%= AppConstrains.RoleGen %>"/>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <title>E-BMS 시스템</title>
    <link rel="stylesheet" type="text/css" href="/resource/css/common.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/jquery-ui.css"> 
    <script type="text/javascript" src="/resource/js/jquery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery/jquery-ui.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery/jquery.tmpl.js"></script>
    <script type="text/javascript" src="/resource/js/jquery/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/resource/js/jquery.form.min.js"></script>
    
    <script type="text/javascript" src="/resource/js/common.js"></script>
    
</head>


<body>
    <!-- errorWrap -->
    <div class="errorWrap">
        <div class="errorBox">
            <h1>Error 404 Not Found</h1>
            <div class="numError">404</div>
            <p class="noPage">페이지를 표시 할 수 없습니다.</p>
            <p class="pageCheck">
                요청하신 결과를 찾을 수 없거나 서버에서 제거 되었습니다.<br>
                URL을 확인해 주시기 바랍니다.
            </p>
            <div class="btnGoMain">
             <a href="/dashboard/dashboard.do">메인으로 돌아가기</a>
            </div>
        </div>
        <div class="blank"></div>
    </div>
    <!-- //errorWrap -->
</body>
</html>