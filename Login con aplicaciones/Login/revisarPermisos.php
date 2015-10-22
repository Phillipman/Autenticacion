<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

include 'settings.php';

header("Content-type: text/javascript");

if (isset($_POST["idRol"])) {
    $idRol = $_POST["idRol"];
    $conexionConBaseDeDatos=mysql_connect(HOST, USER, PASS);
    @mysql_select_db(DB, $conexionConBaseDeDatos) or die("Error en la seleccion, '$php_errormsg'");
    $filas = mysql_query('SELECT m.idModulo, m.nombre, m.descripcion
                        FROM RolesModulos rm
                        INNER JOIN Modulo m
                        ON rm.Modulo_idModulo = m.idModulo
                        WHERE rm.Rol_idRol = ' . $idRol);
    if (mysql_num_rows($filas) != 0) {
        while ($row = mysql_fetch_array($filas)) {
            echo "<a href='" . $row["descripcion"] . "'><img src='" . $row["nombre"] . "' title='" . $row["idModulo"] . "'/></a>";
        }
        mysql_close($conexionConBaseDeDatos);
    }
}
