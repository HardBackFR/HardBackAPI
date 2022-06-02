package fr.hardback.commons;

import fr.hardback.commons.data.AccountProvider;
import fr.hardback.commons.data.redis.RedisAccess;
import fr.hardback.commons.data.redis.RedisData;
import fr.hardback.commons.data.redis.RedisManager;
import fr.hardback.commons.data.sql.DBAccess;
import fr.hardback.commons.data.sql.DBManager;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseManager {

    public static final RedisAccess REDIS_PLAYERS_INFO = RedisManager.PLAYERS_INFO.getRedisAccess();

    public static final RedisData REDIS = RedisManager.PLAYERS_INFO.getRedisData();
    public static final DBAccess DATABASE = DBManager.PLAYERS_INFO.getDBAccess();

    public static final AccountProvider accountProvider = new AccountProvider();

    public static void initAllConnection(){
        DBManager.initAllDBConnection();
        RedisManager.initAllRedisConnection();
    }

    public static void closeAllConnection(){
        DBManager.closeAllDBConnection();
        RedisManager.closeAllRedisConnection();
    }
}
