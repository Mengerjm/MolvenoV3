
//At ready create data table & watch for click on resarvationtable
$(document).ready(function() {

    // Create data table
    $('#reservationTable').DataTable( {
                "order": [[ 0, "asc" ]],
                "ajax": {
                        url: 'http://molvenov3.test.carpago.nl/api/reservation',
                        dataSrc: ''
                    },
                "columns": [
                    { "data": "reservationTime"},
                    { "data": function( data, type, row){

                        var toReturn = "";

                        $.each(data.reservedTable, function (index, current){
                            toReturn = toReturn + current.tableNumber + ", ";
                        });

                        return toReturn;

                    }},
                    { "data": "amountOfPeople"},
                    { "data": "firstName" },
                    { "data": "lastName" }
                ]
         } );

    // Functionality for interaction when clicking on rows of the table
    $('#reservationTable tbody').on( 'click', 'tr', function () {
            console.log("hallo ik heb geklikt");
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                deselect();
                $(this).addClass('selected');
                var table = $('#reservationTable').DataTable();
                var data = table.row(this).data();
                console.log(data);
                apiGetSingleReservation(data.id);
                $('#newReservationModal').modal('toggle');
            }
        });

});

// Get all data
function getData() {
      var api = "http://molvenov3.test.carpago.nl/api/reservation";
        $.get(api, function(data){
            if (data){
                setData(data);
            }
        });
}

// Set data in data table
function setData(data){
    $("#reservationTable").DataTable().clear();
    $("#reservationTable").DataTable().rows.add(data);
    $("#reservationTable").DataTable().columns.adjust().draw();
}

// Get the data of a reservation using an id
function apiGetSingleReservation(id){
    var api = "http://molvenov3.test.carpago.nl/api/reservation/" + id;
    $.get(api, function(data){
        if (data){
            fillUpdateDiv(data);
        }
    });
}

// Fill the form with reservationdata when updating the reservation
function fillUpdateDiv(reservation){

    console.log(reservation);
    $("#btndelete").attr('onclick', 'submitDelete(' + reservation.id + ');');
    $("#btnsubmit").attr('onclick', 'submitEdit(' + reservation.id + ');');
    document.getElementById("modal-title").innerHTML="Edit Reservation";
    $("#firstName").val(reservation.firstName);
    $("#lastName").val(reservation.lastName);
    $("#amountOfPeople").val(reservation.amountOfPeople);
    $("#email").val(reservation.email);
    $("#telephoneNumber").val(reservation.telephoneNumber);
    $("#reservationTime").val(reservation.reservationTime);
    $("#confirmbutton").css('display', 'inline-block');
    deleteID = reservation.id;
    var elem = '<button type="button" class="btn btn-danger" onclick="submitDelete();">Confirm delete</button>';
    $('#confirmbutton').popover({
        animation:true,
        content:elem,
        html:true,
        container: newReservationModal
    });

}

// Deselect all items in the table
function deselect(){
    $('#reservationTable tr.selected').removeClass('selected');
    console.log("Ik ben bij deselect");

}

// Submit the edited data in the form to the database
function submitEdit(id){
// shortcut for filling the formData as a JavaScript object with the fields in the form
    console.log("Formdata");
    var formData = $("#reservationForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    console.log(formData);
    var reservationNumber = id;

    for(var key in formData){
        if(formData[key] == "" || formData == null) delete formData[key];
    }
    // first validate here
            $(".error-messages").text("");

        var fname = $("#firstName").val()
        var lname = $("#lastName").val()
        var amountppl = Number($("#amountOfPeople").val())
        var restime = $("#reservationTime").val()
        var email = $("#email").val()
        var telnr = $("#telephoneNumber").val()

        var alertString = "";

        if(!fname){
            alertString += "Firstname, ";
            $("#firstName").css("backgroundColor", "#f8fbc8");
        } else{
            $("#firstName").css("backgroundColor", "white");
        }
        if(!lname){
            alertString += "Lastname, ";
            $("#lastName").css("backgroundColor", "#f8fbc8");
        }else{
            $("#lastName").css("backgroundColor", "white");
        }
        if(!amountppl){
            alertString += "Amount of people, ";
            $("#amountOfPeople").css("backgroundColor", "#f8fbc8");
        }else{
            $("#amountOfPeople").css("backgroundColor", "white");
        }
        if(!restime){
            alertString += "Reservation time";
            $("#reservationTime").css("backgroundColor", "#f8fbc8");
        }else{
            $("#reservationTime").css("backgroundColor", "white");
        }
        if (!email && !telnr){
            alertString += "Contact information, ";
            $("#email").css("backgroundColor", "#f8fbc8");
            $("#telephoneNumber").css("backgroundColor", "#f8fbc8");
        }else{
            $("#email").css("backgroundColor", "white");
            $("#telehponeNumber").css("backgroundColor", "white");
             }

        if (alertString != "")
        {
         $(".error-messages").text("Please Fill All Required Field(s) \n" + alertString).show();
            return false;
        }


    $.ajax({
        url:"/api/reservation/" + reservationNumber,
        type:"put",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        success: function(){
            getData();
            $('#newReservationModal').modal('toggle');
        },
        error: function(){$(".error-messages").text("There are no tables available for that time period")}
    });

    deselect();


}

// Delete the reservation in the database with the corresponding id
function submitDelete(){
    console.log("Deleting");
    var formData = $("#reservationForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    var reservationNumber = deleteID;
    $.ajax({
        url:"/api/reservation/" + reservationNumber,
        type:"delete",
        data: JSON.stringify(formData),
        success: getData,
        contentType: "application/json; charset=utf-8"
    });




    $('#newReservationModal').modal('toggle');
    deselect();
}