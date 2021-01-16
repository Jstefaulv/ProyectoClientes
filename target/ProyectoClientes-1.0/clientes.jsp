<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">

        <!-- Bootstrap CSS - Aspectos Visuales  -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/c2f0fa766c.js" crossorigin="anonymous"></script>
         
        <title>Clientes</title>
    </head>
    <body>

        <!-- header -->
        <jsp:include page="/WEB-INF/comunes/header.jsp"/>
        
        <!-- Botones Navegacion -->
        <jsp:include page="/WEB-INF/comunes/botonesNav.jsp"/>
        
        <!-- Listado Clientes -->
        <jsp:include page="/WEB-INF/cliente/listadoClientes.jsp"/>
        
        <!-- footer -->
        <jsp:include page="/WEB-INF/comunes/footer.jsp"/>
           

        <!-- Scripts Funcional -->  
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>
</html>