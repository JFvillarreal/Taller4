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
<header style="background: black" id="head"></header>
<script>
    var header= document.getElementById("head");
    console.log("se esta pasandp por la linea 17 de la pagina html")
    let titele= document.createElement("h1");
    console.log(localStorage.getItem("username")+" este es el username de el localstorage");
    console.log("este es jabascritp con arreglo")
    titele.innerHTML=localStorage.getItem("username");
    header.appendChild(titele);
</script>
<h1>Generate new art piece</h1>
<form  action="./api/Users/<%=  request.getParameter("username") %>/imagen" method="post" enctype="multipart/form-data" style="background: #6ACA1B">

    Precio: <input type="text"  name="fcoins"/>
    Put your username again please: <input type="text" name="artist">
    titulo: <input type="text" name="titulo">

    colecction:  <input type="text" name="colecction">

    Choose a file: <input type="file" name="multiPartServlet"  />
    <input type="submit" value="Upload" enctype="multipart/form-data"/>
</form>

<a href="./recargar.html" style="background: #65FF33" style="color: #FFFFFF" > Loaded your count</a>
</body>

</body>
</html>
