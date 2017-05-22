/**
 * Copyright 2017, RadiantBlue Technologies, Inc.
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

/**
 * Utility class to check which operating system the application running on. Possible uses are for scripting purposes.
 * 
 * @author Sonny.Saniev
 * 
 */
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class OsValidator {

	/**
	 * types of Operating Systems
	 */
	public enum OperatingSystemType {
		WINDOWS, MAC_OS, LINUX, OTHER
	};

	/**
	 * detect the operating system from the os.name System property and cache
	 * the result
	 * 
	 * @returns - the operating system detected
	 */
	public OperatingSystemType getOperatingSystemType() {
		OperatingSystemType osType = null;
		if (osType == null) {
			String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
			if ((os.indexOf("mac") >= 0) || (os.indexOf("darwin") >= 0)) {
				osType = OperatingSystemType.MAC_OS;
			} else if (os.indexOf("win") >= 0) {
				osType = OperatingSystemType.WINDOWS;
			} else if (os.indexOf("nux") >= 0) {
				osType = OperatingSystemType.LINUX;
			} else {
				osType = OperatingSystemType.OTHER;
			}
		}

		return osType;
	}
	
	/**
	 * Checks if OS the app is running on is ms-windows
	 * 
	 * @return boolean
	 */
	public boolean isWindows(){
		 return OperatingSystemType.WINDOWS.equals(getOperatingSystemType());
	}

	/**
	 * Checks if OS the app is running on is linux
	 * 
	 * @return boolean
	 */
	public boolean isLinux(){
		return OperatingSystemType.LINUX.equals(getOperatingSystemType());
	}
	
	/**
	 * Checks if OS the app is running on is a mac
	 * 
	 * @return boolean
	 */
	public boolean isMac(){
		return OperatingSystemType.MAC_OS.equals(getOperatingSystemType());
	}
	
	/**
	 * Checks if OS the app is running on is unknown
	 * 
	 * @return boolean
	 */
	public boolean isOther(){
		return OperatingSystemType.OTHER.equals(getOperatingSystemType());
	}
}
