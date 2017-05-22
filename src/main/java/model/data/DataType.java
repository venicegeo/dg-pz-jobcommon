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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import model.data.type.BodyDataType;
import model.data.type.GeoJsonDataType;
import model.data.type.LiteralDataType;
import model.data.type.PointCloudDataType;
import model.data.type.PostGISDataType;
import model.data.type.RasterDataType;
import model.data.type.ShapefileDataType;
import model.data.type.TextDataType;
import model.data.type.URLParameterDataType;
import model.data.type.WfsDataType;

/**
 * Interface for Spatial Resources that can be ingested and stored in Piazza.
 * For different types of spatial resources that can be represented, such as
 * Shapefiles, WFS, etc; there must be an accompanying class that implements
 * this interface and references type information for deserialization in
 * the @JsonTypeInfo annotation.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ 
		@Type(value = BodyDataType.class, name = "body"), 
		@Type(value = GeoJsonDataType.class, name = "geojson"),		
		@Type(value = LiteralDataType.class, name = "literal"),
		@Type(value = PointCloudDataType.class, name = "pointcloud"),
		@Type(value = PostGISDataType.class, name = "postgis"), 
		@Type(value = RasterDataType.class, name = "raster"),
		@Type(value = ShapefileDataType.class, name = "shapefile"),
		@Type(value = TextDataType.class, name = "text"),		
		@Type(value = URLParameterDataType.class, name = "urlparameter"),
		@Type(value = WfsDataType.class, name = "wfs")})
public interface DataType {

	public String getMimeType();
}