/*
Los archivos a.mat y b.mat son archivos binarios que contienen los datos matrices con valores numéricos reales.
El primer byte en el archivo indica el número de renglones en la matriz. El segundo byte indica el número de columnas
en la matriz. Los datos restantes en el archivo corresponden a los valores de tipo double almacenados en la matriz.

Elaborar un programa que lea los archivos a.mat y b.mat y calcule el producto de las matrices.
Almacenar el resultado en un archivo c.mat con el mismo formato de los archivos de entrada.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArchivosBinarios {
    public static void main(String[] args) {
        try {
            FileInputStream ArchivoMatrizA = new FileInputStream("a.mat");
            int renMatrizA = ArchivoMatrizA.read();
            int colMatrizA = ArchivoMatrizA.read();

            FileInputStream ArchivoMatrizB = new FileInputStream("b.mat");
            int renMatrizB = ArchivoMatrizB.read();
            int colMatrizB = ArchivoMatrizB.read();

            //validacion de matrices
            if(renMatrizB != colMatrizA){
                System.out.println("Las matrices no se pueden multiplicar.");
            }

            //crear matrices
            double[][] matrizA = new double[renMatrizA][colMatrizA];
            double[][] matrizB = new double[renMatrizB][colMatrizB];
            double[][] matrizResultado = new double[renMatrizA][colMatrizB];

            //llenar matriz A
            for (int i = 0; i < renMatrizA; i++) {
                for (int j = 0; j < colMatrizA; j++) {
                    matrizA[i][j] = ArchivoMatrizA.read();
                }
            }

            //llenar matriz B
            for (int i = 0; i < renMatrizB; i++) {
                for (int j = 0; j < colMatrizB; j++) {
                    matrizA[i][j] = ArchivoMatrizB.read();
                }
            }

            //multiplicar matrices
            for (int i = 0; i < renMatrizA; i++) {
                for (int j = 0; j < colMatrizB; j++) {
                    int suma = 0;
                    for (int k = 0; k < colMatrizA; k++) {
                        suma += matrizA[i][k] * matrizB[k][j];
                    }
                    matrizResultado[i][j] = suma;
                }
            }

            //imprimir resultado
            for (int i = 0; i < renMatrizA; i++) {
                for (int j = 0; j < colMatrizB; j++) {
                    System.out.println(matrizResultado[i][j] + "\n");
                }
            }

            FileOutputStream archivoMatrizRes = new FileOutputStream("c.mat");

            //almacenar matrizResultados en nuevo mat file
            for (double[] ren : matrizResultado) {
                for (double valor : ren) {
                    archivoMatrizRes.write(Integer.toString((int) valor).getBytes());
                    archivoMatrizRes.write(" ".getBytes());
                }
                archivoMatrizRes.write("\n".getBytes());
            }
            archivoMatrizRes.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}