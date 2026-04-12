/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tonoc
 */
public class ANIMAL {
    
    String nombre;
    int edad;
    double consumoDiario;

    public Animal(String nombre, int edad, double consumoDiario) {
        this.nombre = nombre;
        this.edad = edad;
        this.consumoDiario = consumoDiario;
    }

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Consumo diario: " + consumoDiario);
    }

    public double calcularConsumo(int dias) {
        if (dias == 1) {
            return consumoDiario;
        } else {
            return consumoDiario + calcularConsumo(dias - 1);
        }
    }
     public void alimentar() {
        System.out.println(nombre + " esta comiendo.");
    }

}
