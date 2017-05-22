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

import io.swagger.annotations.ApiModelProperty;
import model.data.DataType;
import model.data.FileRepresentation;
import model.data.location.FileLocation;

/**
 * Shapefile resource with accompanying files on disk. Shapefiles are typically
 * sent to the Gateway compressed into a single .zip, so as to define the user
 * only send a single file through the POST message. Upon receiving the message,
 * the appropriate components will inflate this zip file as necessary.
 * 
 * @author Patrick.Doody
 * 
 */
public class ShapefileDataType implements DataType, FileRepresentation {

	@ApiModelProperty(required = true, value = "The type of Data", allowableValues = "shapefile")
	@JsonIgnore
	public String type;

	@ApiModelProperty(value = "The PostGIS table name that contains the Shapefile Data")
	public String databaseTableName;

	@ApiModelProperty(value = "The location of the Data. Used to describe S3 stores, or folder shares, for where the Data is located", dataType = "model.swagger.SwaggerFileLocation")
	public FileLocation location;

	@ApiModelProperty(value = "The media type of the stored Data")
	public String mimeType;

	public ShapefileDataType() {

	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * Gets the location of the raw, zipped shapefile that was sent to the
	 * Gateway upon Ingest.
	 * 
	 * @return Shapefile location
	 */
	public FileLocation getLocation() {
		return location;
	}

	/**
	 * Sets the location of the raw, zipped shapefile that was sent to the
	 * Gateway upon Ingest.
	 * 
	 * @param location
	 *            The current shapefile location
	 */
	public void setLocation(FileLocation location) {
		this.location = location;
	}

	/**
	 * Gets the Piazza PostGIS table name that this Shapefile was inserted into
	 * 
	 * Shapefiles are more easily managed as PostGIS database tables, than as
	 * flat shapefiles. Because of this, all Shapefiles that are ingested into
	 * Piazza are then inserted into the internal Piazza PostGIS database. From
	 * this point on, they are then referenced via this table.
	 * 
	 * @return Table name for this Resource
	 */
	public String getDatabaseTableName() {
		return databaseTableName;
	}

	/**
	 * Sets the table name for the Piazza PostGIS database that holds this
	 * dataset.
	 * 
	 * Shapefiles are more easily managed as PostGIS database tables, than as
	 * flat shapefiles. Because of this, all Shapefiles that are ingested into
	 * Piazza are then inserted into the internal Piazza PostGIS database. From
	 * this point on, they are then referenced via this table.
	 * 
	 * @param tableName
	 *            The Table name in the database for this dataset.
	 */
	public void setDatabaseTableName(String databaseTableName) {
		this.databaseTableName = databaseTableName;
	}
}