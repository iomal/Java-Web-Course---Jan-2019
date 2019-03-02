<%--
  Created by IntelliJ IDEA.
  User: Blagomira
  Date: 8.2.2019 г.
  Time: 18:07 ч.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<main>
    <div class="container">
        <div class="jumbotron">
            <div class="row"><h2>Create Tube!</h2></div>
            <hr>
            <div class="row">
                <form class="form" action="/tube/create" method="post">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-lg-4 col-lg-offset-4">
                            <label  for="title">Title</label>
                            </div>
                        </div>
                        <div class="row">
                            <input class="col-lg-4 col-lg-12" type="text" id="title" name="name">
                        </div>
                        <br/>
                        <div class="row">
                            <label class="col-lg-4 col-lg-offset-4" for="descr">Description</label>
                        </div>
                        <div class="row">
                        <textarea  class="col-lg-4 col-lg-offset-4" style="resize: none;" cols="75" rows="4" id="descr" name="descr">
                            </textarea>
                        </div>
                        <br/>
                        <div class="row">
                        <label class="col-lg-4 col-lg-offset-4" for="link">YouTube Link</label>
                    </div>
                        <div class="row">
                            <input class="col-lg-4 col-lg-offset-4" type="text" id="link" name="link">
                        </div>
                        <br/>
                        <div class="row">
                            <label class="col-lg-4 col-lg-offset-4" for="uploader">Uploader</label>
                        </div>
                        <div class="row">
                            <input class="col-lg-4 col-lg-offset-4" type="text" id="uploader" name="uploader">
                        </div>
                        <br/>
                        <div class="row">
                            <button type="submit" class="btn btn-primary">Create Tube</button>
                        </div>
                    </div>
                </form>
                <br/>
            </div>
            <hr>
            <div class="col-lg-4 col-lg-offset-4">
                <a  href="/">Back to Home</a>
            </div>
        </div>
    </div>
</main>
<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>
