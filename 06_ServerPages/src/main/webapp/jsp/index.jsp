<%--
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
    <title>MeTube</title>
</head>
<body>
<main>
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-12">
                <h2>Welcome to MeTube!</h2></div></div>
            <hr>
            <div class="row">
                <div class="col-md-12">
                <h3>Cool app in beta version</h3></div></div>
            <br/>
            <hr>
            <div class="row">
                <div class="col-md-6">
                    <a class="btn btn-primary" href="/tube/create">Create Tube</a>
                </div>
                <div class="col-md-6">
                    <a class="btn btn-primary" href="/tube/all">All Tubes</a>
                </div>
        </div>
    </div>
    </div>
</main>
<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>
