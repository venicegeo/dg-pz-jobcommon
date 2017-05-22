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

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Model for defining a user profile template that can be shared amongst users.
 * 
 * @author Russell Orf, Patrick Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileTemplate {

	private String profileTemplateId;

	/**
	 * The list of Throttles for the user that determine how many invocations of an action that user can perform within
	 * a period of time in Piazza.
	 */
	private List<Throttle> throttles;

	/**
	 * The map of endpoints that the user is able to access. The key is the Permission.getKeyName(), and the value is
	 * whether the user can execute that endpoint or not.
	 */
	private Map<String, Boolean> permissions;

	/**
	 * The list of third-party API keys that the user can have. The key is the name of the vendor, and the value is the
	 * API Key.
	 */
	private Map<String, String> thirdPartyKeys;

	public String getProfileTemplateId() {
		return profileTemplateId;
	}

	public void setProfileTemplateId(String profileTemplateId) {
		this.profileTemplateId = profileTemplateId;
	}

	public List<Throttle> getThrottles() {
		return throttles;
	}

	public void setThrottles(List<Throttle> throttles) {
		this.throttles = throttles;
	}

	public Map<String, Boolean> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, Boolean> permissions) {
		this.permissions = permissions;
	}

	public Map<String, String> getThirdPartyKeys() {
		return thirdPartyKeys;
	}

	public void setThirdPartyKeys(Map<String, String> thirdPartyKeys) {
		this.thirdPartyKeys = thirdPartyKeys;
	}
}