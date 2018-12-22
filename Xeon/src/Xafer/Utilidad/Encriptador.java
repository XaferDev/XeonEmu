package Xafer.Utilidad;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 *
 * @author Xerooh
 */
public class Encriptador {

    public static String toUtf(String _in) {
        String _out = "";
        try {
            _out = new String(_in.getBytes("UTF8"));

        } catch (UnsupportedEncodingException e) {
            Consola.Error("Conversion en UTF-8 echoue! : " + e.getMessage());
        }
        return _out;
    }

    public static String RandomString() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder hashkey = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 32; i++) {
            hashkey.append(alphabet.charAt(rand.nextInt(alphabet.length())));
        }
        return hashkey.toString();
    }

    public static char[] HASH = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};

    public static String DesencriptarClave(String Key, String clave) {
        Consola.Debug("Datos: Key: " + Key + " | Pass:" + clave);
        String _Crypted = "";

        for (int i = 0; i < clave.length(); i++) {
            char PPass = clave.charAt(i);
            char PKey = Key.charAt(i);

            int APass = (int) PPass / 16;

            int AKey = (int) PPass % 16;

            int ANB = (APass + (int) PKey) % HASH.length;
            int ANB2 = (AKey + (int) PKey) % HASH.length;

            _Crypted += HASH[ANB];
            _Crypted += HASH[ANB2];

        }
        Consola.Debug(_Crypted);
        return _Crypted;
    }

}
