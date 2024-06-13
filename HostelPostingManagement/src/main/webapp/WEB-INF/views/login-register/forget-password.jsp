<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="bg-light border p-2">Quên mật khẩu</div>
<c:if test="${not empty error}">
	<div class="text-danger bg-light ml-2">
		<c:out value="${error}"></c:out>
		<br />
	</div>
</c:if>
<form class="border p-2 bg-white" action="reset-password" method="post">


	<div class="mb-3">
		<label class="form-label" style="font-weight: bold"> Username
			</label> <input type="text" class="form-control" name="username"
			placeholder="Nhập username"
			required="required">
	</div>

	<div class="mb-3">
		<label class="form-label" style="font-weight: bold">Số điện thoại
			</label> <input type="text" class="form-control" name="phone" id="phone"
			placeholder="Nhập Số điện thoại"
			required="required">
	</div>
	<div class="mb-3">
		<label class="form-label" style="font-weight: bold">Email
			</label> <input type="text" class="form-control" name="email"
			placeholder="Nhập email"
			required="required">
	</div>
	
	<div class="mb-3">
		<label class="form-label" style="font-weight: bold">Nhập mật khẩu mới
			</label> <input type="password" class="form-control" name="newPassword"
			placeholder="Nhập mật khẩu mới" id="newPassword"
			required="required">
	</div>
	
			<!-- Password confirm input -->
		<div class="form-outline mb-4">
			<input type="password" id="pre-password" name="pre-password"
				class="form-control" required="required" placeholder="Nhập lại mật khẩu mới" />
		</div>
	<!-- Submit button -->
	<button type="submit" onclick="return isOkPass()" class="btn btn-success btn-block mb-4">Cập
		nhật mật khẩu</button>
</form>
<script type="text/javascript" src="<c:url value="/resources/js/resetpass.js"/>"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>