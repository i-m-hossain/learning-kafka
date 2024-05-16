#!/bin/bash

# Parse command line arguments
for i in "$@"
do
    case $i in
        --name=*)
            TOPIC_NAME="${i#*=}"
            shift # past argument=value
            ;;
        *)
            # unknown option
            ;;
    esac
done

# Check if topic name is provided
if [ -z "$TOPIC_NAME" ]; then
    echo "Error: Please provide a topic name using --name=<name>"
    exit 1
fi

# Run kafka-topics.sh command to create the topic
kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic "$TOPIC_NAME"

