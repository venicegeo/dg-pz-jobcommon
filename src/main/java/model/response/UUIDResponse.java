/**
 * Copyright 2016, RadiantBlue Technologies, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * re
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package model.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * Represents the UUID returned for authentication purposes with pz-security.
 * 
 * @author Russell.Orf
 * 
 */
public class UUIDResponse extends PiazzaResponse {

	@ApiModelProperty(value = "Contains the Id for the requested user", required = true)
	public String uuid;
	
	public UUIDResponse() {
		
	}
	
	public UUIDResponse(String uuid) {
		setUuid(uuid);
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}