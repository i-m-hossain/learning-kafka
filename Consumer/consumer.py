from kafka import KafkaConsumer
import json
import logging
import time

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

def consume_messages(broker, topic):
    while True:
        try:
            consumer = KafkaConsumer(
                topic,
                bootstrap_servers=[broker],
                auto_offset_reset="earliest",
                enable_auto_commit=True,
                value_deserializer=lambda x: json.loads(x.decode("utf-8"))
            )

            for message in consumer:
                log_message = f"""
                Message received: {message.value}
                Message key: {message.key}
                Message partition: {message.partition}
                Message offset: {message.offset}
                """
                logger.info(log_message)
        except Exception as e:
            logger.error(f"Error consuming messages: {e}")
            time.sleep(5)  # Wait before retrying to avoid a tight loop

if __name__ == "__main__":
    consume_messages(broker='kafka:9092', topic='test-products')
