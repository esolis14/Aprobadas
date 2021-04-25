// Cambio dinámico entre formulario de login y formulario de email
$("#switchForm").click(function(){
    if($("#login_form").is(":visible")) {
        $("#login_form").hide();
        $("#email_form").show();
        $("#switchForm > p").html("¿Tienes ya una cuenta?<br>Inicia sesión");
    } else {
        $("#login_form").show();
        $("#email_form").hide();
        $("#switchForm > p").html("¿Todavía no estás registrado?<br>Regístrate ya");
    }
})

// Cambio dinámico inputs de formulario de registro
$("#next_button").click(function(){
    if (checkNotEmpty()) {
        const tlf = document.getElementById("tlf");
        if (tlf.value.match(/^([679])([0-9]{2})\)?[- ]?([0-9]{3})[- ]?([0-9]{3})$/)) {
            $("#nombre").hide();
            $("#apellido").hide();
            $("#tlf").hide();
            $("#grado").hide();
            $("#next_button").hide();
            $(".info_message").show();
            $("#email").show();
            $("#password").show();
            $("#re_password").show();
            $("#register_button").show();
        } else {
            alert("Por favor, introduzca un teléfono con un formato válido (Ej: 666 777 888, 666-777-888)");
            borrarElement(tlf);
        }
    } else {
        alert("Por favor, rellene todos los campos.");
    }
})

// Comprueba que los campos del formulario no estén vacíos
function checkNotEmpty() {
    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const email = document.getElementById("email").value;
    const tlf = document.getElementById("tlf").value;
    return nombre !== "" && apellido !== "" && email !== "" && tlf !== "" && !($("#grado")[0].selectedIndex <= 0);
}

// Validación del formato del email (@ikasle.ehu.eus)
function validateEmail() {
    const email = document.getElementById("reg_email");
    if (!email.value.match(/^[A-Za-z0-9]+@ikasle.ehu.eus$/)) {
        alert("El email debe ser una dirección válida de @ikasle.ehu.eus");
        borrarElement(email);
        return false;
    } else  {
        return true;
    }
}

function validatePassword() {
    const password = document.getElementById("password");
    const rePassword = document.getElementById("re_password");

    // Compruebación que la contraseña cumpla la expresión regular:
    //      Longitud: min 8 caracteres, max: 20 caracetres
    //      Debe contener al menos: 1 letra mayúscula, 1 letra minúscula, 1 dígito y 1 símbolo
    if(!password.value.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,20}$/)) {
        alert("Las contraseñas no cumplen con el formato especificado.");
        borrarElement(password);
        borrarElement(rePassword);
        return false;
    } else if (password.value !== rePassword.value) { // Comprobación que ambas contraseñas sean iguales
        alert("Las contraseñas no coinciden.");
        borrarElement(password);
        borrarElement(rePassword);
        return false;
    } else {
        return true;
    }
}

// Elimina los datos introducidos y resalta el borde en rojo
function borrarElement(element) {
    element.value = "";
    element.style.borderColor = "red";
}