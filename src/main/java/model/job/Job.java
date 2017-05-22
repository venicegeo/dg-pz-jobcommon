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
package model.job;

import java.util.ArrayList;
import java.util.List;

import model.job.result.ResultType;
import model.request.PiazzaJobRequest;
import model.status.StatusUpdate;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines a Job in the Piazza system.
 * 
 * A Job is uniquely identified by a Job Id. This is the primary key used for lookup in the Jobs collection.
 * 
 * Additional Metadata is also stored with a job, such as who submitted the job, what their API key is, and when the Job
 * was submitted.
 * 
 * Job Status and Progress are also tracked and can be reliably used to determine what the current status of the job is
 * as it passes throughout the Piazza system.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {
	public String jobId;
	public PiazzaJobType jobType;
	@JsonIgnore
	public DateTime createdOn;
	public String createdBy;
	public String status;
	public JobProgress progress = new JobProgress();
	public List<JobProgress> history = new ArrayList<JobProgress>();
	public ResultType result;

	public Job() {

	}

	public Job(PiazzaJobRequest request, String jobId) {
		this.jobId = jobId;
		this.jobType = request.jobType;
		this.createdBy = request.createdBy;
		this.status = StatusUpdate.STATUS_SUBMITTED;
		this.createdOn = new DateTime();
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@JsonProperty("createdOn")
	public String getCreatedOnString() {
		if (createdOn != null) {
			// Defaults to ISO8601
			return createdOn.toString();
		} else {
			return null;
		}
	}

	@JsonProperty("createdOn")
	public void setCreatedOnString(String createdOn) {
		this.createdOn = new DateTime(createdOn);
	}

	public PiazzaJobType getJobType() {
		return jobType;
	}

	public ResultType getResult() {
		return result;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public void setJobType(PiazzaJobType jobType) {
		this.jobType = jobType;
	}

	public void setResult(ResultType result) {
		this.result = result;
	}
}
