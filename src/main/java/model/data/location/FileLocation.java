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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Common interface for representing Resource locations. File resources
 * referenced by the Piazza system can reside in a variety of locations
 * accessible by the Piazza system; such as S3 Buckets, or internal folder
 * shares accessible to internal components, or perhaps extensible to something
 * like an FTP site.
 * 
 * This interface is used by implementations of a specific file location that
 * describe the appropriate parameters required to connect to that
 * implementation.
 * 
 * The factory class FileAccessFactory is what is intended to be used for
 * pulling the bytes out of files represented by these interfaces.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = S3FileStore.class, name = "s3"), @Type(value = FolderShare.class, name = "share") })
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface FileLocation {
	public String getFileName();

	public Long getFileSize();

	public void setFileSize(Long fileSize);
}