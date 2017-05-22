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
import model.service.metadata.Service;

/**
 * Represents the response that the Gateway returns to the user when the user
 * has requested to see the information on a list of Services currently held by
 * the Piazza system; represented internally using the Service object.
 * 
 * @author Patrick.Doody
 * 
 */
@ApiModel(value = "ServiceResults")
public class ServiceListResponse extends PiazzaResponse {

	@ApiModelProperty(value = "The array of Service results", required = true)
	public List<Service> data;

	@ApiModelProperty(value = "The pagination metadata for this query", required = true)
	public Pagination pagination;

	public ServiceListResponse() {

	}

	public ServiceListResponse(List<Service> services) {
		this.data = services;
	}

	public ServiceListResponse(List<Service> services, Pagination pagination) {
		this(services);
		this.pagination = pagination;
	}

	/**
	 * Returns the list of Services held by this response object
	 * 
	 * @return The list
	 */
	public List<Service> getData() {
		return data;
	}

	public Pagination getPagination() {
		return pagination;
	}
}