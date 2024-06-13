
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"
	type="text/javascript"></script>
<div>
	<div class="container">
		<h1>Danh sách User</h1>
		<hr>
		<div class="d-flex pb-4">
			<a href="${contextPath}/all-user" class="btn border mr-2">Xem tất cả
			</a>
			
			<form class="form-inline my-2 my-lg-0  w-50"
				action="${contextPath}/all-user" method="get">
				<input class="form-control w-75  rounded-0 rounded-left"
					value="${keyword}" name="keyword" type="search"
					placeholder="Nhập username" aria-label="Search">
				<button class="btn border my-2 my-sm-0 rounded-0 rounded-right"
					type="submit">
					<i class="bi bi-search text-primary"></i>
				</button>
			</form>

		</div>
		<div class="border rounded">
			<div style="background-color: rgb(237, 240, 243);">
				<h6 class="pt-3 ml-3 ">Danh sách user</h6>
				<hr>
			</div>
			<div class="container">
				<table class="table table-bordered table table-striped">
					<thead>
						<tr>
							<th class="col-2">Họ và tên</th>
							<th class="col-1">Ngày tạo</th>
							<th class="col-1">Số điện thoại</th>
							<th class="col-2">Email</th>
							<th class="col-1">Trạng thái</th>
							<th class="col-1">UserName</th>
							<th class="col-3">Số lần cảnh báo</th>
							<th class="col-1"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${listProducts}">
							<c:url var="blockLink" value="block-user">
								<c:param name="userId" value="${user.id}"></c:param>
							</c:url>
							<c:url var="activeLink" value="active-user">
								<c:param name="userId" value="${user.id}"></c:param>
							</c:url>
							<tr>
								<td>${user.fullName}</td>
								<td>${user.createdDate}</td>
								<td>${user.phone}</td>
								<td>${user.email}</td>
								<td><c:choose>
										<c:when test="${user.account.block == false}">Hoạt động</c:when>
										<c:otherwise>Tạm khóa</c:otherwise>
									</c:choose></td>
								<td>${user.account.username}</td>
								<td>${user.warningCount}</td>
								<td class="d-flex"><a
									class="btn btn-outline-secondary mr-1 btn-sm"
									data-toggle="tooltip" data-placement="top" title="Khóa"
									href="${contextPath}/${blockLink}"><i
										class="bi bi-person-dash text-danger"></i></a> <a
									class="btn btn-outline-secondary mr-1 btn-sm"
									data-toggle="tooltip" data-placement="top" title="Mở khóa"
									href="${contextPath}/${activeLink}"><i
										class="bi bi-person-check text-sucess"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div>&nbsp;</div>
				<c:if test="${totalPages > 1}">
					<div class="text-right">
						Total: ${totalItems} &nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;
						<c:choose>
							<c:when test="${currentPage >1}">
								<a href="1">Fist</a>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${currentPage >1}">
								<a href="${currentPage -1}"><i
									class="bi bi-arrow-left-circle"></i></a>&nbsp;
							</c:when>
						</c:choose>

						<c:forEach begin="1" end="${totalPages}" var="i">
							<c:choose>
								<c:when test="${currentPage != i}">
									<a href="${contextPath}/view-user/page/${i}">[${i}]</a>&nbsp;
								</c:when>
								<c:otherwise>
									<span>[${i}]</span>&nbsp;
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${currentPage < totalPages}">
								<a href="${contextPath}/view-user/page/${currentPage+1}"><i
									class="bi bi-arrow-right-circle"></i></a>&nbsp;
							</c:when>
						</c:choose>

						<c:choose>
							<c:when test="${currentPage < totalPages}">
								<a href="${contextPath}/view-user/page/${totalPages}">Last</a>&nbsp;
							</c:when>
						</c:choose>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$('[data-toggle="tooltip"]').tooltip()
	})
</script>
