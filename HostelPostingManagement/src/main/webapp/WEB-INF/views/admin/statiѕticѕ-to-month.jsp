<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
Gson gsonObj = new Gson();
Map<Object, Object> map = null;
List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

Object totalPostOfMonth = request.getAttribute("totalPostOfMonth");
Object totalApprovePostOfMonth = request.getAttribute("totalApprovePostOfMonth");
Object totalnotApprovePost = request.getAttribute("totalnotApprovePost");
Object totalReportPost = request.getAttribute("totalReportPost");

map = new HashMap<Object, Object>();
map.put("label", "Số bài đăng");
map.put("y", totalPostOfMonth);
list.add(map);
map = new HashMap<Object, Object>();
map.put("label", "Số bài đăng được duyệt");
map.put("y", totalApprovePostOfMonth);
list.add(map);
map = new HashMap<Object, Object>();
map.put("label", "Số bài đăng bị từ chối");
map.put("y", totalnotApprovePost);
list.add(map);
map = new HashMap<Object, Object>();
map.put("label", "Số bài đăng bị báo cáo");
map.put("y", totalReportPost);
list.add(map);

String dataPoints = gsonObj.toJson(list);
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	window.onload = function() {

		var chart = new CanvasJS.Chart("chartContainer", {
			animationEnabled : true,
			theme : "light2", // "light1", "dark1", "dark2"
			title : {
				text : "Thống kê theo tháng"
			},
			subtitles : [ {
				text : '${month}',
				fontSize : 16
			} ],
			axisY : {
				title: "Số lượng bài đăng",
				scaleBreaks : {
					type : "wavy",
					autoCalculate : true
				}
			},
			data : [ {
				type : "column",
				indexLabel : "{y}",
				dataPoints :
<%out.print(dataPoints);%>
	} ]
		});
		chart.render();

	}
</script>
<form action="show-statiѕticѕ-by-month" method="get">
	<div class="d-flex">
		<label for="date" class="form-label mr-3 mt-1">Chọn tháng:</label>
		<div class="mr-3">
			<input class="form-control" type="month" name="monthBegin"
				required="required" min="2022-08" value="${month}">
		</div>
		<button class="btn bg-primary text-white" type="submit">Tìm</button>
	</div>
</form>

<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>