// Cambio dinámico entre formulario de login y formulario de registro
$("#switchForm").click(function(){
    $("login_form")
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