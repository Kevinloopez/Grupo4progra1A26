/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package poo.proyecto_zoo.java;

/**
 *
 * @author madel
 */
import java.util.Scanner;

public class Proyecto_ZooA {

    static int contador = 0; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Animal[] animales = new Animal[3];

        int opcion;
        do {
            System.out.println("------BIENVENIDOS AL ZOOLOGICO LA AURORA-----");
            System.out.println("Comencemos...");
            System.out.println("1. Zoo");
            System.out.println("2.  Calcular comida ");
            System.out.println("3. cvs");
            System.out.println("4. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    //menuZoo(sc, animales);
                    break;
                case 2:
                   //calcularConsumo(sc, animales, contador);
                   break;
                case 4:
                    System.out.println("Adios...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 4);
    }

    public static void menuZoo(Scanner sc, Animal[] animales) {
        int opcion;

        do {
            System.out.println("\n--- ZOOLOGICO LA AURORA  ---");
            System.out.println("1. Agregar animal");
            System.out.println("2. Ver animales");
            System.out.println("3. Regresar");
            System.out.print("Opcion: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    //agregarAnimal(sc, animales); 
                    break;
                case 2:
                    //mostrarAnimales(animales); 
                    break;
            }

        } while (opcion != 3);
    }
}