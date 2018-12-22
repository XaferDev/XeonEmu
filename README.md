# XeonEmu
Emulador de Dofus 1.29.1 desarrollado en Java, (version Alpha) (NetBeans src)
Proyecto OpenSource desarrollado por Xafer(Xerooh) 
Changelog:
Database:
-DBGestor: Gestiona la conexion de la base de datos y genera el cache del servidor
  -Tablas:
    -TablaCuentas: Contiene toda la informacion para cargar la tabla cuentas
    -TablaPersonajes: Contiene toda la informacion para cargar la tabla cuentas
Entidades: Carpeta vacia...

Red: 
  -Game: GameClient,GameServer 0%
  -Realm: 
    -RealmServer: Gestiona la conexion asincrona entre servidor y cliente
    -RealmClient: Gestiona los datos enviados y recibidos...
      > Detecta cuando un cliente se ha desconectado y muestra el mensaje de la conexion perdida con el cliente agregando la ip y el puerto de este.
      > Los mensajes son enviados a traves de un metodo, el cual se utiliza de la siguiente forma: Enviar("AUI AGREGAS PAQUETE QUE QUIERES ENVIAR");
      > La gestion de los mensajes recibidos se realizan en el metodo RecibirDatos y luego son manejados a traves del metodo AnallizarMensajes();
      > Para analizar los mensajes se utiliza un switch el cual utiliza un enum para controlar cada caso
      > RealmClient esta casi completado, falta agregar condicionales y uno que otro detalle
Utilidad:
-Config: Se encarga de leer el archivo de configuracion "ConfigXeon.ini"
  Funciona de la siguiente forma:
  Primero se carga el archivo, guardando todos los datos en un HashMap;
  Luego cuando quieres leer un dato simplemente accedes al HashMap de la siguiente forma: Config.GetString("Parametro");
  Todo dependera del dato que estes leyendo si es INT va a ser Config.GetInt("Parametro");
  Tambien esta GetBool para un valor verdadero o falso, o GetShort para leer un dato de tipo Short;
-Consola: Se encarga de gestionar toda la sintaxys de xeon, comandos de consola, mostrar mensajes, etc
-Encriptador: Se encarga de encriptar y desencriptar datos... Aun no lo he arreglado, asi que hay cosas que estan mal, pero funciona perfectamente.

