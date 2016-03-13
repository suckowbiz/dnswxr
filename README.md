# What is it?
A self containing Java application that exposes a REST API to update DNS records at [InterNetworX](https://www.inwx.de).

# How do I run it?
 1. Execute ```bash mvn package``` to create *target\dnswxer-swarm.jar*
 2. Run it with ```java -jar target\dnswxer-swarm.jar``` to expose the REST API
 3. Call via ```http(s)://<server-ip>:<server-port>/resources/records/a/update/?username=<username>&password=<password>&ip=<ip>&ttl=<time-to-life>&id=<id-of-a-resource-record?id=<id-of-further-a-resource-record>```

Note: This stuff is licensed under APACHE 2 to allow doing anything with proper attribution and without warranty.