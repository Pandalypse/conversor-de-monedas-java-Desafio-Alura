package com.alura.screenmatch.principal;

import com.alura.screenmatch.conversor.ConversorMonedas;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== 🎬 Bienvenido a ScreenMatch ===");
            System.out.println("1️⃣  Ver películas y series");
            System.out.println("2️⃣  Buscar títulos");
            System.out.println("3️⃣  Listar títulos");
            System.out.println("4️⃣  💱 Conversor de Monedas");
            System.out.println("0️⃣  Salir");
            System.out.print("Selecciona una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 4 -> ejecutarConversor(teclado);
                case 0 -> System.out.println("👋 Saliendo del programa...");
                default -> System.out.println("⚠️ Opción no disponible todavía.");
            }
        } while (opcion != 0);
    }

    private static void ejecutarConversor(Scanner teclado) {
        System.out.println("=== 💱 Conversor de Monedas ===");
        System.out.print("Moneda origen (USD, EUR, COP, GBP, BERRY): ");
        String desde = teclado.nextLine().toUpperCase();

        System.out.print("Moneda destino (USD, EUR, COP, GBP, BERRY): ");
        String hacia = teclado.nextLine().toUpperCase();

        System.out.print("Cantidad: ");
        double cantidad = teclado.nextDouble();

        double resultado = ConversorMonedas.convertirMoneda(desde, hacia, cantidad);

        if (resultado >= 0) {
            System.out.printf("💰 %.2f %s equivalen a %.2f %s%n", cantidad, desde, resultado, hacia);
        } else {
            System.out.println("❌ No se pudo realizar la conversión.");
        }
    }
}
