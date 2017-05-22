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

/**
 * Vector resource held by the Piazza PostGIS database.
 * 
 * @author Patrick.Doody
 * 
 */
public class PostGISDataType implements DataType {

	@ApiModelProperty(required = true, value = "The type of Data", allowableValues = "postgis")
	@JsonIgnore
	public String type;

	@ApiModelProperty(required = true, value = "The name of the database hosting the Data")
	@NotNull
	public String database;

	@ApiModelProperty(required = true, value = "The name of the database table holding the Data")
	@NotNull
	public String table;

	@ApiModelProperty(value = "The media type of the stored Data")
	public String mimeType;

	public PostGISDataType() { //NOSONAR
		// Normal for empty constructor even with @NotNull fields
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
}