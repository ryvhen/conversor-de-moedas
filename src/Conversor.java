import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {

    public String pegarSigla(int opcao){
      return switch (opcao){
          case 1: yield "ARS";
          case 2: yield "BRL";
          case 3: yield "CAD";
          case 4: yield "CNY";
          case 5: yield "EUR";
          case 6: yield "JPY";
          case 7: yield "RUB";
          default: yield null;
      };
    }

    public double converterMoeda(String moedaOrigem, String moedaDestino, double valorMoedaOrigem){
        //URL da API com a moeda de destino
        String apiKey = System.getenv("EXCHANGE_API_KEY");
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + moedaOrigem;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;

        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

            JsonObject taxas = jsonObject.getAsJsonObject("conversion_rates");

            double taxaDestino = taxas.get(moedaDestino).getAsDouble();

            return valorMoedaOrigem * taxaDestino;
        }
        catch (IOException | InterruptedException e){
            throw new RuntimeException("Nao foi possivel obter a conversao selecionada.");
        }
    }
}
