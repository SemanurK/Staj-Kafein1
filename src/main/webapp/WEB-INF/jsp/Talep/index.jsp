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
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css" />


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/resources/js/jquery-3.2.1.min.js"></script> --%>
<script src="<%=request.getContextPath()%>/resources/js/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/custom.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/custom.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/waves.js"></script>
<%-- <script src="<%=request.getContextPath()%>/resources/js/jquey.min.js"></script> --%>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script
	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>


<title>Talep</title>

<style>
body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #6c757d;
}

.topnav a {
	float: left;
	color: #fff;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #5cb85c;
	color: white;
}

.topnav a.active {
	background-color: #4CAF50;
	color: white;
}
</style>
</head>
<body>

	<div class="topnav">
		<a href="<%=request.getContextPath()%>/Talep/yenitalep">Talep Ekle</a>
		<a href="<%=request.getContextPath()%>/Talep/index">Talep Listesi</a>

	</div>


	<div class="row" style="margin-top: 20px;">
		<div class="col-sm-1"></div>
		<div class="col-sm-10">


			<div class="card">
				<div class="card-header">
					<h4 style="float: left;">Talep Listesi</h4>
					<form action="<%=request.getContextPath()%>/Talep/yenitalep">
						<Button class="btn btn-info d-none d-lg-block m-l-15"
							style="float: right;">Talep Ekle</Button>
					</form>
					<form action="<%=request.getContextPath()%>/Mail/index">
					<Button class="btn btn-info d-none d-lg-block m-l-15"
							style="float: right;">Mail Kontrol Et</Button>
					</form>
				</div>
				<div class="card-body">
					<div class="col-sm-12">
						<div class="row" style="margin-bottom: 10px;">



							<div class="col-sm-3">
								<select id="secenek" name="secenek" class="form-control"
									onchange="changeFunc();">

									<option value="change_id">Change ID</option>
									<option value="spring_no">Spring No</option>
									<option value="adi">Talep Adı</option>
									<option value="talep_id">Talep ID</option>
									<option value="tarih">Tarih</option>




								</select>
							</div>
							<div class="col-sm-4" id="div_durum">
								<input id="filtre" name="filtre" type="text"
									placeholder="Arama yapmak istediğiniz kelimeyi giriniz "
									value='<c:out value="${deger}"></c:out>' class="form-control" />


							</div>
							<div class="col-sm-4">
								<div class="btn-group">

									<input type="radio" style="margin-top: 10px;" name="durum"
										value="false" /> <label
										style="margin-left: 4px; margin-top: 2px;"> Devam
										Ediyor </label> <input type="radio"
										style="margin-top: 10px; margin-left: 16px;" name="durum"
										value="true" /> <label
										style="margin-left: 4px; margin-top: 2px;"> Tamamlandı
									</label> <input type="radio"
										style="margin-top: 10px; margin-left: 16px;" name="durum"
										value="hepsi" checked="checked" /> <label
										style="margin-left: 4px; margin-top: 2px;"> Hepsi </label>
								</div>
							</div>
							<div class="col-sm-1">
								<Button class="btn btn-success" onclick="javascript:Ara()">Ara</Button>
							</div>


						</div>
						

						<div class="table-responsive" id="x">
							<table class="display" id="talep_table">
								<thead>
									<tr>

										<th>Talep ID</th>
										<th>Change ID</th>
										<th>Talep Adı</th>
										<th>Spring No</th>
										<th>Alınan Tarih</th>
										<th>Başlangiç Tarih</th>
										<th>Bitiş Tarih</th>
										<th>Efor</th>
										<th>Talep Sahip</th>
										<th>Durum</th>
										<th>Işlem 1</th>
									</tr>


								</thead>
								<tbody>
									<c:forEach var="item" items="${taleplist}">

										<tr>

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
													<Button
														class="btn waves-effect waves-light btn-outline-success"
														style="float: left;">Güncelle</Button>
												</form>

											</td>
										</tr>

									</c:forEach>
								</tbody>
							</table>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-1"></div>
	</div>



	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js">
		
	</script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#talep_table').DataTable({
								language: {
					                info: "_TOTAL_ kayıttan _START_ - _END_ kayıt gösteriliyor.",
					                infoEmpty:      "Gösterilecek hiç kayıt yok.",
					                loadingRecords: "Kayıtlar yükleniyor.",
					                zeroRecords: "Talep bulunamadı !",
					                search: "Arama:",
					              
					                infoFiltered:   "(toplam _MAX_ kayıttan filtrelenenler)",
					                buttons: {
					                    copyTitle: "Panoya kopyalandı.",
					                    copySuccess:"Panoya %d satır kopyalandı",
					                    copy: "Kopyala",
					                    print: "Yazdır",
					                },

					                paginate: {
					                    first: "İlk",
					                    previous: "Önceki",
					                    next: "Sonraki",
					                    last: "Son"
					                },
					            }
							});
							var sonuc = '${sonuc}';
							//alert(sonuc);

							if (sonuc != null && sonuc != '') {

								if (sonuc == 0) {
									swal(
											"Hata !",
											"Aynı ID numarasına sahip talep sisteme eklenemez !",
											"warning");
								} else {
									swal(
											"Başarılı !",
											"Talep sisteme basarili sekilde eklendi.",
											"success");
								}
							}
							var yenitalepsayisi='${yenitalepsayisi}';
							if (yenitalepsayisi != null && yenitalepsayisi != '') {

								if (yenitalepsayisi == 0) {
									swal(
											"Yeni Talep Bulunmamakta !",
											"",
											"info");
								} else {
									swal(
											"Yeni Talep !",
											"Sistemde "+yenitalepsayisi+" adet yeni talep mevcut.",
											"info");
								}
							}

					

						});
		
		
	</script>
	<script type="text/javascript">
		function Ara() {
			var deger = document.getElementById("filtre").value;
			var filtre = document.getElementById("secenek").value;
			var durum = document.getElementsByName("durum").value;
			var durum = document.querySelector("input[name=durum]:checked").value;

			// 			if (filtre == "durum" && isNaN(deger)) {
			// 				alert("Tamamlanan talepleri '1' ile Devam etmekte olan talepleri '0' ile goruntuleyebilirsiniz. ");
			// 				//document.getElementById("filtre").style.visibility = "hidden";
			// 			}

			$.ajax({
				type : 'Get',
				url : '/kafein/Talep/filtre',
				data : {
					'deger' : deger,
					'filtre' : filtre,
					'durum' : durum

				},

				success : function(data) {
					$("#x").html(data);

				}
			});

		}

		function changeFunc() {
			var selectBox = document.getElementById("secenek");
			var selectedValue = selectBox.options[selectBox.selectedIndex].value;
			if (selectedValue == ("durum")) {
				alert(selectedValue);
			}

		}
	</script>



</body>
</html>