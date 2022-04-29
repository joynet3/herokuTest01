<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tld/common.tld" prefix="plus"%>
<c:import url="/WEB-INF/tiles/layout/import.jsp" />


<tiles:insertTemplate template="${defaultLayout}" flush="true">
<tiles:putAttribute name="script">

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
</tiles:putAttribute>
<tiles:putAttribute name="contents">
<!-- content 내용 -->
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
							<col style="width: 148px">
							<col style="width: 130px">
							<col style="width: 120px">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">사이트아이디</th>
								<th scope="col">사이트명</th>
								<th scope="col">설치MOUDLE수량</th>
								<th scope="col">설치상태구분코드</th>
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
</tiles:putAttribute>
</tiles:insertTemplate>