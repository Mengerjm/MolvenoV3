$(document).ready(function(){

    $('#modalButton').click(dew);

    function dew (){
    $('#myModal').modal('toggle');
    }

    $("#btnsubmit").click(function(){
        var jsonObject = {
            tableNumber: Number($("#tableNumber").val()),
            numberOfSeats: Number($("#numberOfSeats").val()),
            available: true
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
    });

});