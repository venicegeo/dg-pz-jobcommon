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

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import model.data.DataType;

/**
 * Data passed to service controller for execute service jobs
 * 
 * @author bkrasner
 *
 */
public class ExecuteServiceData {

	@ApiModelProperty(value = "The Inputs for the execution", required = true, dataType = "model.swagger.SwaggerDataType")
	@NotNull
	@JsonProperty("dataInputs")
	public Map<String, DataType> dataInputs;

	@ApiModelProperty(value = "The Output for the execution", required = true, dataType = "model.swagger.SwaggerDataType")
	@NotNull
	@JsonProperty("dataOutput")
	public List<DataType> dataOutput;

	@ApiModelProperty(value = "The Id of the Service to Execute", required = true)
	@NotNull
	@Size(min=1)
	private String serviceId;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Map<String, DataType> getDataInputs() {
		return dataInputs;
	}

	public void setDataInputs(Map<String, DataType> dataInputs) {
		this.dataInputs = dataInputs;
	}

	public List<DataType> getDataOutput() {
		return dataOutput;
	}

	public void setDataOutput(List<DataType> dataOutput) {
		this.dataOutput = dataOutput;
	}
}