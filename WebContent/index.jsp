<%@ page import="nascimentot.model.*"%>
<%
	String errorMessage = (String) session.getAttribute("errors");
	String login = (String) session.getAttribute("login");
	if (errorMessage == null)
		errorMessage = "";
	if (login == null)
		login = "";

	String title = "Welcome Page";
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
				<div class="m-slider">
					<img id="dynamicBanner">
					<script>
						randomBanner();
					</script>
				</div>
				<!-- end of slider -->
				<!-- main -->
				<div class="main">
					<h1>
						<p id="greeting"></p>
						<script>
							showGreeting();
						</script>
					</h1>
					<div class="box-cnt">
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Phasellus eget augue quis lorem ipsum dolor sit amet, consectetur
							adipiscing elit. llus eget augue quis lorem ipsum dolor sit amet,
							free css templates</p>
					</div>
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
