package Xafer;

import Xafer.Database.DBGestor;
import Xafer.Red.Game.GameServer;
import Xafer.Red.Realm.RealmServer;
import Xafer.Utilidad.Config;
import Xafer.Utilidad.Consola;


/**
 *
 * @author Xafer
 */
public class Xeon {

    public static void main(String[] args) 
    {
        Consola.Titulo("Xeon version 1.3");
        Consola.Encabezado();
        Consola.Info("Cargando la configuracion...");
        Config.Cargar();
        Consola.Info("Conectandose a la base de datos...");
        DBGestor.Conectar();
        DBGestor.GenerarCache();
        
//        GameServer.Iniciar();
        RealmServer rs = new RealmServer();
        rs.Iniciar();
        System.gc();
        while(true)
        {
            Consola.LeerLinea();
        }
    }

}
