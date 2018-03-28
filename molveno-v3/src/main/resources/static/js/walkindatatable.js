$(document).ready(function() {

        $('#allTables').DataTable( {
                "order": [[ 0, "asc" ]],
                "ajax": {
                        url: 'http://localhost:8080/api/table/findall',
                        dataSrc: ''
                    },
                "columns": [
                    { "data": "tableNumber" },
                    { "data": "numberOfSeats" }
                ]
         } );


    // Functionality for interaction when clicking on rows of the table
        $('#allTables tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                deselect();
                $(this).addClass('selected');
                var table = $('#allTables').DataTable();
                var data = table.row(this).data();
                apiGetSingleTable(data.tableNumber);
                $('#allTableModal').modal('toggle');
            }
        });

} );

function getData() {
      var api = "http://localhost:8080/api/table/available";
        $.get(api, function(data){
            if (data){
                setData(data);
            }
        });
}

function setData(data){
    $("#allTables").DataTable().clear();
    $("#allTables").DataTable().rows.add(data);
    $("#allTables").DataTable().columns.adjust().draw();
}

// Get the data of a guest using an id
function apiGetSingleTable(id){
    var api = "http://localhost:8080/api/table/get/" + id;
    $.get(api, function(data){
        if (data){
            fillUpdateDiv(data);
        }
    });
}

// Fill the form with guestdata when updating the guest
function fillUpdateDiv(table){

    console.log(table);
    $("#btndelete").attr('onclick', 'submitDelete(' + table.tableNumber + ');');
    $("#editbutton").attr('onclick', 'submitEdit(' + table.tableNumber + ');');
    document.getElementById("modal-title-all-table").innerHTML="Set table Unavailable";
    Number($("#tableNumber").val(table.tableNumber));
    Number($("#numberOfSeats").val(table.numberOfSeats));
    $("#confirmbutton").css('display', 'inline-block');
    deleteID = table.tableNumber;
    var elem = '<button type="button" class="btn btn-danger" onclick="submitDelete();">Confirm delete</button>';
    $('#confirmbutton').popover({
        animation:true,
        content:elem,
        html:true,
        container: allTableModal
    });
}

// Deselect all items in the table
function deselect(){
    $('#allTables tr.selected').removeClass('selected');
}

// Submit the edited data in the form to the database
function submitEdit(id){
// shortcut for filling the formData as a JavaScript object with the fields in the form
    console.log("Formdata");
    var formData = $("#tableForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    console.log(formData);
    var tableNumber = id;
    for(var key in formData){
        if(formData[key] == "" || formData == null) delete formData[key];
    }
    $.ajax({
        url:"/api/table/available/" + tableNumber,
        type:"put",
        contentType: "application/json; charset=utf-8",
        success: getData,
        error: function(error){
            displayError(error);
        }
    });
    deselect();
    $('#allTableModal').modal('toggle');
}

// Delete the guest in the database with the corresponding id
function submitDelete(){
    console.log("Deleting");
    var formData = $("#tableForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    var tableNumber = deleteID;
    $.ajax({
        url:"/api/table/" + tableNumber,
        type:"delete",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8"
    });

    $('#allTableModal').modal('toggle');
    deselect();
}