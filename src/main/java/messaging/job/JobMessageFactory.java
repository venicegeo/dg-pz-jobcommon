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

import java.io.IOException;

import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.PiazzaJobException;
import model.data.DataResource;
import model.job.Job;
import model.job.type.IngestJob;
import model.request.PiazzaJobRequest;
import model.status.StatusUpdate;

/**
 * Factory class to abstract the production of Job-related Kafka messages using this projects defined POJO Models.
 * 
 * @author Patrick.Doody
 * 
 */
public class JobMessageFactory {
	public static final String REQUEST_JOB_TOPIC_NAME = "Request-Job";
	public static final String CREATE_JOB_TOPIC_NAME = "Create-Job";
	public static final String ABORT_JOB_TOPIC_NAME = "Abort-Job";
	public static final String UPDATE_JOB_TOPIC_NAME = "Update-Job";
	public static final String KAFKA_TOPIC_TEMPLATE = "%s-%s";

	/**
	 * Creates a Kafka message for a Piazza Job to be created. This Topic is listened to solely by the Job Manager and
	 * acts as a simple pass-through from the outer world to the internal Piazza components.
	 * 
	 * @param piazzaRequest
	 *            The Job to be created.
	 * @param jobId
	 *            The Id of the Job
	 * @return Kafka message
	 * @throws JsonProcessingException
	 *             The PiazzaJob cannot be serialized to JSON.
	 */
	public static ProducerRecord<String, String> getRequestJobMessage(PiazzaJobRequest piazzaRequest, String jobId, String space)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return new ProducerRecord<String, String>(String.format(KAFKA_TOPIC_TEMPLATE, REQUEST_JOB_TOPIC_NAME, space), jobId,
				mapper.writeValueAsString(piazzaRequest));
	}

	/**
	 * Creates a Kafka message for a Piazza Job to be cancelled; referenced by Job Id. This message is consumed by the
	 * Job Manager, which will update the Job Tables, and any other components that are required to act on the Job being
	 * cancelled.
	 * 
	 * @param abortJob
	 *            The Job, describing the Job to abort and potential reason for requesting the cancellation.
	 * @return Kafka Message
	 * @throws JsonProcessingException
	 */
	public static ProducerRecord<String, String> getAbortJobMessage(PiazzaJobRequest piazzaRequest, String jobId, String space)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return new ProducerRecord<String, String>(String.format(KAFKA_TOPIC_TEMPLATE, ABORT_JOB_TOPIC_NAME, space), jobId,
				mapper.writeValueAsString(piazzaRequest));
	}

	/**
	 * Creates a Kafka message that wraps up a Status Update for a Job. This is intended to be produced by the Worker
	 * component that owns this Job.
	 * 
	 * This can also be used to post the result to the Job. If you're setting the status of the job to complete, you can
	 * also set the StatusUpdate.setResult() method to attach some resulting object or Id that the job has created.
	 * 
	 * @param jobId
	 *            The Id of the Job being updated
	 * @param statusUpdate
	 *            Status Update information
	 * @return
	 * @throws JsonProcessingException
	 */
	public static ProducerRecord<String, String> getUpdateStatusMessage(String jobId, StatusUpdate statusUpdate, String space)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return new ProducerRecord<String, String>(String.format(KAFKA_TOPIC_TEMPLATE, UPDATE_JOB_TOPIC_NAME, space), jobId,
				mapper.writeValueAsString(statusUpdate));
	}

	/**
	 * Creates a Kafka message for a Piazza Job to be created. This Topic is designed to be consumed by the Job Manager
	 * component. The JobManager will track this message and use it to commit the job transaction record into the Job
	 * Tables.
	 * 
	 * @param job
	 *            The Job to be indexed by the Job Manager
	 * @return Kafka message
	 * @throws JsonProcessingException
	 *             The PiazzaJob cannot be serialized to JSON.
	 */
	public static ProducerRecord<String, String> getJobManagerCreateJobMessage(Job job, String space) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return new ProducerRecord<String, String>(String.format(KAFKA_TOPIC_TEMPLATE, CREATE_JOB_TOPIC_NAME, space), job.getJobId(),
				mapper.writeValueAsString(job));
	}

	/**
	 * Creates a Job Request method for an Ingest Job to ingest the specified Data Resource, and assigns the specified
	 * Job Id to this Ingest job. The DataResource passed into this first argument MUST have its DataId specified.
	 * 
	 * This method is only to be used by internal Piazza components who *understand how the Job Process works*. This
	 * method is an abstraction around creating an IngestJob to the system, where a DataResource is meant to be
	 * ingested.
	 * 
	 * Instead of having the developer create a PiazzaJobRequest, followed up by an IngestJob, and then finally
	 * specifying the DataResource, this method aims to abstract out those first two steps and require only the
	 * DataResource to be ingested. This method will handle the rest of the creation and return the Kafka message that
	 * can be sent in order to create the Ingst Job.
	 * 
	 * @param dataResource
	 *            The Data Resource to be ingested
	 * @param jobId
	 *            The Job Id of the Ingest Job that will be created
	 * @param userName
	 *            The authenticated UserName
	 * @return The Kafka message for creating the Ingest Job, that can be Send via a producer.
	 */
	public static ProducerRecord<String, String> getIngestJobForDataResource(DataResource dataResource, String jobId, String userName,
			String space) throws PiazzaJobException, JsonProcessingException {
		// Data Resource must have an Id at this point
		if (dataResource.getDataId() == null) {
			throw new PiazzaJobException("The DataResource object must have a populated Id.");
		}

		// Create the IngestJob
		IngestJob ingestJob = new IngestJob();
		ingestJob.data = dataResource;
		ingestJob.host = true; // This method is only for internal components,
								// so we will always host

		// Create the Job Request and attach the IngestJob
		PiazzaJobRequest jobRequest = new PiazzaJobRequest();
		jobRequest.createdBy = userName;
		jobRequest.jobType = ingestJob;

		return JobMessageFactory.getRequestJobMessage(jobRequest, jobId, space);
	}

	/**
	 * Creates a Kafka message for a Piazza Job to be created and consumed by the internal Piazza worker. This topic is
	 * different from the above method, getJobManagerCreateJobMessage, in that it is meant to be consumed not by the Job
	 * Manager, but the internal component that will be performing the actual work on the job. Thus, the topic for this
	 * message is dynamic based on the JobType's type field.
	 * 
	 * @param job
	 *            The Job to be processed by the internal worker component.
	 * @returnKafka message
	 * @throws JsonProcessingException
	 *             The PiazzaJob cannot be serialized to JSON.
	 */
	public static ProducerRecord<String, String> getWorkerJobCreateMessage(Job job, String space) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return new ProducerRecord<String, String>(String.format(KAFKA_TOPIC_TEMPLATE, job.jobType.getClass().getSimpleName(), space),
				job.getJobId(), mapper.writeValueAsString(job));
	}

	/**
	 * Parses the raw JSON Payload into the PiazzaRest backing models. No value validation done here, only syntax.
	 * 
	 * @param json
	 *            JSON Payload from POST RequestBody
	 * @return PiazzaRequest object for the JSON Payload.
	 * @throws Exception
	 */
	public static PiazzaJobRequest parseRequestJson(String json) throws IOException, JsonParseException, JsonMappingException {
		return new ObjectMapper().readValue(json, PiazzaJobRequest.class);
	}

}
