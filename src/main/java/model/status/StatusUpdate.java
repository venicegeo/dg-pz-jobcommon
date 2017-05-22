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
package model.status;

import model.job.JobProgress;
import model.job.result.ResultType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

/**
 * A Status Update message for a Job. This is intended to be fired by Worker components throughout the processing of a
 * Job.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
public class StatusUpdate {

	@JsonIgnore
	public static final String STATUS_SUBMITTED = "Submitted";

	@JsonIgnore
	public static final String STATUS_PENDING = "Pending";

	@JsonIgnore
	public static final String STATUS_RUNNING = "Running";

	@JsonIgnore
	public static final String STATUS_SUCCESS = "Success";

	@JsonIgnore
	public static final String STATUS_CANCELLED = "Cancelled";

	@JsonIgnore
	public static final String STATUS_CANCELLING = "Cancelling";

	@JsonIgnore
	public static final String STATUS_ERROR = "Error";

	@JsonIgnore
	public static final String STATUS_FAIL = "Fail";

	@ApiModelProperty(required = false, value = "The Status of the Service.", dataType = "string", allowableValues = "Submitted, Pending, Running, Success, Cancelled, Cancelling, Error, Fail")
	@Pattern(regexp = "Submitted|Pending|Running|Success|Cancelled|Cancelling|Error|Fail", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String status;

	@ApiModelProperty(required = false, value = "The Progress information of the Job, including time spent/remaining, or percentage complete.")
	private JobProgress progress;

	@ApiModelProperty(required = false, value = "The Result object of this Job.")
	private ResultType result;

	public StatusUpdate() {

	}

	public StatusUpdate(String status) {
		this.status = status;
	}

	public StatusUpdate(String status, JobProgress progress) {
		this(status);
		this.progress = progress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JobProgress getProgress() {
		return progress;
	}

	public void setProgress(JobProgress progress) {
		this.progress = progress;
	}

	/**
	 * Sets the Result of the Job. This result will likely be an Id that points to a resource in the Resources
	 * collection.
	 * 
	 * As a reminder, by nature, it would seem silly to submit the Result for the Job before the Status of the Job is
	 * set to COMPLETE. If you're attaching the Result to this StatusUpdate object, then it's suggested you also set the
	 * status flag to completed.
	 * 
	 * @param result
	 *            The result of the Job.
	 */
	public void setResult(ResultType result) {
		this.result = result;
	}

	public ResultType getResult() {
		return result;
	}
}