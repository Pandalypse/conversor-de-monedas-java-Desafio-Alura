package com.alura.screenmatch.conversor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class ConversorMonedas {

    // 👉 Coloca tu clave personal aquí
    private static final String API_KEY = "93ffbd0c892fbd341548e28d";

    // URL base de la API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static double convertirMoneda(String desde, String hacia, double cantidad) {
        // Validaciones básicas
        if (desde == null || hacia == null || cantidad <= 0) {
            System.out.println("⚠️ Parámetros inválidos: verifica las monedas y la cantidad.");
            return -1;
        }

        // Si interviene la moneda "BERRY" (ficticia)
        if (desde.equalsIgnoreCase("BERRY") || hacia.equalsIgnoreCase("BERRY")) {
            return convertirBerry(desde, hacia, cantidad);
        }

        try {
            // Construir la URL completa con la clave
            String url = BASE_URL + API_KEY + "/pair/" +
                    desde.toUpperCase() + "/" + hacia.toUpperCase() + "/" + cantidad;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("❌ Error en la solicitud HTTP: código " + response.statusCode());
                return -1;
            }

            JSONObject json = new JSONObject(response.body());

            if (!json.has("result") || !json.getString("result").equalsIgnoreCase("success")) {
                System.out.println("⚠️ Error en la respuesta: " +
                        (json.has("error-type") ? json.getString("error-type") : "desconocido"));
                return -1;
            }

            return json.getDouble("conversion_result");

        } catch (Exception e) {
            System.out.println("❌ Error al conectar con la API: " + e.getMessage());
            return -1;
        }
    }

    // Conversión ficticia para la moneda "BERRY" (1 Berry = 0.01 USD)
    private static double convertirBerry(String desde, String hacia, double cantidad) {
        double valorUSD = 0.01; // 1 Berry = 0.01 USD

        // Evita recursión infinita si ambas son BERRY
        if (desde.equalsIgnoreCase("BERRY") && hacia.equalsIgnoreCase("BERRY")) {
            return cantidad;
        }

        if (desde.equalsIgnoreCase("BERRY")) {
            double usd = cantidad * valorUSD;
            return convertirMoneda("USD", hacia, usd);
        } else {
            double usd = convertirMoneda(desde, "USD", cantidad);
            return usd / valorUSD;
        }
    }
}
