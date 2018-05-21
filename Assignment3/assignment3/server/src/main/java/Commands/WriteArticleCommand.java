package Commands;

import Mappers.Deserializer;
import Mappers.Serializer;
import model.Article;

public class WriteArticleCommand implements Command {

    private Article article;

    public WriteArticleCommand(String jsonString){
        this.article = Deserializer.getArticleFromJSON(jsonString);
    }

    @Override
    public Object execute() {
        System.out.println("Client tried to write new Article.");
        if (article!=null){
            Serializer.serializeArticle(article);
            return true;
        }
        return false;
    }
}
