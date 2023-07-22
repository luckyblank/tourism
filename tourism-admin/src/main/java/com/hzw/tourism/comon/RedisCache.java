package com.hzw.tourism.comon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存的基本对象。Integer String 实体类
     *
     * @param key  缓存的键值
     * @param value 缓存的值
     * @param <T>
     * @return    缓存的对象
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        return operations;
    }

    /**
     *
     * @param key  缓存的键值
     * @param value  缓存的值
     * @param timeout  时间
     * @param timeUnit  时间颗粒度
     * @param <T>
     * @return   缓存的对象
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        operations.set(key, value, timeout, timeUnit);
        return operations;
    }

    /**
     * 获得缓存的基本对象
     *
     * @param key   缓存键值
     * @param <T>
     * @return   缓存键值对应的数据
     */
    public <T> T getCacheObject(String key){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public void deleteObject(String  key){
        redisTemplate.delete(key);
    }


    /**
     * 删除集合对象
     *
     * @param collection
     */
    public void deleteObject(Collection collection){
        redisTemplate.delete(collection);
    }


    /**
     * 缓存list数据
     *
     * @param key    缓存的键值
     * @param dataList    带缓存的list数据
     * @param <T>
     * @return    缓存的对象
     */
    public <T> ListOperations<String, T> setCacheList(String key, List<T> dataList){
        ListOperations<String, T> listOperations = redisTemplate.opsForList();
        if (dataList != null) {
            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                listOperations.leftPush(key, dataList.get(i));
            }
        }
        return listOperations;
    }


    /**
     *  获得缓存的list对象
     *
     * @param key  缓存的键值
     * @param <T>
     * @return   缓存键值对应的集合数据
     */
    public  <T> List<T> getCacheList(String key){
        List<T> list = new ArrayList<>();
        ListOperations<String, T> listOperations = redisTemplate.opsForList();
        Long size = listOperations.size(key);

        for (int i = 0; i < size; i++) {
            list.add(listOperations.index(key, i));
        }
        return list;
    }

    /**
     * 缓存Set
     *
     * @param key 缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext())
        {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(String key) {
        Set<T> dataSet = new HashSet<T>();
        BoundSetOperations<String, T> operation = redisTemplate.boundSetOps(key);
        dataSet = operation.members();
        return dataSet;
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @return
     */
    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap)
        {
            for (Map.Entry<String, T> entry : dataMap.entrySet())
            {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(String key) {
        Map<String, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }

    /**
     *获得缓存的基本对象列表
     * @param pattern  字符串前缀
     * @return
     */
    public Collection<String> keys(String pattern){
        return redisTemplate.keys(pattern);
    }
}

