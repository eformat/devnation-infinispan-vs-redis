package com.redhat.devnation.infinispan;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.infinispan.client.hotrod.DefaultTemplate;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;

import com.redhat.devnation.model.Gender;
import com.redhat.devnation.model.Sheep;


public class Query {
   public static final void main(String args[]) throws Exception {
      ConfigurationBuilder builder = new ConfigurationBuilder();
      builder.security().authentication().username("user").password("password").addContextInitializer(new ModelInitializerImpl());
      try (RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build())) {
         Path proto = Paths.get(Query.class.getClassLoader().getResource("proto/sheep.proto").toURI());
         cacheManager.getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME).put("sheep.proto", Files.readString(proto));
         RemoteCache<Integer, Sheep> cache = cacheManager.administration().getOrCreateCache("query", DefaultTemplate.DIST_SYNC);

         cache.put(1, new Sheep(1, "Shaun", Gender.MALE, 5));
         cache.put(2, new Sheep(2, "Shirley", Gender.FEMALE, 10));
         cache.put(3, new Sheep(3, "Timmy", Gender.MALE, 1));
         cache.put(4, new Sheep(4, "Nuts", Gender.MALE, 6));

         Search.getQueryFactory(cache).create("from devnation.Sheep s where s.age >= 4 and s.age <= 7").list().forEach(System.out::println);
      }
   }
}
