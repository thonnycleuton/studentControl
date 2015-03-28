<%@ page import="nascimentot.model.*"%>
<%
	//Student aStudent = (Student) session.getAttribute("Student");
	//System.out.print(aStudent);
	String title = "Perfil";
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
				</header>
				<div class="main">
					<section class="post">
						<div class="video-holder">
						</div>
						<div class="post-cnt">
							<h2>Welcome <%= aStudent.getFirstName() %></h2>
							<p>
							<br>
								<p><strong>Student Number: </strong><%= aStudent.getStudentNumber() %></p>
								<p><strong>Full Name: </strong><%= aStudent.getFirstName() %> <%= aStudent.getLastName() %>
								<p><strong>Phone: </strong><%= aStudent.getPhone()%>
								<p><strong>Email: </strong><%= aStudent.getEmail() %>
								<p><strong>Birth date: </strong><%= aStudent.getBirthdate() %>
								<p>
								<br>
							        <%=aStudent.getHTMLTranscript() %>
							</p>
						</div>
						<div class="cl">&nbsp;</div>
				</div>
				</section>
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
