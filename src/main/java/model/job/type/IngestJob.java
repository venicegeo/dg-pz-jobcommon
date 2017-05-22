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
import model.data.DataResource;
import model.job.PiazzaJobType;

/**
 * Represents the JSON Model for ingesting data into Piazza via the Ingest/Load
 * component. The contents of this payload should include all information that
 * Piazza needs in order to fetch the data, inspect for metadata, and to copy
 * into Piazza data holdings if necessary.
 * 
 * @author Patrick.Doody
 * 
 */
@ApiModel("LoadJob")
public class IngestJob implements PiazzaJobType {

	@ApiModelProperty(required = true, value = "The type of Job", allowableValues = "ingest")
	public String type;

	@ApiModelProperty(required = true, value = "The Description of the Data being loaded, including metadata, and the path to the Data")
	@NotNull
	@Valid
	public DataResource data;


	@ApiModelProperty(required = true, value = "Dictates if Piazza should internally host the Data or not. If set to true, then the Data will be stored in Piazza's Data holdings. If false, then Piazza will point to the location of the Data, but will not store the Data internally")
	@NotNull
	public Boolean host;

	public IngestJob() { //NOSONAR
		// Normal for empty constructor even with @NotNull fields
	}
	
	public void setData(DataResource data) {
		this.data = data;
	}

	public DataResource getData() {
		return data;
	}

	/**
	 * Determines if the Data should be copied into Piazza data holdings and
	 * stored internally. True if the data should be hosted in Piazza, false if
	 * the data should remain where it currently is.
	 * 
	 * If data is not to be hosted within Piazza, then direct file access may be
	 * required to be able to inspect metadata, and to access the file upon user
	 * request from within other Piazza Jobs.
	 * 
	 * @return True if the Data should be hosted in Piazza, false if not.
	 */
	public Boolean getHost() {
		return host;
	}
}