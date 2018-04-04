$(document).ready(function(){

    $('#modalButton').click(dew);

    function dew (){
    $('#newReservationModal').modal('toggle');
    }

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
            succes: function(data){
                console.log(data);
            }
        });
        updateTable();
        dew();
    });
});

    var updateTable = function(){
          console.log("ik start update");
          $('#allTables').DataTable().ajax.reload();
    }
