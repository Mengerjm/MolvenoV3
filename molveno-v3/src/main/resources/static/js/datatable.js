$(document).ready(function() {

        $('#reservationTable').DataTable( {
                "order": [[ 0, "asc" ]],
                "ajax": {
                        url: 'http://localhost:8080/api/reservation',
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
                $('#myModal').modal('toggle');
            }
        });

} );

function getData() {
      var api = "http://localhost:8080/api/reservation";
        $.get(api, function(data){
            if (data){
                setData(data);
            }
        });
}

function setData(data){
    $("#reservationTable").DataTable().clear();
    $("#reservationTable").DataTable().rows.add(data);
    $("#reservationTable").DataTable().columns.adjust().draw();
}

// Get the data of a reservation using an id
function apiGetSingleReservation(id){
    var api = "http://localhost:8080/api/reservation/" + id;
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
        container: myModal
    });
}

// Deselect all items in the table
function deselect(){
    $('#reservationTable tr.selected').removeClass('selected');
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
    $.ajax({
        url:"/api/reservation/" + reservationNumber,
        type:"put",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        success: getData,
        error: function(error){
            displayError(error);
        }
    });
    deselect();
    $('#myModal').modal('toggle');
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

    $('#myModal').modal('toggle');
    deselect();
}