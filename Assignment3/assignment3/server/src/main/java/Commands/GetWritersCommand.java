package Commands;

import Mappers.Deserializer;
import model.Article;

public class GetWritersCommand implements Command {
    @Override
    public Object execute() {
        System.out.println("Client tried to get all the Writers.");
        return Deserializer.getWritersFromFile();
    }

}
