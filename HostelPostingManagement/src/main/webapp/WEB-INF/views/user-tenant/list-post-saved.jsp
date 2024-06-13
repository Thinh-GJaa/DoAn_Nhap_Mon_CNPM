<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <meta http-equiv="refresh" content="1" > -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div>
	<div class="border-bottom">
		<div class="text-primary btn" onclick="openModal()">Lọc</div>
	</div>
	<div class="row text-center justify-content-center">
		<c:forEach var="post" items="${listProducts}">

			<c:url var="removeSaveListLink" value="/remove-save-list">
				<c:param name="productId" value="${post.id}"></c:param>
			</c:url>

			<c:url var="detailPostLink" value="detail-post">
				<c:param name="productId" value="${post.id}"></c:param>
			</c:url>

			<div class="card col-3 mt-2 mr-4 px-3">
				<img
					src="<c:url value="/resources/images/${post.images.iterator().next().fileName}"/>"
					class="card-img-top" alt="...">
				<div class="card-body">
					<p class="card-text text-truncate">${post.title}</p>
				</div>

				<div class="text-center">
					<p class="card-text">${post.area}&nbsp;m<sup>2</sup>
					</p>
					<p class="card-text">${post.price}&nbsp;triệu/tháng</p>
				</div>

				<div class="card-body">
					<a href="${contextPath}/${detailPostLink}" class="btn btn-primary">
						<i class="bi bi-info-circle-fill"></i> Detail
					</a> <a class="btn btn-warning" href="${removeSaveListLink}"> <i
						class="bi bi-heart"></i> Lưu tin
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="mb-3">
		&nbsp;
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
						<a href="${currentPage -1}"><i class="bi bi-arrow-left-circle"></i></a>&nbsp;
							</c:when>
				</c:choose>

				<c:forEach begin="1" end="${totalPages}" var="i">
					<c:choose>
						<c:when test="${currentPage != i}">
							<a href="${contextPath}/list-post/page/${i}">[${i}]</a>&nbsp;
								</c:when>
						<c:otherwise>
							<span>[${i}]</span>&nbsp;
								</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${currentPage < totalPages}">
						<a href="${contextPath}/list-post/page/${currentPage+1}"><i
							class="bi bi-arrow-right-circle"></i></a>&nbsp;
							</c:when>
				</c:choose>

				<c:choose>
					<c:when test="${currentPage < totalPages}">
						<a href="${contextPath}/list-post/page/${totalPages}">Last</a>&nbsp;
							</c:when>
				</c:choose>
			</div>
		</c:if>
	</div> 
</div>