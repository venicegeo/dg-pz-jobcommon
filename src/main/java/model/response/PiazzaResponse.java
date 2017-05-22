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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a Standard Response from the Piazza Gateway. Responses contain, at
 * bare minimum, the Id of the Job they correspond with.
 * 
 * Each response typically has, at bare minimum, the Job Id for which the
 * request related to. This includes requests such as fetching status, updating
 * metadata, or executing a service. Each request to the Piazza system will have
 * a Job Id generated for it, that can be tracked.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ 
		@Type(value = AlertListResponse.class, name = "alert-list"),
		@Type(value = AuthResponse.class, name = "auth"),
		@Type(value = DataResourceListResponse.class, name = "data-list"),
		@Type(value = DataResourceResponse.class, name = "data"),
		@Type(value = DeploymentListResponse.class, name = "deployment-list"),
		@Type(value = DeploymentResponse.class, name = "deployment"),
		@Type(value = DeploymentGroupResponse.class, name = "deployment-group"),
		@Type(value = ErrorResponse.class, name = "error"),
		@Type(value = EventListResponse.class, name = "event-list"),
		@Type(value = EventResponse.class, name = "event"),
		@Type(value = EventTypeListResponse.class, name = "eventtype-list"),
		@Type(value = EventTypeResponse.class, name = "eventtype"),
		@Type(value = JobErrorResponse.class, name = "job-error"),
		@Type(value = UserProfileResponse.class, name = "user-profile"), 
		@Type(value = JobResponse.class, name = "job"),
		@Type(value = JobStatusResponse.class, name = "status"),
		@Type(value = ServiceIdResponse.class, name = "service-id"),
		@Type(value = ServiceJobResponse.class, name = "service-job"),
		@Type(value = ServiceListResponse.class, name = "service-list"),
		@Type(value = ServiceResponse.class, name = "service"), 
		@Type(value = SuccessResponse.class, name = "success"),
		@Type(value = TriggerListResponse.class, name = "trigger-list"),
		@Type(value = TriggerResponse.class, name = "trigger"),
		@Type(value = UUIDResponse.class, name = "uuid")})
@JsonInclude(Include.NON_NULL)
public class PiazzaResponse {

	public PiazzaResponse() {

	}
}