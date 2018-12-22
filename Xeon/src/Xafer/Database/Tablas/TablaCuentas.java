/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xafer.Database.Tablas;

import Xafer.Database.DBGestor;
import Xafer.Utilidad.Consola;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Xerooh
 */
public class TablaCuentas {

    public int posicion = -1;

    private int id;
    private String usuario;
    private String clave;
    private String apodo;
    private int rol;
    private String pregunta;
    private String respuesta;
    private String amigos;
    private String enemigos;
    private boolean conectado;
    private boolean baneado;
    private String ultima_ip;
    private String ultima_conexion;
    private String banco_objetos;
    private int banco_kamas;
    private String cercado;
    private int abonado;

    //Diferencia entre HashMap y TreeMap | HashMap no acepta valores duplicados y TreeMap si, ademas ordena de menor a mayor los valores
    public static Map<Integer, TablaCuentas> lista_cuentas = new TreeMap<Integer, TablaCuentas>();
    private static Map<String, Integer> cuentas_por_usuario = new TreeMap<String, Integer>();

    public static void Cargar() {
        try {
            Statement s;
            s = (Statement) DBGestor.getCONEXION().createStatement(); //conexion.createStatement();
            ResultSet rs;
            rs = s.executeQuery("select * from " + "cuentas");
            while (rs.next()) {
                TablaCuentas cuentas = new TablaCuentas(
                        rs.getInt("id_cuentas"),
                        rs.getString("usuario"),
                        rs.getString("clave"),
                        rs.getString("apodo"),
                        rs.getInt("rol"),
                        rs.getString("pregunta"),
                        rs.getString("respuesta"),
                        rs.getString("amigos"),
                        rs.getString("enemigos"),
                        rs.getBoolean("conectado"),
                        rs.getBoolean("baneado"),
                        rs.getString("ultima_ip"),
                        rs.getString("ultima_conexion"),
                        rs.getString("banco_objetos"),
                        rs.getInt("banco_kamas"),
                        rs.getString("cercado"),
                        rs.getInt("abonado")
                );
                lista_cuentas.put(cuentas.getId(), cuentas);
                cuentas_por_usuario.put(cuentas.getUsuario(), cuentas.getId());
            }
            s.close();
            rs.close();
            Consola.Debug("Cuentas cargadas " + lista_cuentas.size() + " !");
        } catch (SQLException e) {
            Consola.Error(e.getMessage());
        }
    }

    public static TablaCuentas ObtenerCuentasPorUsuario(String usuario) {
        return (cuentas_por_usuario.get(usuario.toLowerCase()) != null ? lista_cuentas.get(cuentas_por_usuario.get(usuario.toLowerCase())) : null);
    }

    private TablaCuentas(int _id, String _usuario, String _clave, String _apodo, int _rol, String _pregunta,
            String _respuesta, String _amigos, String _enemigos, boolean _conectado, boolean _baneado,
            String _ultima_ip, String _ultima_conexion, String _banco_objetos, int _banco_kamas,
            String _cercado, int _abonado) {
        id = _id;
        usuario = _usuario;
        clave = _clave;
        apodo = _apodo;
        rol = _rol;
        pregunta = _pregunta;
        respuesta = _respuesta;
        amigos = _amigos;
        enemigos = _enemigos;
        conectado = _conectado;
        baneado = _baneado;
        ultima_ip = _ultima_ip;
        ultima_conexion = _ultima_conexion;
        banco_objetos = _banco_objetos;
        banco_kamas = _banco_kamas;
        cercado = _cercado;
        abonado = _abonado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getAmigos() {
        return amigos;
    }

    public void setAmigos(String amigos) {
        this.amigos = amigos;
    }

    public String getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(String enemigos) {
        this.enemigos = enemigos;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public boolean isBaneado() {
        return baneado;
    }

    public void setBaneado(boolean baneado) {
        this.baneado = baneado;
    }

    public String getUltima_ip() {
        return ultima_ip;
    }

    public void setUltima_ip(String ultima_ip) {
        this.ultima_ip = ultima_ip;
    }

    public String getUltima_conexion() {
        return ultima_conexion;
    }

    public void setUltima_conexion(String ultima_conexion) {
        this.ultima_conexion = ultima_conexion;
    }

    public String getBanco_objetos() {
        return banco_objetos;
    }

    public void setBanco_objetos(String banco_objetos) {
        this.banco_objetos = banco_objetos;
    }

    public int getBanco_kamas() {
        return banco_kamas;
    }

    public void setBanco_kamas(int banco_kamas) {
        this.banco_kamas = banco_kamas;
    }

    public String getCercado() {
        return cercado;
    }

    public void setCercado(String cercado) {
        this.cercado = cercado;
    }

    public int getAbonado() {
        return abonado;
    }

    public void setAbonado(int abonado) {
        this.abonado = abonado;
    }

    public int GetCantidadPersonajes() {
        return TablaPersonajes.lista_personajes.size();
    }

}
