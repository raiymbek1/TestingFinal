<%@ page import="repositories.interfaces.IUserRepository" %>
<%@ page import="repositories.UserRepository" %>
<%@ page import="repositories.interfaces.IBookRepository" %>
<%@ page import="repositories.BookRepository" %>
<%@ page import="model.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Stack" %>
<%@ page import="controllers.ReaderBookController" %>
<%@ page import="model.ReaderBook" %>
<%@ page import="java.util.Collections" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nav_css.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">

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
        ReaderBookController readerBookController = new ReaderBookController();
        ArrayList<ReaderBook<Book>> borrowedBooks;
        if (readerBookController.getBooksBorrowed() == null) {
            borrowedBooks = null;
        } else {
            borrowedBooks = readerBookController.getBooksBorrowed();
        }
        Stack<Book> books = bookRepository.query();
    %>




    <div class="nav-mobile"><a id="nav-toggle" href="#!"><span></span></a></div>
    <ul class="nav">
        <li><a href="<%=request.getContextPath()%>/readers">Readers</a></li>
        <li><a href="<%=request.getContextPath()%>/login">Logout</a></li>
    </ul>







    <div id="app" class="container">
        <h3 class="mt-3">Add new book</h3>
        <hr>
        <form>
            <div class="row">
                <div class="col">
                    <label>Book name</label> <h6 style="color: red" id="regisErr2"></h6>
                    <input type="text" class="form-control" name="name" id="name">
                </div>
                <div class="col">

                    <label>Book author</label> <h6 style="color: red" id="regisErr3"></h6>
                    <input type="text" class="form-control" name="author" id="author">
                </div>
                <div class="col">

                    <label>Book copies</label> <h6 style="color: red" id="regisErr1"></h6>
                    <input type="text" class="form-control" name="copies" id="copies">
                </div>

            </div>
            <button class="btn btn-info mt-2" id="btn1" style="background-color: #95A5A6; border:#95A5A6;">Add Book</button>
        </form>
        <hr>




        <div class="container">
            <h2>List of books</h2>
            <ul class="responsive-table">
                <li class="table-header">
                    <div class="col col-1">ISBN</div>
                    <div class="col col-2">Book name</div>
                    <div class="col col-3">Book author</div>
                    <div class="col col-4">Book copies</div>
                    <div class="col col-5">Book info</div>
                </li>


                    <% for(Book book : books){%>
                <li class="table-row">
                    <div class="col col-1" data-label=""><%= book.getISBN() %></div>
                    <div class="col col-2" data-label=""><%= book.getName() %></div>
                    <div class="col col-3" data-label=""><%= book.getAuthor() %></div>
                    <div class="col col-4" data-label=""><%= book.getCopies() %></div>
                    <div class="col col-5" data-label="">
                        <form method="post" action="<%=request.getContextPath()%>/books">
                            <th>
                                <input style="display: none;" name="book_id" value="<%=book.getId()%>">
                                <button style="width: 100%; background-color: #95A5A6; border:#95A5A6;" class="btn btn-info mt-0" id="change">Info</button>
                            </th>
                        </form>
                    </div>
                </li>
                    <% } %>


            </ul>
        </div>



        <br>


    </div>
</div>
<script src="${pageContext.request.contextPath}/js/booksPage_js.js"></script>
<%--<script src="${pageContext.request.contextPath}/js/booksPage_js.js"></script>--%>

</body>
</html>