package proyecto_zooa;

import java.util.Scanner;
import conexion.BaseDatos;
import java.util.ArrayList;


public class PROYECTOZOO2 {

    static int contador = 0; 

    public static void main(String[] args) {
        
        dao.MamiferoDAO mamiferoDao = new dao.MamiferoDAO();
        
        BaseDatos.getConexion();
        Scanner sc = new Scanner(System.in);
        Animal[] animales = new Animal[10];

        int opcion;
        do {
            System.out.println("------------ MENU ZOO ------------");
            System.out.println("1. Zoo");
            System.out.println("2. FASE II");
            System.out.println("3. FASE III");
            System.out.println("4. Salir");
            System.out.println();
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    //menuZoo(sc, animales);
                    break;
                case 2:
                   menuZoo(sc, animales);
                   break;
                case 3:
                   menuFase3(sc);
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
        char opcion;

        do {
            System.out.println("========================================");
            System.out.println("ZOOLOGICO LA AURORA");
            System.out.println("========================================");
            System.out.println("a. Agregar Mamifero");
            System.out.println("b. Agregar Ave");
            System.out.println("c. Agregar Reptil");
            System.out.println();
            System.out.println("d. Buscar animal por id");
            System.out.println("e. Buscar animal por nombre");
            System.out.println("f. Ordenar arreglo por identificador");
            System.out.println("g. Mostrar todos los animales");
            System.out.println("h. Mostrar estadisticas");
            System.out.println("i. Regresar");
            System.out.println();
            
            System.out.print("Opcion: ");
            opcion = sc.next().charAt(0);//sirve para tomar el texto ingresado y devolver una posición

            switch (opcion) {
                case 'a':
                    agregarAnimal(sc, animales, 1); 
                    break;
                    
                case 'b':
                    agregarAnimal(sc, animales, 2); 
                    break;
                    
                case 'c':
                    agregarAnimal(sc, animales, 3); 
                    break;
                    
                case 'd':
                    System.out.println("Ingrese ID: ");
                    long id = sc.nextLong();
                    
                    buscarporid(animales, id);
                    break;
                    
                case 'e':
                    sc.nextLine();
                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();
                    
                    buscarpornombre(animales, nombre);
                    break;
                    
                case 'f':
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    
                    int orden = sc.nextInt();
                    
                    if (orden == 1) {
                        ordenar(animales, true);
                        System.out.println("Ordenado ascendentemente");
                    }
                    else if (orden == 2) {
                        ordenar(animales, false);
                        System.out.println("Ordenado descendentemente");
                    }
                    else {
                        System.out.println("Opcion invalida");
                    }
                    mostrarAnimales(animales);
                    break;

                    
                case 'g':
                    mostrarAnimales(animales);
                    break;
                    
                case 'h':
                    estadisticas(animales);
                    break;
                    
                case 'i':
                    System.out.println("Regresando...");
                    break;
                    
                default:
                
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 'i');
    }

    public static void agregarAnimal(Scanner sc, Animal[] animales, int tipo) {

        if (contador >= animales.length) {
            System.out.println("Ya no se puede agregar mas animales.");
            return;
        }
        
        //parte 2 de la l 86 a 99
        long id;
        
        do {
            System.out.print("ID: ");
            id = sc.nextLong();
            
            if (idExiste(animales, id)) {
                System.out.println("El identificador es unico para cada animal");
            }
            
        } while (idExiste(animales, id));
        
        sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Edad: ");
        int edad = sc.nextInt();

        System.out.print("Comida: "); 
        double comida = sc.nextDouble();

        if (tipo == 1) {
            
            sc.nextLine();
            Mamifero nuevoMamifero = new Mamifero(id, nombre, edad, comida);
            animales[contador] = nuevoMamifero;
            dao.MamiferoDAO mamiferoDao = new dao.MamiferoDAO();
            mamiferoDao.insertar(nuevoMamifero);
            
        } else if (tipo == 2) {
            
            sc.nextLine();
            animales[contador] = new Ave(id, nombre, edad, comida);
            
        } else if (tipo == 3) {
            
            sc.nextLine();
            animales[contador] = new Reptil(id, nombre, edad, comida);
        } else {
            System.out.println("Tipo invalido");
            return;
        }

        contador++; 
        System.out.println("\n " + nombre + " fue agregado correctamente");
    }
    
    public static void menuFase3(Scanner sc) {
        char opcionF3;
        do {
            System.out.println("------------ FASE 3 - BASE DE DATOS ------------");
            System.out.println("a: Trabajar con Mamifero");
            System.out.println("b: Trabajar con Ave");
            System.out.println("c: Trabajar con Reptil");
            System.out.println("d: Regresar al menu principal");
            System.out.println();
            System.out.print("Seleccione una opcion: ");
            opcionF3 = sc.next().toLowerCase().charAt(0);

            switch (opcionF3) {
                case 'a':
                case 'b':
                case 'c':
                    menuCRUD(sc, opcionF3);
                    break;
                case 'd':
                    System.out.println("Regresando al menu principal...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcionF3 != 'd');
    }
    
    public static void menuCRUD(Scanner sc, char tipoAnimal) {
        char opcionCRUD;
        dao.MamiferoDAO mamiferoDao = new dao.MamiferoDAO();
        dao.AveDAO aveDao = new dao.AveDAO();
        dao.ReptilDAO reptilDao = new dao.ReptilDAO();
        
        String identificador = "";
        if (tipoAnimal == 'a') identificador = "MAMIFERO";
        if (tipoAnimal == 'b') identificador = "AVE";
        if (tipoAnimal == 'c') identificador = "REPTIL";

        do {
            System.out.println("\n--- SUB-MENU  (" + identificador + ") ---");
            System.out.println("C: Insertar");
            System.out.println("R: Consultar");
            System.out.println("U: Actualizar");
            System.out.println("D: Eliminar");
            System.out.println("S: Regresar");
            System.out.println();
            System.out.print("Seleccione una accion: ");
            opcionCRUD = sc.next().toUpperCase().charAt(0);

            switch (opcionCRUD) {
                
                case 'C':
                    System.out.print("ID: ");
                        long id = sc.nextLong();
                        sc.nextLine(); 

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Edad: ");
                        int edad = sc.nextInt();

                        System.out.print("Comida: "); 
                        double comida = sc.nextDouble();
                    
                    if (tipoAnimal == 'a') {
                        
                        Mamifero nuevoMamifero = new Mamifero(id, nombre, edad, comida);
                        mamiferoDao.insertar(nuevoMamifero); 

                    } else if (tipoAnimal == 'b') {
                        Ave nuevaAve = new Ave(id, nombre, edad, comida);
                        aveDao.insertar(nuevaAve);
                    } else if (tipoAnimal == 'c') {
                        Reptil nuevoReptil = new Reptil(id, nombre, edad, comida);
                        reptilDao.insertar(nuevoReptil);
                    }
                    break;
                case 'R':
                if (tipoAnimal == 'a') {
                    ArrayList<Mamifero> listaBD = mamiferoDao.consultar();
                    if (listaBD.isEmpty()) {
                        System.out.println("No hay informacion disponible");
                    } else {
                        System.out.println("\n--- REGISTROS EN LA BASE DE DATOS ---");
                        for (Mamifero m : listaBD) {
                            m.mostrarInfo(); 
                        }
                    }
                } else if (tipoAnimal == 'b') {
                    ArrayList<Ave> listaBD = aveDao.consultar();
                    if (listaBD.isEmpty()) {
                        System.out.println("No hay informacion disponible");
                    } else {
                        System.out.println("Informacion en la base de datos: ");
                        for (Ave a : listaBD) { 
                            a.mostrarInfo();
                        }
                    }
                } else if (tipoAnimal == 'c') { // <-- ¡Aquí ya no dará error!
                    ArrayList<Reptil> listaBD = reptilDao.consultar();
                    if (listaBD.isEmpty()) {
                        System.out.println("No hay informacion disponible");
                    } else {
                        System.out.println("Informacion en la base de datos: ");
                        for (Reptil r : listaBD) { 
                            r.mostrarInfo();
                        }
                    }
                }
                break;

                case 'U':
                    System.out.print("Ingrese el ID del animal a actualizar: ");
                    long idBuscar = sc.nextLong();

                    if (tipoAnimal == 'a') {
                        Mamifero mActual = mamiferoDao.buscarPorId(idBuscar);
                        if (mActual == null) {
                            System.out.println("El registro con ID " + idBuscar + " no existe.");
                        } else {
                            System.out.println("\n--- DATOS ACTUALES ---");
                            mActual.mostrarInfo();
                            sc.nextLine();
                            System.out.print("Ingrese el nuevo nombre: "); mActual.setNombre(sc.nextLine());
                            System.out.print("Ingrese la nueva edad: "); mActual.setEdad(sc.nextInt());
                            System.out.print("Ingrese el nuevo consumo diario: "); mActual.setConsumoDiario(sc.nextDouble());
                            mamiferoDao.actualizar(mActual);
                        }
                    } else if (tipoAnimal == 'b') {
                        Ave aActual = aveDao.buscarPorId(idBuscar);
                        if (aActual == null) {
                            System.out.println("El registro con ID " + idBuscar + " no existe.");
                        } else {
                            System.out.println("\n--- DATOS ACTUALES ---");
                            aActual.mostrarInfo();
                            sc.nextLine();
                            System.out.print("Ingrese el nuevo nombre: "); aActual.setNombre(sc.nextLine());
                            System.out.print("Ingrese la nueva edad: "); aActual.setEdad(sc.nextInt());
                            System.out.print("Ingrese el nuevo consumo diario: "); aActual.setConsumoDiario(sc.nextDouble());
                            aveDao.actualizar(aActual);
                        }
                    } else if (tipoAnimal == 'c') {
                        Reptil rActual = reptilDao.buscarPorId(idBuscar);
                        if (rActual == null) {
                            System.out.println("El registro con ID " + idBuscar + " no existe.");
                        } else {
                            System.out.println("\n--- DATOS ACTUALES ---");
                            rActual.mostrarInfo();
                            sc.nextLine();
                            System.out.print("Ingrese el nuevo nombre: "); rActual.setNombre(sc.nextLine());
                            System.out.print("Ingrese la nueva edad: "); rActual.setEdad(sc.nextInt());
                            System.out.print("Ingrese el nuevo consumo diario: "); rActual.setConsumoDiario(sc.nextDouble());
                            reptilDao.actualizar(rActual);
                        }
                    }
                    break;

                case 'D':
                    System.out.print("Ingrese el ID del animal a eliminar: ");
                    long idEliminar = sc.nextLong();

                    if (tipoAnimal == 'a') {
                        Mamifero mEliminar = mamiferoDao.buscarPorId(idEliminar);
                        if (mEliminar == null) {
                            System.out.println("El registro con ID " + idEliminar + " no existe.");
                        } else {
                            System.out.println("\n--- DATOS DEL REGISTRO A ELIMINAR ---");
                            mEliminar.mostrarInfo();
                            System.out.print("Esta seguro de realizar la accion? (si/no): ");
                            String confirmacion = sc.next().toLowerCase();
                            if (confirmacion.equals("si")) {
                                mamiferoDao.eliminar(idEliminar);
                            } else {
                                System.out.println("Operacion cancelada. No se elimino el registro.");
                            }
                        }
                    } else if (tipoAnimal == 'b') {
                        Ave aEliminar = aveDao.buscarPorId(idEliminar);
                        if (aEliminar == null) {
                            System.out.println("El registro con ID " + idEliminar + " no existe.");
                        } else {
                            System.out.println("\n--- DATOS DEL REGISTRO A ELIMINAR ---");
                            aEliminar.mostrarInfo();
                            System.out.print("Esta seguro de realizar la accion? (si/no): ");
                            String confirmacion = sc.next().toLowerCase();
                            if (confirmacion.equals("si")) {
                                aveDao.eliminar(idEliminar);
                            } else {
                                System.out.println("Operacion cancelada. No se elimino el registro.");
                            }
                        }
                    } else if (tipoAnimal == 'c') {
                        Reptil rEliminar = reptilDao.buscarPorId(idEliminar);
                        if (rEliminar == null) {
                            System.out.println("El registro con ID " + idEliminar + " no existe.");
                        } else {
                            System.out.println("\n--- DATOS DEL REGISTRO A ELIMINAR ---");
                            rEliminar.mostrarInfo();
                            System.out.print("Esta seguro de realizar la accion? (si/no): ");
                            String confirmacion = sc.next().toLowerCase();
                            if (confirmacion.equals("si")) {
                                reptilDao.eliminar(idEliminar);
                            } else {
                                System.out.println("Operacion cancelada. No se elimino el registro.");
                            }
                        }
                    }
                    break;

                case 'S':
                    System.out.println("Regresando...");
                    break;

                default:
                    System.out.println("Accion invalida");
            }
        } while (opcionCRUD != 'S');
    }
    
    //parte 2 del proyecto l11 a 119
    public static boolean idExiste(Animal[] animales, long id) {
        for (int i = 0; i < contador; i++) {
            if (animales[i].idAnimal == id) {
                return true;
            }
        }
        return false;
    }
    
    //parte 2 del proyecto l 136 a 146
    public static void buscarporid(Animal[] animales, long id) {
        for (int i = 0; i < contador; i++) {
            if (animales[i].idAnimal == id) {
                System.out.println("Animal en la posicion: "+ i);
                animales[i].mostrarInfo();
                return;
            }
        }
        System.out.println("Animal no encontrado :(");
    }
    
    public static void buscarpornombre(Animal[] animales, String nombre) {
        boolean encontrado = false;
        
        for (int i = 0; i < contador; i++) {
            if (animales[i].nombre.equalsIgnoreCase(nombre)) {
                animales[i].mostrarInfo();
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron coincidencias");
        }
    }
    
    public static void ordenar(Animal[] animales, boolean ascendente) {
        for (int i = 0; i < contador - 1; i++) {
            for (int j = 0; j < contador - 1 - i; j++) {
            boolean condicion;
            
            if (ascendente) {
                condicion =  animales[j].idAnimal > animales[j + 1].idAnimal;
            }
            
            else {
                condicion = animales[j].idAnimal < animales[j + 1].idAnimal;
            }
            
                if (condicion) {
                    Animal temp = animales[j];
                    animales[j] = animales[j + 1];
                    animales[j + 1] = temp;
                }
            }
        }
    }
    
    public static void estadisticas(Animal[] animales) {
        int mamiferos = 0;
        int aves = 0;
        int reptiles = 0;
        double tEDAD = 0;
        
        double mayorconsumo = Double.MIN_VALUE;
        double menorconsumo = Double.MAX_VALUE;
        
        String animalMayor = "";
        String animalMenor = "";
        
        for (int i = 0; i < contador; i++) {
            tEDAD += animales[i].edad;
            
            if (animales[i] instanceof Mamifero) {
                mamiferos++;
            }
            else if (animales[i] instanceof Ave) {
                aves++;
            }
            else if (animales[i] instanceof Reptil) {
                reptiles++;
            }
            
            if (animales[i].consumoDiario > mayorconsumo) {
                mayorconsumo = animales[i].consumoDiario;
                animalMayor = animales[i].nombre;
            }
            
            if (animales[i].consumoDiario < menorconsumo) {
                menorconsumo = animales[i].consumoDiario;
                animalMenor = animales[i].nombre;
            }
        }
        System.out.println("\n--- Estadisticas ---");
        System.out.println("Total animales: " + contador);
        System.out.println("Mamiferos: " + mamiferos);
        System.out.println("Aves: " + aves);
        System.out.println("Reptiles: " + reptiles);
        System.out.println("Animal con mayor consumo: " + animalMayor);
        System.out.println("Animal con menor consumo: " + animalMenor);
        System.out.println("Promedio de edades: " + (tEDAD / contador));
    }

    public static void mostrarAnimales(Animal[] animales) {
        for (int i = 0; i < contador; i++) {
            System.out.println("\n------------------------");
            System.out.println("ID: " + animales[i].idAnimal);
            System.out.println("Nombre: " + animales[i].nombre);
            System.out.println("Edad: " + animales[i].edad + " anios");
            System.out.println("Consumo diario: " + animales[i].consumoDiario + "lbs");
            
            if (animales[i] instanceof Mamifero) {
                Mamifero m = (Mamifero) animales[i];
            }
            
            else if (animales[i] instanceof Ave) {
                Ave a = (Ave) animales[i];
            }
            
            else if (animales[i] instanceof Reptil) {
                Reptil r = (Reptil) animales[i];
            }
            animales[i].alimentar();
            System.out.println("\n------------------------");
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

        
       for (int i = 0; i < contador; i++) {
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
