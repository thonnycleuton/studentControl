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
	
	String title = "Login";
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
						<h1>Log-in</h1>
						<br>
						<p id="error"><%=errorMessage%></p>
							<form name="Input" method="post" action="./LoginServlet">
								<input type="text" id="userId" name="Login" value="<%=login%>">
								<input type="password" name="password" id="password" placeholder="Password"> 
								<input type="button" onclick="if(verify()==true){submit();}" name="login" class="login login-submit" value="login">
							</form>
						<div class="login-help">
							<a href="registration.jsp">Register </a> <a href="#">Forgot
								Password</a>
						</div>
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
