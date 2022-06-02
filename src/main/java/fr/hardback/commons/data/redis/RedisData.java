package fr.hardback.commons.data.redis;

import com.google.gson.Gson;
import fr.hardback.commons.DatabaseManager;
import fr.hardback.commons.data.AccountData;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class RedisData {

    protected final Gson gson;

    public RedisData() {
        this.gson = new Gson();
    }

    private static final String ACCOUNT_KEY = "accounts:";

    public void setAccountData(UUID uuid, AccountData accountData) {
        Jedis jedis = DatabaseManager.REDIS_PLAYERS_INFO.getJedis();
        jedis.set(ACCOUNT_KEY + uuid.toString(), serialize(accountData));
        jedis.close();
    }

    public AccountData getAccountData(UUID uuid) {
        Jedis jedis = DatabaseManager.REDIS_PLAYERS_INFO.getJedis();
        AccountData deserialize = deserialize(jedis.get(ACCOUNT_KEY + uuid.toString()));
        jedis.close();
        return deserialize;
    }

    private String serialize(AccountData data){
        return this.gson.toJson(data);
    }

    private AccountData deserialize(String data){
        return gson.fromJson(data, AccountData.class);
    }
}
