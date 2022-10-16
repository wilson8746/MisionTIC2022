function leerCliente(){
    // FUNCION GET
    $.ajax({    
        url : 'https://g648d2aa236d849-d2sn1vd9b1tc344u.adb.mx-queretaro-1.oraclecloudapps.com/ords/admin/client/client',
        type : 'GET',
        dataType : 'json',
        success : function(Json) {
           console.log(Json)
        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        }
        
    });
    
}
function guardarCliente(){
     let idCliente=$("#idCliente").val();
     let nombre=$("#nombreCliente").val();
     let mailCliente=$("#mailCliente").val();
     let edad=$("#edadCliente").val();

    let data={
        id:idCliente,
        name:nombre,
        email:mailCliente,
        age:edad
    };
    let dataEnviar=JSON.stringify(data);
    console.log(dataEnviar);
}

$.ajax({    
    url : 'https://g648d2aa236d849-d2sn1vd9b1tc344u.adb.mx-queretaro-1.oraclecloudapps.com/ords/admin/client/client',
    type : 'POST',
    dataType : 'json',
    data:dataEnviar,
    contentType:'application/json',
    success : function(Json) {
       
    },
    error : function(xhr, status) {
        alert('ha sucedido un problema');
    }
    
});