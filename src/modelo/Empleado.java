package modelo;

/**
 * Clase abstracta que representa a un empleado general.
 * Demuestra abstracción y encapsulamiento.
 */
public abstract class Empleado {
    // Atributos privados (Encapsulamiento)
    private String dpi;
    private String nombre;

    // Constructor con parámetros
    public Empleado(String dpi, String nombre) {
        this.dpi = dpi;
        this.nombre = nombre;
    }

    // Métodos de acceso (Getters)
    public String getDpi() {
        return dpi;
    }

    public String getNombre() {
        return nombre;
    }

    // Método abstracto que debe ser implementado por las subclases (Abstracción)
    public abstract double calcularPago();

    // Método abstracto para obtener el tipo de empleado (Polimorfismo)
    public abstract String obtenerTipo();
}