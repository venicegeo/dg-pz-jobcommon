/**
 * Copyright 2016, RadiantBlue Technologies, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * re
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package model.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import model.job.Job;
import model.job.JobProgress;
import model.job.result.ResultType;
import model.job.type.ExecuteServiceJob;

/**
 * Represents the Status and Results of a Piazza Job. This includes the readiness of the Job, and potential Status
 * information. If available, the resulting Resource Object created from the Job is attached as well.
 * 
 * @author Patrick.Doody
 * 
 */
public class JobStatusResponse extends PiazzaResponse {

	@ApiModelProperty(value = "Contains the Job Status Information", required = true)
	public JobStatusData data = new JobStatusData();

	private final static Logger LOGGER = LoggerFactory.getLogger(JobStatusResponse.class);

	public JobStatusResponse() {
	}

	public JobStatusResponse(Job job) {
		data.populateFromJob(job);
	}

	/**
	 * Inner class to wrap the information related to a Job's status. The reason we don't return the Job itself, is
	 * because that Job contains sensitive information (the input parameters). So this data block is intended to expose
	 * the members that are applicable to retrieving Job Status, and not expose any of the sensitive properties.
	 */
	@JsonInclude(Include.NON_NULL)
	public class JobStatusData {
		@ApiModelProperty(value = "A reference to the Result of the Job. This could be a Resource Id, or a Service Id, in certain cases. Or perhaps an error if the Job encountered an error during processing", dataType = "model.swagger.SwaggerResultType")
		public ResultType result;

		@ApiModelProperty(value = "The status of the Job. Submitted, Running, Success, Error, or Failure", required = true)
		public String status;

		@ApiModelProperty(value = "Polymorphically describes the information defining what actions this Job should take", required = true)
		public String jobType;

		@ApiModelProperty(value = "The name of the user who submitted the Job", required = true)
		public String createdBy;

		@ApiModelProperty(value = "The time the Job was requested.", required = true)
		public String createdOn;

		@ApiModelProperty(value = "If a Service Job, this will contain the Id of the Service that was executed.", required = true)
		public String executedServiceId;

		@ApiModelProperty(value = "Object containing metadata describing the current status of the Job")
		public JobProgress progress;

		@ApiModelProperty(value = "The Id of the Job.")
		public String jobId;

		/**
		 * Populates the properties of this Data block from the Job.
		 * 
		 * @param job
		 *            The Job.
		 */
		public void populateFromJob(Job job) {
			jobId = job.getJobId();
			result = job.result;
			status = job.status;
			progress = job.progress;
			jobType = job.getJobType().getClass().getSimpleName();
			createdBy = job.createdBy;
			createdOn = job.createdOn.toString();

			// If a Service Job, populate the Id for traceability. Try/catch so as not to break on legacy Jobs that
			// might still exist that do not follow the model of the current Execute Service Job.
			try {
				if (job.getJobType() instanceof ExecuteServiceJob) {
					executedServiceId = ((ExecuteServiceJob) job.getJobType()).data.getServiceId();
				}
			} catch (Exception exception) {
				// Log the error.
				LOGGER.error(String.format(
						"Error populating `executedServiceId` field for Job %s: %s. This will not be included in the Job Status payload.",
						jobId, exception.getMessage()), exception);
			}
		}
	}

}