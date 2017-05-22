/**
 * Copyright 2016, RadiantBlue Technologies, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package messaging.job;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

/**
 * Factory class used to handle the inflation of Kafka consumers and producers to the Piazza Kafka cluster. This avoids
 * the scenario where each component has to write redundant code to join the cluster.
 * 
 * @author Patrick.Doody
 * 
 */
public class KafkaClientFactory {

	/**
	 * Creates a Kafka producer with default parameters for simple JSON messaging.
	 * 
	 * @param serversList
	 *            Cluster hosts. This list should be in the form host1:port1,host2:port2 .
	 * @return Kafka Producer
	 */
	public static KafkaProducer<String, String> getProducer(String serversList) {
		Properties props = new Properties();

		props.put("bootstrap.servers", serversList);
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 0);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		return new KafkaProducer<String, String>(props);
	}

	/**
	 * Creates a Kafka producer with default parameters for simple JSON messaging.
	 * 
	 * @param host
	 *            Cluster host name
	 * @param port
	 *            Cluster port
	 * @return Kafka Producer
	 */
	public static KafkaProducer<String, String> getProducer(String host, String port) {
		Properties props = new Properties();

		props.put("bootstrap.servers", String.format("%s:%s", host, port));
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 0);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		return new KafkaProducer<String, String>(props);
	}

	/**
	 * Creates a Kafka consumer with default parameters for simple JSON messaging.
	 * 
	 * @param host
	 *            Cluster host name
	 * @param port
	 *            Cluster port
	 * @param group
	 *            Group Id to join
	 * @return Kafka Consumer
	 */
	public static KafkaConsumer<String, String> getConsumer(String host, String port, String group) {
		Properties props = new Properties();

		props.put("bootstrap.servers", String.format("%s:%s", host, port));
		props.put("group.id", group);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("fetch.min.bytes", 1);
		props.put("receive.buffer.bytes", "65536");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		return new KafkaConsumer<String, String>(props);
	}

	/**
	 * Creates a Kafka consumer with default parameters for simple JSON messaging.
	 * 
	 * @param serversList
	 *            Cluster hosts. This list should be in the form host1:port1,host2:port2 .
	 * @param group
	 *            Group Id to join
	 * @return Kafka Consumer
	 */
	public static KafkaConsumer<String, String> getConsumer(String serversList, String group) {
		Properties props = new Properties();

		props.put("bootstrap.servers", serversList);
		props.put("group.id", group);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("fetch.min.bytes", 1);
		props.put("receive.buffer.bytes", "65536");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		return new KafkaConsumer<String, String>(props);
	}
}
