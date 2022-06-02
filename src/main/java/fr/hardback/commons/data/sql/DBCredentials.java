package fr.hardback.commons.data.sql;

public class DBCredentials {

    protected final String host, user, password, dbName;
    protected final int port;

    public DBCredentials(String host, String user, String password, String dbName, int port) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.dbName = dbName;
        this.port = port;
    }

    public String toURL(){
        return "jdbc:mysql://" + host + ":" + port + "/" + dbName;
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDbName() {
        return dbName;
    }

    public int getPort() {
        return port;
    }
}
