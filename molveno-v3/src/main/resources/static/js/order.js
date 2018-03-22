var baseUrl= "http://localhost:8080/api/"

$(document).ready(function(){

    $ajax("#getDrinkbutton").click(function(){
            url:baseUrl+"order/getdrink/{id}"


    });

})