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
package model.data;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import model.job.metadata.ResourceMetadata;
import model.job.metadata.SpatialMetadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a piece of data referened by the Piazza system, internally or
 * externally.
 * 
 * The format and type of the data is described by the DataType object, and has
 * interfaces for describing various formats and types.
 * 
 * Also contains metadata containers for describing the metadata of the data
 * object.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResource {

	@ApiModelProperty(value = "Provided by the System. The unique Id of the Data", required = false)
	public String dataId;

	@ApiModelProperty(value = "Polymorphically defines the information specific to this Data Resource, based on its format", required = true, dataType = "model.swagger.SwaggerDataType")
	@NotNull
	@JsonProperty(required = true)
	@Valid
	public DataType dataType;

	@ApiModelProperty(value = "Supplied by the System. Object of spatial metadata fields associated with a Resource. Used to generically specify the bounding box and the spatial reference of a dataset in its native projection system")
	public SpatialMetadata spatialMetadata;

	@ApiModelProperty(value = "Object of common metadata fields used to describe Data or Services within the Piazza system")
	public ResourceMetadata metadata;

	public DataResource() { //NOSONAR
		// Normal for empty constructor even with @NotNull fields
	}

	public ResourceMetadata getMetadata() {
		return metadata;
	}

	public DataType getDataType() {
		return dataType;
	}

	public SpatialMetadata getSpatialMetadata() {
		return spatialMetadata;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDataId() {
		return dataId;
	}
}