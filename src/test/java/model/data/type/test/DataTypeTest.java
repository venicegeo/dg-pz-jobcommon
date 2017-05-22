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
package model.data.type.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import model.data.location.FolderShare;
import model.data.type.BodyDataType;
import model.data.type.GeoJsonDataType;
import model.data.type.LiteralDataType;
import model.data.type.PointCloudDataType;
import model.data.type.PostGISDataType;
import model.data.type.RasterDataType;
import model.data.type.ShapefileDataType;
import model.data.type.TextDataType;
import model.data.type.URLParameterDataType;
import model.data.type.WfsDataType;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests the data type models
 * 
 * @author Patrick.Doody
 *
 */
public class DataTypeTest {
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Tests the body data type
	 */
	@Test
	public void testBody() throws IOException {
		BodyDataType input = new BodyDataType();
		input.content = "Test";
		String serialized = mapper.writeValueAsString(input);
		BodyDataType output = mapper.readValue(serialized, BodyDataType.class);
		assertTrue(input.getContent().equals(output.getContent()));
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
	}

	/**
	 * Tests GeoJSON data type
	 */
	@Test
	public void testGeoJson() throws IOException {
		GeoJsonDataType input = new GeoJsonDataType();
		input.setDatabaseTableName("Test");
		input.setGeoJsonContent("Features");
		input.setMimeType("application/json");
		String serialized = mapper.writeValueAsString(input);
		GeoJsonDataType output = mapper.readValue(serialized, GeoJsonDataType.class);
		assertTrue(input.getGeoJsonContent().equals(output.getGeoJsonContent()));
		assertTrue(output.getLocation() == null);
		assertTrue(input.getMimeType().equals(output.getMimeType()));
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
	}

	/**
	 * Tests Literal data type
	 */
	@Test
	public void testLiteral() throws IOException {
		LiteralDataType input = new LiteralDataType();
		input.setValue("Testing");
		String serialized = mapper.writeValueAsString(input);
		LiteralDataType output = mapper.readValue(serialized, LiteralDataType.class);
		assertTrue(input.getValue().equals(output.getValue()));
		assertTrue(input.getLiteralType().equals(output.getLiteralType()));
	}

	/**
	 * Tests PointCloud Data Type
	 */
	@Test
	public void testPointCloud() throws IOException {
		PointCloudDataType input = new PointCloudDataType();
		input.location = new FolderShare();
		((FolderShare) input.location).filePath = "test/test.laz";
		String serialized = mapper.writeValueAsString(input);
		PointCloudDataType output = mapper.readValue(serialized, PointCloudDataType.class);
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
		assertTrue(output.getLocation() != null);
		assertTrue(((FolderShare) input.getLocation()).getFilePath().equals(
				((FolderShare) output.getLocation()).getFilePath()));
	}

	/**
	 * Tests PostGIS Data Type
	 */
	@Test
	public void testPostGis() throws IOException {
		PostGISDataType input = new PostGISDataType();
		input.setDatabase("Test");
		input.setMimeType("application/json");
		input.setTable("Table");
		String serialized = mapper.writeValueAsString(input);
		PostGISDataType output = mapper.readValue(serialized, PostGISDataType.class);
		assertTrue(input.getDatabase().equals(output.getDatabase()));
		assertTrue(input.getMimeType().equals(output.getMimeType()));
		assertTrue(input.getTable().equals(output.getTable()));
	}

	/**
	 * Tests Raster data type
	 */
	@Test
	public void testRaster() throws IOException {
		RasterDataType input = new RasterDataType();
		FolderShare location = new FolderShare();
		location.filePath = "test/test.tiff";
		location.setFileSize((long) 500);
		input.location = location;
		String serialized = mapper.writeValueAsString(input);
		RasterDataType output = mapper.readValue(serialized, RasterDataType.class);
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
		assertTrue(((FolderShare) input.getLocation()).getFilePath().equals(
				((FolderShare) output.getLocation()).getFilePath()));
	}

	/**
	 * Test Shapefile data type
	 */
	@Test
	public void testShapeFile() throws IOException {
		ShapefileDataType input = new ShapefileDataType();
		input.setDatabaseTableName("Test");
		input.setMimeType("binary");
		String serialized = mapper.writeValueAsString(input);
		ShapefileDataType output = mapper.readValue(serialized, ShapefileDataType.class);
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
		assertTrue(input.getDatabaseTableName().equals(output.getDatabaseTableName()));
		assertTrue(input.getMimeType().equals(output.getMimeType()));
	}

	/**
	 * Tests text data
	 */
	@Test
	public void testText() throws IOException {
		TextDataType input = new TextDataType();
		input.content = "Testing";
		input.mimeType = "text/html";
		String serialized = mapper.writeValueAsString(input);
		TextDataType output = mapper.readValue(serialized, TextDataType.class);
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
		assertTrue(input.getContent().equals(output.getContent()));
		assertTrue(input.getMimeType().equals(output.getMimeType()));
	}

	/**
	 * Tests URL data type
	 */
	@Test
	public void testUrl() throws IOException {
		URLParameterDataType input = new URLParameterDataType();
		input.content = "parameter";
		String serialized = mapper.writeValueAsString(input);
		URLParameterDataType output = mapper.readValue(serialized, URLParameterDataType.class);
		assertTrue(input.getClass().getSimpleName().equals(output.getClass().getSimpleName()));
		assertTrue(input.getContent().equals(output.getContent()));
	}

	/**
	 * Test Wfs Data Type
	 */
	@Test
	public void testWfs() throws IOException {
		WfsDataType input = new WfsDataType();
		input.featureType = "Test";
		input.url = "localhost";
		input.version = "1.1.0";
		String serialized = mapper.writeValueAsString(input);
		WfsDataType output = mapper.readValue(serialized, WfsDataType.class);
		assertTrue(input.getFeatureType().equals(output.getFeatureType()));
		assertTrue(input.getUrl().equals(output.getUrl()));
		assertTrue(input.getVersion().equals(output.getVersion()));
	}
}
