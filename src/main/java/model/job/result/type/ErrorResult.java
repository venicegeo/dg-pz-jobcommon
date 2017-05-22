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
package model.job.result.type;

import io.swagger.annotations.ApiModelProperty;
import model.job.result.ResultType;

/**
 * The result of a Job that has encountered an error. This contains information
 * for the error.
 * 
 * @author Patrick.Doody
 * 
 */
public class ErrorResult implements ResultType {
	
	@ApiModelProperty(value = "A description of the error describing the failure", required = true)
	public Object message;
	
	@ApiModelProperty(value = "Specific details regarding the error")
	public String details;
	
	@ApiModelProperty(value = "Specific statusCode for errors if available")
	public Integer statusCode;


	public ErrorResult() {

	}

	public ErrorResult(String message, String details) {
		this.message = message;
		this.details = details;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
}