<%@ page import="metube.domain.models.view.TubeViewModel" %>
<%@ page import="java.util.*" %>
<%@ page import="metube.domain.models.view.TubeDetailViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Blagomira
  Date: 8.2.2019 г.
  Time: 16:03 ч.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TubeDetails</title>
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'/>
    <link rel="stylesheet" href='../css/style.css'>
</head>
<body>
<main>
    <%TubeDetailViewModel tube = (TubeDetailViewModel) request.getAttribute("tubeDetails");%>
    <div class="container">
        <div class="jumbotron">
            <div class="row"><h2><%=tube.getName()%></h2></div>
            <hr>
            <div class="row"><h3><%=tube.getDescription()%></h3></div>
            <br/>
            <hr>
            <div class="row">
                <div class="col-md-6" >
                    <a href="<%=tube.getLink()%>">Link to Video</a>
                </div>
                <div class="col-md-6">
                   <%=tube.getUploader()%>
                </div>
            </div>
            <hr>
            <a href="/">Back to Home</a>
        </div>
    </div>
</main>
<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
