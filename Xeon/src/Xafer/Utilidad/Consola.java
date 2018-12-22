
package Xafer.Utilidad;

import java.util.Scanner;
import org.fusesource.jansi.AnsiConsole;

/**
 *
 * @author Xafer
 */
public class Consola  {

    private static int datasize = 0;
    public static void ForzarCierre() {
        try {
            
            Thread.sleep(2000);
            System.exit(0);
        } catch (InterruptedException ex) {
            Consola.Error(ex.getMessage());
        }
    
    }

    public static void LeerLinea() {
        Scanner scan = new Scanner(System.in);
        String captura = scan.next();
        switch (captura) {
            case "/memoria":
                datasize = 1024*1024;
                Runtime runtime = Runtime.getRuntime();
                Consola.Error("Advertencia: El calculo de memoria no es exacto, aun falta por arreglar");
                Consola.Info("Memoria total: " + (runtime.totalMemory() / datasize) + "MB");
                Consola.Info("Memoria libre: " + (runtime.freeMemory() / datasize) + "MB");
                Consola.Info("Memoria usada: " + ((runtime.totalMemory() - runtime.freeMemory()) / datasize) + "MB");
                
                datasize = 0;
                break;
            case "/detener":
                System.out.println("Se ha forzado el cierre del servidor...");
                System.exit(0);
                break;
            default:
                System.out.println("Comando " + captura + " invalido!");
                break;
        }
        
    }

    public enum ConsoleColor {
        //fuente
        BOLD(1),
        UNDERLINE(4),
        BLINK(5),
        HIDDEN(8),
        //Color letra
        BLACK(30),
        RED(31),
        GREEN(32),
        YELLOW(33),
        BLUE(34),
        MAGENTA(35),
        CYAN(36),
        WHITE(37),
        //ColorFondo
        BG_BLACK(40),
        BG_RED(41),
        BG_GREEN(42),
        BG_YELLOW(43),
        BG_BLUE(44),
        BG_MAGENTA(45),
        BG_CYAN(46),
        BG_WHITE(47),
        // SPECIAL
        BLACK_AND_BG_WHITE(7),
        RESET(0);

        private int color = 0;

        private ConsoleColor(int color) {
            this.color = color;
        }

        public int get() {
            return color;
        }
    }

    public static void Limpiar() {
        AnsiConsole.out.print("\033[H\033[2J");
    }

    public static void Titulo(String title) {
        AnsiConsole.out.append("\033]0;").append("Xeon: " + title).append("\007");
    }

    private static void Escribir(String msg, ConsoleColor color) {
        AnsiConsole.out.println(new StringBuilder().append("\033[").append(color.get()).append("m").append(msg).append("\033[").append(ConsoleColor.RESET.get()).append("m").toString());
    }

    private static void Escribir(String Key, String msg, ConsoleColor color) {
        AnsiConsole.out.println(new StringBuilder().append("\033[").append(color.get()).append("m").append(msg).append("\033[").append(ConsoleColor.RESET.get()).append("m").toString());
    }

    public static void Info(String msg) {
        Escribir("Info", msg, ConsoleColor.WHITE);
    }

    public static void Error(String msg) {
        Escribir("Eror", msg, ConsoleColor.RED);
    }

    public static void Debug(String msg) {
        Escribir("Debug", msg, ConsoleColor.CYAN);
    }

    public static final void Encabezado() {
        System.out.println("");
        Consola.Escribir("                   ____  ___                   ", ConsoleColor.CYAN);
        Consola.Escribir("                   \\   \\/  /____  ____   ____  ", ConsoleColor.CYAN);
        Consola.Escribir("                    \\     // __ \\/  _ \\ /    \\ ", ConsoleColor.CYAN);
        Consola.Escribir("                    /     \\  ___(  <_> )   |  \\", ConsoleColor.CYAN);
        Consola.Escribir("                   /___/\\  \\___  >____/|___|  / Bersion 1.4 Alpha", ConsoleColor.CYAN);
        Consola.Escribir("                         \\_/   \\/           \\/  Creado por Xafer", ConsoleColor.CYAN);
        DibujarLinea('_');
        System.out.println("");
    }

    public static void DibujarLinea(char caract) {
        for (int i = 0; i <= 79; i++) {
            System.out.print(caract);
        }
        System.out.print("\n");
    }
    
}
