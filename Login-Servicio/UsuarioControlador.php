<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of UsuarioControlador
 *
 * @author felipe
 */
class UsuarioControlador {
    
    private $ubicacion;
    private $sesion;
    
    public function __construct() {
        $this->ubicacion = "http://localhost:8080/AutenticacionServicio/UsuarioServicio?wsdl";
    }
    
    public function crear() {
        $valor = false;
        if (isset($_POST['enviar'])) {
            $nombre = htmlentities($_POST['nombre']);
            $apellido1 = htmlentities($_POST['apellido1']);
            $apellido2 = htmlentities($_POST['apellido2']);
            $nombreUsuario = htmlentities($_POST['nombreUsuario']);
            $clave = htmlentities($_POST['clave']);
            $servicio = SoapClient($this->ubicacion);
            $respuesta = $servicio->crear(array('nombre'=>$nombre, 'apellido1'=>$apellido1, 'apellido2'=>$apellido2, 'nombreUsuario'=>$nombreUsuario, 'clave'=>$clave));
            $valor = $respuesta;
        }
        return $valor;
    }
    
    public function autenticar() {
        $valor = NULL;
        if (isset($_POST['logear'])) {
            $nombreUsuario = $_POST['username'];
            $clave = $_POST['password'];
            echo $nombreUsuario . ' ' . $clave;
            $cliente = new SoapClient($this->ubicacion);
            $valor = $cliente->autenticar(array("nombre"=>$nombreUsuario, "clave"=>$clave));
        }
        if (isset($valor->return)) {
            $valor = $valor->return;
            include '/home/felipe/Sites/Login/datos/Usuario.php';
            $sesion = new Usuario();
            echo 'Listo</br>';
            echo "<script type=\"text/javascript\"> window.location = \"http://localhost/Login/Inicio.php\" </script>";
        }
    }
    
    public function getSesion() {
        return $this->sesion;
    }


    
}
