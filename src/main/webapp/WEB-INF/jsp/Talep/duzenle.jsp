<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%request.setCharacterEncoding("utf-8"); %>

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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<title>Talep Düzenle</title>
</head>
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
<body>

	<div class="topnav">

		<a href="<%=request.getContextPath()%>/Talep/yenitalep">Talep Ekle</a>
		<a href="<%=request.getContextPath()%>/Talep/index">Talep Listesi</a>

	</div>
	<div class="container">
		<div class="row" style="margin-top: 80px;">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<div class="card">
					<div class="card-header">
						<c:if test="${talepbilgi==null }">
							<h6>Talep Oluşturma İşlemi</h6>
						</c:if>
						<c:if test="${talepbilgi!=null }">
							<h6>Talep Düzenleme İşlemi</h6>
						</c:if>

					</div>
					<div class="card-body">

						<c:if test="${talepbilgi==null }">
							<form action="<%=request.getContextPath()%>/Talep/talepekle"
								method="post" id="frm" name="frm">
						</c:if>
						<c:if test="${talepbilgi!=null }">
							<form action="<%=request.getContextPath()%>/Talep/guncelle"
								method="post">
						</c:if>

						<div class="row">

							<div class="col-sm-4">
								<label style="float: right; margin-top: 4px;"> Talep ID
									:</label>
							</div>

							<div class="col-sm-2">
								<input type="number" class="form-control" style="float: left;"
									required name="talep_id" min="1"
									value="<c:out value='${talepbilgi.talep_id}'/>" />
								<c:if test="${id!=null}">
									<input id="id" name="id" type="hidden"
										value="<c:out value='${talepbilgi.id}'/>">
								</c:if>

							</div>
							<div class="col-sm-3">
								<label style="float: right; margin-top: 4px;"> Change ID
									:</label>
							</div>
							<div class="col-sm-3">
								<input type="text" class="form-control" style="float: left;"
									id="change_id" name="change_id" onkeyup="javascript:Kontrol()"
									value="<c:out value='${talepbilgi.change_id}'/>" />
							</div>
						</div>

						<div class="row" style="margin-top: 8px;">
							<div class="col-sm-4">
								<label style="float: right; margin-top: 4px;"> Spring
									Numarası :</label>
							</div>
							<div class="col-sm-8">
								<input type="number" class="form-control" style="float: left;"
									required name="spring_no" min="1"
									value="<c:out value='${talepbilgi.spring_no}'/>" />
							</div>
						</div>
						<div class="row" style="margin-top: 8px;">
							<div class="col-sm-4">
								<label style="float: right; margin-top: 4px;"> Talep Adı
									:</label>
							</div>
							<div class="col-sm-8">
								<input type="text" class="form-control" style="float: left;"
									required name="adi" id="name"
									value="<c:out value='${talepbilgi.adi}'/>" />
							</div>
						</div>
						<div class="row" style="margin-top: 8px;">
							<div class="col-sm-4">
								<label style="float: right; margin-top: 4px;"> Efor :</label>
							</div>
							<div class="col-sm-8">
								<input type="text" class="form-control" style="float: left;"
									required name="efor"
									value="<c:out value='${talepbilgi.efor}'/>" />
							</div>
						</div>

						<div class="row" style="margin-top: 8px;">
							<div class="col-sm-4">
								<label style="float: right; margin-top: 4px;"> Talebin
									Sahibi :</label>
							</div>
							<div class="col-sm-8">
								<select name="talep_sahip" class="form-control">
									<option selected="selected" value="1">Talebin sahibini
										seçiniz.</option>
									<c:forEach items="${kullaniciBilgi}" var="k">
										<option value="${k.kul_id }"
											${k.kul_id  eq talepbilgi.talep_sahip ? 'selected="selected"' : ''}>${k.kul_ad_soyad}</option>

										<%-- 					  BUDA ÇALIŞIYOR	<c:if --%>
										<%-- 											test="${k.kul_id eq talepbilgi.talep_sahip}"> --%>
										<%-- 											<option selected="selected" value="${k.kul_id }">${k.kul_ad_soyad}</option> --%>
										<%-- 										</c:if> --%>
										<%-- 										<c:if test="${k.kul_id ne talepbilgi.talep_sahip}"> --%>
										<%-- 											<option value="${k.kul_id }">${k.kul_ad_soyad}</option> --%>
										<%-- 										</c:if> --%>


									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row" style="margin-top: 8px;">
							<div class="col-sm-4">
								<label style="float: right; margin-top: 4px;"> Talep
									Tarihi :</label>
							</div>
							<div class="col-sm-8">
								<input type="date" class="form-control" name="tarih" id="tarih"
									readonly value="<c:out value='${talepbilgi.tarih}'/>" />
							</div>
						</div>
						<div class="row" style="margin-top: 8px;">
							<div class="col-sm-4">
								<label style="float: right; margin-top: 4px;"> Başlangıç
									Tarihi :</label>
							</div>
							<div class="col-sm-8">
								<input type="date" class="form-control" name="baslangic_tarihi"
									id="baslangic_tarihi"
									<%-- 									<c:if test="${talepbilgi.baslangic_tarihi== }"></c:if> --%>
									value="<c:out value='${talepbilgi.baslangic_tarihi}'/>" />
							</div>
						</div>
						<div class="row" style="margin-top: 8px;">
							<div class="col-sm-4">
								<label style="float: right; margin-top: 4px;"> Bitiş
									Tarihi :</label>
							</div>
							<div class="col-sm-8">
								<input type="date" class="form-control" name="bitis_tarihi"
									id="bitis_tarihi"
									value="<c:out value='${talepbilgi.bitis_tarihi}'/>" />
							</div>
						</div>

						<div class="row" style="margin-top: 10px;">
							<div class="col-sm-4">
								<label style="float: right; margin-top: 4px;"> Durum :</label>
							</div>
							<div class="col-sm-8">
								<div class="btn-group">
									<c:if test="${talepbilgi==null }">
										<input type="radio" style="margin-top: 10px;" name="durum"
											id="d_durum" value="false" checked="checked" />
										<label style="margin-left: 8px; margin-top: 2px;">
											Devam Ediyor </label>
										<input type="radio" name="durum" value="true" id="t_durum"
											style="margin-top: 10px; margin-left: 40px;" />

										<label style="margin-left: 8px; margin-top: 2px;">Tamamlandı</label>
									</c:if>
									<c:if test="${talepbilgi!=null }">
										<c:if test="${talepbilgi.durum==false}">
											<input type="radio" style="margin-top: 10px;" name="durum"
												id="d_durum" value="false" checked="checked" />
											<label style="margin-left: 8px; margin-top: 2px;">
												Devam Ediyor </label>
											<input type="radio" name="durum" value="true" id="t_durum"
												style="margin-top: 10px; margin-left: 40px;" />

											<label style="margin-left: 8px; margin-top: 2px;">Tamamlandı</label>
										</c:if>

										<c:if test="${talepbilgi.durum==true}">
											<input type="radio" style="margin-top: 10px;" name="durum"
												id="d_durum" value="false" />
											<label style="margin-left: 8px; margin-top: 2px;">
												Devam Ediyor </label>
											<input type="radio" name="durum" value="true"
												style="margin-top: 10px; margin-left: 40px;" id="t_durum"
												checked="checked" />

											<label style="margin-left: 8px; margin-top: 2px;">Tamamlandı</label>
										</c:if>
									</c:if>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-12">
								<c:if test="${talepbilgi==null }">

									<Button class="btn waves-effect waves-light btn-success"
										style="float: right;">Talep Olustur</Button>

								</c:if>
								<c:if test="${ talepbilgi!=nul}">
									<Button class="btn waves-effect waves-light btn-success"
										style="float: right;">Talebi Guncelle</Button>
								</c:if>

							</div>
						</div>
						<!-- 						<div class="col-sm-1"> -->
						<%-- 									<Button class="btn btn-success" onclick="javascript:Kontrol2(${talepbilgi})">Kontrol</Button> --%>
						<!-- 								</div> -->



					</div>
				</div>

			</div>
		</div>
		<div class="col-sm-1"></div>
	</div>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

	<script type="text/javascript">
		function Kontrol() {

			var date = new Date();
			var dateOptions = {
				day : '2-digit',
				month : '2-digit',
				year : 'numeric'
			};
			var currentDate = date.toLocaleDateString('ja-JP', dateOptions)
					.replace(/\//gi, '-');
			var timeOptions = {
				hour : '2-digit',
				minute : '2-digit'
			};
			var currentTime = date.toLocaleTimeString('it-IT', timeOptions);

			if (document.getElementById('change_id').value.length != 0) {
				document.getElementById('tarih').value = currentDate;
				var x = document.getElementById('tarih').value;

				document.getElementById('t_durum').checked = true;
			} else {
				document.getElementById('tarih').value = "";
				document.getElementById('d_durum').checked = true;

			}

		}
		// 		function Kontrol2(talep){
		// 			alert(talep.adi);
		// 			  $.ajax({
		// 	                type: 'Post',
		// 	                url: '/kafein/Talep/talepekle',
		// 	                data: { 'talep': talep },
		// 	                //bu işlemler gerçekleşip başarılı olduğunda
		// 	                success: function (data) {
		// 	                	if(data.equals("basarısız"))
		// 	                   alert("basarili");
		// 	                }
		// 	            });

		// 		}

		$(document)
				.ready(
						function() {

							var sonuc = '${sonuc}';

							if (sonuc != null && sonuc != '') {

								if (sonuc == 0) {
									swal(
											"Hata!",
											"Ayni ID numarasına sahip talep sisteme eklenemez !",
											"warning");
								} else {
									swal(
											"Basarili!",
											"Talep sisteme basarili sekilde eklendi.",
											"success");
								}
							}

						});
	</script>






</body>
</html>