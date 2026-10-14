package Ej6.FicherosDeAccesoAleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ej5 {
    private static final String ruta_fichero = "";
    private static final String nombre_fichero = "src/Ej6/FicherosDeAccesoAleatorio/practicas.dat";

    public static String ajustarTexto(String texto, int longitud) {
        StringBuffer sb = new StringBuffer(texto);
        sb.setLength(longitud);
        return sb.toString();
    }

    public static void main(String[] args) {
        File fichero = new File(ruta_fichero + nombre_fichero);
        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce la cantidad de alumnos a registrar: ");
            int n = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < n; i++) {
                System.out.println("Alumno " + (i + 1));
                System.out.print("Nombre (máximo 10 caracteres): ");
                String nombre = ajustarTexto(scanner.nextLine(), 10);
                System.out.print("Apellidos (máximo 35 caracteres): ");
                String apellidos = ajustarTexto(scanner.nextLine(), 35);
                System.out.print("DNI (8 dígitos y 1 letra): ");
                String dni = ajustarTexto(scanner.nextLine(), 9);
                System.out.print("Vehículo de prácticas (entero 2 dígitos): ");
                int vehiculo = scanner.nextInt();
                System.out.print("Número de clases de prácticas (entero 2 dígitos): ");
                int clases = scanner.nextInt();
                scanner.nextLine();
                raf.writeChars(nombre);
                raf.writeChars(apellidos);
                raf.writeChars(dni);
                raf.writeInt(vehiculo);
                raf.writeInt(clases);
            }
            System.out.println("Contenido de practicas.dat");
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                char[] nomArr = new char[10];
                for (int j = 0; j < 10; j++) nomArr[j] = raf.readChar();
                String nombre = new String(nomArr).trim();
                char[] apArr = new char[35];
                for (int j = 0; j < 35; j++) apArr[j] = raf.readChar();
                String apellidos = new String(apArr).trim();
                char[] dniArr = new char[9];
                for (int j = 0; j < 9; j++) dniArr[j] = raf.readChar();
                String dni = new String(dniArr).trim();
                int vehiculo = raf.readInt();
                int clases = raf.readInt();
                System.out.println(nombre + " " + apellidos + " | DNI: " + dni + " | Vehículo: " + vehiculo + " | Clases: " + clases);
            }
        } catch (IOException e) {
            System.out.println("Se ha producido un error al acceder al fichero");
        }
    }
}