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
 * Represents a simple Text result that can be stored with a Job.
 * 
 * @author Patrick.Doody
 * 
 */
public class TextResult implements ResultType {
	
	@ApiModelProperty(value = "The text content of the Data", required = true)
	public Object text;

	public TextResult() {

	}

	public TextResult(Object text) {
		this.text = text;
	}

	public Object getText() {
		return text;
	}

	public void setText(Object text) {
		this.text = text;
	}
}