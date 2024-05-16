from kafka import KafkaProducer
import json

def produce_messages(broker, topic):
    producer = KafkaProducer(
        bootstrap_servers=[broker],
        value_serializer=lambda v: json.dumps(v).encode('utf-8')
    )
    
    # Example messages
    messages = [
        {"product_id": 1, "name": "Product 1", "price": 10.0},
        {"product_id": 2, "name": "Product 2", "price": 20.0},
        {"product_id": 3, "name": "Product 3", "price": 30.0},
    ]
    
    for message in messages:
        producer.send(topic, message)
        print(f'Sent: {message}')
    
    producer.flush()

if __name__ == "__main__":
    produce_messages(broker='kafka:9092', topic='test-products')

