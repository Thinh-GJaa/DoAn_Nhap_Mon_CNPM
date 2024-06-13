<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Gson gsonObj = new Gson();
Map<Object, Object> map = null;
List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

ArrayList<String> monthLists = (ArrayList<String>) request.getAttribute("monthLists");

ArrayList<Long> postAmountOfMonthLists = (ArrayList<Long>) request.getAttribute("postAmountOfMonthLists");
ArrayList<Long> postReportAmountOfMonthLists = (ArrayList<Long>) request.getAttribute("postReportAmountOfMonthLists");
ArrayList<Long> postApproveAmountOfMonthLists = (ArrayList<Long>) request.getAttribute("postApproveAmountOfMonthLists");

String dataPoints1 = null;
String dataPoints2 = null;
String dataPoints3 = null;
if (monthLists != null) {
	for (int i = 0; i < monthLists.size()-1; i++) {
		map = new HashMap<Object, Object>();
		map.put("label", monthLists.get(i));
		map.put("y", postAmountOfMonthLists.get(i));
		list.add(map);
	}
	dataPoints1 = gsonObj.toJson(list);

	list = new ArrayList<Map<Object, Object>>();

	for (int i = 0; i < monthLists.size()-1; i++) {
		map = new HashMap<Object, Object>();
		map.put("label", monthLists.get(i));
		map.put("y", postReportAmountOfMonthLists.get(i));
		list.add(map);
	}

	dataPoints2 = gsonObj.toJson(list);

	list = new ArrayList<Map<Object, Object>>();

	for (int i = 0; i < monthLists.size()-1; i++) {
		map = new HashMap<Object, Object>();
		map.put("label", monthLists.get(i));
		map.put("y", postApproveAmountOfMonthLists.get(i));
		list.add(map);
	}

	dataPoints3 = gsonObj.toJson(list);
}
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	window.onload = function() {

		var chart = new CanvasJS.Chart("chartContainer", {
			animationEnabled : true,
			title : {
				text : "Biểu đồ theo thời gian",
				fontWeight : "normal",
				fontFamily : "tahoma",
			},
			subtitles : [ {
				text : '${monthBegin}  đến  ${monthEnd}',
				fontSize : 16,
				fontFamily : "tahoma",
			} ],
			axisX : {
				title : "Thời gian"
			},
			axisY : {
				title : "Bài đăng",
				labelFormatter : addSymbols
			},
			toolTip : {
				shared : true,
				reversed : true
			},
			legend : {
				verticalAlign : "center",
				horizontalAlign : "right",
				reversed : true,
				cursor : "pointer",
				itemclick : toggleDataSeries
			},
			data : [ {
				type : "stackedArea",
				name : "Tổng số bài đăng",
				showInLegend : true,
				yValueFormatString : "#,### tons",
				dataPoints :
<%out.print(dataPoints1);%>
	}, {
				type : "stackedArea",
				name : "Số lượt báo cáo",
				showInLegend : true,
				yValueFormatString : "#,### tons",
				dataPoints :
<%out.print(dataPoints2);%>
	}, {
				type : "stackedArea",
				name : "Số bài đăng được duyệt",
				showInLegend : true,
				yValueFormatString : "#,### tons",
				dataPoints :
<%out.print(dataPoints3);%>
	} ]
		});
		chart.render();

		function addSymbols(e) {
			var suffixes = [ "", "K", "M", "B" ];
			var order = Math.max(Math.floor(Math.log(Math.abs(e.value))
					/ Math.log(1000)), 0);

			if (order > suffixes.length - 1)
				order = suffixes.length - 1;

			var suffix = suffixes[order];
			return CanvasJS.formatNumber(e.value / Math.pow(1000, order))
					+ suffix;
		}

		function toggleDataSeries(e) {
			if (typeof (e.dataSeries.visible) === "undefined"
					|| e.dataSeries.visible) {
				e.dataSeries.visible = false;
			} else {
				e.dataSeries.visible = true;
			}
			e.chart.render();
		}

	}
</script>
<form action="show-chart-by-time" class="mb-5" method="get">
	<div class="d-flex">
		<label for="date" class="form-label mr-3 mt-1">Chọn tháng:</label>
		<div class="mr-3">
			<input class="form-control" type="month" name="monthBegin"
				required="required" min="2022-08" value="${monthBegin}">
		</div>

		<label for="date" class="form-label mr-3 mt-1">Đến tháng:</label>
		<div class="mr-3">
			<input class="form-control" type="month" name="monthEnd"
				required="required" min="2022-08" value="${monthEnd}">
		</div>
		<button class="btn bg-primary text-white" type="submit">Tìm</button>
	</div>
</form>

<c:if test="${not empty monthLists }">
	<div id="chartContainer" style="height: 370px; width: 100%;"></div>
</c:if>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>
