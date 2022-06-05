document.addEventListener('DOMContentLoaded', function () {
    $('body').on('click', '.password-control', function(){
        if ($('#password').attr('type') === 'password'){
            $(this).addClass('view');
            $('#password').attr('type', 'text');
        } else {
            $(this).removeClass('view');
            $('#password').attr('type', 'password');
        }
        return false;
    });

    $('body').on('click', '.password-control2', function(){
        if ($('#passwordRep').attr('type') === 'password'){
            $(this).addClass('view');
            $('#passwordRep').attr('type', 'text');
        } else {
            $(this).removeClass('view');
            $('#passwordRep').attr('type', 'password');
        }
        return false;
    });
    $('body').on('click', '.password-control3', function(){
        if ($('#passwordCur').attr('type') === 'password'){
            $(this).addClass('view');
            $('#passwordCur').attr('type', 'text');
        } else {
            $(this).removeClass('view');
            $('#passwordCur').attr('type', 'password');
        }
        return false;
    });
    $('body').on('click', '.password-control4', function(){
        if ($('#passwordCur2').attr('type') === 'password'){
            $(this).addClass('view');
            $('#passwordCur2').attr('type', 'text');
        } else {
            $(this).removeClass('view');
            $('#passwordCur2').attr('type', 'password');
        }
        return false;
    });
    $('body').on('click', '.password-control5', function(){
        if ($('#passwordCur3').attr('type') === 'password'){
            $(this).addClass('view');
            $('#passwordCur3').attr('type', 'text');
        } else {
            $(this).removeClass('view');
            $('#passwordCur3').attr('type', 'password');
        }
        return false;
    });
});

