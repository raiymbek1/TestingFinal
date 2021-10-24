$(document).ready(function() {
    $("#btn1").click(function(){
        if(EmailValid() && NameValid() && SurnameValid()) {
            $.ajax({
                url: "http://localhost:8080/CalendarEvents_war_exploded/adduser",
                type: "POST",
                data: {"name": $("#name").val(), "email": $("#email").val(), "surname": $("#surname").val()},
                beforeSend: function () {
                    $("#btn1").prop("disabled", true);
                },
                success: function (data) {
                    alert(data);
                    $("#btn1").prop("disabled", false);
                    location.reload(true);
                }
            });
        }
    });

    $("#delete").click(function() {
        $.ajax({
            url:"http://localhost:8080/CalendarEvents_war_exploded/deleteUser",
            type:"get",
            data:{"id":$("#reader_id").val()},
            beforeSend:function(){
                $("#delete").prop("disabled",true);
            },
            success:function(data){
                alert(data);
                $("#delete").prop("disabled",false);
                $(location).attr('href', 'http://localhost:8080/CalendarEvents_war_exploded/readers?');
            }
        });
    });

    $("#addBook").click(function() {
        $.ajax({
            url:"http://localhost:8080/CalendarEvents_war_exploded/addBook",
            type:"post",
            data:{"ISBN":$("#ISBN").val(),"reader_id":$("#reader_id").val()},
            beforeSend:function(){
                $("#addBook").prop("disabled",true);
            },
            success:function(data){
                alert(data);
                $("#addBook").prop("disabled",false);
                location.reload(true);
            },
            error:function (){
                alert("Servlet error");
            }
        });
    });


    $("#btnRedact").click(function() {
        if(EmailValid() && NameValid() && SurnameValid()) {
            $.ajax({
                url: "http://localhost:8080/CalendarEvents_war_exploded/redact",
                type: "post",
                data: {
                    "id": $("#reader_id").val(),
                    "name": $("#name").val(),
                    "email": $("#email").val(),
                    "surname": $("#surname").val()
                },
                beforeSend: function () {
                    $("#btnRedact").prop("disabled", true);
                },
                success: function (data) {
                    $("#btnRedact").prop("disabled", false);
                    alert(data);
                    location.reload(true);
                },
                error: function () {
                    alert("Servlet ERROR");
                }
            });
        }
    });


    //Email validation
    var EmailValid = function(){
        email = $('#email').val();
        atpos = email.indexOf('@');
        dotpos = email.lastIndexOf('.');
        if (email == '') {
            $('#regisErr1').text('* This field is required !');
            $('#email').focus();
            return false;
        }else{
            if(atpos < 1 || dotpos < atpos+2 || dotpos+2 >= email.length) {
                $('#regisErr1').text('It is not a valid email address!');
                $('#email').focus();
                return false;
            }
            else{
                $('#regisErr1').text('');
                return true;
            }
        }
    };


    //Name Vaildation
    var NameValid = function(){
        name = $('#name').val();
        if (name == '') {
            $('#regisErr2').text('* This field is required !');
            $('#name').focus();
            return false;
        }else{
            $('#regisErr2').removeClass('error').text('');
            return true;
        }
    };

    //Surname Vaildation
    var SurnameValid = function(){
        surname = $('#surname').val();
        if (surname == '') {
            $('#regisErr3').text('* This field is required !');
            $('#surname').focus();
            return false;
        }else{
                $('#regisErr3').text('');
                return true;
        }
    };

    $("#email").keyup(function(){
        EmailValid();
    });
    $("#name").keyup(function(){
        NameValid();
    });
    $("#surname").keyup(function(){
        SurnameValid();
    });

});
