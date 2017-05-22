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

import java.io.Serializable;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model for defining a user profile within the Piazza data store
 * 
 * @author Russell Orf
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private String distinguishedName;

	@JsonIgnore
	private DateTime createdOn;

	@JsonIgnore
	private DateTime lastUpdatedOn;

	private String createdBy;

	private String profileTemplateId;

	private String adminCode;
	
	private String dutyCode;
	
	private String country;
	
	private boolean isNPE;

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@JsonIgnore
	public DateTime getCreatedOn() {
		return createdOn;
	}

	@JsonIgnore
	public void setCreatedOn(final DateTime createdOn) {
		this.createdOn = createdOn;
	}

	@JsonIgnore
	public DateTime getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	@JsonIgnore
	public void setLastUpdatedOn(final DateTime lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	public String getProfileTemplateId() {
		return profileTemplateId;
	}

	public void setProfileTemplateId(final String profileTemplateId) {
		this.profileTemplateId = profileTemplateId;
	}

	@JsonProperty("createdOn")
	public String getCreatedOnString() {
		if (createdOn != null) {
			// Defaults to ISO8601
			return createdOn.toString();
		} else {
			return null;
		}
	}

	@JsonProperty("createdOn")
	public void setCreatedOnString(final String createdOn) {
		this.createdOn = new DateTime(createdOn);
	}

	@JsonProperty("lastUpdatedOn")
	public String getLastUpdatedOnString() {
		if (lastUpdatedOn != null) {
			// Defaults to ISO8601
			return lastUpdatedOn.toString();
		} else {
			return null;
		}
	}

	@JsonProperty("lastUpdatedOn")
	public void setLastUpdatedOnString(final String lastUpdatedOn) {
		this.lastUpdatedOn = new DateTime(lastUpdatedOn);
	}

	public String getDistinguishedName() {
		return distinguishedName;
	}

	public void setDistinguishedName(final String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(final String adminCode) {
		this.adminCode = adminCode;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(final String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public boolean isNPE() {
		return isNPE;
	}

	public void setNPE(final boolean isNPE) {
		this.isNPE = isNPE;
	}

	@Override
	public String toString() {
		return "UserProfile [username=" + username + ", distinguishedName=" + distinguishedName + ", createdOn="
				+ createdOn + ", lastUpdatedOn=" + lastUpdatedOn + ", createdBy=" + createdBy + ", profileTemplateId="
				+ profileTemplateId + ", adminCode=" + adminCode + ", dutyCode=" + dutyCode + ", country=" + country
				+ ", isNPE=" + isNPE + "]";
	}
}