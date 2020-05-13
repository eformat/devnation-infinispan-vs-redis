package com.redhat.devnation.infinispan;

import org.infinispan.client.hotrod.DefaultTemplate;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

public class PutAndGet {

   public static final void main(String args[]) {
      try (RemoteCacheManager cacheManager = new RemoteCacheManager()) {
         RemoteCache<String, String> cache = cacheManager.administration().getOrCreateCache("cache", DefaultTemplate.DIST_SYNC);
         cache.put("k1", "v1");
         cache.get("k1");
      }
   }
}
