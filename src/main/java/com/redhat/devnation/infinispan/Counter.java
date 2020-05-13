package com.redhat.devnation.infinispan;

import java.util.concurrent.CompletableFuture;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.RemoteCounterManagerFactory;
import org.infinispan.counter.api.CounterConfiguration;
import org.infinispan.counter.api.CounterManager;
import org.infinispan.counter.api.CounterType;
import org.infinispan.counter.api.StrongCounter;

public class Counter {

   public static final void main(String args[]) {
      try (RemoteCacheManager cacheManager = new RemoteCacheManager()) {
         CounterManager counterManager = RemoteCounterManagerFactory.asCounterManager(cacheManager);
         counterManager.defineCounter("counter", CounterConfiguration.builder(CounterType.UNBOUNDED_STRONG).initialValue(1).build());
         StrongCounter counter = counterManager.getStrongCounter("counter");
         CompletableFuture<Long> v = counter.incrementAndGet();
      }
   }
}
