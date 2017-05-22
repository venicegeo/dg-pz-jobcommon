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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import model.job.PiazzaJobType;
import model.service.metadata.ExecuteServiceData;

@ApiModel(value = "ExecuteJob")
public class ExecuteServiceJob implements PiazzaJobType {

	@ApiModelProperty(required = true, value = "The type of Job", allowableValues = "execute-service")
	public String type;

	@ApiModelProperty(value = "Specified by the system. The Job Id created for executing the Service")
	public String jobId = null;

	@ApiModelProperty(value = "The parameter inputs for Service execution", required = true)
	@NotNull
	@Valid
	public ExecuteServiceData data;

	public ExecuteServiceData getData() {
		return data;
	}

	public void setData(ExecuteServiceData data) {
		this.data = data;
	}

	public ExecuteServiceJob() { //NOSONAR
		// Normal for empty constructor even with @NotNull fields
	}

	public ExecuteServiceJob(String jobId) { //NOSONAR
		this.jobId = jobId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}