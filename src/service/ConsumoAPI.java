package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConsumoAPI {
    private static final String API_KEY = System.getenv("API_KEY_EXCHANGE");
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    private final HttpClient client;
    private final Gson gson;

    public ConsumoAPI() {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    /**
     * Obtiene las tasas de cambio para la moneda base indicada.
     * @param monedaBase Código de la moneda base (ej. "USD", "EUR")
     * @return JsonObject con todas las tasas de cambio, o null si hay error
     */
    public JsonObject obtenerTasas(String monedaBase) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + monedaBase))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Convertir el JSON a JsonObject usando Gson
            return gson.fromJson(response.body(), JsonObject.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
