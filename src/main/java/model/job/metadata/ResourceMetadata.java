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
package model.job.metadata;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import model.resource.NumericKeyValue;
import model.resource.TextKeyValue;
import model.security.SecurityClassification;

/**
 * Common Meta-data fields used to describe Data or Resources (e.g. Services, etc.)  within the Piazza
 * system. This object should be generic enough to be used to attach common
 * metadata fields to any object stored in the Piazza system.
 * 
 * @author mlynum & Patrick.Doody
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceMetadata {


	
	@ApiModelProperty(value = "Human-readable name of the resource")
	public String name;

	@ApiModelProperty(value = "Human-readable description of the resource")
	public String description;

	@ApiModelProperty(value = "In case of ComplexData, Format defines the allowed input representation")
	public String format;

	@ApiModelProperty(value = "Quality level of the resource (Production, Development)")
	public String qos;

	private final static Logger LOGGER = LoggerFactory.getLogger(ResourceMetadata.class);
	
	// Values indicating the status of the resource
	public enum STATUS_TYPE {
		ONLINE, OFFLINE, DEGRADED, FAILED
	};
	
	@ApiModelProperty(value = "Status of the resource")
	private STATUS_TYPE statusType;
	

	@ApiModelProperty(allowableValues = "status_type", value = "Describes the status of the resource(ONLINE, FAILED (The resoure has failed), DEGRADED (The resource is not performing well), OFFLINE (the resource has been turned off)")
	public String availability;

	@ApiModelProperty(value = "Keywords describing the resource")
	public String tags;

	@ApiModelProperty(value = "Classification of the resource", required = true)
	@NotNull
	public SecurityClassification classType;

	@ApiModelProperty(value = "The date the service will be terminated")
	@JsonIgnore
	public DateTime expiresOn;

	@ApiModelProperty(value = "Indication on whether a client certificate required to access thie resource.  Could be a user certificate or computer certificate")
	public Boolean clientCertRequired;

	@ApiModelProperty(value = "Indication on whether credentials are required to access this resource")
	public Boolean credentialsRequired;

	@ApiModelProperty(value = "Indication on whether preauthorization is required before using the resource?  (e.g. do users need to sign a user agreement, etc.)")
	public Boolean preAuthRequired;

	@ApiModelProperty(value = "A list of networks names where this resource is available")
	public String networkAvailable;

	@ApiModelProperty(value = "Name, e-mail and phone number of point of contact (String concatenated together)")
	public String contacts;

	@ApiModelProperty(value = "Human readable reason on the status of the resource")
	public String reason;

	@ApiModelProperty(value = "The current version of the resource")
	public String version;

	@ApiModelProperty(value = "Username of the individual submitting the resource")
	public String createdBy;


	@ApiModelProperty(value = "The date and time of data submission to Piazza")
	public String createdOn;
	
	@ApiModelProperty(value = "Provided by the system. If available, the Id of the Job that resulted in the creation of this resource.", required = false)
	public String createdByJobId;


	@ApiModelProperty(value = "A generic Map of String:String (key:value) pairs with additional metadata")
	public Map<String, String> metadata;

	/*
	 * Need the ability to accommodate arbitrary key/value pairs
	 */
	@ApiModelProperty(value = "Allows for the optional specification of user-defined key-value pairs of numeric values", required = false)
	@Field(type = FieldType.Nested)
	private List<NumericKeyValue> numericKeyValueList;

	@ApiModelProperty(value = "Allows for the optional specification ofu ser-defined key-value pairs for string values", required = false)
	@Field(type = FieldType.Nested)
	private List<TextKeyValue> textKeyValueList;

	public List<NumericKeyValue> getNumericKeyValueList() {
		return numericKeyValueList;
	}

	public void setNumericKeyValueList(List<NumericKeyValue> numericKeyValueList) {
		this.numericKeyValueList = numericKeyValueList;
	}

	public List<TextKeyValue> getTextKeyValueList() {
		return textKeyValueList;
	}

	public void setTextKeyValueList(List<TextKeyValue> textKeyValueList) {
		this.textKeyValueList = textKeyValueList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getQos() {
		return qos;
	}

	public void setQos(String qos) {
		this.qos = qos;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public SecurityClassification getClassType() {
		return classType;
	}

	public void setClassType(SecurityClassification classType) {
		this.classType = classType;
	}

	public Boolean getClientCertRequired() {
		return clientCertRequired;
	}

	public void setClientCertRequired(Boolean clientCertRequired) {
		this.clientCertRequired = clientCertRequired;
	}

	public Boolean getCredentialsRequired() {
		return credentialsRequired;
	}

	public void setCredentialsRequired(Boolean credentialsRequired) {
		this.credentialsRequired = credentialsRequired;
	}

	public Boolean getPreAuthRequired() {
		return preAuthRequired;
	}

	public void setPreAuthRequired(Boolean preAuthRequired) {
		this.preAuthRequired = preAuthRequired;
	}

	public String getNetworkAvailable() {
		return networkAvailable;
	}

	public void setNetworkAvailable(String networkAvailable) {
		this.networkAvailable = networkAvailable;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getVersion() {
		return version;
	}
	
	public STATUS_TYPE getStatusType() {
		return statusType;
	}

	public void setStatusType(STATUS_TYPE statusType) {
		this.statusType = statusType;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public DateTime getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(DateTime expiresOn) {
		this.expiresOn = expiresOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	/**
	 * Merges the properties of another ResourceMetadata into this one.
	 * 
	 * <p>
	 * If the other ResourceMetadata specifies properties, then those properties
	 * take precedence. If the other ResourceMetadata contains a null value for
	 * a property that exists in this object, then this object's property is
	 * unchanged unless the overwriteNull flag is set to true.
	 * </p>
	 * 
	 * @param other
	 *            The ResourceMetadata properties to merge
	 * @param overwriteNull
	 *            True if null values in the other ResourceMetadata should
	 *            overwrite values in this object. False if not.
	 */
	public void merge(ResourceMetadata other, boolean overwriteNull) {
		Method[] methods = this.getClass().getMethods();

		for (Method fromMethod : methods) {
			if (fromMethod.getDeclaringClass().equals(this.getClass()) && fromMethod.getName().startsWith("get")) {

				String fromName = fromMethod.getName();
				String toName = fromName.replace("get", "set");

				try {
					Method toMethod = this.getClass().getMethod(toName, fromMethod.getReturnType());
					Object value = fromMethod.invoke(other, (Object[]) null);
					if ((value != null) || (overwriteNull == true)) {
						toMethod.invoke(this, value);
					}
				} catch (Exception exception) {
					LOGGER.error("Error merging the properties of ResourceMetadatas", exception);
				}
			}
		}
	}

	public String getCreatedByJobId() {
		return createdByJobId;
	}

	public void setCreatedByJobId(String createdByJobId) {
		this.createdByJobId = createdByJobId;
	}
}