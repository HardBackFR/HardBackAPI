package fr.hardback.commons.data.redis;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Objects;

public enum RedisManager {

    PLAYERS_INFO(new RedisCredentials(Dotenv.configure().load().get("REDIS-IP"), Dotenv.configure().load().get("REDIS-PASSWORD"), Integer.parseInt(Objects.requireNonNull(Dotenv.configure().load().get("REDIS-PORT"))), Integer.parseInt(Objects.requireNonNull(Dotenv.configure().load().get("REDIS-DATABASE")))));

    private final RedisAccess redisAccess;
    private final RedisData redisData;

    RedisManager(RedisCredentials redisCredentials){
        this.redisAccess = new RedisAccess(redisCredentials);
        this.redisData = new RedisData();
    }

    public RedisAccess getRedisAccess() {
        return redisAccess;
    }

    public RedisData getRedisData() {
        return redisData;
    }

    public static void initAllRedisConnection(){
        System.out.println("-----> Redis <-----");
        for(RedisManager redisManager : values()){
            redisManager.redisAccess.init();
            System.out.println("Connected to " + redisManager.getRedisAccess().credentials.getDatabase());
        }
        System.out.println("---------------");
    }

    public static void closeAllRedisConnection(){
        System.out.println("-----> Redis <-----");
        for(RedisManager redisManager : values()){
            redisManager.redisAccess.close();
            System.out.println("Disconnected from " + redisManager.getRedisAccess().credentials.getDatabase());
        }
        System.out.println("---------------");
    }
}
