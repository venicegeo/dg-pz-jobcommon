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
package model.job.result.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import model.data.deployment.Deployment;
import model.job.result.type.DataResult;
import model.job.result.type.DeploymentResult;
import model.job.result.type.ErrorResult;
import model.job.result.type.FileResult;
import model.job.result.type.JobResult;
import model.job.result.type.TextResult;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests Job Result Models
 * 
 * @author Patrick.Doody
 *
 */
public class JobResultTest {
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Tests Data result
	 */
	@Test
	public void testData() throws IOException {
		DataResult input = new DataResult();
		input.setDataId("123456");
		String serialized = mapper.writeValueAsString(input);
		DataResult output = mapper.readValue(serialized, DataResult.class);
		assertTrue(input.getDataId().equals(output.getDataId()));
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
	}

	/**
	 * Tests Deployment Results
	 */
	@Test
	public void testDeployment() throws IOException {
		DeploymentResult input = new DeploymentResult();
		// Mock a deployment
		Deployment mockDeployment = new Deployment("123456", "654321", "localhost", "8080", "Test", "getcapabilities");
		input.setDeployment(mockDeployment);

		// Serialize
		String serialized = mapper.writeValueAsString(input);
		DeploymentResult output = mapper.readValue(serialized, DeploymentResult.class);

		// Verify
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
		assertTrue(input.getDeployment().getCapabilitiesUrl().equals(output.getDeployment().getCapabilitiesUrl()));
		assertTrue(input.getDeployment().getDataId().equals(output.getDeployment().getDataId()));
		assertTrue(input.getDeployment().getHost().equals(output.getDeployment().getHost()));
		assertTrue(input.getDeployment().getLayer().equals(output.getDeployment().getLayer()));
		assertTrue(input.getDeployment().getPort().equals(output.getDeployment().getPort()));
		assertTrue(input.getDeployment().getDeploymentId().equals(output.getDeployment().getDeploymentId()));
	}

	/**
	 * Test Error Result
	 */
	@Test
	public void testError() throws IOException {
		ErrorResult input = new ErrorResult();
		input.setDetails("Oops");
		input.setMessage("An Error");
		String serialized = mapper.writeValueAsString(input);
		ErrorResult output = mapper.readValue(serialized, ErrorResult.class);
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
		assertTrue(input.getDetails().equals(output.getDetails()));
		assertTrue(input.getMessage().equals(output.getMessage()));
	}

	/**
	 * Test File Result
	 */
	@Test
	public void testFile() throws IOException {
		FileResult input = new FileResult();
		input.dataId = "123456";
		String serialized = mapper.writeValueAsString(input);
		FileResult output = mapper.readValue(serialized, FileResult.class);
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
		assertTrue(input.getDataId().equals(output.getDataId()));
	}

	/**
	 * Test Job Result
	 */
	@Test
	public void testJob() throws IOException {
		JobResult input = new JobResult("123456");
		input.setJobId("654321");
		String serialized = mapper.writeValueAsString(input);
		JobResult output = mapper.readValue(serialized, JobResult.class);
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
		assertTrue(output.getJobId().equals("654321"));
	}

	/**
	 * Tests Text Result
	 */
	@Test
	public void testText() throws IOException {
		TextResult input = new TextResult("Success");
		String serialized = mapper.writeValueAsString(input);
		TextResult output = mapper.readValue(serialized, TextResult.class);
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
		assertTrue(output.getText().equals("Success"));
		input.setText("Error");
		serialized = mapper.writeValueAsString(input);
		output = mapper.readValue(serialized, TextResult.class);
		assertTrue(output.getText().equals("Error"));
	}
}
