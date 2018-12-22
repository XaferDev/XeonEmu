/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xafer.Utilidad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Xerooh
 */
public class Config {

    private static final java.util.HashMap<String, String> _Items = new java.util.HashMap<String, String>();
    private static String ruta;

    public static Config AppSettings;

    public static Config getAppSettings() {
        return AppSettings;
    }

    public static void setAppSettings(Config AppSettings) {
        Config.AppSettings = AppSettings;
    }

    public static void Cargar() {
        File f = new File("ConfigXeon.ini");
        
        if (f.exists()) {
            
            AppSettings = new Config("ConfigXeon.ini");
            Config.Leer();
        }else
        {
            Consola.Error("El archivo no existe");
            Consola.ForzarCierre();
        }

    }

    public Config(String _ruta) {
        ruta = _ruta;
    }

    public static String GetString(String Item) {
        if (_Items.containsKey(Item)) {
            return _Items.get(Item);
        }
        System.out.println("No se puede encontrar '" + Item + "' en la configuracion");
        return "";
    }

    public static int GetInt(String Item) {
        if (_Items.containsKey(Item)) {
            return Integer.parseInt(_Items.get(Item));
        }
        System.out.println("No se puede encontrar '" + Item + "' en la configuracion");
        return Integer.parseInt(Item);
    }

    public static Short GetShort(String Item) {
        if (_Items.containsKey(Item)) {
            return Short.parseShort(_Items.get(Item));
        }
        System.out.println("No se puede encontrar '" + Item + "' en la configuracion");
        return Short.parseShort(Item);
    }

    public static Boolean GetBool(String Item) {
        if (_Items.containsKey(Item)) {
            return Boolean.parseBoolean(_Items.get(Item));
        }
        System.out.println("No se puede encontrar '" + Item + "' en la configuracion");
        return Boolean.parseBoolean(Item);
    }

    public static void Leer() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.split("=").length == 1) {
                    continue;
                }

                if (line.contains("=")) {
                    String[] array = line.split("=", 2);
                    String text2 = array[0].trim();
                    String value = array[1].trim();
                    _Items.put(text2, value);
                }
            }
            br.close();
            Consola.Info("Configuracion cargada!");
//            if (GetString("STATIC_DB_NAME") == null || GetString("OTHER_DB_NAME") == null || GetString("DB_HOST") == null || GetString("DB_PASS") == null || GetString("DB_USER") == null) {
//                throw new Exception();
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Consola.Error("El archivo de configuracion no existe o es invalido!");
            Consola.Error("Cerrando el servidor");
            System.exit(0);
        }
    }

}
