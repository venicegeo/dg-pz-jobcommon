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
package model.data.deployment.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import model.data.deployment.Deployment;
import model.data.deployment.Lease;

import org.joda.time.DateTime;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests deployment and lease serializations
 * 
 * @author Patrick.Doody
 *
 */
public class DeploymentTest {
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Tests deployment model
	 */
	@Test
	public void testDeployment() throws IOException {
		// Create Deployment
		Deployment mockDeployment = new Deployment("123456", "654321", "localhost", "8080", "Test",
				"localhost:8080/test");
		// Serialize, deserialize
		String serialized = mapper.writeValueAsString(mockDeployment);
		Deployment deployment = mapper.readValue(serialized, Deployment.class);
		// Verify
		assertTrue(deployment.getDeploymentId().equals(mockDeployment.getDeploymentId()));
		assertTrue(deployment.getDataId().equals(mockDeployment.getDataId()));
		assertTrue(deployment.getPort().equals(mockDeployment.getPort()));
		assertTrue(deployment.getHost().equals(mockDeployment.getHost()));
		assertTrue(deployment.getCapabilitiesUrl().equals(mockDeployment.getCapabilitiesUrl()));
		assertTrue(deployment.getLayer().equals(mockDeployment.getLayer()));
	}

	/**
	 * Lease model test
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLease() throws IOException {
		// Mock
		Lease mockLease = new Lease("123456", "654321", new DateTime().toString());
		// Serialize
		String serialized = mapper.writeValueAsString(mockLease);
		Lease lease = mapper.readValue(serialized, Lease.class);
		// Verify
		assertTrue(lease.getDeploymentId().equals(mockLease.getDeploymentId()));
		assertTrue(lease.getExpiresOn().equals(mockLease.getExpiresOn()));
		assertTrue(lease.getLeaseId().equals(mockLease.getLeaseId()));
	}
}
