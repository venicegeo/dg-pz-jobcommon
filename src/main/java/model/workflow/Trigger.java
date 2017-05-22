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
import model.request.PiazzaJobRequest;

/**
 * Created by sbortman on 6/2/16.
 */
// when the and'ed set of Conditions all are true, do Something
// Events are the results of the Conditions queries
// model.workflow.Job is the JobMessage to submit back to Pz
// TODO: some sort of mapping from the event info into the model.workflow.Job
// string
//
// type model.workflow.Trigger struct {
// Id Ident `json:"id"`
// Title string `json:"title" binding:"required"`
// model.workflow.Condition model.workflow.Condition `json:"condition"
// binding:"required"`
// model.workflow.Job model.workflow.Job `json:"job" binding:"required"`
// PercolationId Ident `json:"percolation_id"`
// }
/*
 * { "id": "987d6", "title": "my found-a-bad-telephone-number trigger",
 * "condition": { "eventtype_ids": ["17de4"], # array of EventType ids "query":
 * { "query" : { # the Elasticsearch query string "bool": { "must": [ { "match"
 * : { "Severity" : 4 } }, { "match" : { "Problem" : "us-bbox" } } ] } } },
 * "job": { "Task":
 * "{\"userName\": \"$userName\", \"jobType\": {\"type\": \"get\", \"jobId\": \"$jobId\"}}"
 * } } }
 */
@JsonInclude(Include.NON_NULL)
public class Trigger {

	@ApiModelProperty(value = "The unique Id for this Trigger. Not used in POST requests", required=true)
	public String triggerId;

	@ApiModelProperty(value = "The description of what this Trigger intends to accomplish", required = true)
	@NotNull
	@Size(min=1)
	public String name;

	@ApiModelProperty(value = "Elastic Search DSL Query 2.2, for details see https://www.elastic.co/guide/en/elasticsearch/reference/2.2/query-dsl.html", required = true)
	@NotNull
	public Map<String, Object> condition;
	
	@ApiModelProperty(value = "The EventTypeId of the EventType to run the query for", required = true)
	@NotNull
	public String eventTypeId;

	@ApiModelProperty(value = "The template of the Job to be executed when the conditions are met", required=true)
	@NotNull
	public PiazzaJobRequest job;

	@ApiModelProperty(value = "Supplied by system", required = true)
	public String percolationId;

	@ApiModelProperty(value = "Supplied by system", required=true)
	public String createdBy;
	
	@ApiModelProperty(value = "Supplied by system", required = true)
	@JsonIgnore
	public DateTime createdOn;
	
	@ApiModelProperty(value = "Field that will determine if the trigger fires", required = true)
	public Boolean enabled;
	
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