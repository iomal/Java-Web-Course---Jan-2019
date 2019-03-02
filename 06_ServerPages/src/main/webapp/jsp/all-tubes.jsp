<%@ page import="metube.domain.models.view.TubeViewModel" %>
<%@ page import="java.util.*" %><%--
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
    <title>AllTubes</title>
</head>
<body>
<main>
    <%List<TubeViewModel> tubes = (List<TubeViewModel>) request.getAttribute("tubes");%>
    <div class="container">
        <div class="jumbotron">
            <div class="row"><h2>All Tubes</h2></div>
            <hr>
            <div class="row"><h3>Check our tubes below</h3></div>
            <br/>
            <hr>
            <div class="row">
                <ul>
                <% for (int i = 0; i < tubes.size(); i++) {%>
                <li><a href="/tube/details?id=<%=tubes.get(i).getId()%>"><%=tubes.get(i).getName()%></a></li>
                <%}%>
                </ul>
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
</html>
