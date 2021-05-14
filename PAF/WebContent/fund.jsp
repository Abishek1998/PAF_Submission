<%@page import="model.FundTest"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fund Management - GadgetBadget</title>

<link href="myStyle.css" rel="stylesheet" />
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/fund.js"></script>

</head>

<body>
	<div class="container">

		<p class="font-weight-bold">
		<center>
			<h1>
				<b>Fund Management - GadgetBadget</b>
			</h1>
		</center>
		</p>
		<br>

		<fieldset>

			<legend>
				<b>Add Fund Details</b>
			</legend>
			<form id="FUND" name="FUND" class="border border-light p-5">

				<!-- Amount -->
				<div class="form-outline mb-4">
					<label class="form-label" for="form6Example3"
						class="col-sm-2 col-form-label col-form-label-sm">Amount</label> <input
						type="hidden" id="id" name="id"> <input type="text"
						id="amount" class="form-control" name="amount">
				</div>

				<!-- Description -->
				<div class="form-outline mb-4">
					<label class="form-label" for="form6Example3"
						class="col-sm-2 col-form-label col-form-label-sm">Description</label>
					<input type="text" id="description" class="form-control"
						name="description">
				</div>

				<input id="btnSave" name="btnSave" type="button" value="Save"
					class="btn btn-success btn-lg btn-block">

			</form>

			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>

		</fieldset>

		<br>

		<div class="container" id="fundGrid">
			<fieldset>
				<legend>
					<b>View Fund Details</b>
				</legend>
				<div class= "row">
			
				<form method="post" action="fund.jsp"
					class="table table-striped thead-dark table-hover">
					<%
						FundTest viewFund = new FundTest();
					out.print(viewFund.readFund());
					%>
				</form>
				
				</div>
				
			</fieldset>
		</div>
	</div>
</body>
</html>