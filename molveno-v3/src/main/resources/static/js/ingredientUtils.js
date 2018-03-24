var baseUrl = "http://localhost:8080/api/";


$(document).ready(function(){
console.log("doc ready");

      $("#updateButton").click(function(){
        $.ajax({

        url : baseUrl+"ingredients",

        type: "get",

        success: function(data){

        ingredientList = "";

            $.each(data,function(index, current){

                ingredientList += createIngredientString(current);
            });

            $("#ingredientList").html(ingredientList);
           }
        });
     });

    $("#addButton").click(function(){

            var jsonObject={
                name: $("#nameIngredient").val(),
                numberOfStock: Number($("#numberOfStock").val()),
                costPrice: Number($("#costPrice").val())
            };


                $.ajax({
                        contentType : "application/json",
                         // waar moet hij de request op uitvoeren
                         url : baseUrl+"ingredients",
                          // type actie
                         type : "post",
                         data: JSON.stringify(jsonObject),
                          // als de actie lukt, voer deze functie uit
                          success: function(data){ // so the data is the bulb of the response of the Spring Boot REST controller
                                console.log(data);
                          }
                });

    });


});

function createIngredientString(ingredient){


    result = "<tr><td>"+"Ingredient: "+ingredient.name+"</td><td>"+"Stock: "+ingredient.numberOfStock+"g"+"</td><td>"+"Price: € "+ingredient.costPrice+"</td></tr>";
return result;
}

function toggle(){

$("table").slideToggle("10")
}