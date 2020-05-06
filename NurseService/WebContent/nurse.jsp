<%@page import="com.Nurse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    if (request.getParameter("nurseName") != null)
    {
     Nurse nurseObj = new Nurse();
     String stsMsg = "";
    //Insert--------------------------
    if (request.getParameter("hidNurseIDSave") == "")
     {
     stsMsg = nurseObj.addNrses(request.getParameter("fName"),
     request.getParameter("lName"),
     request.getParameter("nic"),
     request.getParameter("address"),
     request.getParameter("email"));
     }
    else//Update----------------------
     {
     stsMsg = nurseObj.updateNurses(request.getParameter("hidnurseIDSave"),
     request.getParameter("fName"),
     request.getParameter("lName"),
     request.getParameter("nic"),
     request.getParameter("address"),
     request.getParameter("email"));
     }
     session.setAttribute("statusMsg", stsMsg);
    }
    //Delete-----------------------------
    

	%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nurse Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/nurses.js"></script>
</head>
<body>
	<div class=container>
		<div class=row>
			<div class="col-4">
				<h2>Nurse Service</h2><br>
				<form id="formNurse" name="formNurse" method="post" action="nurses.jsp">
					First Name :
					<input id="fName" name="firstName" type="text" class="form-control form-control-sm"><br>
					Last Name :
					<input id="lName" name="lastName" type="text" class="form-control form-control-sm"><br>
					NIC :
					<input id="nic" name="nic" type="text" class="form-control form-control-sm"><br>
					Address :
					<input id="address" name="address" type="text" class="form-control form-control-sm"><br>
					Email :
					<input id="email" name="email" type="text" class="form-control form-control-sm"><br>
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
					<input id="hidnurseIDSave" name="hidnurseIDSave" type="hidden" value="" >
				</form>
				<br>
				<div id="alertSuccess" class="alert alert-success">
					<% out.print(session.getAttribute("statusMsg")); %>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<%
					Nurse itemObj = new Nurse();
					out.print(itemObj.readNurses());
				%>
			
			</div>
		</div>
	
	</div>

</body>
</html>