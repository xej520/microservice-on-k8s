 #!/bin/bash
 docker stop api-gateway-zuul
 docker rm api-gateway-zuul
 docker run -it --name api-gateway-zuul api-gateway-zuul:latest