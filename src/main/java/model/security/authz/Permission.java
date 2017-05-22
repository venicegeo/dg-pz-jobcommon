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
package model.security.authz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

/**
 * Model for defining resource permissions
 * 
 * @author Russell Orf
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Permission {
	@ApiModelProperty(value = "The HTTP Method.", required = true)
	private String requestMethod;
	@ApiModelProperty(value = "The URL Endpoint.", required = true)
	private String uri;

	public Permission() {

	}

	public Permission(String requestMethod, String uri) {
		setRequestMethod(requestMethod);
		setUri(uri);
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String toString() {
		return String.format("Method %s : URI %s", requestMethod, uri);
	}

	/**
	 * Returns the Key name suitable for the Map of permissions in the ProfileTemplate Model
	 * 
	 * @return
	 */
	public String getKeyName() {
		return String.format("%s %s", requestMethod, uri);
	}
}