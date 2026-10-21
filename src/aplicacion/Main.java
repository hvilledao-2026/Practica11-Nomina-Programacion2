package aplicacion;

import modelo.Empleado;
import modelo.EmpleadoFijo;
import modelo.EmpleadoPorHora;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Arreglo simple estático para los meses (Punto 2)
    private static final String[] MESES = {
        "Enero", "Febrero", "Marzo", "Abril",
        "Mayo", "Junio", "Julio", "Agosto",
        "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    // Colección ArrayList de tipo genérico Empleado (Punto 9)
    private static ArrayList<Empleado> nomina = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int numeroMes;

    public static void main(String[] args) {
        seleccionarMes();
        mostrarMenuPrincipal();
    }

    // Método para simular la limpieza de pantalla (Punto 7)
    private static void limpiarPantalla() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    // Punto 2: Selección del mes con validación
    private static void seleccionarMes() {
        limpiarPantalla();
        System.out.println("CÁLCULO DE NÓMINA");
        while (true) {
            System.out.print("Ingrese el mes a trabajar (1-12): ");
            String entrada = scanner.nextLine().trim();
            try {
                int mes = Integer.parseInt(entrada);
                if (mes >= 1 && mes <= 12) {
                    numeroMes = mes;
                    break;
                } else {
                    System.out.println("Error: El número de mes debe estar entre 1 y 12.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }
    }

    // Punto 3: Menú principal
    private static void mostrarMenuPrincipal() {
        int opcion = 0;
        do {
            limpiarPantalla();
            System.out.println("NÓMINA DEL MES: " + MESES[numeroMes - 1].toUpperCase());
            System.out.println("1 Ingreso de nómina");
            System.out.println("2 Impresión de nómina");
            System.out.println("3 Salir");
            System.out.print("Seleccione una opción: ");

            String entrada = scanner.nextLine().trim();
            try {
                opcion = Integer.parseInt(entrada);
                switch (opcion) {
                    case 1:
                        menuIngresoNomina();
                        break;
                    case 2:
                        imprimirNomina();
                        break;
                    case 3:
                        limpiarPantalla();
                        System.out.println("¡Gracias por utilizar el sistema de nómina! Hasta luego.");
                        break;
                    default:
                        System.out.println("Opción inválida. Presione Enter para continuar...");
                        scanner.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido. Presione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcion != 3);
    }

    // Punto 4: Submenú de Ingreso
    private static void menuIngresoNomina() {
        while (true) {
            limpiarPantalla();
            System.out.println("NÓMINA DEL MES: " + MESES[numeroMes - 1].toUpperCase());
            System.out.println("INGRESO DE EMPLEADO A NÓMINA");
            System.out.println("Trabajadores actuales = " + nomina.size());
            System.out.println("1 Empleado fijo");
            System.out.println("2 Empleado por hora");
            System.out.print("Seleccione el tipo de empleado: ");

            String entrada = scanner.nextLine().trim();
            if (entrada.equals("1")) {
                registrarEmpleadoFijo();
                if (!deseaContinuarIngresando()) break;
            } else if (entrada.equals("2")) {
                registrarEmpleadoPorHora();
                if (!deseaContinuarIngresando()) break;
            } else {
                System.out.println("Opción inválida. Presione Enter para reintentar...");
                scanner.nextLine();
            }
        }
    }

    // Registro de Empleado Fijo
    private static void registrarEmpleadoFijo() {
        limpiarPantalla();
        System.out.println("NÓMINA DEL MES: " + MESES[numeroMes - 1].toUpperCase());
        System.out.println("INGRESO DE EMPLEADO FIJO");

        String dpi = capturarDpi();
        String nombre = capturarNombre();
        double sueldo = capturarDoublePositivo("Sueldo mensual (Q): ");
        double bonificacion = capturarDoublePositivo("Bonificación de este mes (Q): ");

        EmpleadoFijo ef = new EmpleadoFijo(dpi, nombre, sueldo, bonificacion);
        nomina.add(ef);

        System.out.println("\nPago del mes = sueldo mensual + bonificación");
        System.out.printf("Pago de %s = Q %.2f\n", ef.getNombre(), ef.calcularPago());
        System.out.println("\nEmpleado agregado correctamente.");
    }

    // Registro de Empleado Por Hora
    private static void registrarEmpleadoPorHora() {
        limpiarPantalla();
        System.out.println("NÓMINA DEL MES: " + MESES[numeroMes - 1].toUpperCase());
        System.out.println("INGRESO DE EMPLEADO POR HORA");

        String dpi = capturarDpi();
        String nombre = capturarNombre();
        double pagoHora = capturarDoublePositivo("Pago por hora (Q): ");
        double horas = capturarDoublePositivo("Horas trabajadas este mes: ");

        EmpleadoPorHora eh = new EmpleadoPorHora(dpi, nombre, pagoHora, horas);
        nomina.add(eh);

        System.out.println("\nPago del mes = pago por hora x horas trabajadas");
        System.out.printf("Pago de %s = Q %.2f\n", eh.getNombre(), eh.calcularPago());
        System.out.println("\nEmpleado agregado correctamente.");
    }

    // Punto 5: Preguntar si desea continuar
    private static boolean deseaContinuarIngresando() {
        System.out.println("Trabajadores actuales = " + nomina.size());
        while (true) {
            System.out.println("\n¿Desea ingresar otro empleado?");
            System.out.println("1 Sí, ingresar otro empleado");
            System.out.println("2 Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            String op = scanner.nextLine().trim();
            if (op.equals("1")) {
                return true;
            } else if (op.equals("2")) {
                return false;
            } else {
                System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    // Punto 6: Impresión de nómina polimórfica
    private static void imprimirNomina() {
        limpiarPantalla();
        System.out.println("NÓMINA DEL MES: " + MESES[numeroMes - 1].toUpperCase());

        if (nomina.isEmpty()) {
            System.out.println("\nNo hay empleados registrados en la nómina de este mes.");
            System.out.println("\nTotal de trabajadores: 0");
            System.out.println("Total de nómina: Q 0.00");
        } else {
            System.out.println("----------------------------------------------------------------------");
            System.out.printf("%-15s %-25s %-15s %-12s\n", "DPI", "Nombre", "Tipo", "Pago (Q)");
            System.out.println("----------------------------------------------------------------------");

            double totalNomina = 0;
            // Polimorfismo: recorremos la lista invocando métodos de la clase base
            for (Empleado emp : nomina) {
                double pago = emp.calcularPago();
                totalNomina += pago;
                System.out.printf("%-15s %-25s %-15s %12.2f\n",
                        emp.getDpi(),
                        emp.getNombre(),
                        emp.obtenerTipo(),
                        pago);
            }
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Total de trabajadores: " + nomina.size());
            System.out.printf("Total de nómina: Q %.2f\n", totalNomina);
        }

        System.out.println("\nPresione Enter para volver al menú principal...");
        scanner.nextLine();
    }

    // --- Métodos de Validación (Punto 10) ---

    private static String capturarDpi() {
        while (true) {
            System.out.print("DPI: ");
            String dpi = scanner.nextLine().trim();
            if (dpi.matches("\\d{13}")) {
                return dpi;
            }
            System.out.println("Error: El DPI debe contener exactamente 13 dígitos numéricos.");
        }
    }

    private static String capturarNombre() {
        while (true) {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();
            if (!nombre.isEmpty()) {
                return nombre;
            }
            System.out.println("Error: El nombre no puede estar vacío.");
        }
    }

    private static double capturarDoublePositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                double valor = Double.parseDouble(entrada);
                if (valor >= 0 && Double.isFinite(valor)) {
                    return valor;
                }
                System.out.println("Error: El valor debe ser un número mayor o igual a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico válido.");
            }
        }
    }
}