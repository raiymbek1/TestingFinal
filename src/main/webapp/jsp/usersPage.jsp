<%@ page import="repositories.UserRepository" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Reader" %>
<%@ page import="repositories.PostgresRepository" %>
<%@ page import="repositories.interfaces.IUserRepository" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nav_css.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
</head>
<body>
<%
    if(session.getAttribute("username")==null) {
        response.sendRedirect("login.jsp");
    }
    IUserRepository userRepository = new UserRepository();
    ArrayList<Reader> readers = userRepository.query();
    Collections.sort(readers);
%>
<div class="nav-mobile"><a id="nav-toggle" href="#!"><span></span></a></div>
<ul class="nav">
    <li><a href="http://localhost:8080/CalendarEvents_war_exploded/jsp/index.jsp">
        Books
    </a></li>
    <li><a href="<%=request.getContextPath()%>/login">Logout</a></li>
</ul>

<div id="app" class="container">
    <h3 class="mt-3">Add reader</h3>
    <hr>
    <form>
    <div class="row">
        <div class="col">
            <label>Reader Name</label>
            <input type="text" class="form-control" name="name" id="name">
            <h6 style="color: red" id="regisErr2"></h6>
        </div>
        <div class="col">
            <label>Reader Surname</label>
            <input type="text" class="form-control" name="surname" id="surname">
            <h6 style="color: red" id="regisErr3"></h6>
        </div>
        <div class="col">
            <label>Reader Email</label>
            <input type="text" class="form-control" name="email" id="email">
            <h6 style="color: red" id="regisErr1"></h6>
        </div>

    </div>
    <button style="background-color: #95A5A6; border:#95A5A6;" class="btn btn-info mt-2" id="btn1">Add Reader</button>
    </form>
    <hr>


    <div class="container">
        <h2>List of readers</h2>
        <ul class="responsive-table">
            <li class="table-header">
                <div class="col col-1">Reader ID</div>
                <div class="col col-2">Reader Name</div>
                <div class="col col-3">Reader Surname</div>
                <div class="col col-4">Reader Email</div>
                <div class="col col-5">User info</div>
            </li>
                </thead>

                <% for(Reader reader : readers){%>
                <li class="table-row">
                    <div class="col col-1" data-label=""><%= reader.getId() %></div>
                    <div class="col col-2" data-label=""><%= reader.getName() %></div>
                    <div class="col col-3" data-label=""><%= reader.getSurname() %></div>
                    <div class="col col-4" data-label=""><%= reader.getEmail() %></div>
                    <div class="col col-5" data-label="">
                        <form method="post" action="<%=request.getContextPath()%>/readers">
                        <th>
                            <input style="display: none;" name="reader_id" value="<%=reader.getId()%>">
                            <button style="width: 100% ;background-color: #95A5A6; border:#95A5A6;" class="btn btn-info mt-0" id="change">Info</button>
                        </th>
                        </form>
                    </div>
                <% } %>
                </li>
            </table>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/userPage_js.js"></script>
</body>
</html>
