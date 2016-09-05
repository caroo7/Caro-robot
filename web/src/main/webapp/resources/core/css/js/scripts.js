$(document).ready(function() {
    $('.bookTable').DataTable( {
        initComplete: function () {
            this.api().columns().every( function () {
                var column = this;
                var select = $('<select><option value=""></option></select>')
                    .appendTo( $(column.footer()).empty() )
                    .on( 'change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );

                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw();
                    } );

               column.data().unique().sort().each( function ( d, j ) {
                   if(column.search() === '^'+d+'$'){
                       select.append( '<option value="'+d+'" selected="selected">'+d+'</option>' )
                   } else {
                       select.append( '<option value="'+d+'">'+d+'</option>' )
                   }
               } );
            } );
        }
    } );
} );

function replace( hide, show ) {
  document.getElementById(hide).style.display="none";
  document.getElementById(show).style.display="block";
}