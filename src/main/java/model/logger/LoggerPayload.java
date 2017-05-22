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
package model.logger;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Defines logger payload for syslog RFC 5424 standard to be used in ELK-Stack logging/audit/metrics.
 * 
 * @author Sonny.Saniev
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoggerPayload {

	public int facility = 1;
	@JsonIgnore
	public Severity severity;
	public int version = 1;
	@JsonIgnore
	public DateTime timestamp;
	public String hostName;
	public String application;
	public String process;
	public String messageId;
	private AuditElement auditData;
	private MetricElement metricData;
	public String message;

	public LoggerPayload() {

	}

	public int getFacility() {
		return facility;
	}

	public void setFacility(int facility) {
		this.facility = facility;
	}

	@JsonIgnore
	public Severity getSeverity() {
		return severity;
	}

	@JsonIgnore
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@JsonIgnore
	public DateTime getTimestamp() {
		return timestamp;
	}

	@JsonIgnore
	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}

	@JsonProperty("timeStamp")
	public String getCreatedOnString() {
		if (timestamp != null) {
			// Defaults to ISO8601
			return timestamp.toString();
		} else {
			return null;
		}
	}

	@JsonProperty("timeStamp")
	public void setCreatedOnString(String createdOn) {
		this.timestamp = new DateTime(createdOn);
	}

	@JsonProperty("severity")
	public int getSeverityInt() {
		return this.severity.value();
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public AuditElement getAuditData() {
		return auditData;
	}

	public void setAuditData(AuditElement auditData) {
		this.auditData = auditData;
	}

	public MetricElement getMetricData() {
		return metricData;
	}

	public void setMetricData(MetricElement metricData) {
		this.metricData = metricData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder obj = new StringBuilder();
		String auditDataStr = getAuditData() != null ? getAuditData().toString() : "";
		String metricDataStr = getMetricData() != null ? getMetricData().toString() : "";
		obj.append("facility: " + getFacility() + "\n");
		obj.append("severity: " + getSeverity() + "\n");
		obj.append("version : " + getVersion() + "\n");
		obj.append("timeStamp: " + getTimestamp() + "\n");
		obj.append("hostName : " + getHostName() + "\n");
		obj.append("application : " + getApplication() + "\n");
		obj.append("process : " + getProcess() + "\n");
		obj.append("messageId : " + getMessageId() + "\n");
		obj.append("auditData : " + auditDataStr + "\n");
		obj.append("metricData : " + metricDataStr + "\n");
		obj.append("message : " + getMessage() + "\n");

		return obj.toString();
	}
}