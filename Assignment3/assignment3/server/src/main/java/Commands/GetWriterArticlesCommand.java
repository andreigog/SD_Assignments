package Commands;

import Mappers.Deserializer;
import model.Article;
import model.Writer;

public class GetWriterArticlesCommand implements Command {

    private Writer writer;

    public GetWriterArticlesCommand(String jsonString){
        this.writer = Deserializer.getWriterFromJSON(jsonString);
    }

    @Override
    public Object execute() {
        System.out.println("Client tried to get the articles of a specific writer.");
        return Deserializer.getArticlesFromFile(writer);
    }

}
