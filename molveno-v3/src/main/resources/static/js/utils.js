//var baseUrl = "http://molvenov3.test.carpago.nl/api/"
var baseUrl = "http://localhost:8080/api/"

//At ready watch for button click: add button & modal button
$(document).ready(function() {
    console.log("doc ready");

    $('#modalButton').click(activateModal);


    function activateModal() {
        console.log("Ik activeer");
        $('#reservationFormInput').trigger("reset");
        $(".error-messages").text("");
        $('#reservationInputModal').modal('toggle');
    }


    $("#addButton").click(function() {

        var fname = $("#firstNameInput").val()
        var lname = $("#lastNameInput").val()
        var amountppl = Number($("#amountOfPeopleInput").val())
        var restime = $("#reservationTimeInput").val()
        var email = $("#emailInput").val()
        var telnr = $("#telephoneNumberInput").val()

        var alertString = "";

        if(!fname){
            alertString += "Firstname, ";
            $("#firstNameInput").css("backgroundColor", "#f8fbc8");
        } else {
            $("#firstNameInput").css("backgroundColor", "white");
        }
        if(!lname){
            alertString += "Lastname, ";
            $("#lastNameInput").css("backgroundColor", "#f8fbc8");
        } else {
            $("#lastNameInput").css("backgroundColor", "white");
        }
        if(!amountppl){
            alertString += "Amount of people, ";
            $("#amountOfPeopleInput").css("backgroundColor", "#f8fbc8");
        } else {
            $("#amountOfPeopleInput").css("backgroundColor", "white");
        }
        if(!restime){
            alertString += "Reservation time, ";
            $("#reservationTimeInput").css("backgroundColor", "#f8fbc8");
        } else {
            $("#reservationTimeInput").css("backgroundColor", "white");
        }
        if (!email && !telnr){
            alertString += "Contact information, ";
            $("#emailInput").css("backgroundColor", "#f8fbc8");
            $("#telephoneNumberInput").css("backgroundColor", "#f8fbc8");
        }else{
            $("#emailInput").css("backgroundColor", "white");
            $("#telehponeNumberInput").css("backgroundColor", "white");
             }

        if (alertString != "") {
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

             updateTable();
             $('#reservationInputModal').modal('toggle');

                      console.log(data);
                 },
                 error: $(".error-messages").text("There are no tables available for that time period")
        });


    });

});

//Call datatable to reload the data table
var updateTable = function() {
    $('#reservationTable').DataTable().ajax.reload();

}

//If update button click then update Data table
$("#updateButton").click(function() {

    updateTable();
});

//Create line for data table
function createReservationString(reservation) {
    result = "<tr><td>" + reservation.firstName + "</td><td>" + reservation.lastName + "</td><td>" + reservation.amountOfPeople + "</td><td>" + reservation.email + "</td><td>" + reservation.telephoneNumber + "</td><td>" + reservation.reservationTime + "</td></tr>";
    return result;
}