$(document).ready(function() {

    $('#modalButton').click(modalToggle);

    $("#btnsubmit").click(function() {

        var alertString = "";
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

        if (alertString != "") {
            $(".error-messages").text("Please Fill All Required Field(s) \n" + alertString).show();
            return false;
        }

        var jsonObject = {
            tableNumber: Number($("#tableNumber").val()),
            numberOfSeats: Number($("#numberOfSeats").val()),
        };
        $.ajax({
            contentType: "application/json",
            url: "api/table/newtable",
            type: "post",
            data: JSON.stringify(jsonObject),
            success: function(data) {
                console.log(data);
                updateTable();
                modalToggle();
            },
            error: function(error) {
                alert("tableNumber " + tableNumber + " already exists, cannot change table to existing table number")
            }
        });

    });
});

function modalToggle() {
    $('#newTableForm').trigger("reset");
    $("#tableNumber").css("backgroundColor", "white");
    $("#numberOfSeats").css("backgroundColor", "white");
    $(".error-messages").text("");
    $('#newReservationModal').modal('toggle');
}

var updateTable = function() {
    console.log("ik start update");
    $('#allTables').DataTable().ajax.reload();
}