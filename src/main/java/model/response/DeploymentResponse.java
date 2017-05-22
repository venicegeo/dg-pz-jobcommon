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
import model.data.deployment.Deployment;

/**
 * A Response containing information regarding a Piazza Deployment.
 * 
 * @author Patrick.Doody
 *
 */
public class DeploymentResponse extends PiazzaResponse {

	@ApiModelProperty(value = "Contains the Deployment and the Expiration Date", required = true)
	public DeploymentData data = new DeploymentData();

	public DeploymentResponse() {

	}

	public DeploymentResponse(Deployment deployment, String expiresOn) {
		this.data.setDeployment(deployment);
		this.data.setExpiresOn(expiresOn);
	}

	/**
	 * Used to wrap the Deployment and Expiration date into the data tag.
	 */
	public class DeploymentData {
		@ApiModelProperty(value = "The Deployment metadata, including GIS Server URL to access the data", required = true)
		public Deployment deployment;
		@ApiModelProperty(value = "If one exists, the date at which this Deployment may be subject to automatic resource cleanup on the Piazza GIS Server", required = true)
		public String expiresOn;

		public DeploymentData() {

		}

		public Deployment getDeployment() {
			return deployment;
		}

		public void setDeployment(Deployment deployment) {
			this.deployment = deployment;
		}

		public String getExpiresOn() {
			return expiresOn;
		}

		public void setExpiresOn(String expiresOn) {
			this.expiresOn = expiresOn;
		}

	}
}