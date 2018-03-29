var baseUrl = "http://localhost:8080/api/"
//var baseUrl ="/api/";

$(document).ready(function(){
console.log("doc ready");

$('#modalButton').click(activateModal);

function activateModal (){
$('#myModalInput').modal('toggle');
}

$("#addButton").click(function() {

        var jsonObject = {
            firstName: $("#firstNameInput").val(),
            lastName: $("#lastNameInput").val(),
            amountOfPeople: Number($("#amountOfPeopleInput").val()),
            reservationTime: $("#reservationTimeInput").val()
        };

        console.log(JSON.stringify(jsonObject));

        $.ajax({
                 contentType : "application/json",
                 // waar moet hij de request op uitvoeren
                 url : baseUrl+"reservation",
                 // type actie
                 type : "post",
                 data: JSON.stringify(jsonObject),
                 // als de actie lukt, voer deze functie uit
                 success: function(data){ // so the data is the bulb of the response of the Spring Boot REST controller
                      console.log(data);
                 }
             });

             updateTable();
             $('#myModalInput').modal('toggle');
      });

});


var updateTable = function(){
  // var baseUrl = "/api/";
  console.log("ik start update");
    /*$.ajax({



        url : baseUrl+"reservation",
        type : "get",

        success : function(data){

              reservationList ="";

              $.each(data, function(index, current){
                 reservationTable += createReservationString(current);
              });

              $("#reservationTable").html(reservationTable);
        }
    });*/

    $('#reservationTable').DataTable().ajax.reload();
 }


/**
//$(document).ready(function(){
    $("#testButton").click(function(){
        $("#test").hide();
    );

  $("#changeButton").click(updateTable);
});
*/


$("#updateButton").click(function() {
console.log("ik klik de update knop");
  updateTable();
     });


function createReservationString(reservation){
   result ="<tr><td>"+reservation.firstName+"</td><td>"+reservation.lastName+"</td><td>"+reservation.amountOfPeople+"</td><td>"+reservation.reservationTime+"</td></tr>";

   return result;
   }