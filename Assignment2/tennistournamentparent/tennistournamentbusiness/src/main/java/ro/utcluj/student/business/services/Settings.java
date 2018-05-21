package ro.utcluj.student.business.services;

public class Settings {


    private  CONNECTION_TYPE connectionType;

    public Settings(CONNECTION_TYPE connection_type){
        this.connectionType=connection_type;
    }

    public CONNECTION_TYPE getConnectionType() {
        return connectionType;
    }
}
