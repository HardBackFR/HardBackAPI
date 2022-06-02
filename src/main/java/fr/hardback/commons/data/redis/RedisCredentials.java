package fr.hardback.commons.data.redis;

public class RedisCredentials {

    protected final String ip, password, clientName;
    protected final int port, database;

    public RedisCredentials(String ip, String password, String clientName, int port, int database) {
        this.ip = ip;
        this.password = password;
        this.clientName = clientName;
        this.port = port;
        this.database = database;
    }

    public RedisCredentials(String ip, String password, int port, int database) {
        this(ip, password, "HardBack_bungee_access", port, database);
    }

    public String toRedisURL(){
        return "redis://" + ip + ":" + port;
    }

    public String getIp() {
        return ip;
    }

    public String getPassword() {
        return password;
    }

    public String getClientName() {
        return clientName;
    }

    public int getPort() {
        return port;
    }

    public int getDatabase() {
        return database;
    }
}
