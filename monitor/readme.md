

-------------


## zipkin-server搭建与使用

docker-compose

```
  zipkin-server:
    image: openzipkin/zipkin
    container_name: sc-zipkin-server
    restart: always
    volumes:
      - ./data/logs/zipkin-server:/logs
    networks:
      - sc-net
    ports:
      - 9411:9411
    environment:
      - RABBIT_ADDRESSES=rabbitmq:5672
      - RABBIT_MQ_PORT=5672
      - RABBIT_PASSWORD=guest
      - RABBIT_USER=guest
    depends_on:
      - rabbitmq

```


