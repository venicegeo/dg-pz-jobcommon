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
 * Represents a File Request to the Gateway made by an end-user posting to the
 * /file Gateway endpoint. The Access component is responsible for making the
 * file available to the user before the file can be downloaded.
 * 
 * @author Patrick.Doody
 * 
 */
public class FileRequest {
	public String createdBy;
	public String dataId;
}