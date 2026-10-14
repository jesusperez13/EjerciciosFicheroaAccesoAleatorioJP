package Ej6.FicherosDeAccesoAleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ej7 {
    private static final String ruta_fichero = "";
    private static final String fichero_origen = "src/Ej6/FicherosDeAccesoAleatorio/precio.dat";
    private static final String fichero_destino = "src/Ej6/FicherosDeAccesoAleatorio/descuento.dat";

    public static void main(String[] args) {
        File origen = new File(ruta_fichero + fichero_origen);
        File destino = new File(ruta_fichero + fichero_destino);
        try (RandomAccessFile rafOrigen = new RandomAccessFile(origen, "r");
             RandomAccessFile rafDestino = new RandomAccessFile(destino, "rw")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce el porcentaje de descuento a aplicar: ");
            double porcentaje = scanner.nextDouble();
            while (rafOrigen.getFilePointer() < rafOrigen.length()) {
                char[] nomArr = new char[10];
                for (int j = 0; j < 10; j++) nomArr[j] = rafOrigen.readChar();
                char[] dniArr = new char[9];
                for (int j = 0; j < 9; j++) dniArr[j] = rafOrigen.readChar();
                String dni = new String(dniArr).trim();
                rafOrigen.readInt();
                double cantidadAnterior = rafOrigen.readDouble();
                double cantidadConDescuento = cantidadAnterior - (cantidadAnterior * porcentaje / 100.0);
                String dniFijo = Ej5.ajustarTexto(dni, 9);
                rafDestino.writeChars(dniFijo);
                rafDestino.writeDouble(cantidadAnterior);
                rafDestino.writeDouble(cantidadConDescuento);
            }
            System.out.println("Contenido de descuento.dat");
            rafDestino.seek(0);
            while (rafDestino.getFilePointer() < rafDestino.length()) {
                char[] dniArr = new char[9];
                for (int j = 0; j < 9; j++) dniArr[j] = rafDestino.readChar();
                String dni = new String(dniArr).trim();
                double cantAnterior = rafDestino.readDouble();
                double cantDescuento = rafDestino.readDouble();
                System.out.println("DNI: " + dni + " | Precio original: " + cantAnterior + " | Precio con Descuento: " + cantDescuento);
            }
        } catch (IOException e) {
            System.out.println("Se ha producido un error al acceder al fichero");
        }
    }
}