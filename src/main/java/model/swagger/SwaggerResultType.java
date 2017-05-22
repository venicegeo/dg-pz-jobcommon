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
package model.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import model.job.result.type.DataResult;
import model.job.result.type.DeploymentResult;
import model.job.result.type.ErrorResult;
import model.job.result.type.FileResult;
import model.job.result.type.JobResult;
import model.job.result.type.TextResult;

/**
 * This class is needed as a workaround to nonfunctional swagger annotations on
 * interface classes. It is intentionally abstract so as not to be instantiated.
 * 
 * @author Russell Orf
 *
 */
@ApiModel(value = "ResultTypeInterface")
public abstract class SwaggerResultType {

	@ApiModelProperty("DataResult Implementation")
	public DataResult impl01;

	@ApiModelProperty("DeploymentResult Implementation")
	public DeploymentResult impl02;
	
	@ApiModelProperty("ErrorResult Implementation")
	public ErrorResult impl03;
	
	@ApiModelProperty("FileResult Implementation")
	public FileResult impl04;
	
	@ApiModelProperty("JobResult Implementation")
	public JobResult impl05;
	
	@ApiModelProperty("TextResult Implementation")
	public TextResult impl06;
}