var formulario = document.getElementById("formulario");
formulario.addEventListener("submit",function(e){
    e.preventDefault();
    console.log("le diste click")
    var dato=new FormData(formulario);
    console.log(dato);
    console.log(dato.get("username"));
    console.log(dato.get("password"));
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Access-Control-Allow-Origin", "*");
    var newdata= fetch("./api/Users/found",{method: "POST",
        body: JSON.stringify(dato),
        headers: myHeaders
    })
        .then(res =>
            res.json()
        )
        .then(data => {
            console.log("se esta intentando tener el dato")
            console.log(data)})
})