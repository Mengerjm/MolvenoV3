$(document).ready(function() {

        $('#dishesTable').DataTable( {
                "order": [[ 0, "asc" ]],
                "ajax": {
                        url: 'http://localhost:8080/api/editDishes',
                        dataSrc: ''
                    },
                "columns": [
                    { "data": "Dish Name"},
                    { "data": "Dish Price"},
                    { "data": "Dish Type" },
                    { "data": "Dish Ingredients" },
                    { "data": "Dish Allergen" }
                ]
         } );

        $('#dishesTable tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                deselect();
                $(this).addClass('selected');
                var table = $('#dishesTable').DataTable();
                var data = table.row(this).data();
                console.log(data);
                apiGetSingleDish(data.id);
                $('#newEditDishModal').modal('toggle');
            }
        });

} );

function deselect(){
    $('#dishesTable tr.selected').removeClass('selected');
}

function apiGetSingleDish(id){
    var api = "http://localhost:8080/api/editDishes/" + id;
    $.get(api, function(data){
        if (data){
            showEditScreen(data);
        }
    });
}

function showEditScreen (Dish){

}