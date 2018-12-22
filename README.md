# XeonEmu
Emulador de Dofus 1.29.1 desarrollado en Java, (version Alpha) (NetBeans src) <br>
Proyecto OpenSource desarrollado por Xafer(Xerooh) <br>
Changelog:<br>
Database:<br>
-DBGestor: Gestiona la conexion de la base de datos y genera el cache del servidor<br>
  -Tablas:<br>
    -TablaCuentas: Contiene toda la informacion para cargar la tabla cuentas<br>
    -TablaPersonajes: Contiene toda la informacion para cargar la tabla cuentas<br>
Entidades: Carpeta vacia...<br>

Red: <br>
  -Game: GameClient,GameServer 0%<br>
  -Realm: <br>
    -RealmServer: Gestiona la conexion asincrona entre servidor y cliente<br>
    -RealmClient: Gestiona los datos enviados y recibidos...<br>
      > Detecta cuando un cliente se ha desconectado y muestra el mensaje de la conexion perdida con el cliente agregando la ip y el puerto de este.<br>
      > Los mensajes son enviados a traves de un metodo, el cual se utiliza de la siguiente forma: Enviar("AUI AGREGAS PAQUETE QUE QUIERES ENVIAR");<br>
      > La gestion de los mensajes recibidos se realizan en el metodo RecibirDatos y luego son manejados a traves del metodo<br> AnallizarMensajes();<br>
      > Para analizar los mensajes se utiliza un switch el cual utiliza un enum para controlar cada caso<br>
      > RealmClient esta casi completado, falta agregar condicionales y uno que otro detalle<br>
Utilidad:<br>
-Config: Se encarga de leer el archivo de configuracion "ConfigXeon.ini"<br>
  Funciona de la siguiente forma:<br>
  Primero se carga el archivo, guardando todos los datos en un HashMap;<br>
  Luego cuando quieres leer un dato simplemente accedes al HashMap de la siguiente forma: Config.GetString("Parametro");<br>
  Todo dependera del dato que estes leyendo si es INT va a ser Config.GetInt("Parametro");<br>
  Tambien esta GetBool para un valor verdadero o falso, o GetShort para leer un dato de tipo Short;<br>
-Consola: Se encarga de gestionar toda la sintaxys de xeon, comandos de consola, mostrar mensajes, etc<br>
-Encriptador: Se encarga de encriptar y desencriptar datos... Aun no lo he arreglado, asi que hay cosas que estan mal, pero funciona perfectamente.<br>

![alt text](https://raw.githubusercontent.com/XaferDev/XeonEmu/blob/master/Image/1.png)
