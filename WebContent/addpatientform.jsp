<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${patient != null && forview != null}">
<h3>View Patient</h3>
<form action="AddPatient" method="post" style="width: 300px">
	<div class="form-group">
		<label for="name1">Name</label> <input disabled type="text"
			class="form-control" name="p_name" id="name1" value="<c:out value='${patient.p_name}' />" />
	</div>
	<div class="form-group">
		<label for="email1">Mobile</label> <input type="number"
			disabled required="required" class="form-control" name="p_mobile" id="email1"
			value="<c:out value='${patient.p_mobile}' />" />
	</div>
	<div class="form-group">
		<label for="password1">Age</label> <input type="number"
			disabled required="required" class="form-control" name="p_age" id="password1"
			value="<c:out value='${patient.p_age}' />" />
	</div>
	<div class="form-group">
		<label for="address1">Address</label>
		<textarea class="form-control" required="required" rows="5"
			disabled name="p_address" id="address1"><c:out value='${patient.p_address}' /></textarea>
	</div>
	<div class="radio">
		<label><input type="radio" required="required" name="p_gender"
			disabled checked="checked" id="gender1" value="<c:out value='${patient.p_gender}' />"  /><c:out value='${patient.p_gender}' /></label>
	</div>
</form>
</c:if>

<c:if test="${patient != null && forview == null}">
	<h3>Edit Patient</h3>
<form action="EditPatient" method="post" style="width: 300px">
	
	<input type="hidden" name="p_id" value="<c:out value='${patient.p_id}' />" />
	
	<div class="form-group">
		<label for="name1">Name</label> <input type="text"
			required="required" class="form-control" name="p_name" id="name1" value="<c:out value='${patient.p_name}' />" />
	</div>
	<div class="form-group">
		<label for="email1">Mobile</label> <input type="number"
			required="required" class="form-control" name="p_mobile" id="email1"
			value="<c:out value='${patient.p_mobile}' />" />
	</div>
	<div class="form-group">
		<label for="password1">Age</label> <input type="number"
			required="required" class="form-control" name="p_age" id="password1"
			value="<c:out value='${patient.p_age}' />" />
	</div>
	<div class="form-group">
		<label for="address1">Address</label>
		<textarea class="form-control" required="required" rows="5"
			name="p_address" id="address1"><c:out value='${patient.p_address}' /></textarea>
	</div>
	
	<c:if test="${patient.p_gender == 'male'}">
	<div class="radio">
		<label><input type="radio" required="required" name="p_gender"
			id="gender1" checked="checked" value="male" />Male</label>
	</div>
	<div class="radio">
		<label><input type="radio" name="p_gender" id="gender2"
			value="female" />Female</label>
	</div>
	</c:if>
	
	<c:if test="${patient.p_gender == 'female'}">
	<div class="radio">
		<label><input type="radio" required="required" name="p_gender"
			id="gender1" value="male" />Male</label>
	</div>
	<div class="radio">
		<label><input type="radio" name="p_gender" id="gender2"
			value="female" checked="checked"/>Female</label>
	</div>
	</c:if>
	
	<button type="submit" class="btn btn-primary">Update</button>
	
</form>
</c:if>


<c:if test="${patient == null && forview == null}">
<h3>Add Patient</h3>
<form action="AddPatient" method="post" style="width: 300px">
	<div class="form-group">
		<label for="name1">Name</label> <input type="text"
			required="required" class="form-control" name="p_name" id="name1" placeholder="Name" />
	</div>
	<div class="form-group">
		<label for="email1">Mobile</label> <input type="number"
			required="required" class="form-control" name="p_mobile" id="email1"
			placeholder="Mobile" />
	</div>
	<div class="form-group">
		<label for="password1">Age</label> <input type="number"
			required="required" class="form-control" name="p_age" id="password1"
			placeholder="Age" />
	</div>
	<div class="form-group">
		<label for="address1">Address</label>
		<textarea class="form-control" required="required" rows="5"
			name="p_address" id="address1" placeholder="Address"></textarea>
	</div>
	<div class="radio">
		<label><input type="radio" required="required" name="p_gender"
			id="gender1" value="male" />Male</label>
	</div>
	<div class="radio">
		<label><input type="radio" name="p_gender" id="gender2"
			value="female" />Female</label>
	</div>
	<button type="submit" class="btn btn-primary">Submit</button>
</form>
</c:if>