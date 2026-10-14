package Ej6.FicherosDeAccesoAleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ej4 {
    private static final String ruta_fichero = "";
    private static final String nombre_fichero = "src/Ej6/FicherosDeAccesoAleatorio/juegos.dat";

    public static void main(String[] args) {
        File fichero = new File(ruta_fichero + nombre_fichero);
        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce la edad para buscar juegos aptos: ");
            int edadUsuario = scanner.nextInt();
            System.out.println("Juegos recomendaos para esa edad: " + edadUsuario);
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                raf.readInt();
                char[] nom = new char[10];
                for (int j = 0; j < 10; j++) {
                    nom[j] = raf.readChar();
                }
                String nombre = new String(nom).trim();
                int edadMinima = raf.readInt();
                char[] tem = new char[10];
                for (int j = 0; j < 10; j++) {
                    tem[j] = raf.readChar();
                }
                String tematica = new String(tem).trim();
                if (edadUsuario >= edadMinima) {
                    System.out.println("Juego: " + nombre + " | Temática: " + tematica + " | Edad mínima: " + edadMinima);
                }
            }
        } catch (IOException e) {
            System.out.println("Se ha producido un error al acceder al fichero");
        }
    }
}