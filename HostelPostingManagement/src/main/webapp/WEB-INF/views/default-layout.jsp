<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<head>
<!-- import bootstrap cdn-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
</head>
<!-- import bootstrap icon cdn -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">

<title><tiles:insertAttribute name="title" /></title>
<body class="vh-100 ">
	<div class="border-bottom bg-light p-3 fixed-top mb-5">
		<div class="row">
			<div class="col-6">
				<h2>
					<a class="text-decoration-none" href="${contextPath}/view-posts">CHO
						THUÊ TRỌ CLT</a>
				</h2>
			</div>
			<div class="col-6 d-flex row justify-content-end">
				<sec:authorize access="hasAnyRole('ADMIN','OWN','TENANT')">
					<sec:authorize access="hasRole('OWN')">
						<a class="btn col-2 p-0" href="${contextPath}/add-post"><i
							class="bi bi-plus-square-dotted  h5 mr-2"></i>Đăng Tin</a>
					</sec:authorize>
					<!-- navbar links -->
					<div class="dropdown col-3 align-middle text-center mr-5 pr-5">
						<div class="dropdown-toggle px-4" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							Chào
							<c:out value="${user_fullName}" />
							<i class="bi bi-person-fill h5"></i>
						</div>

						<div class="dropdown-menu " aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item border-bottom"
								href="${contextPath}/user-profile"><i
								class="bi bi-person-lines-fill h5 mr-1"></i>Thông tin của tôi</a> <a
								class="dropdown-item" href="${contextPath}/logout"><i
								class="bi bi-box-arrow-right h5 mr-1"></i> Đăng xuất</a>
						</div>
					</div>
				</sec:authorize>
			</div>
		</div>
	</div>
	<!-- This container contains the side bar
            and main content of the page -->
	<!-- h-100 takes the full height of the body-->
	<div class="container-fluid h-100 navbar-left mt-5 pt-3">
		<div class="row h-100">
			<div class="col-2 bg-light text-dark pt-4 px-0 ">

				<!-- Navigation links in side bar-->
				<div class="border-bottom">
					<a class="nav-link " href="${contextPath}/home"><i
						class="bi bi-card-list mr-2 h5"></i>Tất cả bài đăng</a>
				</div>
				<sec:authorize access="hasAnyRole('TENANT','OWN')">
					<div class="border-bottom">
						<a class="nav-link" href="${contextPath}/list-user-save-post"><i
							class="bi bi-heart mr-2 h5"></i>Bài đăng đã lưu</a>
					</div>
				</sec:authorize>
				<sec:authorize access="hasRole('OWN')">

					<div class="border-bottom">
						<a class="nav-link" href="${contextPath}/list-user-notification"><i
							class="bi bi-bell mr-2 h5"></i>Thông báo
							(${notificationOfUserAmount})</a>
					</div>

					<div class="border-bottom">
						<a class="nav-link" href="${contextPath}/list-user-post"><i
							class="bi bi-file-earmark-person mr-2 h5"></i>Bài đăng của tôi
							(${postAmount})</a>
					</div>
					<div class="border-bottom">
						<a class="nav-link" href="${contextPath}/add-post"><i
							class="bi bi-pencil-square mr-2 h5"></i>Thêm Bài đăng</a>
					</div>
					<div class="border-bottom">
						<a class="nav-link" href="${contextPath}/list-user-post-approved"><i
							class="bi bi-check2-circle mr-2 h5"></i>Bài đăng được duyệt
							(${postApproveAmount})</a>
					</div>
					<div class="border-bottom">
						<a class="nav-link"
							href="${contextPath}/list-user-post-wait-approve"><i
							class="bi bi-hourglass mr-2 h5"></i>Bài đăng đang chờ duyệt
							(${postWaitApproveAmount})</a>
					</div>
					<div class="border-bottom">
						<a class="nav-link" href="${contextPath}/list-user-post-hidden"><i
							class="bi bi-file-earmark-x mr-2 h5"></i>Bài đăng bị từ chối/ báo cáo vi phạm
							(${postHiddenAmount})</a>
					</div>

				</sec:authorize>
				<sec:authorize access="hasRole('ADMIN')">
					<div class="border-bottom">
						<a class="nav-link"
							href="${contextPath}/all-list-post-wait-approve"><i
							class="bi bi-hourglass mr-2 h5"></i>Bài đăng chờ duyệt
							(${allPostWaitApproveAmount})</a>
					</div>
					<div class="border-bottom">
						<a class="nav-link" href="${contextPath}/all-list-post-hidden"><i
							class="bi bi-file-earmark-x mr-2 h5"></i>Bài đăng bị từ chối/ báo cáo vi phạm</a>
					</div>
					<div class="border-bottom">
						<a class="nav-link" href="${contextPath}/all-list-post-report"><i
							class="bi bi-card-list mr-2 h5"></i>Bài đăng bị báo cáo
							(${allPostReportAmount})</a>
					</div>
					<div class="border-bottom">
						<a class="nav-link" href="${contextPath}/all-user"><i
							class="bi bi-file-earmark-person mr-2 h5"></i>Quản lý User</a>
					</div>

					<div class="border-bottom">
						<a class="nav-link" href="${contextPath}/show-statiѕticѕ-by-month"><i
							class="bi bi-pie-chart mr-2 h5"></i>Thống kê theo tháng</a>
					</div>
					
					<div class="border-bottom">
						<a class="nav-link" href="${contextPath}/show-chart-by-time"><i
							class="bi bi-file-earmark-bar-graph mr-2 h5"></i>Biểu đồ theo thời gian</a>
					</div>

				</sec:authorize>
			</div>
			<!--Contains the main content of the web page-->
			<div class="col-10 content mt-4 justify-content-center">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
	</div>
</body>
<!-- import jquery cdn -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous">
	
</script>
<!-- import popper.js cdn -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous">
	
</script>
<!-- import javascript cdn -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous">
	
</script>

</html>