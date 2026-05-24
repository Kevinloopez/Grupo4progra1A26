package proyecto_zooa;

public class Mamifero extends Animal {
    
    
     public Mamifero(long idAnimal, String nombre, int edad, double consumoDiario) {
        super(idAnimal, nombre, edad, consumoDiario);
    }

    public void comer() {
        System.out.println(nombre + " come carne o plantas.");
    }
    
    @Override
    public String getNombre(){
        return super.getNombre();
    }
    
    @Override
    public int getEdad(){
        return super.getEdad();
    }
    
    @Override
    public long getIdAnimal(){
        return super.getIdAnimal();
    }
}
