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

/**
 * Standard Response that contains a simple Job Id.
 * 
 * @author Patrick.Doody
 *
 */
public class JobResponse extends PiazzaResponse {

	@ApiModelProperty(required = true, value = "The Object containing the Job Id")
	public JobIdData data = new JobIdData();

	public JobResponse(String jobId) {
		data.setJobId(jobId);
	}

	public JobResponse() {
	}

	/**
	 * Used to wrap the Job Id in an annotatable class.
	 */
	public class JobIdData {
		@ApiModelProperty(required = true, value = "The Id of the Job referenced in the request")
		private String jobId;

		public String getJobId() {
			return this.jobId;
		}

		public void setJobId(String jobId) {
			this.jobId = jobId;
		}
	}
}
