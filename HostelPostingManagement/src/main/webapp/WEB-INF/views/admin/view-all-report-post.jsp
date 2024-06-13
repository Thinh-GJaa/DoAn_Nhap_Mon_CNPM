
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
<form>
	<div class="container">
		<h1>Danh sách bài đăng</h1>
		<hr>
		<div class="border rounded">
			<div style="background-color: rgb(237, 240, 243);">
				<h6 class="pt-3 ml-3 ">Danh sách bài đăng báo cáo</h6>
				<hr>
			</div>
			<div class="container">
				<table class="table table-bordered table table-striped">
					<thead>
						<tr>
							<th class="col-2">Tiêu đề</th>
							<th class="col-1">Giá</th>
							<th class="col-1">Diện tích</th>
							<th class="col-1">Danh mục</th>
							<th class="col-1">Địa chỉ</th>
							<th class="col-1">Nội dung báo cáo</th>
							<th class="col-1">Username</th>
							<th class="col-1"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reportPost" items="${listReportPosts}">
							<c:url var="hiddenLink" value="hidden-post">
								<c:param name="postId" value="${reportPost.pk.post.id}"></c:param>
								<c:param name="reportPostId" value="${reportPost.id}"></c:param>
								<c:param name="report" value="true"></c:param>
							</c:url>
							<c:url var="spamReportLink" value="spam-report">
								<c:param name="reportPostId" value="${reportPost.id}"></c:param>
							</c:url>
							<c:url var="detailLink" value="detail-post">
								<c:param name="postId" value="${reportPost.pk.post.id}"></c:param>
							</c:url>
							<tr>
								<td class="overflow-auto">${reportPost.pk.post.title}</td>
								<td>${reportPost.pk.post.price}&nbsp;triệuđ</td>
								<td>${reportPost.pk.post.area}&nbsp;m<sup>2</sup></td>
								<td>${reportPost.pk.post.category.name}</td>
								<td>${reportPost.pk.post.district.name}</td>
								<td>${reportPost.content}</td>
								<td>${reportPost.pk.post.user.account.username}</td>
								<td><a class="btn btn-outline-secondary mr-1 btn-sm"
									data-toggle="tooltip" data-placement="top"
									title="Khóa bài đăng" href="${contextPath}/${hiddenLink}"><i
										class="bi bi-eye-slash"></i></a> <a
									class="btn btn-outline-secondary mr-1 btn-sm"
									data-toggle="tooltip" data-placement="top"
									title="Report spam, bỏ qua!" href="${contextPath}/${spamReportLink}"><i
										class="bi bi-slash-circle"></i></a> <a
									class="btn btn-outline-secondary mr-1 btn-sm mt-1"
									data-toggle="tooltip" data-placement="top"
									title="Chi tiết bài đăng" href="${contextPath}/${detailLink}"><i
										class="bi bi-info-circle"></i></a></td>


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
									<a href="${contextPath}/all-list-post-report/page/${i}">[${i}]</a>&nbsp;
								</c:when>
								<c:otherwise>
									<span>[${i}]</span>&nbsp;
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${currentPage < totalPages}">
								<a
									href="${contextPath}/all-list-post-report/page/${currentPage+1}"><i
									class="bi bi-arrow-right-circle"></i></a>&nbsp;
							</c:when>
						</c:choose>

						<c:choose>
							<c:when test="${currentPage < totalPages}">
								<a href="${contextPath}/all-list-post-report/page/${totalPages}">Last</a>&nbsp;
							</c:when>
						</c:choose>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	$(function() {
		$('[data-toggle="tooltip"]').tooltip()
	})
</script>