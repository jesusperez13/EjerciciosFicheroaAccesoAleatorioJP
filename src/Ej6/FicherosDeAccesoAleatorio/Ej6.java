package Ej6.FicherosDeAccesoAleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ej6 {
    private static final String ruta_fichero = "";
    private static final String fichero_origen = "src/Ej6/FicherosDeAccesoAleatorio/practicas.dat";
    private static final String fichero_destino = "src/Ej6/FicherosDeAccesoAleatorio/precio.dat";
    private static final double precio_clase = 45.7;

    public static void main(String[] args) {
        File origen = new File(ruta_fichero + fichero_origen);
        File destino = new File(ruta_fichero + fichero_destino);
        try (RandomAccessFile rafOrigen = new RandomAccessFile(origen, "r");
             RandomAccessFile rafDestino = new RandomAccessFile(destino, "rw")) {
            while (rafOrigen.getFilePointer() < rafOrigen.length()) {
                char[] nomArr = new char[10];
                for (int j = 0; j < 10; j++) nomArr[j] = rafOrigen.readChar();
                String nombre = new String(nomArr).trim();
                char[] apArr = new char[35];
                for (int j = 0; j < 35; j++) apArr[j] = rafOrigen.readChar();
                char[] dniArr = new char[9];
                for (int j = 0; j < 9; j++) dniArr[j] = rafOrigen.readChar();
                String dni = new String(dniArr).trim();
                rafOrigen.readInt();
                int clases = rafOrigen.readInt();
                double totalPagar = clases * precio_clase;
                String nombreFijo = Ej5.ajustarTexto(nombre, 10);
                String dniFijo = Ej5.ajustarTexto(dni, 9);
                rafDestino.writeChars(nombreFijo);
                rafDestino.writeChars(dniFijo);
                rafDestino.writeInt(clases);
                rafDestino.writeDouble(totalPagar);
            }
            System.out.println("Fichero creado correctamente");
        } catch (IOException e) {
            System.out.println("Se ha producido un error al acceder al fichero");
        }
    }
}