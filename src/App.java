import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static final String RESET = "\033[0m";
    public static final String WHITE_BOLD = "\033[1;37m";
    public static final String RED_BOLD = "\033[1;31m";

    public static void main(String[] args) throws Exception {

        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(RED_BOLD + "Titulo: " + RESET + filme.get("title"));
            System.out.println(RED_BOLD + "Capa: " + RESET + filme.get("image"));
            System.out.println(RED_BOLD + "Classificação: " + RESET + filme.get("imDbRating"));
            System.out.println();
        }
    }
}
