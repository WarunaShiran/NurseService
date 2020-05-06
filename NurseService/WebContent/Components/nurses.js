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
		var status = validateItemForm();
		if (status != true)
		 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
		 }
		// If valid------------------------
		 $("#formNurse").submit();
});

function validateItemForm()
{
	if ($("#fName").val().trim() == "")
	 {
	 return "Insert the first name.";
	 }
	if ($("#lName").val().trim() == "")
	 {
	 return "Insert the last name.";
	 }
	if ($("#nic").val().trim() == "")
	 {
	 return "Insert the email nic.";
	 }
	if ($("#address").val().trim() == "")
	{
	return "Insert the address.";
	}
	if ($("#email").val().trim() == "")
	{
	return "Insert the email.";
	}
		
	
	return true;
}

$(document).on("click", ".btnUpdate", function(event)
		{
			 $("#hidnurseIDSave").val($(this).closest("tr").find('#hidnurseIDSave').val());
			 $("#firstName").val($(this).closest("tr").find('td:eq(0)').text());
			 $("#lastName").val($(this).closest("tr").find('td:eq(1)').text());
			 $("#nic").val($(this).closest("tr").find('td:eq(2)').text());
			 $("#address").val($(this).closest("tr").find('td:eq(3)').text());
			 $("#email").val($(this).closest("tr").find('td:eq(4)').text());
		});