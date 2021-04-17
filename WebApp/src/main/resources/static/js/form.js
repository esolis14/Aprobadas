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

// Validación del formato del email (@ikasle.ehu.eus)
function validateEmail() {
    const email = document.getElementById("reg_email");
    if (!email.value.match(/^[A-Za-z0-9]+@ikasle.ehu.eus$/)) {
        alert("El email debe ser una dirección válida de @ikasle.ehu.eus");
        email.value = "";
        email.style.borderColor = "red";
        return false;
    } else  {
        return true;
    }
}

// Comprobación que ambas contraseñas sean iguales
function validatePassword() {
    const password = document.getElementById("password");
    const rePassword = document.getElementById("re_password");
    if(password.value === rePassword.value) {
        return true;
    } else {
        alert("Las contraseñas no coinciden.");
        password.value = "";
        rePassword.value = "";
        password.style.borderColor = "red";
        rePassword.style.borderColor = "red";
        return false;
    }
}