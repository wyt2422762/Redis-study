package com.wyt.test;

import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisTest {
  
    /**
     * 字符串测试
     */
    public static void stringOpt(){
        Jedis jedis = RedisUtil.getJedis();
        
        //1.添加字符串
        System.out.println("Redis 添加字符串数据 ----> key:stringTest1");
        jedis.set("stringTest1", "redis 字符串测试1");
        //2.判断是否存在key stringTest1
        boolean exist = jedis.exists("stringTest1");
        if(exist){
            //查询key对应的数据
            System.out.println("Redis 查询 key: stringTest1对应的字符串数据 ----> " + jedis.get("stringTest1"));
            
            System.out.println("Redis 覆盖字符串数据 ----> key:stringTest1 value: redis 字符串测试(覆盖)");
            //直接覆盖原来的数据
            jedis.set("stringTest1", "redis 字符串测试(覆盖)");
            System.out.println("Redis 查询 key: stringTest1对应的新的字符串数据 ----> " + jedis.get("stringTest1"));
            
            //在原来的数据上追加
            jedis.append("stringTest1", "(追加)");
            System.out.println("Redis 查询 key: stringTest1对应的追加后的字符串数据 ----> " + jedis.get("stringTest1"));
            
            //如果stringTest1不存在，则新增数据，如果存在则不做任何操作
            jedis.setnx("stringTest1", "(setnx)");
            System.out.println("Redis 查询 key: stringTest1对应的setnx后的字符串数据 ----> " + jedis.get("stringTest1"));
            
            //删除数据
            jedis.del("stringTest1");
            System.out.println("Redis 查询 key: stringTest1对应的字符串数据 ----> " + jedis.get("stringTest1"));
            
        }else{
            System.out.println("Redis key: stringTest1 不存在");
        }
        
        System.out.println("=============增，删，查（多个）=============");
        
        System.out.println("批量新增stringTest2,stringTest3");
        jedis.mset("stringTest2", "redis 字符串测试2", "stringTest3", "redis 字符串测试3");
        
        System.out.println("批量查询stringTest2,stringTest3对应的值： " + jedis.mget("stringTest2", "stringTest3"));
        
        System.out.println("批量删除stringTest2,stringTest3");
        jedis.del("stringTest2", "stringTest3");
        
        System.out.println("批量查询stringTest2,stringTest3对应的值： " + jedis.mget("stringTest2", "stringTest3"));
        
        RedisUtil.returnResource(jedis);
    }
    
    /**
     * 列表操作
     */
    public static void listOpt(){
        Jedis jedis = RedisUtil.getJedis();
        
        long rpush = jedis.rpush("listTest", "rpush");
        System.out.println("rpush = " + rpush);
        
        long lpush = jedis.lpush("listTest", "lpush");
        System.out.println("lpush = " + lpush);
        
        String rpop = jedis.rpop("listTest");
        System.out.println("rpop = " + rpop);
        String lpop = jedis.lpop("listTest");
        System.out.println("lpop = " + lpop);
        
        String lindex = jedis.lindex("listTest", 1);
        System.out.println("lindex = " + lindex);
        
        List<String> lrange = jedis.lrange("listTest", 0, -1);
        System.out.println("lrange = " + lrange.toString());
        
        String ltrim = jedis.ltrim("listTest", 1, 2);
        System.out.println("ltrim = " + ltrim);
        
        
        //将一个元素从一个列表移动到另一个列表,并返回这个元素
        jedis.rpush("list1", "list1_1", "list1_2");
        jedis.rpush("list2", "list2_1", "list2_2");
        String rpoplpush = jedis.rpoplpush("list1", "list2");
        System.out.println("rpoplpush = " + rpoplpush);
        System.out.println("list1 = " + jedis.lrange("list1", 0, -1));
        System.out.println("list2 = " + jedis.lrange("list2", 0, -1));
        
        
    }
    
    public static void main(String[] args) {
        //stringOpt();
        
        listOpt();
    }
    
}
