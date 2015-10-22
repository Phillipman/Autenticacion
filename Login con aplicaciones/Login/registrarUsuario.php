<?php
include ("settings.php");
//include ("common.php");
//error_reporting(0);

if(isset($_REQUEST['enviar']))
{ 
    $nombre = $_POST['fullname'];
    $email = $_POST['email'];
    $nomUsuario = $_POST['username'];
    $contrasenia = $_POST['password'];
    $stringRegistro = "Insertar Usuario:.$nombre.:.$email:.$nomUsuario:.$contrasenia";
    
    
    if($nombre != ''  && $email != ''
        && $nomUsuario != '' && $contrasenia != '')
    {
            $conexionConBaseDeDatos=mysql_connect(HOST, USER, PASS);
            echo 'exito';
            @mysql_select_db(DB,$conexionConBaseDeDatos) or die("Error en la seleccion, '$php_errormsg'");
            echo "conecion";

                $consultaSql = "INSERT INTO autentificacion.usuario (nombreCompleto,email,nombreUsuario,clave) VALUES ('$nombre','$email','$nomUsuario','$contrasenia');";
     
            mysql_query($consultaSql); 
            mysql_close($conexionConBaseDeDatos);
            echo "<script>alert(\"Se a registrado correctamente, puede ingresar al sistema\");</script>";
            echo "<script>setTimeout(location.href='index.php#portfolio', 5000);</script>";
    }
    else
    {
            echo "<script>alert(\"Debe llenar todos los datos intentelo de nuevo.\");</script>";
            echo "<script>javascript:history.back();</script>";
    }
    
    
}



?>