package service;

import com.google.gson.JsonObject;

import java.util.Map;

public class Conversor {
    private final ConsumoAPI api;

    public Conversor() {
        this.api = new ConsumoAPI();
    }

    /**
     * Convierte una cantidad de una moneda a otra.
     *
     * @param monedaOrigen  Código de la moneda de origen (ej. "USD")
     * @param monedaDestino Código de la moneda destino (ej. "ARS")
     * @param cantidad      Cantidad a convertir
     * @return Monto convertido, o 0 si ocurre algún error
     */
    public double convertir(String monedaOrigen, String monedaDestino, double cantidad) {
        JsonObject tasasOrigen = api.obtenerTasas(monedaOrigen);

        if (tasasOrigen == null || !tasasOrigen.has("conversion_rates")) {
            System.out.println("Error al obtener las tasas de cambio.");
            return 0;
        }

        JsonObject conversionRates = tasasOrigen.getAsJsonObject("conversion_rates");

        // Verifica que existan las monedas destino y origen
        if (!conversionRates.has(monedaDestino)) {
            System.out.println("Moneda destino no disponible: " + monedaDestino);
            return 0;
        }

        // Calcula el resultado
        double tasaDestino = conversionRates.get(monedaDestino).getAsDouble();
        return cantidad * tasaDestino;
    }

    /**
     * Método opcional para filtrar las monedas que nos interesan
     */
    public Map<String, Double> filtrarMonedas(JsonObject conversionRates) {
        return Map.of(
                "ARS", conversionRates.get("ARS").getAsDouble(),
                "BOB", conversionRates.get("BOB").getAsDouble(),
                "BRL", conversionRates.get("BRL").getAsDouble(),
                "CLP", conversionRates.get("CLP").getAsDouble(),
                "COP", conversionRates.get("COP").getAsDouble(),
                "USD", conversionRates.get("USD").getAsDouble()
        );
    }
}

