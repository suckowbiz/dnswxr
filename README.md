# What is it?
A self contained Java application to expose a REST API to update DNS records at [InterNetworX](https://www.inwx.de).

# How do I run it?
 1. Run ```java -jar target\dnswxer-swarm.jar``` to expose the REST API.
 2. Call (replace placeholders) ```http(s)://<server-ip>:<server-port>/resources/records/a/update/?username=<username>&password=<password>&ip=<ip>&ttl=<time-to-life>&id=<id-of-a-resource-record?id=<id-of-further-a-resource-record>``` to update your records.

Note: This stuff is licensed under APACHE 2 to allow doing anything with proper attribution and without warranty.
