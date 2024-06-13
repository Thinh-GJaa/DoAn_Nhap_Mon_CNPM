<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <meta http-equiv="refresh" content="1" > -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div>
	<div class="">
		<div class="text-primary border btn" onclick="openModal()">Lọc</div>
	</div>
	<div class="row text-center justify-content-center">
		<c:forEach var="post" items="${listPosts}">

			<c:url var="addToSaveListLink" value="add-save-post">
				<c:param name="postId" value="${post.id}"></c:param>
			</c:url>

			<c:url var="detailPostLink" value="detail-post">
				<c:param name="postId" value="${post.id}"></c:param>
			</c:url>

			<div class="card col-3 mt-2 mr-4 px-3 pt-2">
				<img
					src="<c:url value="/resources/images/${post.images.iterator().next().fileName}"/>"
					class="card-img-top" style="height: 15rem" alt="...">
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
					</a> <a class="btn btn-warning"
						href="${contextPath}/${addToSaveListLink}"> <i
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
<div class="modal fade" id="sortInformation" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Lọc kết quả</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="mt-3 mx-auto" name="form-sort-post" id="formSortPost"
					action="${contextPath}/view-posts" method="get">
					<div class="mb-3">
						<label class="form-label ">Danh mục</label> <select
							class="form-select w-100 border-primary p-2 rounded"
							name="categoryId">
							<c:forEach var="category" items="${listCategories}">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="mb-3">
						<label class="form-label ">Vị trí</label> <select
							class="form-select w-100 border-primary p-2 rounded"
							name="districtId">
							<c:forEach var="district" items="${listDistricts}">
								<option value="${district.id}">${district.name}</option>
							</c:forEach>
						</select>
					</div>

					<div class="d-flex row">
						<div class="col-6">
							<div class="row mb-3 align-bottom">
								<label class="col-sm-4 form-label align-bottom">Giá từ:</label>
								<div class="col-sm-8">
									<select
										class="form-select w-100 border-primary p-2 rounded w-100"
										name="minPrice" required="required">
										<option value="0">0đ</option>
										<option value="1.5">1,5 triệu đ</option>
										<option value="2">2 triệu đ</option>
										<option value="2.5">2,5 triệuđ</option>
										<option value="3">3 triệu đ</option>
										<option value="4.5">4,5 triệu đ</option>
									</select>
								</div>
							</div>

							<div class="row mb-3">
								<label class="col-sm-4 form-label pr-0">Diện
									tích từ (m<sup>2</sup>):
								</label>
								<div class="col-sm-8">
									<select class="form-select w-100 border-primary p-2 rounded"
										name="minArea" required="required">
										<option value="0">0</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="30">30</option>
										<option value="40">40</option>
									</select>
								</div>
							</div>
						</div>

						<div class="col-6">
							<div class="row mb-3">
								<label class="col-sm-3 form-label">Đến: </label>
								<div class="col-sm-9">
									<select class="form-select w-100 border-primary p-2 rounded"
										name="maxPrice" required="required">
										<option value="2">2 triệu đ</option>
										<option value="3.5">3,5 triệu đ</option>
										<option value="5">5 triệuđ</option>
										<option value="7">3 triệu đ</option>
										<option selected="selected" value="10">10 triệu đ</option>
									</select>
								</div>
							</div>

							<div class="row mb-3">
								<label class="col-sm-3 form-label">Đến: </label>
								<div class="col-sm-9">
									<select class="form-select w-100 border-primary p-2 rounded"
										name="maxArea" required="required">
										<option value="20">20</option>
										<option value="35">35</option>
										<option value="55">55</option>
										<option selected="selected" value="70">70</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="submit" form="formSortPost"
					onclick="return isOkeSort()" class="btn btn-warning">OK</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function openModal() {
		$('#sortInformation').modal();
	}
</script>
