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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sbortman on 6/2/16.
 */
// a notification, automatically created when an model.workflow.Trigger happens
//
// type model.workflow.Alert struct {
// Id Ident `json:"id"`
// TriggerId Ident `json:"trigger_id"`
// EventId Ident `json:"event_id"`
// }

/*
 * { "id": "8e6fa", "trigger_id": "987d6", "event_id": "53dac" }
 */

public class Alert {
	@ApiModelProperty(value = "The unique Id for this Alert", required = true)
	public String alertId;

	@ApiModelProperty(value = "The unique Id for the Trigger that was fired", required = true)
	@NotNull
	@Size(min=1)
	public String triggerId;

	@ApiModelProperty(value = "The unique Id for the Event that fired the Trigger", required = true)
	@NotNull
	@Size(min=1)
	public String eventId;

	@ApiModelProperty(value = "The unique Id for the Job that was submitted", required = true)
	public String jobId;
	
	@ApiModelProperty(value = "Supplied by system", required = true)
	@JsonIgnore
	public DateTime createdOn;
	
	@ApiModelProperty(value = "Supplied by system", required = true)
	public String createdBy;
	
	@ApiModelProperty(value = "Supplied by system", required = true)
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