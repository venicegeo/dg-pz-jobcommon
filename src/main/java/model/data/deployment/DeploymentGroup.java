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
package model.data.deployment;

import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Basic container object a Deployment Group Id.
 * 
 * <p>
 * There is a limitation of GeoServer such that Layer Groups can only be created
 * if there is at least one valid layer populated at the time of creation.
 * However, for Piazza use-cases, we want to allow users to grab a unique Id for
 * a Layer Group before they have created any actual layers. To support this
 * goal, we use this DeploymentGroup model, as persisted in Mongo, to maintain a
 * connection between a GUID delivered to a user, and a Layer Group (once
 * created) in GeoServer. The user only ever receives the GUID as held by this
 * DeploymentGroup object. In that way, we can deliver some placeholder Id to
 * the user <b>before</b> the actual Layer Group is ready to be created on
 * GeoServer.
 * </p>
 * 
 * @author Patrick.Doody
 *
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeploymentGroup {
	@ApiModelProperty(value = "The unique identifier for this DeploymentGroup", required = true)
	public String deploymentGroupId;

	@ApiModelProperty(value = "The user who created this DeploymentGroup", required = true)
	public String createdBy;

	@ApiModelProperty(value = "Determines if the GIS Server instance has a corresponding Layer Group for this DeploymentGroup", required = true)
	public Boolean hasGisServerLayer;

	/**
	 * Creates a new Deployment Group.
	 */
	public DeploymentGroup() {

	}

	/**
	 * Creates a new Deployment Group.
	 * 
	 * @param id
	 *            The deployment group Id for this object
	 * @param createdBy
	 *            The user who created this deployment group.
	 */
	public DeploymentGroup(String id, String createdBy) {
		this.deploymentGroupId = id;
		this.createdBy = createdBy;
	}

	public Boolean getHasGisServerLayer() {
		return hasGisServerLayer;
	}

	public void setHasGisServerLayer(Boolean hasGisServerLayer) {
		this.hasGisServerLayer = hasGisServerLayer;
	}
}
