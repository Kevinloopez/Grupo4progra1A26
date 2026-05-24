package proyecto_zooa;

public class Ave extends Animal {
    
   
    public Ave(long idAnimal, String nombre, int edad, double consumoDiario) {
        super(idAnimal, nombre, edad, consumoDiario);
    }

    public void comer() {
        System.out.println(nombre + " come semillas.");
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
