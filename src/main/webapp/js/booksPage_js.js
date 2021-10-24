$(document).ready(function() {
    $("#btn1").click(function(){
        if(NameValid() && AuthorValid() && CopiesValid) {
            $.ajax({
                url: "http://localhost:8080/CalendarEvents_war_exploded/addbook",
                type: "POST",
                data: {"name": $("#name").val(), "author": $("#author").val(), "copies": $("#copies").val()},
                beforeSend: function () {
                    $("#btn1").prop("disabled", true);
                },
                success: function (data) {
                    $("#btn1").prop("disabled", false);
                    location.reload(true);
                }
            });
        }
    });

    $("#delete").click(function() {
        $.ajax({
            url:"http://localhost:8080/CalendarEvents_war_exploded/deletebook",
            type:"get",
            data:{"id":$("#book_id").val()},
            beforeSend:function(){
                $("#delete").prop("disabled",true);
            },
            success:function(data){
                $("#delete").prop("disabled",false);
                $(location).attr('href', 'http://localhost:8080/CalendarEvents_war_exploded/jsp/index.jsp');
            }
        });
    });

    $("#btnRedact").click(function() {
        if(NameValid() && AuthorValid() && CopiesValid) {
            $.ajax({
                url: "http://localhost:8080/CalendarEvents_war_exploded/redactBook",
                type: "post",
                data: {
                    "name": $("#name").val(),
                    "author": $("#author").val(),
                    "copies": $("#copies").val(),
                    "book_id": $("#book_id").val()
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

    // Author Valid
    var AuthorValid = function(){
        name = $('#author').val();
        if (name == '') {
            $('#regisErr3').text('* This field is required !');
            $('#author').focus();
            return false;
        }else{
            $('#regisErr3').removeClass('error').text('');
            return true;
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

    //Copies Vaildation
    var CopiesValid = function(){
        name = $('#copies').val();
        if (name == '') {
            $('#regisErr1').text('* This field is required !');
            $('#copies').focus();
            return false;
        }else{
            $('#regisErr1').removeClass('error').text('');
            return true;
        }
    };


    $("#name").keyup(function(){
        NameValid();
    });
    $("#author").keyup(function(){
        AuthorValid();
    });
    $("#copies").keyup(function(){
        CopiesValid();
    });

});
