<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="bg-light border p-2">Đăng nhập</div>
<c:choose>
	<c:when
		test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Tài khoản đã tạm khóa, vui lòng liên hệ với chúng tôi!'}">
		<div class="text-danger bg-light ml-2">
			<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
		</div>
	</c:when>
	<c:otherwise>
		<c:if test="${not empty error}">
			<div class="text-danger bg-light ml-2">
				<c:out value="${error}"></c:out>
				<br />
			</div>
		</c:if>
	</c:otherwise>

</c:choose>
<form class="border p-2 bg-white" action="login" method="post">
	<!-- Email input -->
	<div class="form-outline mb-4">
		<input type="text" name="username" class="form-control"
			placeholder="Nhập username" required="required" />
	</div>

	<!-- Password input -->
	<div class="form-outline mb-4">
		<input type="password" name="password" id="password"
			class="form-control" placeholder="Nhập mật khẩu"
			required="required" />
	</div>

	<!-- Check box -->
	<div class="form-check mb-3">
		<input class="form-check-input" type="checkbox" value="remember-me"
			id="form2Example31" checked /> <label class="form-check-label"
			for="form2Example31"> Remember me </label>
	</div>

	<!-- Submit button -->
	<button type="submit" class="btn btn-success btn-block mb-4">Đăng
		nhập</button>

	<!-- Register buttons -->
	<div class="d-flex justify-content-between">
		<p>
			<a class="nav-link" href="register">Click here to register</a>
		</p>
		<p>
			<a class="nav-link" href="forget-password">Forget password?</a>
		</p>
	</div>
</form>