package Ej6.FicherosDeAccesoAleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ej2 {
    private static final String ruta_fichero = "";
    private static final String nombre_fichero = "src/Ej6/FicherosDeAccesoAleatorio/enteros.dat";

    public static void main(String[] args) {
        File fichero = new File(ruta_fichero + nombre_fichero);
        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce un número entre 0 y 9: ");
            int numero = scanner.nextInt();
            System.out.print("¿Cuántas veces deseas añadirlo?: ");
            int veces = scanner.nextInt();
            raf.seek(raf.length());
            for (int i = 0; i < veces; i++) {
                raf.writeInt(numero);
            }
            System.out.println("Contenido del fichero actualizado:");
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                System.out.print(raf.readInt() + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Se ha producido un error al acceder al fichero");
        }
    }
}