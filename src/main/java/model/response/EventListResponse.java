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
import model.workflow.Event;

@ApiModel(value = "EventResults")
public class EventListResponse extends PiazzaResponse {

	@ApiModelProperty(value = "The array of Event results", required = true)
	public List<Event> data;

	@ApiModelProperty(value = "The pagination metadata for this query", required = true)
	public Pagination pagination;

	public EventListResponse() {

	}

	public EventListResponse(List<Event> data) {
		this.data = data;
	}

	public EventListResponse(List<Event> data, Pagination pagination) {
		this(data);
		this.pagination = pagination;
	}

	/**
	 * Returns the list of Events held by this response object
	 * 
	 * @return The list
	 */
	public List<Event> getData() {
		return data;
	}

	public Pagination getPagination() {
		return pagination;
	}
}