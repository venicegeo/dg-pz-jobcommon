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
import model.data.DataResource;

/**
 * Represents the response that the Gateway returns to the user when the user has requested to see the information on a
 * list of Resources currently held by the Piazza system; represented internally using the DataResource object.
 * 
 * @author Patrick.Doody
 * 
 */
@ApiModel("DataResourceResults")
public class DataResourceListResponse extends PiazzaResponse {

	@ApiModelProperty(value = "The array of Data Resource results", required = true)
	public List<DataResource> data;

	@ApiModelProperty(value = "The pagination metadata for this query", required = true)
	public Pagination pagination;

	public DataResourceListResponse() {

	}

	public DataResourceListResponse(List<DataResource> dataResources) {
		this.data = dataResources;
	}

	public DataResourceListResponse(List<DataResource> dataResources, Pagination pagination) {
		this(dataResources);
		this.pagination = pagination;
	}

	/**
	 * Returns the list of Data Resources held by this response object
	 * 
	 * @return The list
	 */
	public List<DataResource> getData() {
		return data;
	}

	public Pagination getPagination() {
		return pagination;
	}
}