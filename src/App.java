import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import configuration.KeyConfiguration;
import generator.StickerGenerator;

public class App extends KeyConfiguration {

    public static final String RESET = "\033[0m";
    public static final String WHITE_BOLD = "\033[1;37m";
    public static final String RED_BOLD = "\033[1;31m";

    public static void main(String[] args) throws Exception {

        //String url = "https://imdb-api.com/en/API/Top250Movies/" + getApiKey();
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        var gerador = new StickerGenerator();

        for (Map<String, String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            String titulo = filme.get("title");
            float rating = Float.parseFloat(filme.get("imDbRating"));

            if(urlImage.contains("@")) {
                String subUrlImg = urlImage.substring(0, urlImage.lastIndexOf("@") + 1);
                System.out.println(subUrlImg + ".jpg");

                InputStream inputStream = new URL(urlImage).openStream();
                String nomeArquivo = titulo + ".png";

                new StickerGenerator();
                gerador.cria(inputStream, nomeArquivo, rating);

            }
            else{
                InputStream inputStream = new URL(urlImage).openStream();
                System.out.println(urlImage);
                String nomeArquivo = titulo + ".png";

                new StickerGenerator();
                gerador.cria(inputStream, nomeArquivo, rating);

            }
            
            System.out.println(RED_BOLD + "Titulo: " + RESET + titulo);
            System.out.println();
        }
    }
}
