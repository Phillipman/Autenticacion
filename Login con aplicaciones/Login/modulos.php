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
        <script type="text/javascript">
            function ajax(url, method, data) {
                var query = "";
                for (var key in data) {
                    query += encodeURIComponent(key) + "=" + encodeURIComponent(data[key]) + "&";
                }
                if (query != "") {
                    query = query.substring(0, query.length - 1);
                }
                var xhr;
                if (window.XMLHttpRequest) {
                    xhr = new XMLHttpRequest();
                } else {
                    xhr = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xhr.open(method, url, true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState == 4) {
                        if (xhr.status == 200) {
                            alert(xhr.responseText);
                            var datos = JSON.parse(xhr.responseText);
                            for (var key in datos) {
                                var modulo = datos[key];
                                document.body.innerHTML("<a href='" + modulo.descripcion + "'><img src='" + modulo.nombre + "' title=" + modulo.idModulo + "/></a>");
                            }
                        } else {
                            alert(xhr.status);
                        }
                    }
                }
                if (method == "GET") {
                    if (query != "") {
                        url += "?" + query;
                    }
                    xhr.send(null);
                }
                if (method == "POST") {
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    xhr.send(JSON.stringify(data));
                }
            }
        </script>
        
        <?php 
            session_start();
            echo "<h1>Bienvenido " . $_SESSION["nombreUsuario"] . "!</h1>";
        ?>
        <h2>
            Sesion como:
        </h2>
        <form action="modulos.php" method="post" style="display:inline">
            <select id="cmbRoles" name="idRol" onchange="this.form.submit()">
                <option value="vacio"></option>
                <?php
                    $roles = $_SESSION["roles"];
                    foreach ($roles as $key => $value) {
                        echo "<option value='" . $key . "'>" . $value["nombre"] . "</option>";
                    }
                ?>
            </select>
        </form>
        <p></p>
        <div style="margin:auto;padding: 5% 5%">
        <?php
            if (isset($_POST["idRol"])) {
                include 'settings.php';
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
                        echo "<a href='" . $row["descripcion"] . "'><img src='assets/Imagenes/" . $row["nombre"] . "' title='" . $row["idModulo"] . "' width=30% height=30%/></a>";
                    }
                    mysql_close($conexionConBaseDeDatos);
                }
            }
        ?>
        </div>
    </body>
</html>
