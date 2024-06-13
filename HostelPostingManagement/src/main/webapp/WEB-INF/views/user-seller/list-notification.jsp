
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
	<div class="d-flex justify-content-between border-bottom pb-2">
		<h1>Thông báo</h1>
		<a class="mt-2" href="${contextPath}/setAllSeen">Đánh dấu tất
			cả đã đọc</a>
	</div>
	<c:forEach var="noti" items="${notifications}">
		<c:url var="detailLink" value="detail-post">
			<c:param name="postId" value="${noti.post.id}"></c:param>
			<c:param name="notiId" value="${noti.id}"></c:param>
			<c:param name="seen" value="${noti.seen}"></c:param>
		</c:url>
		<c:choose>
			<c:when test="${noti.seen == true}">
				<div class="border-bottom pl-2">
					<a class="text-decoration-none" href="${contextPath}/${detailLink}">
						<div>
							<h6 class="pt-2">${noti.notificationName}</h6>
							<p class="text-dark mb-0">${noti.post.title}</p>
							<p class="text-dark blockquote-footer">${noti.createdDate}</p>
						</div>
					</a>
				</div>
			</c:when>

			<c:otherwise>
				<div class="border-bottom bg-light pl-2">
					<a class="text-decoration-none" href="${contextPath}/${detailLink}">
						<div>
							<h6 class="pt-2">${noti.notificationName}</h6>
							<p class="text-dark mb-0">${noti.post.title}</p>
							<p class="text-dark blockquote-footer">${noti.createdDate}</p>
						</div>
					</a>
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</form>