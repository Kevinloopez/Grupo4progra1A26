/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tonoc
 */
public class Ave extends Animal {
   
    public Ave(String nombre, int edad, double consumoDiario) {
        super(nombre, edad, consumoDiario);
    }

    public void comer() {
        System.out.println(nombre + " come semillas.");
    }
}

