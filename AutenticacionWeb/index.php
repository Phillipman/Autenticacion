<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
            $cliente = new SoapClient("http://localhost:8080/AutenticacionServicio/UsuarioServicio?wsdl");
            $resultado = $cliente->autenticar(array("nombre"=>"rVargas", "clave"=>"rVargas"));
            print_r($resultado);
        ?>
    </body>
</html>
