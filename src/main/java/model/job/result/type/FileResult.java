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
package model.job.result.type;

import io.swagger.annotations.ApiModelProperty;
import model.job.result.ResultType;

/**
 * Represents a File that can be acquired by accessing a REST endpoint on the
 * Gateway. These files are prepared by the Accessor component.
 * 
 * @author Patrick.Doody
 * 
 */
public class FileResult implements ResultType {
	
	@ApiModelProperty(value = "The unique Id of the Data resource", required = true)	
	public String dataId;

	/**
	 * Default Constructor
	 */
	public FileResult() {

	}

	/**
	 * 
	 * @param url
	 *            The URL, off of the Gateway, that the file can be acquired
	 *            from.
	 * @param dataId
	 *            The Data Id of the resource.
	 */
	public FileResult(String dataId) {
		this.dataId = dataId;
	}

	public String getDataId() {
		return dataId;
	}

}