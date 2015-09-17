<?php

include ("Socket.php");
//error_reporting(0);

if(isset($_REQUEST['logear']))
{
    $usuario = $_POST['username'];
    $contrasenia = $_POST['password'];

    
    /* Crear un socket TCP/IP. */    
    $socket = Socket::getInstance();
    $socket->conectar();

    $in = "Verificar Usuario:".$usuario.":".$contrasenia;

    $out = '';


    echo " <p/> Enviando ".$in;
    socket_write($socket->resource, $in, strlen($in));
    echo "OK. <p/>";

    echo " <p/> Leyendo...";
    $out = socket_read($socket->resource, 2048);
    echo $out;

    echo " <p/> Cerrando socket...";
    //socket_close($socket);
    echo "OK.<p/><p/>";
    
    //Conexion con la BD
    if (isset($_POST['logear']))
{ // if form has been submitted
   if(!$_POST['username'] | !$_POST['password'])
   {//Verificar que las casillas no esten en blanco
      die('Es necesario escribir un Usuario y una contraseÃ±a <a href=index.php>Intente de nuevo</a>');
      
   }
   $usuario = $_POST['username'];

    $conexionConBaseDeDatos=mysql_connect(HOST, USER, PASS);

    @mysql_select_db(DB, $conexionConBaseDeDatos) or die("Error en la seleccion, '$php_errormsg'");

    $consultaSql = "SELECT nombreUsuario, clave FROM usuario WHERE nombreUsuario = '" . $usuario . "'";

    $resultadoConsulta = mysql_query($consultaSql);   
   
   if (mysql_num_rows($resultadoConsulta) == 0) 
   {
       echo "<script>alert(\"El usuario no existe en la base de datos.\");</script>";
       echo "<script>javascript:history.back();</script>";
   }
   
   
   while ($row = mysql_fetch_array($resultadoConsulta))
   {
       echo $_POST['password'];
       echo strtoupper($row['clave']);
      if (strtoupper($_POST['password']) !=  strtoupper($row['clave'])) 
      {
          echo "<script>alert(\"Password incorrecto.\");</script>";
          echo "<script>javascript:history.back();</script>";
      }
      else
      {

            echo "<script>alert(\"Password Correcto.\");</script>";
            echo "<script>javascript:history.back();</script>";
      }
   }
    mysql_close($conexionConBaseDeDatos);
}
 
}
?>