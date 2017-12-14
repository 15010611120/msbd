package com.yxd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Component
public class RedisUtils {
	@Autowired  
    private JedisPool jedisPool;  
      
	/**
	 * 写入缓存
	 * @param key
	 * @param value
	 * @throws Exception
	 */
    public void set(String key, String value) throws Exception {  
        Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            jedis.set(key, value);
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }  
    
    /**
     * 存入key的失效时间
     * @param key
     * @param value
     * @param expireTime
     * @throws Exception
     */
    public void set(String key, int expireTime) throws Exception {  
        Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            jedis.expire(key, expireTime);
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }  
    
    /**
     * 存入key的失效时间
     * @param key
     * @param value
     * @param expireTime
     * @throws Exception
     */
    public void set(String key, String value, int expireTime) throws Exception {  
        Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            jedis.set(key, value);
            jedis.expire(key, expireTime);
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }  
      
      
    /**
     * 读取缓存
     * @param key
     * @return
     * @throws Exception
     */
    public String get(String key) throws Exception  {  
  
        Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            return jedis.get(key);  
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }  
    
    /**
     * 删除对应的Value
     * @param key
     */
    @SuppressWarnings("null")
	public void remove(String key) throws Exception{
    	 Jedis jedis = null; 
    	 try {
			jedis.del(key);
		} finally {
    	 //返还到连接池  
         jedis.close();  
		}
    }

}
