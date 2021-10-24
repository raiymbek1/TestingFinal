<%@ page import="repositories.interfaces.IBookRepository" %>
<%@ page import="repositories.BookRepository" %>
<%@ page import="controllers.ReaderBookController" %>
<%@ page import="model.Book" %>
<%@ page import="model.ReaderBook" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Stack" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nav_css.css">


    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<link href="https://fonts.googleapis.com/css?family=Julius+Sans+One&display=swap" rel="stylesheet">
<div class="lib">
    <%
        if(session.getAttribute("username")==null) {
            response.sendRedirect("login.jsp");
        }
        IBookRepository bookRepository = new BookRepository();
        Stack<Book> books = bookRepository.query();
    %>

    <div class="nav-mobile"><a id="nav-toggle" href="#!"><span></span></a></div>
    <ul class="nav">
        <li><a href="<%=request.getContextPath()%>/login">Logout</a></li>
    </ul>




    <div class="container">
        <h2>Available Books for our Library</h2>

        <ul class="responsive-table">

            <li class="table-header">
                <div class="col col-6">ISBN</div>
                <div class="col col-7">Book Name</div>
                <div class="col col-8">Book Author</div>
                <div class="col col-9">Book Copies</div>
            </li>


            <% for(Book book : books){%>
            <li class="table-row">

                <div class="col col-6" data-label=""><%= book.getISBN() %></div>
                <div class="col col-7" data-label=""><%= book.getName() %></div>
                <div class="col col-8" data-label=""><%= book.getAuthor() %></div>
                <div class="col col-9" data-label=""><%= book.getCopies() %></div>
            </li>

            <% } %>

        </ul>
    </div>
    <h6>If want to borrow book, please contact to Librarian</h6>
</div>


$( "#target" ).click(function() {

alert( "Handler for .click() called." );

});



<script src="${pageContext.request.contextPath}/js/booksPage_js.js"></script>
<%--<script src="${pageContext.request.contextPath}/js/booksPage_js.js"></script>--%>

</body>
</html>