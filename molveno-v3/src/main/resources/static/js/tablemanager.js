$(document).ready(function(){

    $('#modalButton').click(modalToggle);

    $("#btnsubmit").click(function(){
        var jsonObject = {
            tableNumber: Number($("#tableNumber").val()),
            numberOfSeats: Number($("#numberOfSeats").val()),
        };
        $.ajax({
            contentType: "application/json",
            url: "api/table/newtable",
            type: "post",
            data: JSON.stringify(jsonObject),
            success: function(data){
                console.log(data);
                updateTable();
                modalToggle();
            }
        });

    });
});

    function modalToggle (){
    $('#newTableForm').trigger("reset");
    $('#newReservationModal').modal('toggle');
    }

    var updateTable = function(){
          console.log("ik start update");
          $('#allTables').DataTable().ajax.reload();
    }
