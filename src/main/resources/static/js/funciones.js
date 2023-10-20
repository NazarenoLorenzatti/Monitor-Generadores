function nuevoHost() {
    $('#nuevoHostModal')
            .modal('show');
}

function close(){    
    $('#close-modal').modal('hide');
}


$(document).ready(function () {
    $('#close-modal-editar').click(function () {
                $('#editarHostModal').modal('hide');
    });
});

$(document).ready(function () {
    $('#close-modal-nuevo').click(function () {
                $('#nuevoHostModal').modal('hide');
    });
});



$(document).ready(function () {
    $('.editar-host').click(function () {
        var idHost = $(this).data('id-host');
        var ipHost = $(this).data('ip-host');
        var nombreHost = $(this).data('nombre-host');      
        
        // Llena los campos del formulario con los datos del host
        $('#editarHostModal input[name="idHost"]').val(idHost);
        $('#editarHostModal input[name="nombreHost"]').val(nombreHost);
        $('#editarHostModal input[name="ipHost"]').val(ipHost);

        $('#editarHostModal').modal('show');
    });
});

$(document).ready(function() {
    $('#tabla-paginada').DataTable({
        paging: true, // Habilita la paginación
        lengthChange: false, // Oculta el control para cambiar el número de elementos por página
        searching: true, // Oculta el campo de búsqueda
        info: false, // Oculta la información de la página actual
        pageLength: 10 // Número de elementos por página
    });
});
