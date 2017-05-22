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
package model.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * This class is needed as a workaround to nonfunctional swagger annotations on
 * interface classes. It is intentionally abstract so as not to be instantiated.
 * 
 * @author Russell Orf
 *
 */
@ApiModel(value = "DataTypeInterface")
public abstract class SwaggerDataType {

	@ApiModelProperty("BodyDataType Implementation")
	public BodyDataType impl01;

	@ApiModelProperty("GeoJsonDataType Implementation")
	public GeoJsonDataType impl02;

	@ApiModelProperty("LiteralDataType Implementation")
	public LiteralDataType impl03;

	@ApiModelProperty("PointCloudDataType Implementation")
	public PointCloudDataType impl04;

	@ApiModelProperty("PostGISDataType Implementation")
	public PostGISDataType impl05;

	@ApiModelProperty("RasterDataType Implementation")
	public RasterDataType impl06;

	@ApiModelProperty("ShapefileDataType Implementation")
	public ShapefileDataType impl07;

	@ApiModelProperty("TextDataType Implementation")
	public TextDataType impl08;

	@ApiModelProperty("URLParameterDataType Implementation")
	public URLParameterDataType impl09;

	@ApiModelProperty("WfsDataType Implementation")
	public WfsDataType impl10;
}