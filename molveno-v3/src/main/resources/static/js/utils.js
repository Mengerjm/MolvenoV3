var baseUrl = "http://localhost:8080/api/"

//At ready watch for button click: add button & modal button
$(document).ready(function(){
console.log("doc ready");

    $('#modalButton').click(activateModal);


function activateModal (){
        console.log("Ik activeer");
        $('#reservationFormInput').trigger("reset");

        $('#reservationInputModal').modal('toggle');
}


    $("#addButton").click(function() {

        var a = $("#firstNameInput").val()
        var b = $("#lastNameInput").val()
        var c = Number($("#amountOfPeopleInput").val())
        var d = $("#reservationTimeInput").val()

        var alertString = "";

        if(a== null || a==""){

            alertString += "Firstname, ";
            $("#firstNameInput").css("backgroundColor", "#f8fbc8");
        } else{
            $("#firstNameInput").css("backgroundColor", "white");
        }
        if(b== null || b==""){
            alertString += "Lastname, ";
            $("#lastNameInput").css("backgroundColor", "#f8fbc8");
        }else{
            $("#lastNameInput").css("backgroundColor", "white");
        }
        if(c== null || c==""){
            alertString += "Amount of people, ";
            $("#amountOfPeopleInput").css("backgroundColor", "#f8fbc8");
        }else{
            $("#amountOfPeopleInput").css("backgroundColor", "white");
        }
        if(d== null || d==""){
            alertString += "Reservation time";
            $("#reservationTimeInput").css("backgroundColor", "#f8fbc8");
        }else{
            $("#reservationTimeInput").css("backgroundColor", "white");

        }

        if (alertString != "")
        {
         $(".error-messages").text("Please Fill All Required Field(s) \n" + alertString).show();
            return false;
        }

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
             $('#reservationInputModal').modal('toggle');

    });
});

//Call datatable to reload the data table
var updateTable = function(){
    $('#reservationTable').DataTable().ajax.reload();

 }

//If update button click then update Data table
$("#updateButton").click(function() {

    updateTable();
});

//Create line for data table
function createReservationString(reservation){
      result ="<tr><td>"+reservation.firstName+"</td><td>"+reservation.lastName+"</td><td>"+reservation.amountOfPeople+"</td><td>"+reservation.email+"</td><td>"+reservation.telephoneNumber+"</td><td>"+reservation.reservationTime+"</td></tr>";
      return result;
   }