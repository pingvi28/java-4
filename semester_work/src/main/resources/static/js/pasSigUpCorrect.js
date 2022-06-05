//---------Проверка комбинаций символов при вводе пароля------------
document.addEventListener('DOMContentLoaded', function () {
    window.onload = function () {
        document.getElementById("password").onchange = validatePassword;
        document.getElementById("passwordRepeat").onchange = validatePassword;
    }
    function validatePassword(){
        let pass2 = document.getElementById("password").value;
        let pass1 = document.getElementById("passwordRepeat").value;

        if(pass1 !== pass2)
            document.getElementById("passwordRepeat").setCustomValidity("Пароли не совпадают");
        else
            document.getElementById("passwordRepeat").setCustomValidity("");
    }

});

