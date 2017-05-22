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
package model.data.location;

import io.swagger.annotations.ApiModelProperty;

/**
 * Model for the necessary information that is required to access a file on an
 * AWS S3 bucket share.
 * 
 * @author Patrick.Doody
 * 
 */
public class S3FileStore implements FileLocation {

	@ApiModelProperty(required = true, value = "The type of file location", allowableValues = "s3")
	public String type;

	@ApiModelProperty(value = "The name of the Amazon S3 bucket hosting the data")
	public String bucketName;

	@ApiModelProperty(value = "The name of the file stored")
	public String fileName;

	@ApiModelProperty(value = "The size of the file stored, in bytes")
	public Long fileSize;

	@ApiModelProperty(value = "The name of the domain in the Amazon S3 URL hosting the data")
	public String domainName;

	public S3FileStore() {

	}

	public S3FileStore(String bucketName, String fileName, Long fileSize, String domainName) {
		this.bucketName = bucketName;
		this.fileName = fileName;
		this.domainName = domainName;
		this.fileSize = fileSize;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
}