<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<!-- <!DOCTYPE html>
<html>
<head>
<style>
table {
    border-collapse: collapse;
}
table,th,td {
    border: 1px solid black;
}
td[rowspan] {
  vertical-align: top;
  text-align: left;
}

@media print {
  #printPageButton {
    display: none;
  }
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Report Form</title>
</head>
<body> -->
	
	<center>
		<u><h1><b>DISTRICT HOSPITAL</b></h1></u>
		<p>Muradnagar, Ghaziabad 201206</p>
		<p>Phone: 9547631746, 9852146357, 8465214785</p>
		<h2>Patient Report Form</h2>
		
		<div style="width: 100%;height: 800px">
		<table cellpadding="5px" style="width: 100%">
			<tr>
				<td style="text-align: center;width: 50%" colspan="2"><b>Patient Name</b></td>
				<td style="text-align: center;width: 50%" colspan="2"><c:out value="${p_name}" /></td>
			</tr>
			<tr>
				<td style="text-align: center;width: 50%" colspan="2"><b>Address</b></td>
				<td style="text-align: center;width: 50%" colspan="2"><c:out value="${p_address}" /></td>
			</tr>
			<tr>
				<td style="text-align: center;width: 50%" colspan="2"><b>Description Of Problem</b></td>
				<td style="text-align: center;width: 50%" colspan="2"><c:out value="${description}" /></td>
			</tr>
			<tr>
				<td style="text-align: center;width: 50%" colspan="2"><b>Doctor Assigned</b></td>
				<td style="text-align: center;width: 50%" colspan="2">Dr. <c:out value="${doc_name}" /> (<c:out value="${doc_specialization}" />)</td>
			</tr>
			
			<tr>
				<td style="text-align: center;width: 25%"><b>Age</b></td>
				<td style="text-align: center;width: 25%"><c:out value="${p_age}" /> years</td>
				
				<td style="text-align: center;width: 25%"><b>Gender</b></td>
				<td style="text-align: center;width: 25%"><c:out value="${p_gender}" /></td>
			</tr>
			
			<tr>
				<td style="text-align: center;width: 25%"><b>Patient Contact</b></td>
				<td style="text-align: center;width: 25%"><c:out value="${p_mobile}" /></td>
				
				<td style="text-align: center;width: 25%"><b>Date</b></td>
				<td style="text-align: center;width: 25%"><c:out value="${dt}"/></td>
			</tr>
			
			<tr>
				<td style="width: 50%;height:650px;padding-left: 15px " colspan="4" rowspan="2"><h3><b><u>Observations and Prescriptions:</u></b></h3></td>
			</tr>
			
		</table>
		</div>
		<button onclick="window.print();" id="printPageButton">Print</button>
	</center>
</body>
</html>