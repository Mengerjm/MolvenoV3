$(document).ready(function() {
    $('#modalButton').click(modalToggle);

    $("#addButton").click(function() {
        var jsonObject = {
            name: $("#nameInput").val(),
            description: $("#descriptionInput").val(),
            price: Number($("#priceInput").val()),
            toc: $("#courseType").val(),
            tod: $("#dishType").val(),
            ingredients: null
        };
        $.ajax({
            contentType: "application/json",
            url: "api/editDishes/newDish",
            type: "post",
            data: JSON.stringify(jsonObject),
            success: function(data) {
                console.log(data);
                updateTable();
                modalToggle();
            }
        });

    });
});

function modalToggle() {
    $('#dishFormInput').trigger("reset");
    $('#dishInputModal').modal('toggle');
}

var updateTable = function() {
    $('#dishesTable').DataTable().ajax.reload();
}

$(document).ready(function() {

    $('#dishesTable').DataTable({
        "order": [
            [0, "asc"]
        ],
        "ajax": {
            url: 'http://localhost:8080/api/editDishes/findall',
            dataSrc: ''
        },
        "columns": [{
                "data": "name"
            },
            {
                "data": "description"
            },
            {
                "data": "price"
            },
            {
                "data": "toc"
            },
            {
                "data": "tod"
            },
            {
                "data": "ingredients"
            }
        ]
    });

    $('#dishesTable tbody').on('click', 'tr', function() {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            deselect();
            $(this).addClass('selected');
            var table = $('#dishesTable').DataTable();
            var data = table.row(this).data();
            apiGetSingledish(data.id);
            $('#allDishesModal').modal('toggle');
        }
    });

});

function getData() {
    var api = "http://localhost:8080/api/editDishes/findall";
    $.get(api, function(data) {
        if (data) {
            setData(data);
        }
    });
}

function setData(data) {
    $("#dishesTable").DataTable().clear();
    $("#dishesTable").DataTable().rows.add(data);
    $("#dishesTable").DataTable().columns.adjust().draw();
}

function apiGetSingledish(id) {
    var api = "http://localhost:8080/api/editDishes/get/" + id;
    $.get(api, function(data) {
        if (data) {
            fillUpdateDiv(data);
        }
    });
}

function fillUpdateDiv(dish) {

    console.log(dish);
    $("#btndelete").attr('onclick', 'submitDelete(' + dish.id + ');');
    $("#editbutton").attr('onclick', 'submitEdit(' + dish.id + ');');
    document.getElementById("modal-title-all-dishes").innerHTML = "Edit dish";
    $("#nameInputEdit").val(dish.name);
    $("#descriptionInputEdit").val(dish.description);
    Number($("#priceInputEdit").val(dish.price));
    //toc tod
    $("#ingredients").val(null); // verzin hier wat op
    $("#confirmbutton").css('display', 'inline-block');
    deleteID = dish.id;
    var elem = '<button type="button" class="btn btn-danger" onclick="submitDelete();">Confirm delete</button>';
    $('#confirmbutton').popover({
        animation: true,
        content: elem,
        html: true,
        container: allDishesModal
    });
}

function deselect() {
    $('#dishesTable tr.selected').removeClass('selected');
    document.getElementById("dishForm").reset();
}

function submitEdit(id) {
    console.log("Formdata");
    var formData = $("#dishForm").serializeArray().reduce(function(result, object) {
        result[object.name] = object.value;
        return result
    }, {});
    console.log(formData);
    var dishId = id;
    for (var key in formData) {
        if (formData[key] == "" || formData == null) delete formData[key];
    }
    $.ajax({
        url: "/api/editDishes/update/" + dishId,
        type: "put",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        success: getData,
        error: function(error) {
            displayError(error);
        }
    });
    deselect();
    $('#allDishesModal').modal('toggle');
}

function submitDelete() {
    console.log("Deleting");
    var formData = $("#dishForm").serializeArray().reduce(function(result, object) {
        result[object.name] = object.value;
        return result
    }, {});
    var dishId = deleteID;
    $.ajax({
        url: "/api/editDishes/" + dishId,
        type: "delete",
        data: JSON.stringify(formData),
        success: getData,
        contentType: "application/json; charset=utf-8"
    });

    updateTable();

    $('#allDishesModal').modal('toggle');
    deselect();
}

var updateTable = function() {
    $('#dishesTable').DataTable().ajax.reload();
}