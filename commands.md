# start container

    docker-compose up -d

## show running containers

    docker ps

## how to create a topic

    docker exec -it container_id bash
    #INSIDE THE CONTAINER
     kafka-topics.sh --create --bootstrap-server kafka:9092 --topic test-message --replication-factor 1 --partitions 1

## show topics

    #INSIDE THE CONTAINER
     kafka-topics.sh --bootstrap-server kafka:9092 --list

## topic consumer from kafka console

    #INSIDE THE CONTAINER
    kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic topicName --from-beginning

## topic producer from kafka console

     #INSIDE THE CONTAINER
     kafka-console-producer.sh --bootstrap-server kafka:9092 --topic topicName
