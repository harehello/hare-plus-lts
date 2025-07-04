package org.hare.framework.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author wang cheng
 */
@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void set(String key, T value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, java.util.concurrent.TimeUnit.SECONDS);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void delete(String... keys) {
        redisTemplate.delete(Arrays.asList(keys));
    }

    public void deletePattern(String pattern) {
        redisTemplate.delete(redisTemplate.keys(pattern));
    }

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }


    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void expire(String key, long timeout, java.util.concurrent.TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

}
