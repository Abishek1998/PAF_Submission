//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#id").val("");
	$("#FUND")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	

	// Form validation-------------------
	var status = validateFundForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#id").val() == "") ? "POST" : "PUT";

	$.ajax({
		url: "FundAPI",
		type: type,
		data: $("#FUND").serialize(),
		dataType: "text",
		complete: function(response, status) {
			//console.log(status);
			onFundSaveComplete(response.responseText, status);
			//window.location.reload(true);
		}
		
	});

});

function onFundSaveComplete(response, status) {

	if (status == "success") {

		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#fundGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {

			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}
	else if (status == "error") {

		$("#alertError").text("Error while saving.");
		$("#alertError").show();

	} else {

		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#id").val("");
	$("#FUND")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "FundAPI",
			type: "DELETE",
			data: "id=" + $(this).data("itemid"),
			dataType: "text",
			complete: function(response, status) {
				onFundDeleteComplete(response.responseText, status);
				//window.location.reload(true);
			}
		});
});


function onFundDeleteComplete(response, status) {

	if (status == "success") {

		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#fundGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {

			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {

		$("#alertError").text("Error while deleting.");
		$("#alertError").show();

	} else {

		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	$("#id").val($(this).closest("tr").find('td:eq(0)').text());
	$("#amount").val($(this).closest("tr").find('td:eq(1)').text());
	$("#description").val($(this).closest("tr").find('td:eq(2)').text());
});


// CLIENTMODEL=========================================================================
function validateFundForm() {

	// Amount
	if ($("#amount").val().trim() == "") {
		return "Please insert an amount.";
	}

	// Description
	if ($("#description").val().trim() == "") {
		return "Please insert a description";
	}


	return true;
}