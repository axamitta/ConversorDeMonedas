package app;

import service.ConsumoAPI;
import com.google.gson.JsonObject;

public class TestApi {
    public static void main(String[] args) {
        ConsumoAPI api = new ConsumoAPI();

        // Intentamos obtener las tasas de cambio desde USD
        JsonObject tasas = api.obtenerTasas("USD");

        if (tasas != null && tasas.has("conversion_rates")) {
            JsonObject conversionRates = tasas.getAsJsonObject("conversion_rates");

            // Mostramos la tasa del dólar frente a otras monedas
            System.out.println("Tasa USD -> ARS: " + conversionRates.get("ARS").getAsDouble());
            System.out.println("Tasa USD -> BRL: " + conversionRates.get("BRL").getAsDouble());
            System.out.println("Tasa USD -> CLP: " + conversionRates.get("CLP").getAsDouble());
        } else {
            System.out.println("Error al obtener las tasas de cambio. Revisa tu API_KEY.");
        }
    }
}

