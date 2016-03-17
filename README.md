# What is it?
A self contained Java application to expose a REST API to update DNS records at [InterNetworX](https://www.inwx.de).  

The intention to develop this tool was to update my Homeserver's IP when my FritzBox (re)connects to the internet.

# How do I run it?
With docker (see [https://hub.docker.com/r/suckowbiz/dnswxr/](https://hub.docker.com/r/suckowbiz/dnswxr/)) or:

 1. Download and extract [dsnwxr-swarm](https://github.com/suckowbiz/dnswxr/releases/download/v1.0.0/dnswxr-swarm.tar.gz).
 2. Run ```java -jar dnswxr-swarm.jar``` to expose the REST API.
 2. Call (replace placeholders) ```http(s)://<server-ip>:<server-port>/resources/records/a/update/?username=<username>&password=<password>&ip=<ip>&ttl=<time-to-life>&id=<id-of-a-resource-record?id=<id-of-further-a-resource-record>``` to update your records.

Note: This stuff is licensed under APACHE 2 to allow doing anything with proper attribution and without warranty.
