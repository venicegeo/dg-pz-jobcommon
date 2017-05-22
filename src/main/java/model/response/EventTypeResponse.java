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
import model.workflow.EventType;

/**
 * A Response containing information regarding a Piazza EventType.
 * 
 * @author Russell.Orf
 *
 */
public class EventTypeResponse extends PiazzaResponse {

	@ApiModelProperty(value = "Object containing information regarding a Piazza EventType", required = true)
	public EventType data;

	public EventTypeResponse() {

	}

	public EventTypeResponse(EventType eventType) {
		this.data = eventType;
	}
}