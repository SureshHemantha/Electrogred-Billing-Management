$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});
$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateBillForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidBIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "BillsAPI", 
 type : type, 
 data : $("#formBill").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onBillSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onBillSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divBillsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidBIDSave").val(""); 
$("#formBill")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidBIDSave").val($(this).data("bid")); 
		 $("#B_date").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#B_account").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#B_units").val($(this).closest("tr").find('td:eq(3)').text());
		 $("#B_total").val($(this).closest("tr").find('td:eq(4)').text()); 
		 
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "BillsAPI", 
		 type : "DELETE", 
		 data : "Bid=" + $(this).data("bid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onBillDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onBillDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divBillsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validateBillForm()
{
0	// CODE
	if ($("#B_date").val().trim() == "")
	{
	return "Insert Bill Date.";
	}
	// NAME
	if ($("#B_account").val().trim() == "")
	{
	return "Insert Bill account.";
}

// PRICE-------------------------------
if ($("#B_total").val().trim() == ""){
	return "Insert Bill Total Price.";
}
		// is numerical value
		var tmpPrice = $("#B_total").val().trim();
		if (!$.isNumeric(tmpPrice))
	{
	return "Insert a numerical value for Bill Price.";
	}
		
// convert to decimal price
$("#B_total").val(parseFloat(tmpPrice).toFixed(2));

// DESCRIPTION------------------------
if ($("#B_units").val().trim() == ""){
	
	return "Insert Bill Units.";
}
	return true;
}