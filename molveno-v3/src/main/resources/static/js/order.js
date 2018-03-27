var baseUrl= "http://localhost:8080/api/"

$(document).ready(function(){

    $("#addOrderButton").click(function(){
            var jsonObject = {
                tableNumber: Number($("#tableNumber").val()),
                orderId: Number($("#orderId").val())
            };
            $.ajax({
                contentType: "application/json",
                url: baseUrl+"/order/createorder",
                type: "post",
                data: JSON.stringify(jsonObject),
                succes: function(data){
                    console.log(data);
                }
            });
    });

    $("#getOrders").click(function(){
        $.ajax({
            url: baseUrl+"/api/order/getorders",
            type:"get",
            success: function(data){

            orderList = "";

            $.each(data, function(index, current){
                orderList += createOrderString(current);
            });
            $("#orders").html(orderList);
        }});

    });

});

function createOrderString(order){
    result = "<tr><td>Tafelnummer "+order.tableNumber+"</td><td>Ordernummer "+order.orderId+"</td></tr>";
    return result;
}