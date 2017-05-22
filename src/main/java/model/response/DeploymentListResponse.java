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

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import model.data.deployment.Deployment;

/**
 * Represents the response that the Gateway returns to the user when the user
 * has requested to see the information on a list of Deployments currently held
 * by the Piazza system; represented internally using the DataResource object.
 * 
 * @author Patrick.Doody
 * 
 */
@ApiModel("DeploymentResults")
public class DeploymentListResponse extends PiazzaResponse {

	@ApiModelProperty(value = "The array of Deployment results", required = true)
	public List<Deployment> data;

	@ApiModelProperty(value = "The pagination metadata for this query", required = true)
	public Pagination pagination;

	public DeploymentListResponse() {

	}

	public DeploymentListResponse(List<Deployment> deployments) {
		this.data = deployments;
	}

	public DeploymentListResponse(List<Deployment> deployments, Pagination pagination) {
		this(deployments);
		this.pagination = pagination;
	}

	/**
	 * Returns the list of Deployments held by this response object
	 * 
	 * @return The list
	 */
	public List<Deployment> getData() {
		return data;
	}

	public Pagination getPagination() {
		return pagination;
	}
}