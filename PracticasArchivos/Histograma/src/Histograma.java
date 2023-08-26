/*
Desarrollar un programa que genere un histograma con la longitud en caracteres de cada una de las palabras
 contenidas en un archivo de texto. Ignorar cifras numéricas y considerar exclusivamente palabras desde 2 hasta
 un máximo de 10 caracteres de longitud. Usar el archivo divina_comedia_sp.txt para las pruebas.
 */

import java.io.*;
import java.util.ArrayList;

public class Histograma {
    public static final String RUTA = "C:\\Users\\ingri\\Documents\\UNISON\\Desarrollo de Sistemas III\\PracticasArchivos\\src\\Resource\\divina_comedia_sp.txt";
    public static String linea = "";
    public static ArrayList<String> divinaComedia;

    public static void main(String[] args) throws IOException {
        //leer el archivo a un array list con un br
        divinaComedia = new ArrayList<>();
        File file = new File(RUTA);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = br.readLine();
        int[] cuenta = new int[9];

        try{
            while ((linea = br.readLine()) != null) {
                //almacenar en un array cada espacio en blanco
                String[] palabras = linea.split(" ");
                for (String palabra : palabras) {
                    validarPalabras(palabra, cuenta);
                }
            }
    } catch (IOException e) {
        e.printStackTrace();

}
        System.out.println("Letras\t Frecuencia");
        for (int i = 0; i < cuenta.length; i++) {
            int tamanioPalabra = i + 2;
            System.out.println("  " + tamanioPalabra + " \t\t" + cuenta[i]);

        }
    }

    //metodo digitos
    public static boolean noDigitos(String palabra) {
        for (char letra : palabra.toCharArray()) {
            if (Character.isDigit(letra)) {
                return false;
            }
        }
        return true;
    }

    //metodo para validar palabra
    public static void validarPalabras(String palabra, int[] aux) {
        if (palabra.length() <= 10 && palabra.length() >= 2 && noDigitos(palabra)) {
            int indice = palabra.length() - 2;
            aux[indice]++;
        }
    }

}

