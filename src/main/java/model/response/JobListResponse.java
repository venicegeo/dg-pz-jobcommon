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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import model.job.Job;

/**
 * Represents the response that the Gateway returns to the user when the user
 * has requested to see the information on a list of Jobs currently held by the
 * Piazza Job Manager.
 * 
 * @author Patrick.Doody
 * 
 */
@ApiModel("JobResults")
public class JobListResponse extends PiazzaResponse {

	@ApiModelProperty(value = "The array of Job results", required = true)
	public List<Job> data;

	@ApiModelProperty(value = "The pagination metadata for this query", required = true)
	public Pagination pagination;

	public JobListResponse() {

	}

	public JobListResponse(List<Job> jobs) {
		this.data = jobs;
	}

	public JobListResponse(List<Job> deployments, Pagination pagination) {
		this(deployments);
		this.pagination = pagination;
	}

	/**
	 * Returns the list of Jobs held by this response object
	 * 
	 * @return The list
	 */
	public List<Job> getData() {
		return data;
	}

	public Pagination getPagination() {
		return pagination;
	}
}