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
package model.request;

import io.swagger.annotations.ApiModelProperty;
import model.job.PiazzaJobType;

/**
 * The core format for a Job request into the Piazza system. Every Job request
 * contains an API Key. This is used to authenticate the user and ensure they
 * are authorized to execute the specified Job.
 * 
 * The PiazzaJobType is the interface container that is used to define the
 * format for every Job that the Piazza Gateway is capable of handling.
 * 
 * @author Patrick.Doody
 * 
 */
public class PiazzaJobRequest {
	
	@ApiModelProperty(value = "The user that submitted the job")
	public String createdBy;
	
	@ApiModelProperty(value = "The type of Job submitted", dataType = "model.swagger.SwaggerJobType")
	public PiazzaJobType jobType;
}