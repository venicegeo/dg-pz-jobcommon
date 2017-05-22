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
package model.workflow;

import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sbortman on 6/2/16.
 */

// type model.workflow.EventType struct {
// Id Ident `json:"id"`
// Name string `json:"name" binding:"required"`
// Mapping map[string]elasticsearch.MappingElementTypeName `json:"mapping"
// binding:"required"`
// }

/*
 * { "id": "17de4", "name": "USDataFound", # short, id-like string "mapping": {
 * "ItemId": "string", # the uuid of the bad data "Severity": "integer", # level
 * of offense, 1..5 "Problem": "string" # nature of the issue, e.g. US bbox, US
 * phone number, etc } }
 */
@JsonInclude(Include.NON_NULL)
public class EventType {

	@ApiModelProperty(value = "The unique Id of this EventType. Not used in POST requests", required = true)
	public String eventTypeId;

	@ApiModelProperty(value = "A human-readable name for this EventType", required = true)
	@NotNull
	@Size(min=1)
	public String name;

	@ApiModelProperty(value = "The map of key-value pairs that define the properties of this EventType. The key is the name of the property, and the value is the type of that property. Valid types are 'string', 'boolean', 'integer', 'double' 'date', 'float', 'short', 'long' and 'byte''")
	@NotNull
	public Map<String, Object> mapping;

	@ApiModelProperty(value = "Supplied by system", required = true)
	public String createdBy;
	
	@ApiModelProperty(value = "Supplied by system", required = true)
	@JsonIgnore
	public DateTime createdOn;

	@JsonProperty("createdOn")
	public String getCreatedOnString() {
		// Defaults to ISO8601
		if( createdOn != null ) {
			return createdOn.toString();
		}
		return null;
	}

	@JsonProperty("createdOn")
	public void setCreatedOnString(String createdOn) {
		if( createdOn != null) {
			this.createdOn = new DateTime(createdOn);
		}
	}	
}