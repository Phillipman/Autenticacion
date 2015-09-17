<?php
include ("settings.php");

$conexionConBaseDeDatos=mysql_connect(HOST, USER, PASS);
@mysql_select_db(DB, $conexionConBaseDeDatos) or die("Error en la seleccion, '$php_errormsg'");


$consultaSql = "SELECT * FROM usuario";
$resultadoConsulta = mysql_query($consultaSql);


while ($row = mysql_fetch_array($resultadoConsulta))
{
    echo $row["usuario"];
    echo $row["contrasenia"];
}


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

