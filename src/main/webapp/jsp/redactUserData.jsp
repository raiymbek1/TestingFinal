<%@ page import="model.Reader" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="controllers.ReaderBookController" %>
<%@ page import="model.Book" %>
<%@ page import="model.BorrowedBook" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nav_css.css">
    <title>Title</title>
</head>
<body>
<%
    if(session.getAttribute("username")==null) {
        response.sendRedirect("login.jsp");
    }
    Reader reader = (Reader) request.getAttribute("reader");
    ReaderBookController readerBookController = new ReaderBookController();
    ArrayList<BorrowedBook> books = readerBookController.getBookByReaderId(reader.getId());
    Collections.sort(books);
%>
<div class="nav-mobile"><a id="nav-toggle" href="#!"><span></span></a></div>
<ul class="nav">
    <li><a href="http://localhost:8080/CalendarEvents_war_exploded/readers">
        Back
    </a></li>
    <li><a href="<%=request.getContextPath()%>/login">Logout</a></li>
</ul>

<div id="app" class="container">
    <h3 class="mt-3">Profile details</h3>
    <hr>
    <div class="row">
        <div class="col">
            <label>Reader Name</label>
            <input type="text" class="form-control" name="name" id="name" value="<%= reader.getName() %>">
            <h6 style="color: red" id="regisErr2"></h6>
        </div>
        <div class="col">
            <label>Reader Surname</label>
            <input type="text" class="form-control" name="surname" id="surname" value="<%= reader.getSurname() %>">
            <h6 style="color: red" id="regisErr3"></h6>
        </div>
        <div class="col">
            <label>Reader Email</label>
            <input type="text" class="form-control" name="email" id="email" value="<%= reader.getEmail() %>">
            <input style="display: none;" id="reader_id" value="<%=reader.getId()%>">
            <h6 style="color: red" id="regisErr1"></h6>
        </div>
    </div>
    <br>
    <button style="background-color: #95A5A6; border:#95A5A6;" class="btn btn-info mt-0" id="btnRedact">Edit</button>
    <button style="" class="btn btn-danger" id="delete">Delete</button>

    <h4 class="mt-3">Borrow book</h4>
    <div class="row">
        <div class="col">
            <label>Book ISBN</label> <h6 style="color: red"></h6>
            <input type="text" class="form-control" id="ISBN">
        </div>
    </div>
    <br>
    <button class="btn btn-info mt-0" style="background-color: #95A5A6; border:#95A5A6;"id="addBook">Borrow</button>

    <div class="container">
        <h2>Borrowed books</h2>
        <ul class="responsive-table">
            <li class="table-header">

                <div class="col col-1">Book ID</div>
                <div class="col col-2">Book ISBN</div>
                <div class="col col-3">Book name</div>
                <div class="col col-4">Author</div>
                <div class="col col-5">Remove book</div>
            </li>

                            <% for(BorrowedBook book: books){%>
            <li class="table-row">
                                <div class="col col-1" data-label=""><%= book.getId() %></div>
                                <div class="col col-2" data-label=""><%= book.getISBN() %></div>
                                <div class="col col-3" data-label=""><%= book.getName() %></div>
                                <div class="col col-4" data-label=""><%= book.getAuthor() %></div>
                                <div class="col col-5" data-label="">
                                <form method="get" action="<%=request.getContextPath()%>/removeBook">

                                        <input style="display: none;" name="reader_id" value="<%=reader.getId()%>">
                                        <input style="display: none;"  name="borrow_id" value="<%=book.getId_borrowedBook()%>">
                                        <button style="width: 100%;" class="btn btn-danger" id="change">Remove</button>

                                </form>
                                </div>
            </li>
                            <% } %>


        </ul>
        </div>

    </div>
</div>

<script src="${pageContext.request.contextPath}/js/userPage_js.js"></script>
</body>
</html>
