//$(document).ready(started);

// instead of started .. and mostly done ...

var baseUrl = "http://localhost:8080/api/"

$(document).ready(function() {
     console.log("Ready ... page loaded");

     $("#testButton").click(function() { // register (more the jQuery way) the function (event handler) to the button
        $("#test").hide();
     });

     $("#updateButton").click(function() {
        $.ajax({
                // waar moet hij de request op uitvoeren
                url : baseUrl+"reservation",
                // type actie
                type : "get",
                // als de actie lukt, voer deze functie uit
                success: function(data){ // so the data is the bulb of the response of the Spring Boot REST controller

                	reservationList = "";

                	$.each(data, function(index, current){ // index (the index starting from 0, current: the current object of the iterable

                		reservationList += createReservationString(current);

                	});

                	$("#reservationList").html(reservationList);
                }
            });
     });

     $("#addButton").click(function() {

            var jsonObject = {
                firstName: $("#firstName").val(),
                lastName: $("#lastName").val(),
                numberOfPeople: Number($("#numberOfPeople").val()),
                reservationTime: Number($("#reservationTime").val())
            };
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
          });
});

function started() {
      console.log("Ready ... page loaded");
}

function hideSome() {

$("p").hide();

}

function createReservationString(reservation) {
 result = "<tr><td>"+reservation.firstName+"</td><td>"+reservation.lastName+"</td><td>"+reservation.yearOfBirth+"</td><td>"reservation.reservationTime"</td></tr>";

  return result;
}

function hideAllParagraphsWithIntroOrSlot() {

    $("p.intro").hide();
    $("p.slot").hide();

}

function showAll() {
    $("p").show();
}

function toggleEpilog() {
    $("#epilog").toggle();
}

