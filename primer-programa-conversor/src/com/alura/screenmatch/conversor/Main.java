package com.alura.screenmatch.conversor;

public class Main {
    public static void main(String[] args) {
        double resultado = ConversorMonedas.convertirMoneda("USD", "COP", 10);
        System.out.println("💰 Resultado: 10 USD = " + resultado + " COP");
    }
}
