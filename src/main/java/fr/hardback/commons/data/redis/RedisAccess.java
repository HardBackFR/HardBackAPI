package fr.hardback.commons.data.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisAccess {

    public static RedisAccess instance;

    protected final RedisCredentials credentials;
    protected JedisPool jedisPool;

    public RedisAccess(RedisCredentials credentials) {
        instance = this;

        this.credentials = credentials;
        this.jedisPool = initJedis(this.credentials);
    }

    public void init(){
        new RedisAccess(new RedisCredentials(credentials.getIp(), credentials.getPassword(), credentials.getPort(), credentials.getDatabase()));
    }

    public void close(){
        jedisPool.close();
        jedisPool.destroy();
    }

    public JedisPool initJedis(RedisCredentials credentials) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(-1);
        config.setJmxEnabled(false);

        try {
            this.jedisPool = new JedisPool(config, credentials.getIp(), credentials.getPort(), 0, credentials.getPassword(), credentials.getDatabase());
            this.jedisPool.getResource().close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return jedisPool;
    }

    public Jedis getJedis(){
        return jedisPool.getResource();
    }
}
