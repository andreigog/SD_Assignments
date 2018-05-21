package Commands;

import Mappers.Deserializer;
import Mappers.Serializer;
import model.AccessRight;
import model.Admin;
import model.Article;
import model.Writer;

public class LoginCommand implements Command {

    private String userName;
    private String password;
    private AccessRight accessRight;


    public LoginCommand(String userName, String password, String accessRight){
        this.userName=userName;
        this.password=password;
        this.accessRight = AccessRight.valueOf(accessRight);
    }

    @Override
    public Object execute() {
        System.out.println("Client tried to login with \n User: "+this.userName+"\n Password:" + this.password);
        if (accessRight.ordinal()==AccessRight.ADMIN.ordinal()){
            Admin admin = Deserializer.getAdminFromFile();
            if( admin.getPassword().equals(password) && admin.getEmail().equals(userName))
                return "admin";
        }
        if (accessRight.ordinal()==AccessRight.WRITER.ordinal()){
            Writer writer = Deserializer.getWriterFromFile(userName);
            if( writer.getPassword().equals(password)) {
                return "writer\n" + Serializer.writerToJson(writer);
            }
        }
        return "failed";
    }
}
