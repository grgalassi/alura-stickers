package extractor;

import java.util.List;

import contents.Conteudo;

public interface ExtratorConteudo {

    public List<Conteudo> extract(String json);

}
