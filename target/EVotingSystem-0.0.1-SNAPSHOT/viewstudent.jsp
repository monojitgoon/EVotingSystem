<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="uni.bamberg.appengine.model.Student"%>
<%
	List<Student> Students = (List<Student>) request.getAttribute("StudentsList");
%>
<html>

<head>
<title>Electronic Voting System of University of Bamberg</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="resources/style/style.css"
	title="style" />
<link rel="stylesheet" href="resources/style/login-style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>


<!-- Bootstrap core CSS -->
<link href="resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>
	<div id="main">
		<div id="header">
			<div id="logo">
				<div id="logo_text">
					<!-- class="logo_colour", allows you to change the colour of the text -->

				</div>
			</div>
			<div id="menubar">
				<ul id="menu">
					<!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
					<li><a href="admin.jsp">Home</a></li>
					<li><a href="#">Election Management</a>
						<ul>
							<li><a href="timing.jsp">Timing</a></li>
							<li><a href="candidate.jsp">Candidate</a></li>
							<li><a href="studentimport.jsp">Student Email Import</a></li>
							<li><a href="sendvotingcard.jsp">Send Voting Card</a></li>
							<li><a href="result.jsp">Result</a></li>
						</ul></li>
					<c:choose>
						<c:when test="${not empty userEmail}">
							<!-- using pageContext requires jsp-api artifact in pom.xml -->
							<li><a style="text-transform: lowercase;" href="/logout">Logout
									${fn:escapeXml(userEmail)} </a></li>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>


				</ul>
			</div>
		</div>
		<div id="site_content">
			<a href="studentimport.jsp" class="btn btn-primary">Import new
				Students</a>
			<div class="contentgrid">

				<header class="content_header">
					<h3 class="content_title">Existing Candidates</h3>
				</header>
				<div class="content_body">
					<table class="table table-striped">
						<thead>
							<tr>

								<th class="col-md-4" scope="col">Email of student</th>
								<th class="col-md-4" scope="col">Token</th>
								<th class="col-md-4" scope="col">Isvalid</th>


							</tr>
						</thead>
						<tbody>
							<%
								for (Student cndt : Students) {
							%>
							<tr>
								<td class="col-md-4"><%=cndt.getEmail()%></td>
								<td class="col-md-4"><%=cndt.getToken()%></td>

								<td class="col-md-4"><%=cndt.getIsvalid()%></td>

							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<%-- <c:choose>
						<c:when test="${empty candidates}">
							<p>No candidates found</p>
						</c:when>
						<c:otherwise>
							<c:forEach items="${candidates}" var="candidates">
								<div class="media">
									<a href="/read?id=${candidates.firstname}">

										<div class="media-body">
											<h4>${fn:escapeXml(candidates.surname)}</h4>
											<p>${fn:escapeXml(candidates.faculty)}</p>
										</div>
									</a>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose> --%>




				</div>
			</div>
		</div>
		<div id="content_footer"></div>
		<div id="footer">Copyright &copy; DSG-DSAM-M: Assignment 2-
			Group 7 - Monojit Goon & Lucky Sutradhar (2018)</div>
	</div>
</body>
</html>
