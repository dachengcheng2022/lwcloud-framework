package com.bt.component.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

@Component
public class RedisComponent {

    private static Logger logger = LoggerFactory.getLogger(RedisComponent.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void delKey(String key) {
        try {
            this.redisTemplate.delete(key);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
    }

    public Long addForSet(String key, Object... values) {
        try {
            return this.redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return null;
    }

    public Boolean isMemberForSet(String key, Object value) {
        try {
            return this.redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return null;
    }

    public Long sizeForSet(String key) {
        try {
            return this.redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return null;
    }

    public void setForValue(String key, Object value) {
        try {
            this.redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
    }

    public Object opsForValue(String key) {
        return opsForValue(key, null);
    }

    public String opsForByteValue(String key) {
        return redisTemplate.execute(new RedisCallback<String>() {

            @Override
            public String doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] content = connection.get(key.getBytes());
                if (content == null) {
                    return null;
                }
                return new String(content);
            }

        });
    }

    @SuppressWarnings("unchecked")
    public <T> T opsForValue(String key, T defalutValue) {
        Object value = null;
        try {
            value = this.redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return value == null ? defalutValue : (T) value;
    }

    public Object opsForHash(String key) {
        return opsForHash(key, null);
    }

    @SuppressWarnings("unchecked")
    public <T> T opsForHash(String key, T defalutValue) {
        Object value = null;
        try {
            value = this.redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return value == null ? defalutValue : (T) value;
    }

    public void opsForHashPutAll(String key, Map<String, ?> putMap) {
        try {
            this.redisTemplate.opsForHash().putAll(key, putMap);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
    }

    public void opsForHashPut(String key, String hashKey, Object value) {
        try {
            this.redisTemplate.opsForHash().put(key, hashKey, value);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
    }

    public Long opsForHashDelete(String key, Object... hashKeys) {
        Long deletecount = null;
        try {
            HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
            deletecount = hashOperations.delete(key, hashKeys);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return deletecount;
    }

    public Long opsForHashDelete(String key, String... hashKeys) {
        return opsForHashDelete(key, (Object[]) hashKeys);
    }

    @SuppressWarnings("unchecked")
    public <T> T opsForHashGet(String key, String hashKey, T defalutValue) {

        Object value = null;
        try {
            HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
            value = hashOperations.get(key, hashKey);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return value == null ? defalutValue : (T) value;
    }

    public Long opsForHashIncr(String key, String hashKey, Long increment) {
        try {
            HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
            return hashOperations.increment(key, hashKey, 1);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return null;
    }

    public List<Object> opsForHashMultiGet(String key, Collection<String> hashKeys) {
        List<Object> value = null;
        try {
            HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
            value = hashOperations.multiGet(key, hashKeys);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return value;
    }

    public Map<String, Object> opsForHashGetAll(String key) {
        Map<String, Object> value = null;
        try {
            HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
            value = hashOperations.entries(key);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    public <T> Map<String, T> opsForHashGetAll(String key, T defaultValue) {
        Map<String, T> result = null;
        try {
            HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
            Map<String, Object> hashVals = hashOperations.entries(key);
            if (!CollectionUtils.isEmpty(hashVals)) {
                result = new HashMap<String, T>();
                for (Entry<String, Object> entry : hashVals.entrySet()) {
                    result.put(entry.getKey(), (T) entry.getValue());
                }
            }
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return result;
    }

    public <T> Map<String, T> opsForHashGetAllAndFormatJson(String key, Class<T> clazz) {
        Map<String, T> result = null;
        try {
            HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
            Map<String, Object> hashVals = hashOperations.entries(key);
            if (!CollectionUtils.isEmpty(hashVals)) {
                result = new HashMap<String, T>();
                for (Entry<String, Object> entry : hashVals.entrySet()) {
                    result.put(entry.getKey(), JSONObject.parseObject(entry.getValue().toString(), clazz));
                }
            }
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return result;
    }

    public Boolean opsForHashHasKey(String key, String hashKey) {
        Boolean value = null;
        try {
            value = this.redisTemplate.opsForHash().hasKey(key, hashKey);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return value;
    }

    public Boolean opsForExpire(String key, final long timeout, final TimeUnit unit) {
        Boolean value = null;
        try {
            value = this.redisTemplate.expire(key, timeout, unit);
        } catch (Exception e) {
            logger.error("error type:{},error:{}", "redis", e);
        }
        return value;
    }


    public Long incre(String key) {
        BoundValueOperations<String, Object> operations = redisTemplate.boundValueOps(key);
        Long increment = operations.increment(1);
        return increment;
    }

    public Boolean addZset(String key, Object value, double secore) {
        Boolean result = redisTemplate.opsForZSet().add(key, value, secore);
        return result;
    }

    public Set rangeZset(String key, Long start, Long end) {
        Set<Object> data = redisTemplate.opsForZSet().reverseRange(key, start, end);
        return data;
    }
}
