//var baseUrl = "http://molvenov3.test.carpago.nl/api/"
var baseUrl = "http://localhost:8080/api/"

$(document).ready(function() {

    $('#allTables').DataTable({
        "order": [
            [0, "asc"]
        ],
        "ajax": {
            url: baseUrl + 'table/findall',
            dataSrc: ''
        },
        "columns": [{
                "data": "tableNumber"
            },
            {
                "data": "numberOfSeats"
            }
        ]
    });


    // Functionality for interaction when clicking on rows of the table
    $('#allTables tbody').on('click', 'tr', function() {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            deselect();
            $(this).addClass('selected');
            var table = $('#allTables').DataTable();
            var data = table.row(this).data();
            apiGetSingleTable(data.id);
            $('#allTableModal').modal('toggle');
        }
    });

});

function getData() {
    var api = baseUrl + "table/findall";
    $.get(api, function(data) {
        if (data) {
            setData(data);
        }
    });
}

function setData(data) {
    $("#allTables").DataTable().clear();
    $("#allTables").DataTable().rows.add(data);
    $("#allTables").DataTable().columns.adjust().draw();
}

// Get the data of a guest using an id
function apiGetSingleTable(id) {
    var api = baseUrl + "table/get/" + id;
    $.get(api, function(data) {
        if (data) {
            fillUpdateDiv(data);
        }
    });
}

// Fill the form with guestdata when updating the guest
function fillUpdateDiv(table) {

    console.log(table);
    $("#btndelete").attr('onclick', 'submitDelete(' + table.id + ');');
    $("#editbutton").attr('onclick', 'submitEdit(' + table.id + ');');
    document.getElementById("modal-title-all-table").innerHTML = "Edit Table";
    Number($("#tableNumberTwo").val(table.tableNumber));
    Number($("#numberOfSeatsTwo").val(table.numberOfSeats));
    $("#confirmbutton").css('display', 'inline-block');
    deleteID = table.id;
    var elem = '<button type="button" class="btn btn-danger" onclick="submitDelete();">Confirm delete</button>';
    $('#confirmbutton').popover({
        animation: true,
        content: elem,
        html: true,
        container: allTableModal
    });
}

// Deselect all items in the table
function deselect() {
    $('#allTables tr.selected').removeClass('selected');
    // rloman dit moet straks terug. ik denk dat dit het modal form is
    document.getElementById("tableForm").reset();
}

// Submit the edited data in the form to the database
function submitEdit(id) {

    $(".error-messages").text("");

    var tableNumber = Number($("#tableNumber").val())
    var nos = Number($("#numberOfSeats").val())

    if (!tableNumber) {
        alertString += "Tablenumber, ";
        $("#tableNumber").css("backgroundColor", "#f8fbc8");
    } else {
        $("#tableNumber").css("backgroundColor", "white");
    }

    if (!nos) {
        alertString += "Number of seats, ";
        $("#numberOfSeats").css("backgroundColor", "#f8fbc8");
    } else {
        $("#numberOfSeats").css("backgroundColor", "white");
    }

    var alertString = "";

    if (alertString != "") {
        $(".error-messages").text("Please Fill All Required Field(s) \n" + alertString).show();
        return false;
    }

    // shortcut for filling the formData as a JavaScript object with the fields in the form
    console.log("Formdata");
    var formData = $("#tableForm").serializeArray().reduce(function(result, object) {
        result[object.name] = object.value;
        return result
    }, {});
    console.log(formData);
    var tableNumber = id;
    for (var key in formData) {
        if (formData[key] == "" || formData == null) delete formData[key];
    }
    $.ajax({
        url: "/api/table/update/" + tableNumber,
        type: "put",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        success: getData,
        error: function(error) {
            alert("tableNumber " + tableNumber + " already exists, cannot change table to existing table number")
        }
    });
    deselect();
    $('#allTableModal').modal('toggle');
}

// Delete the guest in the database with the corresponding id
function submitDelete() {
    console.log("Deleting");
    var formData = $("#tableForm").serializeArray().reduce(function(result, object) {
        result[object.name] = object.value;
        return result
    }, {});
    var tableNumber = deleteID;
    $.ajax({
        url: "/api/table/" + tableNumber,
        type: "delete",
        data: JSON.stringify(formData),
        success: getData,
        error: function(error) {
            alert("Cannot delete " + tableNumber + ", it has been reserved by somebody")
        },
        contentType: "application/json; charset=utf-8"
    });

    updateTable();

    $('#allTableModal').modal('toggle');
    deselect();
}

var updateTable = function() {
    console.log("ik start update");
    $('#allTables').DataTable().ajax.reload();
}