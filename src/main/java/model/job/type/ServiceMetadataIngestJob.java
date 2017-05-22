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
package model.job.type;

import model.job.PiazzaJobType;
import model.service.metadata.Service;

/**
 * Represents the JSON Model for ingesting service metadata into Piazza for
 * subsequent query, e.g. used when registering a service. This ingest allows
 * subsequent searches for services.
 * 
 * 
 * 
 * 
 */
public class ServiceMetadataIngestJob implements PiazzaJobType {

	public Service data;

	public ServiceMetadataIngestJob() {

	}

	public Service getData() {
		return data;
	}

	public void setData(Service data) {
		this.data = data;
	}
}