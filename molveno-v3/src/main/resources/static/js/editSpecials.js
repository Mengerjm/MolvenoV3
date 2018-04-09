var baseUrl = "http://molvenov3.test.carpago.nl/api/"
//var baseUrl = "http://localhost:8080/api/"

$(document).ready(function() {
    $('#modalButton').click(modalToggle);

    $("#addButton").click(function() {
        var jsonObject = {
            name: $("#nameInput").val(),
            description: $("#descriptionInput").val(),
            price: Number($("#priceInput").val()),
            dishes: null
        };
        $.ajax({
            contentType: "application/json",
            url: "api/editSpecials/newSpecial",
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
    $('#specialFormInput').trigger("reset");
    $('#specialInputModal').modal('toggle');
}

var updateTable = function() {
    console.log("ik start update");
    $('#specialsTable').DataTable().ajax.reload();
}

$(document).ready(function() {

    $('#specialsTable').DataTable({
        "order": [
            [0, "asc"]
        ],
        "ajax": {
            url: baseUrl+'editSpecials/findall',
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
                "data": "dishes"
            }
        ]
    });

    $('#specialsTable tbody').on('click', 'tr', function() {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            deselect();
            $(this).addClass('selected');
            var table = $('#specialsTable').DataTable();
            var data = table.row(this).data();
            apiGetSingleSpecial(data.id);
            $('#allSpecialsModal').modal('toggle');
        }
    });

});

function getData() {
    var api = baseUrl+"editSpecials/findall";
    $.get(api, function(data) {
        if (data) {
            setData(data);
        }
    });
}

function setData(data) {
    $("#specialsTable").DataTable().clear();
    $("#specialsTable").DataTable().rows.add(data);
    $("#specialsTable").DataTable().columns.adjust().draw();
}

function apiGetSingleSpecial(id) {
    var api = baseUrl+"editSpecials/get/" + id;
    $.get(api, function(data) {
        if (data) {
            fillUpdateDiv(data);
        }
    });
}

function fillUpdateDiv(special) {

    console.log(special);
    $("#btndelete").attr('onclick', 'submitDelete(' + special.id + ');');
    $("#editbutton").attr('onclick', 'submitEdit(' + special.id + ');');
    document.getElementById("modal-title-all-specials").innerHTML = "Edit Special";
    $("#nameInputEdit").val(special.name);
    $("#descriptionInputEdit").val(special.description);
    Number($("#priceInputEdit").val(special.price));
    $("#dishes").val(null); // verzin hier wat op
    $("#confirmbutton").css('display', 'inline-block');
    deleteID = special.id;
    var elem = '<button type="button" class="btn btn-danger" onclick="submitDelete();">Confirm delete</button>';
    $('#confirmbutton').popover({
        animation: true,
        content: elem,
        html: true,
        container: allSpecialsModal
    });
}

function deselect() {
    $('#specialsTable tr.selected').removeClass('selected');
    document.getElementById("specialForm").reset();
}

function submitEdit(id) {
    console.log("Formdata");
    var formData = $("#specialForm").serializeArray().reduce(function(result, object) {
        result[object.name] = object.value;
        return result
    }, {});
    console.log(formData);
    var specialId = id;
    for (var key in formData) {
        if (formData[key] == "" || formData == null) delete formData[key];
    }
    $.ajax({
        url: "/api/editSpecials/update/" + specialId,
        type: "put",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        success: getData,
        error: function(error) {
            displayError(error);
        }
    });
    deselect();
    $('#allSpecialsModal').modal('toggle');
}

function submitDelete() {
    console.log("Deleting");
    var formData = $("#specialForm").serializeArray().reduce(function(result, object) {
        result[object.name] = object.value;
        return result
    }, {});
    var specialId = deleteID;
    $.ajax({
        url: "/api/editSpecials/" + specialId,
        type: "delete",
        data: JSON.stringify(formData),
        success: getData,
        contentType: "application/json; charset=utf-8"
    });

    updateTable();

    $('#allSpecialsModal').modal('toggle');
    deselect();
}

var updateTable = function() {
    $('#specialsTable').DataTable().ajax.reload();
}