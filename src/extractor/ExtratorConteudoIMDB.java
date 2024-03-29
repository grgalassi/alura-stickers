package extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import contents.Conteudo;

public class ExtratorConteudoIMDB implements ExtratorConteudo {

    public List<Conteudo> extract(String json) {

        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        for (Map<String, String> atributos : listaDeAtributos) {

            String titulo = atributos.get("title");

            String urlImage = atributos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");

            var conteudo = new Conteudo(titulo, urlImage);

            conteudos.add(conteudo);
        }

        return conteudos;

    }

}
