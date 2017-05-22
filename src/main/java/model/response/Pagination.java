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
 * Metadata describing pagination information when returning lists of data in
 * responses.
 * 
 * @author Patrick.Doody
 *
 */
public class Pagination {

	@ApiModelProperty(value = "The number of results that matched the query", required = true)
	public Integer count;

	@ApiModelProperty(value = "The current page returned", required = true)
	public Integer page;

	@ApiModelProperty(value = "The current number of results returned per page", required = true)
	public Integer perPage;

	@ApiModelProperty(value = "The field that the data is being sorted by", required = true)
	public String sortBy;

	@ApiModelProperty(value = "The order of the results. Either 'asc' or 'desc' for ascending or descending", required = true)
	public String order;

	public Pagination() {

	}

	public Pagination(Integer count, Integer page, Integer perPage, String sortBy, String order) {
		this.count = count;
		this.page = page;
		this.perPage = perPage;
		this.sortBy = sortBy;
		this.order = order;
	}

	public Integer getCount() {
		return count;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPerPage() {
		return perPage;
	}

	public String getSortBy() {
		return sortBy;
	}

	public String getOrder() {
		return order;
	}
}