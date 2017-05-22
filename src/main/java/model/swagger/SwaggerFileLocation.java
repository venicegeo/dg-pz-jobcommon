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
import model.data.location.FolderShare;
import model.data.location.S3FileStore;

/**
 * This class is needed as a workaround to nonfunctional swagger annotations on
 * interface classes. It is intentionally abstract so as not to be instantiated.
 * 
 * @author Russell Orf
 *
 */
@ApiModel(value = "FileLocationInterface")
public abstract class SwaggerFileLocation {

	@ApiModelProperty("S3FileStore Implementation")
	public S3FileStore impl01;

	@ApiModelProperty("FolderShare Implementation")
	public FolderShare impl02;
}