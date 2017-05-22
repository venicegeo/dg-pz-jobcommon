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
package model.response;

import io.swagger.annotations.ApiModelProperty;
import model.job.type.ExecuteServiceJob;
import model.service.taskmanaged.ServiceJob;

/**
 * Response containing a Service Job, and the input data for execution
 * 
 * @author Patrick.Doody
 *
 */
public class ServiceJobResponse extends PiazzaResponse {
	@ApiModelProperty(value = "The Service Job Metadata.", required = true)
	public ServiceJobData data = new ServiceJobData();

	public ServiceJobResponse(ExecuteServiceJob serviceData, String jobId) {
		data.setServiceData(serviceData);
		data.setJobId(jobId);
	}

	public ServiceJobResponse() {
	}

	/**
	 * Used to wrap the Service Job in an annotatable class.
	 */
	public class ServiceJobData {
		@ApiModelProperty(value = "The Service Job Execution Metadata", required = true)
		private ExecuteServiceJob serviceData;
		@ApiModelProperty(value = "The Id of the Job", required = true)
		private String jobId;

		public ExecuteServiceJob getServiceData() {
			return serviceData;
		}

		public void setServiceData(ExecuteServiceJob serviceData) {
			this.serviceData = serviceData;
		}

		public String getJobId() {
			return jobId;
		}

		public void setJobId(String jobId) {
			this.jobId = jobId;
		}
	}
}
