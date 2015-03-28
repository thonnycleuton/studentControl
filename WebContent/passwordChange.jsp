<script>
	function verify() {
		if (document.getElementById("userId").value.trim() == ""
				&& document.getElementById("password").value.trim() == "") {
			document.getElementById("error").innerHTML = "Please, fill in all required fields.";
			return false;
		} else
			return true;
	}
</script>
<%@ page import="nascimentot.model.*"%>
<%
	String errorMessage = (String) session.getAttribute("errors");
	String login = (String) session.getAttribute("login");
	if (errorMessage == null)
		errorMessage = "";
	if (login == null)
		login = "";
	
	String title = "Change Password";
%>
<body>
	<!-- wraper -->
	<div id="wrapper">
		<!-- shell -->
		<div class="shell">
			<!-- container -->
			<div class="container">
				<!-- header -->
				<%@ include file="header.jsp" %>
				<nav id="navigation">
					<div class="login-card">
						<h1><%= title %></h1>
						<br>
						<p id="error"><%=errorMessage%></p>
							<form name="Input" method="post" action="./UpdatePasswordServlet">
								<input type="hidden" id="userId" name="Login" value="<%=login%>">
								
							<p class="contact"><label for="actualPassword">Actual Passsword</label></p>
								<input type="password" id="actualPassword" name="actualPassword" >
								
							<p class="contact"><label for="password">New Password</label></p>
								<input type="password" id="password" name="password" >
							
							<p class="contact"><label for="repassword">Confirm your new password</label></p>
								<input type="password" id="repassword" name="repassword">
							<br> 
							<br> 
							<input class="buttom"
								name="submit" id="submit" value="Update"
								type="submit">
							</form>
					</div>
				</nav>
				<jsp:include page="footer.jsp" />
			</div>
			<!-- end of container -->
		</div>
		<!-- end of shell -->
	</div>
	<!-- end of wrapper -->
</body>
</html>
