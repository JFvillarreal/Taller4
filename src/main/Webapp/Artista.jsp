<%--
  Created by IntelliJ IDEA.
  User: jpiza
  Date: 23/04/2022
  Time: 9:00 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./Artista.css">
</head>
<body style="background: #1B2678">
<header style="background: black" ><h1 style="color: #0097A7"><%= "Artist : " + request.getParameter("username") %></h1></header>

<h1>Generate new art piece</h1>
<form  action="./uploadFile" method="post" enctype="multipart/form-data" style="background: #6ACA1B">

    Precio: <input type="text"  name="fcoins"/>
    Put your username again please: <input type="text" name="artist">
    titulo: <input type="text" name="titulo">

    Choose a file: <input type="file" name="multiPartServlet"/>
    <input type="submit" value="Upload"/>
</form>

<a href="./recargar.html" style="background: #65FF33" style="color: #FFFFFF" > Loaded your count</a>
</body>

</body>
</html>
