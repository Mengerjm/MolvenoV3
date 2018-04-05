var baseUrl = "http://localhost:8080/api/"

$(document).ready(function(){
console.log("doc ready");

$('#modalButton').click(activateModal);

function activateModal (){
        console.log("Ik activeer");
        $('#reservationFormInput').trigger("reset");

        $('#myModalInput').modal('toggle');
}

function checkIfDataIsGiven(){

        var a = $("#firstNameInput").val()
        var b = $("#lastNameInput").val()
        var c = Number($("#amountOfPeopleInput").val())
        var d = $("#reservationTimeInput").val()

        var alertString = "";

        if(a== null || a==""){
            alertString += "Firstname, ";
            $("#firstNameInput").css("backgroundColor", "yellow");
        } else{
            $("#firstNameInput").css("backgroundColor", "white");
        }
        if(b== null || b==""){
            alertString += "Lastname, ";
            $("#lastNameInput").css("backgroundColor", "yellow");
        }else{
            $("#lastNameInput").css("backgroundColor", "white");
        }
        if(c== null || c==""){
            alertString += "Amount of people, ";
            $("#amountOfPeopleInput").css("backgroundColor", "yellow");
        }else{
            $("#amountOfPeopleInput").css("backgroundColor", "white");
        }
        if(d== null || d==""){
            alertString += "Reservation time";
            $("#reservationTimeInput").css("backgroundColor", "yellow");
        }else{
            $("#reservationTimeInput").css("backgroundColor", "white");
        }

        if (alertString != "")
        {
            $(".error-messages").text("Please Fill All Required Field(s): \n" + alertString).show();
            return false;
        } else{
            $(".error-messages").text("");
            return true;
           //jeej;
        }
}

$("#addButton").click(function() {
        if(checkIfDataIsGiven()){

        var jsonObject = {
            firstName: $("#firstNameInput").val(),
            lastName: $("#lastNameInput").val(),
            amountOfPeople: Number($("#amountOfPeopleInput").val()),
            email: $("#emailInput").val(),
            telephoneNumber: $("#telephoneNumberInput").val(),
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

    }});

});

var updateTable = function(){

console.log("ik start update");
    $('#reservationTable').DataTable().ajax.reload();
 }


$("#updateButton").click(function() {
console.log("ik klik de update knop");
  updateTable();
     });


function createReservationString(reservation){
   result ="<tr><td>"+reservation.firstName+"</td><td>"+reservation.lastName+"</td><td>"+reservation.amountOfPeople+"</td><td>"+reservation.email+"</td><td>"+reservation.telephoneNumber+"</td><td>"+reservation.reservationTime+"</td></tr>";

   return result;
   }