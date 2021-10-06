<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Collection" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="en">

<head>
    <!--Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="cache-control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="expires" content="Fri, 20 Mar 2014 00:00:00 GMT"/>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/validate.js"></script>
    <title>
        <% if (request.getParameter("id") == null) { %>
        Add post
        <% } else { %>
        Edit post
        <% } %>
    </title>
</head>

<body class="d-flex  text-center text-white bg-dark">
<div class="container d-flex w-100 p-3 mx-auto flex-column">
    <header class="p-3 bg-dark text-white">
        <div class="px-3 py-2 mb-3">
            <div class="container d-flex flex-wrap justify-content-center">
                <a href="/cars/" class="col-12 col-lg-auto mb-2 mb-lg-0 me-lg-auto">
            <span style="font-size: 2rem;">
              <span style="color: rgb(250, 250, 246);">
                <i class="fa fa-superpowers" aria-hidden="true"></i>
              </span>
            </span>
                </a>
                <div class="text-end" id="loginButtons">
                    <a href="/cars/login.html" class="btn btn-outline-light me-2">Login</a>
                    <a href="/cars/reg.html" class="btn btn-info me-2">Sign-up</a>
                </div>
            </div>
        </div>
    </header>
    <main>
        <section class="mb-4 text-center container">
            <div class="row py-lg-3">
                <div class="col-lg-9 col-md-3 mx-auto">
                    <h1 class="fw-light">
                        <% Integer id;
                            if (request.getParameter("id") == null) {
                                id = 0;
                        %>
                        Add form
                        <% } else {
                            id = Integer.valueOf(request.getParameter("id"));
                        %>
                        Edit form
                        <% } %>
                    </h1>
                    <p class="lead text-muted">Just a few steps separate you from publishing an post!</p>
                </div>
            </div>
        </section>
        <div class="container col-xl-10 col-xxl-8 ">
            <div class="row align-items-top">
                <div class="col-lg-5 text-center text-lg-start">
                    <h1 class="display-4 fw-bold lh-1 mb-3">Attention please</h1>
                    <p class="col-lg-10 fs-4 mt-5">Below tate thae form without completing it.</p>
                    <ul class="col-lg-10 fs-4">
                        <li>Type the required data</li>
                        <li>Choose file from your photo album</li>
                        <li>Press the action button</li>
                    </ul>
                </div>
                <div class="col-md-10 mx-auto col-lg-7">
                    <form action="/cars/add.do" method="post" enctype="multipart/form-data"
                          class="p-4 p-md-5 border rounded-3 bg-dark">
                        <div class="form-floating mb-3">
                            <input type="hidden" name="id" value="<%=id%>" id="hidden">
                            <input name="brand" type="text" class="form-control bg-dark text-white" id="brand">
                            <label for="brand">Brand</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input name="type" type="text" class="form-control bg-dark text-white" id="type">
                            <label for="type">Body type</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input name="power" type="number" class="form-control bg-dark text-white" id="power">
                            <label for="power">Power</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input name="description" type="text" class="form-control bg-dark text-white"
                                   id="description">
                            <label for="description">Description</label>
                        </div>
                        <div class="input-group mb-3">
                            <label class="input-group-text bg-dark text-white"
                                   for="photo">Photo</label>
                            <input name="photo" type="file" class="form-control bg-dark text-white"
                                   id="photo">
                        </div>
                        <div class="checkbox mb-3 text-lg-start">
                            <label>
                                <input class="form-check-input" value="sale"
                                       name="sale" type="checkbox"> Sale
                            </label>
                        </div>
                        <button onclick="return validate()" class="w-100 btn btn-lg btn-info"
                                type="submit">
                            <% if (request.getParameter("id") == null) { %>
                            Add post
                            <% } else { %>
                            Edit post
                            <% } %>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </main>
    <footer class="mt-5 text-white-50">
        <p>Cover template for <a href="https://getbootstrap.com/" class="text-white">Bootstrap</a>, by <a
                href="https://twitter.com/mdo" class="text-white">@mdo</a>.</p>
    </footer>
</div>
<!-- JavaScript Bundle with Popper-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>

</html>
