/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto_zooa;

/**
 *
 * @author Daniel
 */
public class Proyecto_ZooA {
    //Aqui lo que hacemos es realizar un recorrido por la matriz animal para mostrar los animales que se encuentran en ella
     public static void mostrarAnimales(Animal[] animales) {
        for (int i = 0; i < contador; i++) {
            System.out.println(animales[i].nombre + " - " + animales[i].edad + " anios");
            animales[i].alimentar();   
            //Lo que pensamos aqui  seria bueno que al mostrar los animales podriamos alimentarlos de una vez
        }
    }
     //Aqui solicitamos al usuario que nos proporcione los dias para poder realizar el calculo del consumo del animal
    public static void calcularConsumo(Scanner sc, Animal[] animales, int contador) {
    System.out.print("Ingrese dias: ");
    int dias = sc.nextInt();

    for (int i = 0; i < contador; i++) {  // solo recorre animales que existen
        double total = animales[i].calcularConsumo(dias);
        System.out.println(animales[i].nombre + " necesita " + total + " de comida " + dias + " dias");
        //Esta parte lo que nos permite es mostrarle el alimento necesario para los dias dados por el usuario 
    }
}
}
