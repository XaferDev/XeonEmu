package Xafer.Database;

import Xafer.Database.Tablas.TablaCuentas;
import Xafer.Database.Tablas.TablaPersonajes;
import Xafer.Utilidad.Config;
import Xafer.Utilidad.Consola;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Xafer
 */
public class DBGestor {

//    private static Thread hilo = null;

    private static Connection CONEXION;

    public static Connection getCONEXION() {
        return CONEXION;
    }

    public static boolean Conectar() {
        try {
            CONEXION = DriverManager.getConnection("jdbc:mysql://" + Config.GetString("DB_Host") + "/" + Config.GetString("DB_Nombre"), Config.GetString("DB_Usuario"), Config.GetString("DB_Clave"));
            CONEXION.setAutoCommit(false);
            Consola.Debug("Database: Conexion establecida!");
            return true;
        } catch (SQLException e) {
            Consola.Error(e.getMessage());
            return false;
        }
    }

    public static void GenerarCache() {
        //Runnable runnable = () -> AceptarConexion();
//        Runnable runnable = () -> CorrerHilo();
//        hilo = new Thread(runnable);
//        hilo.setDaemon(true);
//        hilo.start();
        CorrerHilo();
    }

    private static void CorrerHilo() {
        Consola.Info("Cargando tabla cuentas");
        TablaCuentas.Cargar();
        TablaPersonajes.Cargar();
    }
    
    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
    }
}
