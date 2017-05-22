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

/**
 * A Response containing an Id regarding a Piazza Service.
 * 
 * @author Patrick.Doody
 *
 */
public class ServiceIdResponse extends PiazzaResponse {

	@ApiModelProperty(value = "The Object containing the Service Id", required = true)
	public ServiceIdData data = new ServiceIdData();

	public ServiceIdResponse(String serviceId) {
		data.setServiceId(serviceId);
	}

	public ServiceIdResponse() {
	}

	/**
	 * Used to wrap the Service Id in an annotatable class.
	 */
	public class ServiceIdData {
		@ApiModelProperty(value = "The Id of the Service referenced in the request", required = true)
		private String serviceId;

		public String getServiceId() {
			return this.serviceId;
		}

		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}
	}
}