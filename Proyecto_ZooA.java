/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_zooa;

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
                    menuZoo(sc, animales);
                    break;
                case 2:
                   calcularConsumo(sc, animales, contador);
                   break;
                case 3:
                   exportarCSV(animales);
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
                    agregarAnimal(sc, animales); 
                    break;
                case 2:
                    mostrarAnimales(animales); 
                    break;
            }

        } while (opcion != 3);
    }

    public static void agregarAnimal(Scanner sc, Animal[] animales) {

        if (contador >= 3) {
            System.out.println("Ya no se puede agregar mas animales.");
            return;
        }

        System.out.println("1. Mamifero"); //
        System.out.println("2. Ave");
        System.out.println("3. Reptil");

        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Edad: ");
        int edad = sc.nextInt();

        System.out.print("comida:  "); 
        double comida = sc.nextDouble();

        if (tipo == 1) {
            animales[contador] = new Mamifero(nombre, edad, comida);
        } else if (tipo == 2) {
            animales[contador] = new Ave(nombre, edad, comida);
        } else if (tipo == 3) {
            animales[contador] = new Reptil(nombre, edad, comida);
        } else {
            System.out.println("Tipo invalido");
            return;
        }

        contador++; 
    }

    public static void mostrarAnimales(Animal[] animales) {

        for (int i = 0; i < contador; i++) {
            System.out.println(animales[i].nombre + " - " + animales[i].edad + " anios");
            animales[i].alimentar(); 
        }
    }
    public static void calcularConsumo(Scanner sc, Animal[] animales, int contador) {
    System.out.print("Ingrese dias: ");
    int dias = sc.nextInt();

    for (int i = 0; i < contador; i++) {  // solo recorre animales que existen
        double total = animales[i].calcularConsumo(dias);
        System.out.println(animales[i].nombre + " necesita " + total + " de comida " + dias + " dias");
    }
}

public static void exportarCSV(Animal[] animales) {
    try {
        java.io.FileWriter writer = new java.io.FileWriter("animales.csv");

       
        writer.write("Nombre,  Edad, ConsumoDiario\n");

        
       for (int i = 0; i < Proyecto_ZooA.contador; i++) {
            writer.write(animales[i].nombre + ", " +
                         animales[i].edad + ", " +
                         animales[i].consumoDiario + "\n");
        }

        writer.close();
        System.out.println("Datos exportados a animales.csv correctamente");

    } catch (Exception e) {
        System.out.println("Error al exportar CSV");
    }
}
}

   