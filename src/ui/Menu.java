package ui;

import service.Conversor;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final Conversor conversor;

    // Constructor
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.conversor = new Conversor();
    }

    // Método principal que inicia la interacción con el usuario
    public void iniciar() {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.print("Ingrese su opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> convertirMoneda("USD", "ARS");
                case 2 -> convertirMoneda("ARS", "USD");
                case 3 -> convertirMoneda("USD", "BRL");
                case 4 -> convertirMoneda("BRL", "USD");
                case 5 -> convertirMoneda("CLP", "USD");
                case 6 -> convertirMoneda("USD", "CLP");
                case 7 -> {
                    salir = true;
                    System.out.println("¡Gracias por usar el Conversor de Monedas!");
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println();
        }
    }

    // Muestra el menú en pantalla
    private void mostrarMenu() {
        System.out.println("""
        *********************************************************
        Bienvenido/a al Conversor de Monedas =]
        Elija una opción válida:
        
        1) Dólar =>> Peso argentino
        2) Peso argentino =>> Dólar
        3) Dólar =>> Real brasileño
        4) Real brasileño =>> Dólar
        5) Peso chileno =>> Dólar
        6) Dólar =>> Peso chileno
        7) Salir
        *********************************************************
        """);
    }

    // Lee un entero del usuario y controla errores de entrada
    private int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Ingrese un número: ");
            scanner.nextLine(); // limpiar buffer
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return valor;
    }

    // Método que solicita la cantidad y realiza la conversión
    private void convertirMoneda(String monedaOrigen, String monedaDestino) {
        System.out.print("Ingrese la cantidad a convertir: ");
        double cantidad = leerDouble();

        double resultado = conversor.convertir(monedaOrigen, monedaDestino, cantidad);
        System.out.printf("%.2f %s equivalen a %.2f %s%n",
                cantidad, monedaOrigen, resultado, monedaDestino);
    }

    // Lee un número decimal del usuario y controla errores de entrada
    private double leerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Entrada inválida. Ingrese un número válido: ");
            scanner.nextLine(); // limpiar buffer
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); // limpiar buffer
        return valor;
    }
}
