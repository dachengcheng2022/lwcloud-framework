package com.autumn;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisDistributeLock {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 尝试全局锁定一个key一段时间,如果该key已经存在则返回false,否则将key设置到redis字段中
     * @param lockKey 锁名称
     * @param requestId 请求id
     * @param reqTime 锁定时间
     * @return true成功，false失败
     */
    @SuppressWarnings("unchecked")
	public boolean lock(String lockKey, String requestId, long reqTime) {
    	final RedisSerializer<Object> serializer = (RedisSerializer<Object>) redisTemplate.getValueSerializer();
    	return redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				Boolean value = connection.set(lockKey.getBytes(), serializer.serialize(requestId), Expiration.milliseconds(reqTime), SetOption.SET_IF_ABSENT);
				return value == null ? false : value;
			}
		});
    }
	
    public boolean unLock(String lockKey, String requestId) {
    	String value = redisTemplate.opsForValue().get(lockKey);
    	//非锁持有人不允许解锁
    	if (value == null || !value.equalsIgnoreCase(requestId)) {
    		return false;
    	}
    	redisTemplate.delete(lockKey);
    	return true;
    }
}