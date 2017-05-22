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

import java.io.File;
import java.io.InputStream;

import model.data.location.FileAccessFactory;
import model.data.location.FolderShare;
import model.data.location.S3FileStore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Tests the File Access Factory and location-related Models
 * 
 * @author Patrick.Doody
 *
 */
public class FileAccessTest {
	@InjectMocks
	private FileAccessFactory factory = new FileAccessFactory();

	/**
	 * Initialization
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(factory);

		// Create a Mock amazon client

	}

	/**
	 * Tests setting credentials
	 */
	@Test
	public void testCredentials() {
		FileAccessFactory factory = new FileAccessFactory();
		assertTrue(ReflectionTestUtils.getField(factory, "s3ak").equals(""));
		assertTrue(ReflectionTestUtils.getField(factory, "s3pk").equals(""));
		assertTrue(ReflectionTestUtils.getField(factory, "s3ek").equals(""));
		
		factory = new FileAccessFactory("access", "private", "encrypt");
		assertTrue(ReflectionTestUtils.getField(factory, "s3ak").equals("access"));
		assertTrue(ReflectionTestUtils.getField(factory, "s3pk").equals("private"));
		assertTrue(ReflectionTestUtils.getField(factory, "s3ek").equals("encrypt"));
		
		factory.setS3Credentials("newAccess", "newPrivate", "newEncrypt");
		assertTrue(ReflectionTestUtils.getField(factory, "s3ak").equals("newAccess"));
		assertTrue(ReflectionTestUtils.getField(factory, "s3pk").equals("newPrivate"));
		assertTrue(ReflectionTestUtils.getField(factory, "s3ek").equals("newEncrypt"));
	}

	/**
	 * Tests getting files
	 */
	@Test
	public void testGetFile() throws Exception {
		// Test getting a file from disk
		File mockFile = new File("Test.txt");
		mockFile.createNewFile();
		FolderShare location = new FolderShare();
		location.filePath = "Test.txt";
		InputStream input = factory.getFile(location);
		assertTrue(input != null);
		// Clean up temp file
		mockFile.deleteOnExit();
	}

	/**
	 * Tests an S3 error
	 */
	@Test(expected = Exception.class)
	public void testS3Error() throws Exception {
		// Test getting an S3 file, throwing an exception.
		S3FileStore s3Location = new S3FileStore("test", "test.txt", (long) 0, "amazon");
		s3Location.setBucketName("test");
		s3Location.setDomainName("amazon");
		s3Location.setFileSize((long) 0);
		s3Location.setFileName("test.txt");
		factory.getFile(s3Location);
	}

	/**
	 * Tests exception handling for getting files
	 */
	@Test(expected = Exception.class)
	public void testGetFileException() throws Exception {
		// Null exception must be thrown
		factory.getFile(null);
	}

	/**
	 * Tests File URI generation
	 */
	@Test
	public void testFileUri() throws Exception {
		// Test files
		FolderShare location = new FolderShare();
		location.filePath = "Test.txt";
		String uri = factory.getFileUri(location);
		assertTrue(uri.equals("Test.txt"));
	}

	/**
	 * Tests URI exception
	 */
	@Test(expected = Exception.class)
	public void testUriError() throws Exception {
		factory.getFileUri(null);
	}
}
