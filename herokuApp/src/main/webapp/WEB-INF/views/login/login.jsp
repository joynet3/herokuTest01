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
    <title>E-BMS 시스템</title>
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
    
    
<!-- 장비 설치 일정 템플릿 -->
<script id="vndr-list-tmp" type="text/x-jquery-tmpl">
<tr>
	<td>\${termsName}</td>
	<td>\${termlocaCode}</td>
	<td>\${areaCode}</td>
	<td>\${areaName}</td>
	<td>\${createDateTime}</td>
</tr>
</script>


    <script type="text/javascript">

  	$(document).ready(function() {

  	  vndrInstallSchList();

  	})

  	/* 장비 설치 일정 */
  	function vndrInstallSchList() {

  		$.ajax({
  			type : 'POST',
  			url : '/dashboard/getList.do',
  			success : function(data) {

  				/* 장비 설치 일정 */
  				if (data.dataList != null) {
  					var vndrList = data.dataList;
  					console.log(data);
  					$('#vndr-list').empty();
  					if (vndrList.length > 0) {
  						$('#vndr-list-tmp').tmpl(vndrList).appendTo('#vndr-list');
  					} else {
  						$('#vndr-list').html();
  					}
  				}

  			}
  		});
  	}

    </script>
    
</head>
    <body>
        <div id="wrap">
            <div id="container">        
              <div id="content" class="dashboard">
              	<div class="boardWrap">
              		<div class="boardBox05">
              			<div class="box type14">
              				<!-- 더보기 버튼 추가 : 20200605 -->
              				<div class="titleWrap">
              					<h2 class="title">장비 설치 일정</h2>
              					<a href="/installState/installStatusList.do" class="btnMore">더보기</a>
              				</div>
              				
              				<!-- //더보기 버튼 추가 : 20200605 -->
              				<!-- tblList -->
              				<div class="tblList dashboard">
              					<table>
              						<caption>장비 설치 일정</caption>
              						<colgroup>
              							<col style="width: 165px">
              							<col>
              							<col style="width: 150px">
              							<col style="width: 120px">
              						</colgroup>
              						<thead>
              							<tr>
              								<th scope="col">사이트아이디</th>
              								<th scope="col">사이트명</th>
              								<th scope="col">설치MOUDLE수량</th>
              								<th scope="col">설치확정일자</th>
              								<th scope="col">설치담당자명</th>
              							</tr>
              						</thead>
              						<tbody id="vndr-list"></tbody>
              					</table>
              				</div>
              				<!-- //tblList -->
              			</div>
              		</div>
              	</div>
              </div>
            </div>
        </div>
    </body>
</html>