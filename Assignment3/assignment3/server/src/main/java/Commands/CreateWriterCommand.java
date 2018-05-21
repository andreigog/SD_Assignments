package Commands;

import Mappers.Deserializer;
import Mappers.Serializer;
import model.Article;
import model.Writer;

public class CreateWriterCommand implements Command {

    private Writer writer;


    public CreateWriterCommand(String jsonStrig) {
        writer = Deserializer.getWriterFromJSON(jsonStrig);
    }

    @Override
    public Object execute() {
        System.out.println("Client tried to create new Writer.");
        if (writer!=null){
            Serializer.serializeWriter(writer);
            return true;
        }
        return false;
    }

}
