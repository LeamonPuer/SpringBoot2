package com.SpringBoot;


import org.junit.Test;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Lemon
 * @create 2022-12-27-16:44
 */
public class JedisTest {
    JedisPool jp=new JedisPool("192.168.64.100",6379);
    @Test
    public void test(){
        Jedis jedis = new Jedis("192.168.64.100",6379);
        jedis.set("name","John");
        jedis.set("age","20");
        Transaction multi = jedis.multi();
        try {
            multi.set("name","Jerry");
            multi.incrBy("name",5);
            multi.exec();
        } catch (Exception e) {
            multi.discard();
        }
        System.out.printf("%s,%s",jedis.get("name"),jedis.get("age"));
        jedis.close();
    }
    /*
    @Test
    public void test1(){
        Jedis jedis = jp.getResource();
        jedis.sadd("cities","fs","sc","sz","bj");
        Set<String> cities = jedis.smembers("cities");
        System.out.println(cities);
        jedis.close();
    }
    JedisPooled jpd=new JedisPooled("192.168.64.100",6379);
    @Test
    public void test2(){
        Set<String> cities = jpd.smembers("cities");
        System.out.println(cities);
    }
    JedisSentinelPool jsp;
    {
        Set<String> sentinel=new HashSet<>();
        sentinel.add("192.168.64.100:26380");
        sentinel.add("192.168.64.100:26381");
        sentinel.add("192.168.64.100:26382");
        jsp=new JedisSentinelPool("mymaster",sentinel);
    }
    @Test
    public void test3(){
        Jedis jedis = jsp.getResource();
        jedis.hset("emp","name","John");
        String name = jedis.hget("emp", "name");
        System.out.println(name);
        jedis.close();
    }
    JedisCluster jc;
    {
        Set<HostAndPort> cluster=new HashSet<>();
        cluster.add(new HostAndPort("192.168.64.100",6380));
        cluster.add(new HostAndPort("192.168.64.100",6381));
        cluster.add(new HostAndPort("192.168.64.100",6382));
        cluster.add(new HostAndPort("192.168.64.100",6383));
        cluster.add(new HostAndPort("192.168.64.100",6384));
        cluster.add(new HostAndPort("192.168.64.100",6385));
        jc=new JedisCluster(cluster);
    }
    @Test
    public void test4(){
        jc.rpush("hobby","music","game","dance");
        List<String> hobby = jc.lrange("hobby", 0, -1);
        System.out.println(hobby);
    }
*/
}
