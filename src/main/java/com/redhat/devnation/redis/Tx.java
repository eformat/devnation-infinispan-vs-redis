package com.redhat.devnation.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class Tx {

   public static final void main(String args[]) {
      RedisClient client = RedisClient.create("redis://password@localhost:6379/0");
      try (StatefulRedisConnection<String, String> connection = client.connect()) {
         RedisCommands<String, String> redis = connection.sync();
         redis.multi();
         redis.set("k1", "v1");
         redis.set("k2", "v2");
         redis.exec();

      }
      client.shutdown();
   }
}
