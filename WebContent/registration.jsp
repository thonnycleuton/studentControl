<%@ page import="nascimentot.model.*"%>
<%
	String title = "Registration";
	String errorMessage = (String) session.getAttribute("errors");
	Student student = (Student) session.getAttribute("aStudent");
%>

<body>
	<!-- wrapper -->
	<div id="wrapper">
		<!-- shell -->
		<div class="shell">
			<!-- container -->
			<div class="container">
				<!-- header -->
				<%@ include file="header.jsp"%>
				<div class="main">
					<div class="form">
						<p id="error"><%=errorMessage%></p>
						<form id="contactform" method="post" action="./RegistrationServlet">
							<p class="contact"><label for="StudentNumber">Student Number</label></p>
							<input id="StudentNumber" name=StudentNumber placeholder="Student Number"  type="text" value = "<%= (student != null && student.getStudentNumber() != 0) ? student.getStudentNumber():"" %>">
						
							<p class="contact"><label for="FirstName">First Name</label></p>
							<input id="FirstName" name="FirstName" placeholder="First Name" type="text" value = "<%= (student != null) ? student.getFirstName():"" %>">

							<p class="contact"><label for="LastName">Last Name</label></p>
							<input id="LastName" name="LastName" placeholder="Last Name" type="text" value = "<%= (student != null) ? student.getLastName():"" %>">

							<p class="contact">
								<label for="EmailAddress">Email</label>
							</p>
							<input id="EmailAddress" name="EmailAddress" placeholder="example@domain.com" type="text" value = "<%= (student != null) ? student.getEmail():"" %>">

							<fieldset>
								<label>Birthday</label> <label class="month"> 
									<select class="select-style" name="BirthMonth">
										<option value="3">Month</option>
										<option value="01">January</option>
										<option value="02">February</option>
										<option value="03">March</option>
										<option value="04">April</option>
										<option value="05">May</option>
										<option value="06">June</option>
										<option value="07">July</option>
										<option value="08">August</option>
										<option value="09">September</option>
										<option value="10">October</option>
										<option value="11">November</option>
										<option value="12">December</option></label> 
									</select> 
								<label>Day<input class="birthday" maxlength="2" name="BirthDay" placeholder="DD" ></label>
								<label>Year <input class="birthyear" maxlength="4" name="BirthYear" placeholder="YYYY"></label>
							</fieldset>

							<p class="contact">
								<label for="PhoneNumber">Phone Number</label>
							</p>
							
							<input id="PhoneNumber" name="PhoneNumber" placeholder="Phone Number"
								 type="text" value = "<%= (student != null) ? student.getPhone():"" %>"> 
								
							<p class="contact"><label for="Password">Password</label></p>
								<input type="password" id="Password" name="Password" >
							
							<p class="contact"><label for="Repassword">Confirm your password</label>
							</p>
							<input type="password" id="Repassword" name="Repassword">
							<br> 
							<br> 
							<input class="buttom"
								name="submit" id="submit" value="Sign me up!"
								type="submit">
								
						</form>

					</div>
					<jsp:include page="footer.jsp" />
				</div>
				<!-- end of container -->
			</div>
			<!-- end of shell -->
		</div>
		<!-- end of wrapper -->
</body>
</html>