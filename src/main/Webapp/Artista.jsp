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
<body style=" background: url(./img/marilyn.jpg);">
<header id="head"><h1 style="color: rgba(5,103,47,0.68)"><%= "Artist : " + request.getParameter("username") %></h1></header>
<script>
    var header= document.getElementById("head");
    fetch("./api/Users/formindex")
    .then(response => response.json())
        .then(images => {
            images.map(image => {
                // Creating the image element in DOM
                let header=document.createElement("h2");
                header.innerText=images[username];
                head.appendChild(header);
            });
        });
</script>
<h1>Generate new art piece</h1>
<form  action="./api/Users/<%=  request.getParameter("username") %>/imagen" method="post" enctype="multipart/form-data">
    <div class="ref1">Precio: <input type="text"  name="fcoins"/></div>
    <div class="ref1">Put your username again please: <input type="text" name="artist"></div>

   <div class="ref1"> titulo: <input type="text" name="titulo"></div>

    <div class="ref1">colecction:  <input type="text" name="colecction"></div>

    Choose a file: <input type="file" name="multiPartServlet"  />
    <input type="submit" value="Upload" enctype="multipart/form-data"/>
</form>

<a href="./recargar.html" style="background: #65FF33" style="color: #FFFFFF" > Loaded your count</a>
</body>

</body>
</html>
