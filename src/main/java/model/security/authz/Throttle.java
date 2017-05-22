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

/**
 * Model for defining throttling restrictions to be checked during Authorization decisions.
 * 
 * @author Russell Orf
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Throttle {

	public enum Component {
		job, trigger, query
	};

	private String component;

	private Integer numberOfInvocations;

	public Throttle() {

	}

	public Throttle(String component, Integer numberOfInvocations) {
		setComponent(component);
		setNumberOfInvocations(numberOfInvocations);
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		Component verifiedComponent = Component.valueOf(component);
		this.component = verifiedComponent.toString();
	}

	public Integer getNumberOfInvocations() {
		return numberOfInvocations;
	}

	public void setNumberOfInvocations(Integer numberOfInvocations) {
		this.numberOfInvocations = numberOfInvocations;
	}
}