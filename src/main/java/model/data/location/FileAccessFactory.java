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

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3EncryptionClient;
import com.amazonaws.services.s3.model.CryptoConfiguration;
import com.amazonaws.services.s3.model.KMSEncryptionMaterialsProvider;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.annotation.JsonIgnore;

import exception.InvalidInputException;

/**
 * Factory class that helps with the obtaining of Files represented by FileLocation interfaces; such as S3 files or
 * folder shares.
 * 
 * @author Patrick.Doody, Sonny.Saniev
 * 
 */
public class FileAccessFactory {
	private String s3ak = "";
	private String s3pk = "";
	private String s3ek = "";

	private final static Logger LOGGER = LoggerFactory.getLogger(FileAccessFactory.class);

	private final String PROTOCOL_PREFIX = "https://";

	public FileAccessFactory() {

	}

	public FileAccessFactory(String s3AccessKey, String s3PrivateKey, String s3EncryptKey) {
		this.s3ak = s3AccessKey;
		this.s3pk = s3PrivateKey;
		this.s3ek = s3EncryptKey;
	}

	public FileAccessFactory(String s3AccessKey, String s3PrivateKey) {
		this.s3ak = s3AccessKey;
		this.s3pk = s3PrivateKey;
		this.s3ek = null;
	}

	/**
	 * Sets the AWS Credentials, required for pulling S3 files. If encryption is required, then the key should be
	 * specified. If encryption is not required, then null should be specified as this value.
	 * 
	 * @param accessKey
	 *            Access key
	 * @param privateKey
	 *            Private key
	 * @param s3EncryptKey
	 *            The encryption key. If specified, then encryption will be used. Set to null if not desired.
	 */
	public void setS3Credentials(String s3AccessKey, String s3PrivateKey, String s3EncryptKey) {
		this.s3ak = s3AccessKey;
		this.s3pk = s3PrivateKey;
		this.s3ek = s3EncryptKey;
	}

	/**
	 * Returns the InputStream for a file represented by an object implementing the FileLocation interface
	 * 
	 * @param fileLocation
	 *            The file location
	 * @return The File stream
	 */
	public InputStream getFile(FileLocation fileLocation) throws AmazonClientException, InvalidInputException {
		if (fileLocation instanceof FolderShare) {
			return ((FolderShare) fileLocation).getFile();
		} else if (fileLocation instanceof S3FileStore) {
			try {
				return getS3File(fileLocation, s3ak, s3pk, s3ek);
			} catch (AmazonClientException exception) {
				// Add helpful text to Exception
				String systemError = String.format(
						"Could not retrieve File Bytes for S3 File. Failed with Message : %s. AWS Client Credentials may be incorrect or not specified.",
						exception.getMessage());
				String userError = "There was an issue pushing the file to Piazza S3 Bucket. Please contact a Piazza administrator for details.";
				LOGGER.error(systemError, exception);
				throw new AmazonClientException(userError);
			}
		} else {
			throw new InvalidInputException("Unsupported Object type.");
		}
	}

	/**
	 * Gets the input stream for an S3 file store. This will stream the bytes from S3. Null, or exception will be thrown
	 * if an error occurs during acquisition.
	 * 
	 * The S3 Credentials MUST be populated using the setCredentials() method before executing this call, or a
	 * Credentials exception is likely to be thrown by S3.
	 */
	@JsonIgnore
	public InputStream getS3File(FileLocation fileLocation, String accessKey, String privateKey, String s3EncryptKey) {
		// Get the file from S3. Connect to S3 Bucket. Only apply credentials if they are present.
		AmazonS3Client s3Client = null;
		S3FileStore fileStore = (S3FileStore) fileLocation;
		if (accessKey.isEmpty() || privateKey.isEmpty()) {
			s3Client = new AmazonS3Client();
		} else {
			// If an encryption key was provided, use the encrypted client
			BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, privateKey);
			if (s3EncryptKey != null) {
				KMSEncryptionMaterialsProvider materialProvider = new KMSEncryptionMaterialsProvider(s3EncryptKey);
				s3Client = new AmazonS3EncryptionClient(credentials, materialProvider,
						new CryptoConfiguration().withKmsRegion(Regions.US_EAST_1)).withRegion(Region.getRegion(Regions.US_EAST_1));
			} else {
				s3Client = new AmazonS3Client(credentials);
			}
		}
		S3Object s3Object = s3Client.getObject(fileStore.getBucketName(), fileStore.getFileName());
		return s3Object.getObjectContent();
	}

	/**
	 * Returns the URI for a file represented by an object implementing the FileLocation interface
	 * 
	 * @param fileLocation
	 *            The file location
	 * @return The File URI
	 */
	public String getFileUri(FileLocation fileLocation) throws InvalidInputException {
		if (fileLocation instanceof FolderShare) {
			return ((FolderShare) fileLocation).getFilePath();
		} else if (fileLocation instanceof S3FileStore) {
			S3FileStore s3FileStore = ((S3FileStore) fileLocation);
			return String.format("%s%s/%s/%s", PROTOCOL_PREFIX, s3FileStore.getDomainName(), s3FileStore.getBucketName(),
					s3FileStore.getFileName());
		} else {
			throw new InvalidInputException("Unsupported Object type.");
		}
	}
}