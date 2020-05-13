package com.redhat.devnation.infinispan;

import javax.transaction.TransactionManager;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

public class Tx {

   public static final void main(String args[]) {
      try (RemoteCacheManager cacheManager = new RemoteCacheManager()) {
         RemoteCache<String, String> cache = cacheManager.administration().getOrCreateCache("tx", "<infinispan><cache-container>" +
               "<distributed-cache name=\"tx\">" +
               "  <locking isolation=\"REPEATABLE_READ\"/>" +
               "  <transaction mode=\"NON_XA\" locking=\"PESSIMISTIC\"/>" +
               "</distributed-cache>" +
               "</cache-container></infinispan>");
         TransactionManager txManager = cache.getTransactionManager();
         try {
            txManager.begin();
            cache.put("k1", "v1");
            cache.put("k2", "v2");
            txManager.commit();
         } catch (Exception e) {
         }
      }
   }
}
