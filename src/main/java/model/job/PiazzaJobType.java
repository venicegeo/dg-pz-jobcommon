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
package model.job;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import model.job.type.AbortJob;
import model.job.type.AccessJob;
import model.job.type.DeleteServiceJob;
import model.job.type.DescribeServiceMetadataJob;
import model.job.type.ExecuteServiceJob;
import model.job.type.IngestJob;
import model.job.type.ListServicesJob;
import model.job.type.RegisterServiceJob;
import model.job.type.RepeatJob;
import model.job.type.SearchMetadataIngestJob;
import model.job.type.SearchQueryJob;
import model.job.type.SearchServiceJob;
import model.job.type.ServiceMetadataIngestJob;
import model.job.type.UpdateServiceJob;

/**
 * Interface collecting together all of the available Job Types. This is used by
 * Jackson in order to properly serialize and deserialize all of the Job
 * messages that are passed throughout the Piazza system.
 * 
 * For a list of all of the Job types available, see the model.job.type package.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ 
		@Type(value = AbortJob.class, name = "abort"),
		@Type(value = AccessJob.class, name = "access"),
		@Type(value = DeleteServiceJob.class, name = "delete-service"),
		@Type(value = DescribeServiceMetadataJob.class, name = "read-service"),
		@Type(value = ExecuteServiceJob.class, name = "execute-service"),
		@Type(value = IngestJob.class, name = "ingest"),
		@Type(value = ListServicesJob.class, name = "list-service"),
		@Type(value = RegisterServiceJob.class, name = "register-service"),
		@Type(value = RepeatJob.class, name = "repeat"),
		@Type(value = SearchMetadataIngestJob.class, name = "search-metadata-ingest"),
		@Type(value = SearchQueryJob.class, name = "search-query"),
		@Type(value = SearchServiceJob.class, name = "search-service"),
		@Type(value = ServiceMetadataIngestJob.class, name = "servicemetadata-ingest"),
		@Type(value = UpdateServiceJob.class, name = "update-service")
})
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface PiazzaJobType {

}