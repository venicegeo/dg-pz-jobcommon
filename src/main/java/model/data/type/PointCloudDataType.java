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
package model.data.type;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import model.data.DataType;
import model.data.FileRepresentation;
import model.data.location.FileLocation;

/**
 * Represents a Point Cloud format with accompanying file stored somewhere
 * accessible to Piazza.
 * 
 * @author Sonny.Saniev
 * 
 */
public class PointCloudDataType implements DataType, FileRepresentation {

	@ApiModelProperty(required = true, value = "The type of Data", allowableValues = "pointcloud")
	@JsonIgnore
	public String type;

	@ApiModelProperty(required = true, value = "The location of the Data. Used to describe S3 stores, or folder shares, for where the Data is located", dataType = "model.swagger.SwaggerFileLocation")
	@NotNull
	public FileLocation location;

	@ApiModelProperty(value = "The media type of the stored Data")
	public String mimeType;

	public PointCloudDataType() { //NOSONAR
		// Normal for empty constructor even with @NotNull fields
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public FileLocation getLocation() {
		return location;
	}

	public void setLocation(FileLocation location) {
		this.location = location;
	}
}