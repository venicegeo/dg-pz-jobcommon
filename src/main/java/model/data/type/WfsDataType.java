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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import model.data.DataType;

/**
 * OGC Web Feature Service (WFS) Resource.
 * 
 * @author Patrick.Doody
 * 
 */
public class WfsDataType implements DataType {

	@ApiModelProperty(required = true, value = "The type of Data", allowableValues = "wfs")
	@JsonIgnore
	public String type;

	@ApiModelProperty(required = true, value = "HTTP web address to the WFS endpoint")
	@NotNull
	@Size(min=1)
	public String url;

	@ApiModelProperty(required = true, value = "The current version of the Data")
	@NotNull
	@Size(min=1)
	public String version;

	@ApiModelProperty(required = true, value = "The type of geospatial feature represented in the Data")
	@NotNull
	@Size(min=1)
	public String featureType;

	@ApiModelProperty(value = "The media type of the stored Data")
	public String mimeType;

	public WfsDataType() { //NOSONAR
		// Normal for empty constructor even with @NotNull fields
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getUrl() {
		return url;
	}

	public String getVersion() {
		return version;
	}

	public String getFeatureType() {
		return featureType;
	}
}