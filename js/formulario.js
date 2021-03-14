// Cambio dinámico entre formulario de login y formulario de registro
$("#switchForm").click(function(){
    $("login_form")
    const isLoginVisible = $("#login_form").is(":visible");
    if(isLoginVisible) {
        $("#login_form").hide();
        $("#register_form").show();
        $("#switchForm > p").html("Iniciar sesión");
    } else {
        $("#login_form").show();
        $("#register_form").hide();
        $("#switchForm > p").html("¿Todavía no estás registrado?<br>Regístrate ya.");
    }
})