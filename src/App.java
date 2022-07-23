import java.io.InputStream;
import java.net.URL;
import java.util.List;

import configuration.KeyConfiguration;
import connection.ClienteHttp;
import contents.Conteudo;
import extractor.ExtratorConteudoAPI;
import generator.StickerGenerator;

public class App extends KeyConfiguration {

    public static final String RESET = "\033[0m";
    public static final String RED_BOLD = "\033[1;31m";

    public static void main(String[] args) throws Exception {

        // String url = "https://imdb-api.com/en/API/Top250Movies/" + getApiKey();
        // String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        // var extrator = new ExtratorConteudoIMDB();
        // String url = "https://api.nasa.gov/planetary/apod?api_key=" + getApiKey2();
        // String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        // var extrator = new ExtratorConteudoNasa();
        String url = "http://localhost:8080/linguagens";
        var extrator = new ExtratorConteudoAPI();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extract(json);

        var gerador = new StickerGenerator();

        for (int i = 0; i < 6; i++) {

            var conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            new StickerGenerator();
            gerador.cria(inputStream, nomeArquivo);

            System.out.println(RED_BOLD + "Titulo: " + RESET + conteudo.getTitulo());
            System.out.println();
        }
    }
}
