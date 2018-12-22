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
public class TablaPersonajes {

    public static Map<Integer, TablaPersonajes> lista_personajes = new TreeMap<Integer, TablaPersonajes>();
    private int id;
    private int prop;
    private String nom;
    private boolean gen;
    private int clas;
    private int color1;
    private int color2;
    private int color3;
    
    private TablaPersonajes(int _id, int _prop, String _nom, boolean _gen, int _clas, int _col1, int _col2, int _col3) {
        id = _id;
        prop = _prop;
        nom = _nom;
        gen = _gen;
        clas = _clas;
        color1 = _col1;
        color2 = _col2;
        color3 = _col3;
    }

    public static void Cargar() {
        try {
            Statement s;
            s = (Statement) DBGestor.getCONEXION().createStatement(); //conexion.createStatement();
            ResultSet rs;

            rs = s.executeQuery("select * from " + "personajes");
            while (rs.next()) {
                TablaPersonajes personajes;
                personajes = new TablaPersonajes(rs.getInt("id_personajes"),
                         rs.getInt("propietario"),
                        rs.getString("nombre"),
                        rs.getBoolean("genero"),
                        rs.getInt("clase"),
                        rs.getInt("color1"),
                        rs.getInt("color2"),
                        rs.getInt("color3"));
                lista_personajes.put(personajes.getId(), personajes);
                //cuentas_por_usuario.put(personajes.getUsuario(), personajes.getId());
            }
            s.close();
            rs.close();
            Consola.Debug("Personajes cargados: " + lista_personajes.size() + " !");
        } catch (SQLException e) {
            Consola.Error(e.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProp() {
        return prop;
    }

    public void setProp(int prop) {
        this.prop = prop;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isGen() {
        return gen;
    }

    public void setGen(boolean gen) {
        this.gen = gen;
    }

    public int getClas() {
        return clas;
    }

    public void setClas(int clas) {
        this.clas = clas;
    }

    public int getColor1() {
        return color1;
    }

    public void setColor1(int color1) {
        this.color1 = color1;
    }

    public int getColor2() {
        return color2;
    }

    public void setColor2(int color2) {
        this.color2 = color2;
    }

    public int getColor3() {
        return color3;
    }

    public void setColor3(int color3) {
        this.color3 = color3;
    }

}
