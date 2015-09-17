<?php
include ("settings.php");
/**
 * Socket
 *
 * @author rogeriolino
 */
class Socket 
{
    
   private static $instancia;
   private $contador;
    
    
    
    public $resource;
    public $conectado;
    
    
    
   private function __construct()
   {
      $this->contador =0;
   }

   public static function getInstance()
   {
      if (  !self::$instancia instanceof self)
      {
         self::$instancia = new self;
      }
      return self::$instancia;
   }
    
    


    public function  conectar() 
    {
        if ($this->resource == null )
        {
            $this->resource = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
            $conectado = socket_connect($this->resource, SOCKET_HOST, SOCKET_PORT);
        }
    }




}


