package com.redhat.devnation.redis;

import java.util.Map;

import com.redhat.devnation.model.Gender;
import com.redhat.devnation.model.Sheep;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class Exec {
   public static final void main(String args[]) {
      RedisClient client = RedisClient.create("redis://password@localhost:6379/0");
      try (StatefulRedisConnection<String, String> connection = client.connect()) {
         RedisCommands<String, String> redis = connection.sync();
         store(redis, new Sheep(1, "Shaun", Gender.MALE, 5));
         store(redis, new Sheep(2, "Shirley", Gender.FEMALE, 10));
         store(redis, new Sheep(3, "Timmy", Gender.MALE, 1));
         store(redis, new Sheep(4, "Nuts", Gender.MALE, 6));
      }
      client.shutdown();
   }

   private static void store(RedisCommands<String, String> redis, Sheep sheep) {
      redis.multi();
      redis.hmset("user:" + sheep.getId(), Map.of("name", sheep.getName(), "age", sheep.getAge().toString(), "gender", sheep.getGender().name()));
      redis.zadd("user_age", sheep.getAge(), "user:" + sheep.getId());
      redis.zadd("user_gender", sheep.getGender().name(), "user:" + sheep.getId());
      redis.exec();
   }
}
