function getWeekNo(v_date_str) {
	var date = new Date();
	if(v_date_str){
		date = new Date(v_date_str);
	}
	return Math.ceil(date.getDate() / 7);
}

function fnAbnDailyChart(data) {
	
	var returnObj = new Object();
	
	var dataArr = new Array();
	
	if ( data != null && data.length > 0 ) {
		
		$.each(data, function(idx, obj){
			
			var chartData = new Object();
			chartData.label = obj.colecDate;
			chartData.value = obj.occCnt;
			chartData.color = '#0d78ff';
			dataArr.push(chartData);
		});
	}
	
	var chartset = new Object();
	chartset.theme = 'fusion';
	chartset.maxColWidth = 15;
	
	returnObj.width = '100%';
	returnObj.height = '18%';
	returnObj.chart = chartset;
	returnObj.type = 'column2d';
	returnObj.render = 'abnDaily-chart';
	returnObj.chartData = dataArr;
	
	return returnObj;
}

function fnBmsOffConChart(data) {
	
	var returnObj = new Object();
	
	var dataArr = new Array();
	
	if ( data != null && data.length > 0 ) {
		
		for ( var key in data[0] ) {
			
			var chartData = new Object();
			if ( key == 'error' ) {
				chartData.label = 'ERROR';
				chartData.value = data[0].error;
				chartData.color = '#f07151';
			} else if ( key == 'normal' ) {
				chartData.label = '정상';
				chartData.value = data[0].normal;
				chartData.color = '#ebebeb';
			} else {
				continue;
			}
			dataArr.push(chartData);
		}
	}
	
	var chartset = new Object();
	chartset.theme = 'fusion';
	chartset.showLegend = 0;
  	chartset.chartLeftMargin = 0;
  	chartset.chartRightMargin = 0;
  	chartset.chartTopMargin = 0;
  	chartset.chartBottomMargin = 0;
  	chartset.captionPadding = 0;
  	chartset.startingAngle = 270;
  	chartset.pieRadius = '140';
  	chartset.doughnutRadius = '90';
  	chartset.showPercentValues = 0;
	chartset.showValues = 0;
	
	returnObj.width = '100%';
	returnObj.height = '100%';
	returnObj.chart = chartset;
	returnObj.type = 'doughnut2d';
	returnObj.render = 'bms-doughnut';
	returnObj.chartData = dataArr;
	
	return returnObj;
}

function fnHubOffConChart(data) {
	
	var returnObj = new Object();
	
	var dataArr = new Array();
	
	if ( data != null && data.length > 0 ) {
		
		for ( var key in data[0] ) {
			
			var chartData = new Object();
			if ( key == 'error' ) {
				chartData.label = 'ERROR';
				chartData.value = data[0].error;
				chartData.color = '#f07151';
			} else if ( key == 'normal' ) {
				chartData.label = '정상';
				chartData.value = data[0].normal;
				chartData.color = '#ebebeb';
			} else {
				continue;
			}
			dataArr.push(chartData);
		}
	}
	
	var chartset = new Object();
	chartset.theme = 'fusion';
	chartset.showLegend = 0;
	chartset.chartLeftMargin = 0;
	chartset.chartRightMargin = 0;
  	chartset.chartTopMargin = 0;
  	chartset.chartBottomMargin = 0;
  	chartset.captionPadding = 0;
	chartset.startingAngle = 270;
	chartset.pieRadius = '140';
	chartset.doughnutRadius = '90';
	chartset.showPercentValues = 0;
	chartset.showValues = 0;
  
	returnObj.width = '100%';
	returnObj.height = '100%';
	returnObj.chart = chartset;
	returnObj.type = 'doughnut2d';
	returnObj.render = 'hub-doughnut';
	returnObj.chartData = dataArr;
	
	return returnObj;
}

function fnWeeklyChart(data) {
	
	/* 날짜 정렬 */
	data.sort(function(a,b){
		return new Date(a.weeklyStartDate) - new Date(b.weeklyStartDate)
	});
	
	var returnObj = new Object();
	
	var categoryArr = new Array();
	var dataArr 	= new Array();
	var dataArrSec 	= new Array();
	
	var dataset = new Array();
	
	if ( data != null && data.length > 0 ) {
		
		/* 이전값과 비교하기위한 Temp Object */
		var tempWeekNo = new Object();	
		var tempObj = new Object();
		
		$.each(data, function(idx, obj){
			
			var categoryData = new Object();
			
			var weekNo = getWeekNo(obj.weeklyStartDate);
			
			if ( idx > 0 && (weekNo == tempWeekNo && obj.weeklyStartDate.substring(5,7) == tempObj.weeklyStartDate.substring(5,7)) ) {
				
			} else {
				
				categoryData.label = obj.weeklyStartDate.substring(5,7) + '월 ' + weekNo + '주차';
				categoryArr.push(categoryData);
			}
			
			if ( obj.alarmProtectionDivCd == 'G011C001' ) {
				
				var data = new Object();
				data.value = obj.alarmProtectionCnt;
				dataArr.push(data);
			} else if ( obj.alarmProtectionDivCd == 'G011C002' ) {
				
				var dataSec = new Object();
				dataSec.value = obj.alarmProtectionCnt;
				dataArrSec.push(dataSec);
			}
			
			tempWeekNo = getWeekNo(obj.weeklyStartDate);
			tempObj = obj;
		});
	}
	
	dataset.push({
		seriesname : '알람',
		color : '#0d78ff',
		data : dataArr
    });
    
    dataset.push({
    	seriesname : '프로텍션',
    	color : '#0dcfff',
    	data : dataArrSec
    });
		
	var chartset = new Object();
	chartset.theme = 'fusion';
	chartset.maxColWidth = 15;
	chartset.legendPosition = 'top-right';
	chartset.legendIconSides = 20;
	chartset.legendItemFont = 'NotoSansKR Regular';
	chartset.legendItemFontSize = '12';
	
	returnObj.chart		= chartset;
	returnObj.category 	= categoryArr;
	returnObj.dataset 	= dataset;
	
	return returnObj;
}


alert(1);