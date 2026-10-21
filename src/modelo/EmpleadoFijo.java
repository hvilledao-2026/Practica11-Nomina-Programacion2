package modelo;

/**
 * Representa a un empleado de tipo Fijo.
 * Hereda de Empleado.
 */
public class EmpleadoFijo extends Empleado {
    private double sueldoMensual;
    private double bonificacion;

    // Constructor que utiliza super() para inicializar atributos heredados
    public EmpleadoFijo(String dpi, String nombre, double sueldoMensual, double bonificacion) {
        super(dpi, nombre);
        this.sueldoMensual = sueldoMensual;
        this.bonificacion = bonificacion;
    }

    public double getSueldoMensual() {
        return sueldoMensual;
    }

    public double getBonificacion() {
        return bonificacion;
    }

    @Override
    public double calcularPago() {
        return sueldoMensual + bonificacion;
    }

    @Override
    public String obtenerTipo() {
        return "Fijo";
    }
}