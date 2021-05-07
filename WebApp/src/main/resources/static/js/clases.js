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

function filterOfertas() {
    // Variables
    const selectedCuso = document.getElementById("select-197f").value;
    const selectedAsignatura = document.getElementById("select-636b").value;

    let ofertas, auxCurso, auxAsignatura;
    ofertas = document.getElementById("desplegable-ofertas").getElementsByClassName("clase-oferta");

    // Recorrer todas las filas y ocultar aquellas que no cumplan la condición del filtro
    for (let i = 0; i < ofertas.length; i++) {
        auxAsignatura = ofertas[i].getElementsByClassName("clase-nombre-asignatura")[0].innerHTML;
        auxCurso = ofertas[i].getElementsByClassName("clase-curso-asignatura")[0].textContent[0];

        if (selectedCuso !== "" && selectedCuso !== auxCurso)  {
            ofertas[i].style.display = "none"; // ocultar fila
        } else if (selectedAsignatura !== "" && selectedAsignatura !== auxAsignatura)  {
            ofertas[i].style.display = "none"; // ocultar fila
        } else {
            ofertas[i].style.display = ""; // mostrar fila
        }
    }
}