<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div class="" table-responsive"" id="x">

		<table class="display" id="talep_table">
			<thead>
				<tr>
					<!--	<th>#</th>  -->
					<th>Talep ID</th>
					<th>Change ID</th>
					<th>Talep Adı</th>
					<th>Spring No</th>
					<th>Tarih</th>
					<th>Başlangıç Tarih</th>
					<th>Bitiş Tarih</th>
					<th>Efor</th>
					<th>Talep Sahip</th>
					<th>Durum</th>
					<th>Işlem 1</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty taleplist }">
					<td colspan="8" align="center" style="color: #a6a6a6;"><span><b>Hiç
								bir sonuç bulunamadı.</b></span></td>
				</c:if>
				<c:forEach var="item" items="${taleplist}">

					<tr>
						<!--  <td><c:out value="${item.id}" /></td>-->
						<td><c:out value="${item.talep_id}" /></td>
						<td><c:out value="${item.change_id}" /></td>

						<td><c:out value="${item.adi}" /></td>
						<td><c:out value="${item.spring_no}" /></td>
						<td><c:out value="${item.tarih}" /></td>
						<td><c:out value="${item.baslangic_tarihi}" /></td>
						<td><c:out value="${item.bitis_tarihi}" /></td>
						<td><c:out value="${item.efor }"></c:out>
						<td><c:out value="${item.ad_soyad}"></c:out> <br> <small><c:out
									value="${item.sahip_gorev}"></c:out></small></td>
						<td><c:if test="${item.durum==false}">
								<input type="radio" onclick="this.checked = false;" />
								<b style="padding-left: 5px;">Devam Ediyor </b>
							</c:if> <c:if test="${item.durum==true}">
								<input type="radio" checked="checked"
									onclick="this.checked = true;" />
								<b style="padding-left: 5px; color: #a6a6a6;">Tamamlandı</b>
							</c:if></td>
						<td>
							<form
								action="<%=request.getContextPath()%>/Talep/duzenle?id=<c:out value='${item.id}' />"
								method="post">
								<Button class="btn waves-effect waves-light btn-outline-success"
									style="float: left;">Güncelle</Button>
							</form>

						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#talep_table')
									.DataTable(
											{
												language : {
													info : "_TOTAL_ kayıttan _START_ - _END_ kayıt gösteriliyor.",
													infoEmpty : "Gösterilecek hiç kayıt yok.",
													loadingRecords : "Kayıtlar yükleniyor.",
													zeroRecords : "Talep bulunamadı !",
													search : "Arama:",

													infoFiltered : "(toplam _MAX_ kayıttan filtrelenenler)",
													buttons : {
														copyTitle : "Panoya kopyalandı.",
														copySuccess : "Panoya %d satır kopyalandı",
														copy : "Kopyala",
														print : "Yazdır",
													},

													paginate : {
														first : "İlk",
														previous : "Önceki",
														next : "Sonraki",
														last : "Son"
													},
												}
											});
						});
	</script>

</body>
</html>