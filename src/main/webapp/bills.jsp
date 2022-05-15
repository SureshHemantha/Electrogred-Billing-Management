<%@page import="com.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/bills.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 

<h1 class = " text-center ">Bill Management </h1>
<form id="formBill" name="formBill" method="post" action="bills.jsp">
 Bill Date: 
 <input id="B_date" name="B_date" type="text" 
 class="form-control form-control-sm">
 <br> Bill Account NO: 
 <input id="B_account" name="B_account" type="text" 
 class="form-control form-control-sm">
 <br> Bill Total price: 
 <input id="B_total" name="B_total" type="text" 
 class="form-control form-control-sm">
 <br> Bill Units: 
 <input id="B_units" name="B_units" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidBIDSave" 
 name="hidBIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divBillsGrid">
 <%
 	Bill billObj = new Bill(); 
  out.print(billObj.readBilling());
 %>
</div>
</div> </div> </div> 
<style>
body{
/* background-image: radial-gradient( circle 610px at 5.2% 51.6%, rgba(5,8,114,1) 0%, rgba(7,3,53,1) 97.5% ); */



 background-image: linear-gradient( 90.9deg, rgba(3,195,195,1) 0.3%, rgba(37,84,112,1) 87.8% );





/*background: rgb(2,0,36);
background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 26%, rgba(0,212,255,1) 100%);*/
}

</style>
</body>
</html>