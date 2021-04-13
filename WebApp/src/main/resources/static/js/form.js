// Cambio dinámico entre formulario de login y formulario de email
$("#switchForm").click(function(){
    //$("login_form")
    const isLoginVisible = $("#login_form").is(":visible");
    if(isLoginVisible) {
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
    const password = document.getElementById("password").value;
    const rePassword = document.getElementById("re_password").value;
    return password === rePassword;
}