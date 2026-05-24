package proyecto_zooa;

public class Animal {
    
   
    protected String nombre;
    protected long idAnimal;
    protected int edad;
    protected double consumoDiario;


    public Animal(long idAnimal, String nombre, int edad, double consumoDiario) {
        this.idAnimal = idAnimal;
        this.nombre = nombre;
        this.edad = edad;
        this.consumoDiario = consumoDiario;
    }

    public long getIdAnimal() {
        return idAnimal;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getConsumoDiario() {
        return consumoDiario;
    }

    public void setIdAnimal(long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setConsumoDiario(double consumoDiario) {
        this.consumoDiario = consumoDiario;
    }

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " anios");
        System.out.println("Consumo diario: " + consumoDiario + " lbs");
        System.out.println();
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

    @Override
    public String toString() {
        return "ID: " + idAnimal +
               ", Nombre: " + nombre +
               ", Edad: " + edad +
               ", Consumo diario: " + consumoDiario;
    }
}

