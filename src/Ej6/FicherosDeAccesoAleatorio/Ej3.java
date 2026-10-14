package Ej6.FicherosDeAccesoAleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ej3 {
    private static final String ruta_fichero = "";
    private static final String nombre_fichero = "src/Ej6/FicherosDeAccesoAleatorio/juegos.dat";

    public static String ajustarTexto(String texto, int longitud) {
        StringBuffer sb = new StringBuffer(texto);
        sb.setLength(longitud);
        return sb.toString();
    }
    public static void main(String[] args) {
        File fichero = new File(ruta_fichero + nombre_fichero);
        int[] numeros = {1, 2, 3};
        String[] nombres = {"GTA", "Minecraft", "Fortnite"};
        int[] edades = {6, 8, 12};
        String[] tematicas = {"Aventura", "Creativo", "Accion"};
        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            for (int i = 0; i < numeros.length; i++) {
                raf.writeInt(numeros[i]);
                raf.writeChars(ajustarTexto(nombres[i], 10));
                raf.writeInt(edades[i]);
                raf.writeChars(ajustarTexto(tematicas[i], 10));
            }
            System.out.println("Juegos:");
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                int numero = raf.readInt();
                char[] nom = new char[10];
                for (int j = 0; j < 10; j++) {
                    nom[j] = raf.readChar();
                }
                String nombre = new String(nom).trim();
                int edad = raf.readInt();
                char[] tem = new char[10];
                for (int j = 0; j < 10; j++) {
                    tem[j] = raf.readChar();
                }
                String tematica = new String(tem).trim();
                System.out.println("Número: " + numero + " | Nombre: " + nombre + " | Edad mínima: " + edad + " | Temática: " + tematica);
            }
        } catch (IOException e) {
            System.out.println("Se ha producido un error al acceder al fichero");
        }
    }
}