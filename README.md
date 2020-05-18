# infinispan-vs-redis


## Infinispan cluster

Start and stop 2 node infinispan cluster
```bash
podman-compose -t hostnet up
podman-compose down
```

Create users
```bash
-- exec container
podman exec -it ispn1 /bin/bash
podman exec -it ispn2 /bin/bash

-- do this on BOTH nodes
/opt/infinispan/bin/user-tool.sh
Specify a username: admin
Set a password for the user: 
Confirm the password for the user: 
bash-4.4$ exit
``` 

Test CLI
```bash
podman exec -it ispn1 /opt/infinispan/bin/cli.sh
[disconnected]> connect http://127.0.0.1:11222
Username: admin
Password: *****

create cache --template=org.infinispan.DIST_SYNC distcache
cache distcache
put k1 v1
put k2 v2
ls
get k1
```

Test HTTP
```bash
curl --digest -u admin:admin http://localhost:11322/rest/v2/caches/distcache/k2
curl --digest -u admin:admin http://localhost:11222/rest/v2/caches/distcache/k2
```

#### Refs:

- https://infinispan.org/blog/2019/11/11/serverng/
- https://infinispan.org/blog/tags/server/
- https://infinispan.org/blog/tags/docker/
- https://github.com/infinispan/infinispan-images
- https://gist.github.com/bjalon/315467b91f2208087281896dcf387e08
