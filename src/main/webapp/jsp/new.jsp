<%--
  Created by IntelliJ IDEA.
  User: Райымбек
  Date: 06.11.2020
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" />
</head>
<body>
<p>this is a 1 text</p>
<p>this is a 2 text</p>
<p>this is a 3 text</p>

<script>
    $("p").click(function (){
        $(this).slideUp();
    });
</script>

</body>
</html>
