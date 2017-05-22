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
package model.request;

/**
 * Model used in interactions with the pz-logger REST service. This encapsulates the Logs in the expected format that
 * the pz-logger expects.
 * 
 * @author Patrick.Doody
 * 
 */
public class LogRequest {

	private String service;
	private String address;
	private String createdOn;
	private String message;
	private String severity;

	public LogRequest() {
	}

	public LogRequest(String service, String address, String createdOn, String message, String severity) {
		setService(service);
		setAddress(address);
		setCreatedOn(createdOn);
		setMessage(message);
		setSeverity(severity);
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String toPrettyString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Service : " + getService() + "\n");
		sb.append("Address : " + getAddress() + "\n");
		sb.append("Time Stamp : " + getCreatedOn() + "\n");
		sb.append("Message : " + getMessage() + "\n");
		sb.append("Severity : " + getSeverity() + "\n");

		return sb.toString();
	}
}