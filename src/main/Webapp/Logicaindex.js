var formulario = document.getElementById("formulario");
formulario.addEventListener("submit",function(e){
    e.preventDefault();
    console.log("le diste click")
    var data = {
        "username": document.getElementById("username").value,
        "password": document.getElementById("password").value,
    };
    console.log( document.getElementById("username").value+" ets es el correo en login");
    localStorage.setItem("email",document.getElementById("username").value);
    console.log(data);
    console.log(data["username"]);
    console.log(data["password"]);
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Access-Control-Allow-Origin", "*");
    var newdata= fetch("./api/Users/found",{method: "POST",
        body: JSON.stringify(data),
        headers: myHeaders
    })
        .then(res =>
            res.json()
        )
        .then(dato => {
            console.log("se esta intentando tener el dato");
            console.log(dato)
            console.log(dato["username"]+" este es el username");
            console.log(dato["fcoins"]+" estes cantidad de fcoins");
            console.log(dato["descrip"]+" este es la descripcion");
            localStorage.setItem("username",dato["username"]);
            localStorage.setItem("role",dato["role"]);
            localStorage.setItem("fcoins",dato["fcoins"]);
            localStorage.setItem("descrip",dato["descrip"]);
            console.log("cambipo nuevo en javascript");
            console.log("este es el rol "+dato["role"]);
            if(dato["role"] == "Artist"){
                window.location.href = "http://localhost:8080/Taller4-1.0-SNAPSHOT/Artista.html";
            }else if(dato["role"] == "Costumer"){
                window.location.href = "http://localhost:8080/Taller4-1.0-SNAPSHOT/comprador.html";
            }
        })

})