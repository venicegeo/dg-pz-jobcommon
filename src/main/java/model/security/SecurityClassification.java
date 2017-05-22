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
package model.security;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * Specifies security classification
 * 
 * @author Bkrasner
 * 
 */
public class SecurityClassification {

	@ApiModelProperty(value = "The security classification marking for the Data", required = true)
	@NotNull
	private String classification;

	public SecurityClassification() { //NOSONAR
		// Normal for empty constructor even with @NotNull fields
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public SecurityClassification(String classification) { //NOSONAR
		super();
		this.classification = classification;
	}
}