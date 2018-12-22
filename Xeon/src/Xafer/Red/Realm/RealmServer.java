package Xafer.Red.Realm;

import Xafer.Utilidad.Config;
import Xafer.Utilidad.Consola;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xerooh
 */
public class RealmServer{

    private static ServerSocket servidor;
    private static Socket socket;
    private static Thread hilo;
 
    public static ArrayList<RealmClient> cliente_realm = new ArrayList<RealmClient>();
    
    public void Iniciar() {
        try
        {
            //servidor = new ServerSocket(Config.GetInt("Puerto_Realm"));
            servidor = new ServerSocket();
            InetSocketAddress conexion = new InetSocketAddress(Config.GetString("IP_Servidor"),Config.GetInt("Puerto_Realm"));
            servidor.bind(conexion);
            Runnable esperar_conexion = () -> EsperarConexion();
            hilo = new Thread(esperar_conexion);
            hilo.setDaemon(true);
            hilo.start();
            Consola.Info("Realm: Servidor iniciado " + Config.GetString("Puerto_Realm") +"!, esperando conexiones...");
        }
        catch(IOException e){
            Consola.Error(e.getMessage());
        }
    }
    
    private void EsperarConexion()
    {
        try{
        while(true)//siempre espera conexion
        {
                socket = servidor.accept();
                RealmClient realm = new RealmClient(socket);
                cliente_realm.add(realm);
                Consola.Titulo("Jugadores: " + cliente_realm.size());
                Consola.Info("Realm: Nuevo cliente conectado <" + socket.getRemoteSocketAddress().toString() + ">");
                
        }
        } catch (IOException ex) {
                Consola.Error(ex.getMessage());
        }
    }
}