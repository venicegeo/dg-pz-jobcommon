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
package model.resource;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Serves as the data model for the parameters associated registering with
 * pz-discover
 * 
 * @author mlynum
 * @date Dec 16, 2015
 *
 **/
public class RegisterService {

	private String name;

	@JsonProperty("data")
	private Map<String, String> data;

	public RegisterService(String name, Map<String, String> data) {
		setData(data);
		setName(name);
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}