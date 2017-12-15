package com.yxd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;  
 
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {
	  
    @Bean(name= "jedis.pool")  
    @Autowired  
    public JedisPool jedisPool(@Qualifier("jedis.pool.config") JedisPoolConfig config,   
                @Value("${jedis.pool.host}")String host,   
                @Value("${jedis.pool.port}")int port) {  
        return new JedisPool(config, host, port);  
    }  
      
    @Bean(name= "jedis.pool.config")  
    public JedisPoolConfig jedisPoolConfig (@Value("${jedis.pool.config.maxTotal}")int maxTotal,  
                                @Value("${jedis.pool.config.maxIdle}")int maxIdle,  
                                @Value("${jedis.pool.config.maxWaitMillis}")int maxWaitMillis) {  
        JedisPoolConfig config = new JedisPoolConfig();  
        config.setMaxTotal(maxTotal);  
        config.setMaxIdle(maxIdle);  
        config.setMaxWaitMillis(maxWaitMillis);  
        return config;  
    }  
    /*@SuppressWarnings("rawtypes")
	@Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        Map<String, Long> expires = new HashMap<>();
        expires.put("12h",3600 * 12L);
        expires.put("1h",3600 * 1L);
        expires.put("10m",60 * 5L);
        rcm.setExpires(expires);
//        rcm.setDefaultExpiration(60 * 60 * 12);//秒
        return rcm;
    }*/
      
}
