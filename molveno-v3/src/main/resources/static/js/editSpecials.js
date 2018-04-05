$(document).ready(function() {

        $('#specialsTable').DataTable( {
                "order": [[ 0, "asc" ]],
                "ajax": {
                        url: 'http://localhost:8080/api/editSpecials',
                        dataSrc: ''
                    },
                "columns": [
                    { "data": "Special Name"},
                    { "data": "Special Price"},
                    { "data": "Special Dishes" },
                    { "data": "Special Allergen" }
                ]
         } );

        $('#specialsTable tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                deselect();
                $(this).addClass('selected');
                var table = $('#specialsTable').DataTable();
                var data = table.row(this).data();
                console.log(data);
                apiGetSingleSpecial(data.id);
                $('#newEditSpecialModal').modal('toggle');
            }
        });

} );

function deselect(){
    $('#specialsTable tr.selected').removeClass('selected');
}

function apiGetSingleSpecial(id){
    var api = "http://localhost:8080/api/editSpecials/" + id;
    $.get(api, function(data){
        if (data){
            showEditScreen(data);
        }
    });
}

function showEditScreen (Dish){

}