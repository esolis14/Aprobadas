// Se muestra el formulario para crear un nuevo Anuncio
function mostrarForm() {
    document.getElementById("sec-0673").style.display = "block";
    document.getElementById("sec-0673").scrollIntoView();
}

function checkForm() {
    const descrip = document.getElementById("message-3b9a").value;
    if (descrip === "" || ($("#select-d846")[0].selectedIndex <= 0)) {
        alert("Por favor rellena todos los campos");
        return false;
    } else {
        return true;
    }
}