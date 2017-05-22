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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import model.data.DataType;
import model.data.FileRepresentation;
import model.data.location.FileLocation;

/**
 * Represents a geojson format with accompanying file stored somewhere accessible to Piazza.
 * 
 * @author Sonny.Saniev
 * 
 */
@JsonInclude(Include.NON_NULL)
public class GeoJsonDataType implements DataType, FileRepresentation {

	@ApiModelProperty(required = true, value = "The type of Data", allowableValues = "geojson")
	@JsonIgnore
	public String type;

	@ApiModelProperty(value = "The name of the database table holding the Data")
	public String databaseTableName;

	@ApiModelProperty(value = "The location of the Data. Used to describe S3 stores, or folder shares, for where the Data is located", dataType = "model.swagger.SwaggerFileLocation")
	public FileLocation location;

	@ApiModelProperty(value = "The GeoJSON content of the Data")
	public String geoJsonContent;

	@ApiModelProperty(value = "The media type of the stored Data.  Refer to http://www.iana.org for standard values")
	public String mimeType;

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public GeoJsonDataType() {

	}

	/**
	 * Gets the location of the geojson.
	 * 
	 * @return geojson postgis location
	 */
	public FileLocation getLocation() {
		return location;
	}

	/**
	 * Sets the location of the geojson
	 * 
	 * @param location
	 *            The location object
	 */
	public void setLocation(FileLocation location) {
		this.location = location;
	}

	/**
	 * 
	 * @param databaseTableName
	 */
	public void setDatabaseTableName(String databaseTableName) {
		this.databaseTableName = databaseTableName;
	}

	/**
	 * Gets the geojson content string
	 * 
	 * @return
	 */
	public String getGeoJsonContent() {
		return geoJsonContent;
	}

	/**
	 * 
	 * @param geoJsonContent
	 */
	public void setGeoJsonContent(String geoJsonContent) {
		this.geoJsonContent = geoJsonContent;
	}
}