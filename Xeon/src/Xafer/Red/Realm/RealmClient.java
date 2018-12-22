package Xafer.Red.Realm;

import Xafer.Database.Tablas.TablaCuentas;
import Xafer.Utilidad.Config;
import Xafer.Utilidad.Consola;
import Xafer.Utilidad.Encriptador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xerooh
 */
public class RealmClient {

    

    public enum EstadoCliente
    {
        Version,Usuario,Clave,Servidores
    }
    
    private static EstadoCliente estado = EstadoCliente.Version;
    private BufferedWriter salida = null;
    private BufferedReader entrada = null;
    private String llave = null;
    private Thread hilo = null;
    private Socket conexion = null;
    private TablaCuentas Cuenta;
    private String _usuario;

    RealmClient(Socket _conexion) {

        try {
            conexion = _conexion;
            entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            salida = new BufferedWriter(new OutputStreamWriter(conexion.getOutputStream()));
            
            Runnable runnable = () -> RecibirDatos();//creamos n runnable para meter el metodo 
            hilo = new Thread(runnable);
            hilo.setDaemon(true);
            hilo.start();
            
        }catch(IOException ex) {
            Consola.Error(ex.getMessage());
            this.Desconectar();
        }

    }

    private void Enviar(String msg) {
        try {
            if (salida != null && !msg.equals("") && !msg.equals("" + (char) 0x00)) {
                msg = Encriptador.toUtf(msg);
                salida.write((msg) + (char) 0x00);
                salida.flush();
                Consola.Debug("Realm: Enviado > " + msg);
            }
        } catch (IOException e) {
            Consola.Error(e.getMessage());
        }
    }

    private void RecibirDatos() {
        try {
            llave = Encriptador.RandomString();
            Enviar("HC" + llave);

            String noTransformado;// = _in.readLine();
            while ((noTransformado = entrada.readLine()) != null) {
                for (String msg : noTransformado.replace("\\u000a", "").split("\\u0000", -1)) {
                    if (msg.equals("")) {
                        continue;
                    }
                    Consola.Debug("Realm: Recibido < " + msg);
                    AnalizarPaquetes(msg);
                }
            }
            this.Desconectar();
        } catch (IOException e) {
            Consola.Error(e.getMessage());
        }
    }

    private void AnalizarPaquetes(String msg) {
        if (msg.equalsIgnoreCase("Af")) {
            return;
        } else if (msg.equalsIgnoreCase("Ax")) {
            if (Cuenta == null) {
            return;
            }
        }
        
        switch(estado)
        {
            case Version:
                this.VerificarVErsion(msg);
                break;
            case Usuario:
                this.VerificarUsuario(msg);
                break;
            case Clave:
                this.VerificarClave(msg);
                break;
            case Servidores:
                this.VerificarServidores(msg);
                break;
                
        }

    }

    private void Desconectar() {
        try {
            String ip = conexion.getRemoteSocketAddress().toString();
            RealmServer.cliente_realm.remove(this);
            conexion.close();
            salida = null;
            entrada = null;
            llave = null;
            hilo = null;
            conexion = null;
            Consola.Titulo("Jugadores: " + RealmServer.cliente_realm.size());
            Consola.Info("Realm: Cliente desconectado! " + ip);
        } catch (IOException ex) {
            Consola.Error(ex.getMessage());
        }
    }

    private void VerificarVErsion(String msg) {
        if (msg.equalsIgnoreCase("1.29.1")) {
            Consola.Debug("Realm: Version verificada!");
            estado = EstadoCliente.Usuario;
        } else {
            Enviar("AlEv" + "1.29.1");
            Consola.Debug("Version incorrecta");
            this.Desconectar();
        }
    }

    private void VerificarUsuario(String msg) {
        _usuario = msg.toLowerCase();
        estado = EstadoCliente.Clave;
    }

    private void VerificarClave(String msg) {
        String user = _usuario.split("#")[0].substring(0);
        String pass = msg.split("#")[1].substring(1);
        Cuenta = Xafer.Database.Tablas.TablaCuentas.ObtenerCuentasPorUsuario(user);

        if (Cuenta != null && (Encriptador.DesencriptarClave(this.llave, Cuenta.getClave()).equals(pass))) {
            Consola.Debug("clave es correcta!");
            this.Enviar("Ad" + this.Cuenta.getApodo());
            this.Enviar("Ac0");
            RefrescarHost();
            this.Enviar("AlK" + Cuenta.getRol());
            this.Enviar("AQ" + Cuenta.getPregunta());
            
            Consola.Debug(msg);
            estado = EstadoCliente.Servidores;
        } else {
            Enviar("AlEx");//login error
            this.Desconectar();
        }
    }

    private void VerificarServidores(String msg) {
        if (msg.equalsIgnoreCase("Ax")) {
            // axk mas la cantidad de abono | 1 nose q mrd , cantidad personajes
            //AxK31556900000 1 año
            this.Enviar("AxK31556900000|" + "1,0");
        } 
        else if (msg.equalsIgnoreCase("AX1")) 
        {
            Consola.Debug("En AX");
            this.Enviar("AYK" + Config.GetString("IP_Servidor") + ":" + Config.GetInt("Puerto_Game") + ";" + "1");
            estado = EstadoCliente.Version;
        }
    }
    
    private void RefrescarHost() {
        Enviar("AH" + Config.GetInt("ID_Servidor") + ";1;" + Config.GetInt("ID_Servidor") * 75 + ";1");
    }

}
