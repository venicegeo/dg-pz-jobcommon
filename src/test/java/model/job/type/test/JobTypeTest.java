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
package model.job.type.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.data.DataResource;
import model.data.DataType;
import model.data.type.TextDataType;
import model.job.metadata.ResourceMetadata;
import model.job.type.AbortJob;
import model.job.type.AccessJob;
import model.job.type.ExecuteServiceJob;
import model.job.type.IngestJob;
import model.job.type.RepeatJob;
import model.service.metadata.ExecuteServiceData;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests the Job Types, and serialization
 * 
 * @author Patrick.Doody
 *
 */
public class JobTypeTest {
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Tests abort job
	 */
	@Test
	public void testAbort() throws IOException {
		AbortJob input = new AbortJob("123456", "Testing");
		String serialized = mapper.writeValueAsString(input);
		AbortJob output = mapper.readValue(serialized, AbortJob.class);
		assertTrue(input.getJobId().equals(output.getJobId()));
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
		assertTrue(input.reason.equals(output.reason));
	}

	/**
	 * Tests Access Job
	 */
	@Test
	public void testAccess() throws IOException {
		AccessJob input = new AccessJob();
		input.setDataId("123456");
		input.setDeploymentType(AccessJob.ACCESS_TYPE_GEOSERVER);
		String serialized = mapper.writeValueAsString(input);
		AccessJob output = mapper.readValue(serialized, AccessJob.class);
		assertTrue(input.getDataId().equals(output.getDataId()));
		assertTrue(input.getDeploymentType().equals(output.getDeploymentType()));
	}

	/**
	 * Tests Execute Job
	 */
	@Test
	public void testExecute() throws IOException {
		ExecuteServiceJob input = new ExecuteServiceJob();
		input.setJobId("123456");
		input.data = new ExecuteServiceData();
		input.data.setServiceId("654321");
		// Define an Input
		TextDataType inputParam = new TextDataType();
		inputParam.content = "Testing";
		inputParam.mimeType = "text";
		Map<String, DataType> inputData = new HashMap<String, DataType>();
		inputData.put("param1", inputParam);
		// Attach to Inputs
		input.data.setDataInputs(inputData);
		// Define an output
		TextDataType outputParam = new TextDataType();
		outputParam.mimeType = "text";
		// Attach to Outputs
		List<DataType> outputData = new ArrayList<DataType>();
		outputData.add(outputParam);
		input.data.setDataOutput(outputData);

		// Serialize
		String serialized = mapper.writeValueAsString(input);
		ExecuteServiceJob output = mapper.readValue(serialized, ExecuteServiceJob.class);

		// Verify
		assertTrue(input.getJobId().equals(output.getJobId()));
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
		assertTrue(input.data.getServiceId().equals(output.data.getServiceId()));
		assertTrue(input.data.getDataInputs().get("param1").getClass().getSimpleName().equals(output.data.getDataInputs().get("param1").getClass().getSimpleName()));
		assertTrue(input.data.getDataOutput().get(0).getClass().getSimpleName().equals(output.data.getDataOutput().get(0).getClass().getSimpleName()));
	}

	/**
	 * Tests Ingest Job
	 */
	@Test
	public void testIngest() throws IOException {
		IngestJob input = new IngestJob();
		input.host = true;
		input.data = new DataResource();
		input.data.setDataId("123456");
		input.data.dataType = new TextDataType();
		((TextDataType) input.data.dataType).content = "Testing";
		input.data.metadata = new ResourceMetadata();
		input.data.getMetadata().setName("Test name");

		// Serialize
		String serialized = mapper.writeValueAsString(input);
		IngestJob output = mapper.readValue(serialized, IngestJob.class);

		// Verify
		assertTrue(output.getHost().equals(input.getHost()));
		assertTrue(output.getClass().getSimpleName().equals(input.getClass().getSimpleName()));
		assertTrue(output.getData().getDataType().getClass().getSimpleName().equals(input.getData().getDataType().getClass().getSimpleName()));
		assertTrue(output.getData().getMetadata().getName().equals(input.getData().getMetadata().getName()));
	}

	/**
	 * Tests repeat job
	 */
	@Test
	public void testRepeat() throws IOException {
		RepeatJob input = new RepeatJob();
		input.setJobId("123456");
		String serialized = mapper.writeValueAsString(input);
		RepeatJob output = mapper.readValue(serialized, RepeatJob.class);
		assertTrue(output.getClass().getSimpleName().equals(input.getClass().getSimpleName()));
		assertTrue(output.getJobId().equals(input.getJobId()));
	}
}
