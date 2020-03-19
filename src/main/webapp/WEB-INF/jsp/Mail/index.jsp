<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/custom.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/waves.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquey.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css" />

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container" style="margin-top:30px;">
		<div class="table-responsive">
			<table class="display" id="talep_table">
				<thead>
					<tr>
						<!--	<th>#</th>  -->
						<th>ID</th>
						<th>Gönderici</th>
						<th>Alıcı</th>
						<th>Konu</th>
						<th>Mesaj</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${mailliste}">
						<tr>
							<td><c:out value="${item.id }"></c:out></td>
							<td><c:out value="${item.gonderici }"></c:out></td>
							<td><c:out value="${item.alici }"></c:out></td>
							<td><c:out value="${item.konu }"></c:out></td>
							<td><c:out value="${item.mesaj }"></c:out></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#talep_table').DataTable()
		});
	</script>
</body>
</html>