from kafka import KafkaProducer
import json
import time


def produce_messages(broker, topic):
    producer = KafkaProducer(
        bootstrap_servers=[broker],
        value_serializer=lambda v: json.dumps(v).encode('utf-8')
    )

    while True:
        # Example message
        message = {"product_id": 77, "name": "Product 7", "price": 60.0}
        producer.send(topic, message)
        print(f'Sent: {message}')
        producer.flush()
        time.sleep(5)  # Wait for a while before sending the next message


if __name__ == "__main__":
    produce_messages(broker='kafka:9092', topic='test-products')
