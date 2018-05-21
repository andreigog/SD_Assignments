import Commands.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientManager implements Runnable {

    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public ClientManager(ObjectOutputStream outStream, ObjectInputStream inStream) {
        super();
        this.inStream=inStream;
        this.outStream=outStream;
    }

    @Override
    public void run() {

        while (true) {
            String request = null;
            try {
                request = (String) inStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Command command = processComand(request);
            if (command != null) {
                try {
                    outStream.writeObject(command.execute());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    outStream.writeObject(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private Command processComand(String request) {
        String[] args = request.split("\n");
        if (args[0].equals("login"))
            return new LoginCommand(args[1], args[2], args[3]);
        if (args[0].equals("create_writer"))
            return new CreateWriterCommand(args[1]);
        if (args[0].equals("get_writers"))
            return new GetWritersCommand();
        if (args[0].equals("create_article"))
            return new WriteArticleCommand(args[1]);
        if (args[0].equals("get_writer_articles"))
            return new GetWriterArticlesCommand(args[1]);
        return null;
    }

    public ObjectInputStream getInStream() {
        return inStream;
    }

    public ObjectOutputStream getOutStream() {
        return outStream;
    }

}
