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
package model.service.taskmanaged;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Job that has been sent to a Task-Managed Piazza Service.
 * <p>
 * This object correlates the Job ID with the time that is was pulled off the Jobs queue and sent to an external Service
 * Worker for processing.
 * </p>
 * 
 * @author Patrick.Doody
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ServiceJob {
	/**
	 * The ID of the Service this Job pertains to.
	 */
	public String serviceId;
	public String jobId;
	public Integer timeouts;
	/**
	 * The time that work began processing on this Job.
	 */
	@JsonIgnore
	public DateTime startedOn;
	/**
	 * The time that the Job was inserted into the Jobs queue.
	 */
	@JsonIgnore
	public DateTime queuedOn;

	public ServiceJob() {
		this.queuedOn = new DateTime();
		this.timeouts = new Integer(0);
	}

	public ServiceJob(String jobId, String serviceId) {
		this();
		this.jobId = jobId;
		this.serviceId = serviceId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@JsonIgnore
	public DateTime getStartedOn() {
		return startedOn;
	}

	@JsonIgnore
	public void setStartedOn(DateTime startedOn) {
		this.startedOn = startedOn;
	}

	@JsonProperty("startedOn")
	public Long getStartedOnString() {
		if (startedOn != null) {
			// Defaults to ISO8601
			return startedOn.getMillis();
		} else {
			return null;
		}
	}

	@JsonProperty("startedOn")
	public void setStartedOnString(Long startedOn) {
		this.startedOn = new DateTime(startedOn);
	}

	@JsonIgnore
	public DateTime getQueuedOn() {
		return queuedOn;
	}

	@JsonIgnore
	public void setQueuedOn(DateTime queuedOn) {
		this.queuedOn = queuedOn;
	}

	@JsonProperty("queuedOn")
	public Long getQueuedOnString() {
		if (queuedOn != null) {
			// Defaults to ISO8601
			return queuedOn.getMillis();
		} else {
			return null;
		}
	}

	@JsonProperty("queuedOn")
	public void setQueuedOnString(Long queuedOn) {
		this.queuedOn = new DateTime(queuedOn);
	}

	public Integer getTimeouts() {
		return timeouts;
	}

	public void setTimeouts(Integer timeouts) {
		this.timeouts = timeouts;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
}
