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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * Model representing a File accessible via a folder share that is will be
 * accessible to the Piazza Core internal components.
 * 
 * @author Patrick.Doody
 * 
 */
public class FolderShare implements FileLocation {

	private final static Logger LOGGER = LoggerFactory.getLogger(FolderShare.class);
	
	@ApiModelProperty(required = true, value = "The type of file location", allowableValues = "share")
	public String type;

	@ApiModelProperty(value = "The path to the file on the shared folder")
	public String filePath;

	@ApiModelProperty(value = "The size of the file stored, in bytes")
	public Long fileSize;

	@ApiModelProperty(value = "The name of the file stored")
	public String fileName;

	public String getFileName() {
		fileName = new File(filePath).getName();
		return fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Returns the InputStream for the file which resides at a folder share or
	 * local file system. Null if no file can be found.
	 */
	@JsonIgnore
	public InputStream getFile() {
		try {
			return new FileInputStream(filePath);
		} catch (FileNotFoundException exception) {
			LOGGER.error("File not found", exception);
			return null;
		}
	}
}
