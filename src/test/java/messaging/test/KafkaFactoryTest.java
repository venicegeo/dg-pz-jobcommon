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
package messaging.test;

import static org.junit.Assert.assertTrue;
import messaging.job.JobMessageFactory;
import messaging.job.KafkaClientFactory;
import model.data.DataResource;
import model.data.type.GeoJsonDataType;
import model.job.Job;
import model.job.JobProgress;
import model.job.metadata.ResourceMetadata;
import model.job.type.AbortJob;
import model.job.type.IngestJob;
import model.request.PiazzaJobRequest;
import model.status.StatusUpdate;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test the messaging.job.* package, which includes factories for generating
 * Kafka clients and messages.
 * 
 * @author Patrick.Doody
 *
 */
public class KafkaFactoryTest {
	/**
	 * Tests the creation of Job Messages
	 */
	@Test
	public void testJobMessages() throws Exception {
		// Test Job Requesting
		PiazzaJobRequest request = new PiazzaJobRequest();
		request.createdBy = "Test User";
		request.jobType = new AbortJob("123456");
		ProducerRecord<String, String> record = JobMessageFactory.getRequestJobMessage(request, "123456", "TEST");
		assertTrue(record.topic().equals(JobMessageFactory.REQUEST_JOB_TOPIC_NAME + "-TEST"));
		assertTrue(record.key().equals("123456"));
		PiazzaJobRequest serialized = new ObjectMapper().readValue(record.value(), PiazzaJobRequest.class);
		assertTrue(serialized.jobType instanceof AbortJob);
		assertTrue(((AbortJob) serialized.jobType).jobId.equals("123456"));

		// Test Job Abort Message
		record = JobMessageFactory.getAbortJobMessage(request, "123456", "TEST");
		assertTrue(record.topic().equals(JobMessageFactory.ABORT_JOB_TOPIC_NAME + "-TEST"));
		assertTrue(record.key().equals("123456"));

		// Test Update Status Message
		StatusUpdate update = new StatusUpdate(StatusUpdate.STATUS_RUNNING, new JobProgress(50));
		record = JobMessageFactory.getUpdateStatusMessage("123456", update, "TEST");
		assertTrue(record.topic().equals(JobMessageFactory.UPDATE_JOB_TOPIC_NAME + "-TEST"));
		assertTrue(record.key().equals("123456"));
		StatusUpdate serializedUpdate = new ObjectMapper().readValue(record.value(), StatusUpdate.class);
		assertTrue(serializedUpdate.getProgress().getPercentComplete().equals(50));

		// Test Create Job Message
		Job mockJob = new Job(request, "123456");
		record = JobMessageFactory.getJobManagerCreateJobMessage(mockJob, "TEST");
		assertTrue(record.topic().equals(JobMessageFactory.CREATE_JOB_TOPIC_NAME + "-TEST"));
		assertTrue(record.key().equals("123456"));
		Job serializedJob = new ObjectMapper().readValue(record.value(), Job.class);
		assertTrue(serializedJob.jobType instanceof AbortJob);

		// Test Ingest Job Creation
		DataResource mockData = new DataResource();
		mockData.setDataId("654321");
		mockData.metadata = new ResourceMetadata();
		mockData.metadata.setName("Test Data");
		mockData.dataType = new GeoJsonDataType();
		record = JobMessageFactory.getIngestJobForDataResource(mockData, "123456", "Test User", "TEST");
		assertTrue(record.topic().equals(JobMessageFactory.REQUEST_JOB_TOPIC_NAME + "-TEST"));
		assertTrue(record.key().equals("123456"));
		serialized = new ObjectMapper().readValue(record.value(), PiazzaJobRequest.class);
		assertTrue(serialized.jobType instanceof IngestJob);

		// Test Worker Job Creation
		record = JobMessageFactory.getWorkerJobCreateMessage(mockJob, "TEST");
		assertTrue(record.topic().equals(mockJob.jobType.getClass().getSimpleName() + "-TEST"));
		assertTrue(record.key().equals("123456"));

		// Test deserialized helper method
		PiazzaJobRequest createdRequest = JobMessageFactory.parseRequestJson(new ObjectMapper()
				.writeValueAsString(request));
		assertTrue(createdRequest instanceof PiazzaJobRequest);
		assertTrue(createdRequest.createdBy.equals("Test User"));
		assertTrue(createdRequest.jobType instanceof AbortJob);
	}

	/**
	 * Test kafka producer and consumer factories
	 */
	@Test
	public void testClientFactory() {
		// Inflate producer and consumer, ensure no exceptions are thrown
		KafkaProducer<String, String> producer = KafkaClientFactory.getProducer("localhost", "12345");
		assertTrue(producer instanceof KafkaProducer);
		KafkaConsumer<String, String> consumer = KafkaClientFactory.getConsumer("localhost", "12345", "TEST");
		assertTrue(consumer instanceof KafkaConsumer);
	}
}
