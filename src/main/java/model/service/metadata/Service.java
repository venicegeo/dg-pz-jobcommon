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
package model.service.metadata;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import model.job.metadata.ResourceMetadata;

/**
 * Class which represents a service registered and managed by Piazza
 * 
 * @author mlynum
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Service {

	private final static Logger LOGGER = LoggerFactory.getLogger(Service.class);

	public enum METHOD_TYPE {
		GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
	};

	@ApiModelProperty(value = "HTTP method types")
	private METHOD_TYPE methodType;

	@ApiModelProperty(required = false, value = "Provided by the System. The unique Id of the Service")
	private String serviceId;

	@ApiModelProperty(required = true, value = "The URL to the user Service to be executed. This is *optional* for Task Managed services, and rqeuired for all other types.")
	private String url;

	@ApiModelProperty(required = true, value = "URL to the schema or contract to interface with the Service, such as a Swagger file, or documentation")
	@NotNull
	@Size(min = 1)
	private String contractUrl;

	@ApiModelProperty(required = true, value = "The HTTP Method used to invoke this User Service. For Asynchronous Services, this applies only to the Execution endpoint.", dataType = "string", allowableValues = "GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT")
	@NotNull
	@Pattern(regexp = "GET|HEAD|POST|PUT|DELETE|TRACE|CONNECT", flags = Pattern.Flag.CASE_INSENSITIVE)
	@Size(min = 1)
	private String method;

	@ApiModelProperty(value = "The timeout in seconds Piazza waits for a response (defaults to 600 seconds)")
	private Long timeout;

	@ApiModelProperty(value = "The frequency in which Piazza sends heartbeat requests to check on the health of the Service  (defaults to 120 seconds)")
	private Long heartbeat;

	@ApiModelProperty(required = false, value = "Determines if this User Service is a long-running service that supports asynchronous endpoints, or if this is a synchronous service that returns results directly. If `isTaskManaged` is set to `true`, then this value is ignored.")
	private Boolean isAsynchronous;

	@ApiModelProperty(required = false, value = "Determines if this User Service uses Piazza Task Management as a Service functionality. If this value is set to true, then the `isSynchronous` value is ignored.")
	private Boolean isTaskManaged;

	@ApiModelProperty(required = false, value = "The list of Piazza user names (not API Keys) who are able to administrate this services Task Management capabilities. By default, the user registering the service will have these privelages. This user list is only applicable if `isTaskManaged` is set to `true`.")
	private List<String> taskAdministrators;

	@ApiModelProperty(value = "Object of common metadata fields used to describe Data or Services within the Piazza system")
	@NotNull
	@Valid
	private ResourceMetadata resourceMetadata;

	public Long getHeartbeat() {
		return heartbeat;
	}

	public void setHeartbeat(Long heartbeat) {
		this.heartbeat = heartbeat;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Long getTimeout() {
		return timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	public ResourceMetadata getResourceMetadata() {
		return resourceMetadata;
	}

	public void setResourceMetadata(ResourceMetadata resourceMetadata) {
		this.resourceMetadata = resourceMetadata;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContractUrl() {
		return contractUrl;
	}

	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
	}

	public METHOD_TYPE getMethodType() {
		return methodType;
	}

	public void setMethodType(METHOD_TYPE methodType) {
		this.methodType = methodType;
	}

	public Boolean getIsAsynchronous() {
		return isAsynchronous;
	}

	public void setIsAsynchronous(Boolean isAsynchronous) {
		this.isAsynchronous = isAsynchronous;
	}

	public Boolean getIsTaskManaged() {
		return isTaskManaged;
	}

	public void setIsTaskManaged(Boolean isTaskManaged) {
		this.isTaskManaged = isTaskManaged;
	}

	public List<String> getTaskAdministrators() {
		return taskAdministrators;
	}

	public void setTaskAdministrators(List<String> taskAdministrators) {
		this.taskAdministrators = taskAdministrators;
	}

	/**
	 * Merges the properties of another Service into this one.
	 * 
	 * <p>
	 * If the other Service specifies properties, then those properties take precedence. If the other Service contains a
	 * null value for a property that exists in this object, then this object's property is unchanged unless the
	 * overwriteNull flag is set to true.
	 * </p>
	 * 
	 * <p>
	 * The ServiceId is locked and can never be overridden. The ResourceMetadata properties will follow the same
	 * principals - only properties with legit values will be overridden.
	 * </p>
	 * 
	 * @param other
	 *            The Service properties to merge
	 * @param overwriteNull
	 *            True if null values in the other Service should overwrite values in this object. False if not.
	 */
	public void merge(Service other, boolean overwriteNull) {
		// Protected method names that are not automatically transferred (Id is protected, and ResourceMetadata merging
		// is delegated to that object.
		List<String> protectedNames = new ArrayList<String>();
		protectedNames.add("setResourceMetadata");
		protectedNames.add("setServiceId");

		Method[] methods = this.getClass().getMethods();

		// Loop through every Property
		for (Method fromMethod : methods) {
			if (fromMethod.getDeclaringClass().equals(this.getClass()) && fromMethod.getName().startsWith("get")) {

				String fromName = fromMethod.getName();
				String toName = fromName.replace("get", "set");

				if (protectedNames.contains(toName)) {
					continue;
				}

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

		// ResourceMetadata handles its own merging.
		if (other.getResourceMetadata() != null) {
			if (getResourceMetadata() == null) {
				this.setResourceMetadata(new ResourceMetadata());
			}
			this.resourceMetadata.merge(other.getResourceMetadata(), overwriteNull);
		}
	}
}