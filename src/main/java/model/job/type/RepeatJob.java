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
package model.job.type;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import model.job.PiazzaJobType;

/**
 * Repeats a Job that is currently stored within the Job Manager's Job Table.
 * This will re-run the Job with the API Key of the user requesting the repeat.
 * 
 * All resources generated (including the Job itself) will have new Ids
 * associated with them. This creates an entirely new, decoupled Job that has
 * only its exact inputs tied to the first Job.
 * 
 * @author Patrick.Doody
 * 
 */
public class RepeatJob implements PiazzaJobType {

	@ApiModelProperty(required = true, value = "The type of Job", allowableValues = "repeat")
	public String type;

	@ApiModelProperty(required = true, value = "The Id of the subject Job")
	@NotNull
	@Size(min=1)
	public String jobId = null;

	public RepeatJob() { //NOSONAR
		// Normal for empty constructor even with @NotNull fields
	}

	public RepeatJob(String jobId) { //NOSONAR
		this.jobId = jobId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}