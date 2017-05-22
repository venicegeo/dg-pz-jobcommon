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

import model.data.DataResource;
import model.job.PiazzaJobType;

/**
 * Represents the JSON Model for ingesting metadata into Piazza for subsequent
 * query, e.g. used in conjunction/within the flow of an InjestJob The contents
 * of this payload includes DataResource serving as complete metadata record to
 * locate Piazza data access key(s) from Search service.
 * 
 * @author Christopher Smith
 * 
 */
public class SearchMetadataIngestJob implements PiazzaJobType {
	
	@NotNull
	public DataResource data;


	public SearchMetadataIngestJob() { //NOSONAR
		// Normal for empty constructor even with @NotNull fields
	}
	public void setData(DataResource data) {
		this.data = data;
	}
	
	public DataResource getData() {
		return data;
	}
	
}