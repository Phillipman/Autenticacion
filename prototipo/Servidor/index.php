<?php
//Escribir c:/wamp/run
//Cambiar ip del server en ServerName del archivo httpd.conf
include ("settings.php");

error_reporting(E_ALL);

/* Permitir al script esperar para conexiones. */
set_time_limit(0);

/* Activar el volcado de salida implícito, así veremos lo que estamo obteniendo
* mientras llega. */
ob_implicit_flush();

if (($sock = socket_create(AF_INET, SOCK_STREAM, SOL_TCP)) === false) 
{
    echo "socket_create() falló: razón: " . socket_strerror(socket_last_error()) . "\n";
}

if (socket_bind($sock, SOCKET_HOST, SOCKET_PORT) === false) 
{
    echo "socket_bind() falló: razón: " . socket_strerror(socket_last_error($sock)) . "\n";
}

if (socket_listen($sock, 5) === false) 
{
    echo "socket_listen() falló: razón: " . socket_strerror(socket_last_error($sock)) . "\n";
}

//clients array
$clients = array();

do {
    $read = array();
    $read[] = $sock;
    
    $read = array_merge($read,$clients);
    
    // Set up a blocking call to socket_select
    if(socket_select($read,$write = NULL, $except = NULL, $tv_sec = 5) < 1)
    {
        //    SocketServer::debug("Problem blocking socket_select?");
        continue;
    }
    
    // Handle new Connections
    if (in_array($sock, $read)) 
    {        
        
        if (($msgsock = socket_accept($sock)) === false) 
        {
            echo "socket_accept() falló: razón: " . socket_strerror(socket_last_error($sock)) . "\n";
            break;
        }
        $clients[] = $msgsock;
        $key = array_keys($clients, $msgsock);
    }
    
    // Handle Input
    foreach ($clients as $key => $client) 
    { // for each client        
        if (in_array($client, $read)) 
        {
            if (false === ($buf = socket_read($client, 2048))) 
            {
                echo "socket_read() falló: razón: " . socket_strerror(socket_last_error($client)) . "\n";
                break 2;
            }
            $buf = trim($buf);
            list($Instruccion, $var1, $var1, $var1, $var1, $var1, $var1) = split(':', $buf);

            if ($Instruccion == 'Insertar Usuario') 
            {
                echo 'Insertar Usuario';
                $talkback = "Se inserto correctamente";
                socket_write($client, $talkback, strlen($talkback));
            }
            if ($Instruccion == 'Verificar Usuario') 
            {
                echo 'Verificar Usuario';
                $talkback = "Se verifico correctamente";
                socket_write($client, $talkback, strlen($talkback));
            }
//            $talkback = "Cliente {$key}: Usted dijo '$buf'.\n";
//            socket_write($client, $talkback, strlen($talkback));
            echo "$buf\n";
        }
        
    }        
} while (true);

socket_close($sock);
?>

