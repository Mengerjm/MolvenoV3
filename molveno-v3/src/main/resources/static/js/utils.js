var updateTable = function(){

var table = $('#reservationTable').DataTable();
 table.ajax.reload();
 }

$(document).ready(function(){
    $("#testButton").click(function(){
        $("#test").hide();
    });

    $("#changeButton").click(updateTable);


$("#updateButton").click(function() {
  updateTable();
     });

$("#addButton").click(function() {

  var baseUrl ="/api/";
        var jsonObject = {
            firstName: $("#firstNameInput").val(),
            lastName: $("#lastNameInput").val(),
            amountOfPeople: Number($("#amountOfPeopleInput").val()),
            reservationTime: Number($("#reservationTimeInput").val())

        };

        console.log(jsonObject);

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
                      // update datatable
                      // getreservations
                 }
             });

             updateTable();
      });
});
function started (){
    console.log("Pagina is geladen")
}

function popup(){
    var a = "I' m not your friend, buddy!"

    console.log("I'm not your buddy, guy");
    alert(a);
}

function verberg(){
$("#paragraph").hide();
}

function createReservationString(reservation){
   result ="<tr><td>"+reservation.firstName+"</td><td>"+reservation.lastName+"</td><td>"+reservation.amountOfPeople+"</td><td>"+reservation.reservationTime+"</td></tr>";

   return result;
   }