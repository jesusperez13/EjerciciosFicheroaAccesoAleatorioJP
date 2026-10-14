package Ej6.FicherosDeAccesoAleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ej1 {
    private static final String ruta_fichero = "";
    private static final String nombre_fichero = "src/Ej6/FicherosDeAccesoAleatorio/enteros.dat";

    public static void main(String[] args) {
        File fichero = new File(ruta_fichero + nombre_fichero);
        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < 6; i++) {
                raf.writeInt(1);
            }
            for (int i = 0; i < 10; i++) {
                raf.writeInt(2);
            }
            System.out.println("Contenido inicial del fichero:");
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                System.out.print(raf.readInt() + " ");
            }
            System.out.println();
            System.out.print("Introduce un número entre 0 y 9 para sustituir los 2: ");
            int nuevoValor = scanner.nextInt();
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                long posicion = raf.getFilePointer();
                int valor = raf.readInt();
                if (valor == 2) {
                    raf.seek(posicion);
                    raf.writeInt(nuevoValor);
                }
            }
            System.out.println("Contenido final del fichero:");
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
