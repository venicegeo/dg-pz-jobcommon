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
package model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import model.security.authz.UserProfile;

/**
 * Represents the Response object for an Authorization/Authentication check.
 * 
 * @author Patrick.Doody
 *
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponse extends PiazzaResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "True if the auth is granted, false if not.", required = true)
	public Boolean isAuthSuccess;
	@ApiModelProperty(value = "The user profile associated with the Auth check.", required = false)
	public String details;
	@ApiModelProperty(value = "Details on success or failure of an auth check.", required = false)
	public UserProfile userProfile;

	public AuthResponse() {
	}

	public AuthResponse(Boolean isAuthSuccess) {
		this.isAuthSuccess = isAuthSuccess;
	}

	public AuthResponse(Boolean isAuthSuccess, String details) {
		this(isAuthSuccess);
		this.details = details;
	}

	public AuthResponse(Boolean isAuthSuccess, UserProfile userProfile) {
		this(isAuthSuccess);
		this.userProfile = userProfile;
	}

	public AuthResponse(Boolean isAuthSuccess, UserProfile userProfile, String details) {
		this(isAuthSuccess, details);
		this.userProfile = userProfile;
	}

	public Boolean getIsAuthSuccess() {
		return isAuthSuccess;
	}

	public Object getDetails() {
		return details;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}
}
