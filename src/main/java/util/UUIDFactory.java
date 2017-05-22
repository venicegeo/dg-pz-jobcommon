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
package util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * UUIDGen factory class that calls the Piazza Core UUIDGen service and returns a new GUID.
 * 
 * @author mlynum, rorf, patrick.doody
 */
@Component
public class UUIDFactory {
	private final static Logger LOGGER = LoggerFactory.getLogger(UUIDFactory.class);

	/**
	 * Default constructor required for Bean instantiation.
	 */
	public UUIDFactory() {
	}

	/**
	 * Only gets one UUID
	 * 
	 * @return UUID - Piazza unique identifier
	 */
	public String getUUID() {
		return getUUID(1).get(0);
	}

	/**
	 * Calls the UUIDgen service to get a unique identifier.
	 * 
	 * @return List of UUIDs
	 */
	public List<String> getUUID(Integer count) {
		List<String> uuidList = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			uuidList.add(java.util.UUID.randomUUID().toString());
		}
		return uuidList;
	}
}