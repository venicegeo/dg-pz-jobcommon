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
package model.job.type;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import model.job.PiazzaJobType;

/**
 * Describes a user Request for Accessing data out of the Piazza core system. This could potentially be for data that is
 * held within the Piazza holdings, or data that is external to Piazza core which has had its Metadata previously
 * loaded.
 * 
 * The two current deployment types are 1) Hosted on a Piazza GeoServer instance, which can allow for Web Coverage
 * Service or Web Feature Service, depending on the type of data to be accessed. 2) Raw file download. This will place
 * the file in a accessible location that the user can access, such as an FTP or an S3 location.
 * 
 * Currently, a single resource Id is provided to denote the Resource in the Piazza holdings to access.
 * 
 * @author Patrick.Doody
 * 
 */
public class AccessJob implements PiazzaJobType {

	@ApiModelProperty(required = true, value = "The type of Job", allowableValues = "access")
	public String type;

	@JsonIgnore
	public static final String ACCESS_TYPE_GEOSERVER = "geoserver";

	@ApiModelProperty(required = true, value = "The Id of the subject Data")
	@NotNull
	@Size(min = 1)
	public String dataId;

	@ApiModelProperty(required = true, value = "The type of Deployment, such as hosted on a Piazza GIS Server instance. Currently, the only acceptable value for this is 'geoserver'", example = "geoserver")
	@NotNull
	@Size(min = 1)
	public String deploymentType;

	@ApiModelProperty(value = "Optional. If provided, the Deployment will also be added to the specified DeploymentGroup", required = false)
	public String deploymentGroupId;

	@ApiModelProperty(value = "Optional. The number of days that this Deployment will be needed for. Once the Deployment expires, it is subject to automatic resource cleanup on the Piazza GIS Server", required = false)
	public Integer durationDays;

	public AccessJob() { //NOSONAR
		// Normal for empty constructor even with @NotNull fields
	}

	public AccessJob(String dataId) { //NOSONAR
		this.dataId = dataId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	/**
	 * 
	 * @param deploymentType
	 *            Valid ACCESS_TYPE_ static string defined in this Job
	 */
	public void setDeploymentType(String deploymentType) {
		this.deploymentType = deploymentType;
	}

	public String getDeploymentType() {
		return deploymentType;
	}

	public String getDeploymentGroupId() {
		return deploymentGroupId;
	}

	public void setDeploymentGroupId(String deploymentGroupId) {
		this.deploymentGroupId = deploymentGroupId;
	}

	public Integer getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(Integer durationDays) {
		this.durationDays = durationDays;
	}
}