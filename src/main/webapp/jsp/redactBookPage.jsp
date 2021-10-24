<%@ page import="model.Book" %>
<%@ page import="java.io.PrintWriter" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nav_css.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>Title</title>
</head>
<body>
<%
    if(session.getAttribute("username")==null) {
        response.sendRedirect("login.jsp");
    }
    Book book = (Book) request.getAttribute("book");
%>

<div class="nav-mobile"><a id="nav-toggle" href="#!"><span></span></a></div>
<ul class="nav">
    <li><a href="http://localhost:8080/CalendarEvents_war_exploded/jsp/index.jsp">Back</a>
    </li>

</ul>

<%--<a href="http://localhost:8080/CalendarEvents_war_exploded/jsp/index.jsp">Back</a>--%>
<div id="app" class="container">
    <h3 class="mt-3">Book details</h3>
    <hr>
    <div class="row">
        <div class="col">
            <label>Book ISBN</label>
            <input type="text" readonly class="form-control" name="name" value="<%= book.getISBN() %>">
            <h6 style="color: red"></h6>
        </div>
        <div class="col">
            <label>Book name</label>
            <input type="text" class="form-control" name="name" id="name" value="<%= book.getName() %>">
            <h6 style="color: red" id="regisErr2"></h6>
        </div>
        <div class="col">
            <label>Book author</label>
            <input type="text" class="form-control" name="author" id="author" value="<%= book.getAuthor() %>">
            <h6 style="color: red" id="regisErr3"></h6>
        </div>
        <div class="col">
            <label>Book copies</label>
            <input type="text" class="form-control" name="copies" id="copies" value="<%= book.getCopies() %>">
            <h6 style="color: red" id="regisErr1"></h6>
            <input style="display: none;" id="book_id" value="<%=book.getId()%>">
        </div>
    </div>
    <br>
    <button class="btn btn-info mt-0" id="btnRedact" style="background-color: #95A5A6; border:#95A5A6;">Edit</button>
    <button class="btn btn-danger" id="delete">Delete</button>
    <hr>
</div>

<script src="${pageContext.request.contextPath}/js/booksPage_js.js"></script>
</body>
</html>
