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
package model.data;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import model.job.metadata.ResourceMetadata;
import model.job.metadata.SpatialMetadata;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests the Metadata models. Primarily tests the merging capabilities to ensure
 * correctness of merging.
 * 
 * @author Patrick.Doody
 *
 */
public class MetadataTest {

	/**
	 * Tests the merging capabilities.
	 */
	@Test
	public void testMerges() {
		// Create an original object with some standard properties
		ResourceMetadata original = new ResourceMetadata();
		original.setName("United States");
		original.setDescription("This is a map of the United States.");
		original.setFormat("Raster");

		// Check accessors
		assertTrue(original.getName().equals("United States"));
		assertTrue(original.getDescription().equals("This is a map of the United States."));

		// Create an object to merge.
		ResourceMetadata toMerge = new ResourceMetadata();
		toMerge.setName("North America");
		toMerge.setDescription("This is a map of North America.");

		// Check accessors
		assertTrue(toMerge.getName().equals("North America"));
		assertTrue(toMerge.getDescription().equals("This is a map of North America."));

		// Merge the two. Do not overwrite null values.
		original.merge(toMerge, false);

		// Verify the merge was successful
		assertTrue(original.getName().equals("North America"));
		assertTrue(original.getDescription().equals("This is a map of North America."));
		assertTrue(original.getFormat().equals("Raster"));

		// Merge again, but overwrite null values this time.
		original.merge(toMerge, true);

		// Verify
		assertTrue(original.getName().equals("North America"));
		assertTrue(original.getDescription().equals("This is a map of North America."));
		assertTrue(original.getFormat() == null);
	}

	/**
	 * Test Spatial Metadata
	 */
	@Test
	public void testSpatial() throws IOException {
		// Mock
		SpatialMetadata input = new SpatialMetadata();
		input.setCoordinateReferenceSystem("WGS84");
		input.setEpsgCode(4326);
		input.setMaxX((double) 0);
		input.setMaxY((double) 1);
		input.setMaxZ((double) 2);
		input.setMinY((double) 1);
		input.setMinX((double) 2);
		input.setMinZ((double) 3);
		input.setNumFeatures(500);
		ObjectMapper mapper = new ObjectMapper();
		String serialized = mapper.writeValueAsString(input);
		SpatialMetadata output = mapper.readValue(serialized, SpatialMetadata.class);
		// Verify
		assertTrue(input.getCoordinateReferenceSystem().equals(output.getCoordinateReferenceSystem()));
		assertTrue(input.getEpsgCode().equals(output.getEpsgCode()));
		assertTrue(input.getEpsgString().equals(output.getEpsgString()));
		assertTrue(input.getMinX().equals(output.getMinX()));
		assertTrue(input.getMinY().equals(output.getMinY()));
		assertTrue(input.getMinZ().equals(output.getMinZ()));
		assertTrue(input.getMaxX().equals(output.getMaxX()));
		assertTrue(input.getMaxY().equals(output.getMaxY()));
		assertTrue(input.getMaxZ().equals(output.getMaxZ()));
	}
}
