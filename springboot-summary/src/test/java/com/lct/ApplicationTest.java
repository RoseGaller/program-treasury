//package com.lct;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lct.bean.Some;
//import com.lct.service.SomeService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Properties;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ApplicationTest {
//
//    @Autowired
//    private SomeService someService;
//
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private RedisTemplate<Object, Object> redisTemplate;
//
//    @Test
//    public  void testSome() throws Exception {
//        someService.doSome();
//    }
//
//    @Test
//    public  void testRedisStringValueOpsWithDiffTemplate(){
//        //springboot默认会提供两种template，一种是RedisTemplate，一种是StringRedisTemplate
//        //从字面意思看，stringRedisTemplate主要用来操作字符串的，redisTemplate主要用来操作对象的
//        //虽然用stringRedisTemplate也可以操作对象，把对象序列化成json，获取时还有自己反序列成对象
//        //如果使用后者去存一个数据，当你使用前者去取数据时是取不到的
//        //使用springboot-data包时，注意默认值的使用
//        String key = "test";
//        String value = "value";
//        //key 、Value的序列化方式：StringRedisSerializer
//        stringRedisTemplate.opsForValue().set(key,value);
//        //key、value的序列化方式：JdkSerializationRedisSerializer
//        //对key的序列化方式不同，自然取不到值
//        Object result = redisTemplate.opsForValue().get(key); // return null
//        Assert.assertEquals(value,result); //抛出异常
//        System.out.println(stringRedisTemplate);
//        System.out.println(redisTemplate);
//    }
//
//    @Test
//    public  void testRedisStringValueOps(){
//        //使用时，对象记得实现接口Serializable
//        String key = "test1111";
//        Some some = new Some();
//        some.setAge(12);
//        some.setName("test");
//        redisTemplate.opsForValue().set(key,some);
//        Object result = redisTemplate.opsForValue().get(key);
//        System.out.println(redisTemplate.getValueSerializer());
//        Assert.assertEquals(some,result);
//    }
//
//    @Test
//    public  void testRetry(){
//        Properties properties = (Properties) redisTemplate.execute(new RedisCallback(){
//
//            @Override
//            public Properties doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                Properties properties =  redisConnection.info();
//                return properties;
//            }
//        });
//        String[] commands = {"total_commands_processed","total_reads_processed","total_writes_processed",
//                                "keyspace_misses","keyspace_hits","connected_clients",
//                                "used_memory","used_memory_rss"};
//        for(String command : commands){
//            System.out.println(command + "----------------" +  properties.get(command));
//        }
//        //total_commands_processed/keyspace_hits/keyspace_misses/connected_clients/used_memory(数据实际大小)
//        //used_memory_rss(操作系统实际分配)/total_reads_processed/total_writes_processed
//    }
//
//}
