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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 * Helpful utility methods used in conjunction with GeoTools Data Stores and Features.
 * 
 * The Ingest and Access components use GeoTools heavily for data parsing and manipulation, and this utility aims to
 * create a common place for redundant code.
 * 
 * @author Patrick.Doody
 * 
 */
public class GeoToolsUtil {
	private static final String POSTGIS_DATASTORE_TYPE = "postgis";

	/**
	 * GeoTools offers no simple way to rename an existing FeatureType. This method provides a nice way to clone a
	 * feature type with a new name.
	 * 
	 * @param featureType
	 *            The existing Feature Type to clone
	 * @param newTypeName
	 *            The name of the new, cloned Feature Type
	 * @return New Feature Type with the specified name.
	 */
	public static SimpleFeatureType cloneFeatureType(SimpleFeatureType featureType, String newTypeName) {
		SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
		builder.setName(newTypeName);
		builder.setNamespaceURI(featureType.getName().getNamespaceURI());
		builder.setCRS(featureType.getCoordinateReferenceSystem());
		builder.addAll(featureType.getAttributeDescriptors());
		if (featureType.getGeometryDescriptor() != null) {
			builder.setDefaultGeometry(featureType.getGeometryDescriptor().getLocalName());
		} else {
			builder.setDefaultGeometry(null);
		}
		return builder.buildFeatureType();
	}

	/**
	 * Creates a PostGIS Data Store.
	 * 
	 * @param host
	 *            The hostname of the Database
	 * @param port
	 *            The port
	 * @param schema
	 *            The schema name (such as "public")
	 * @param database
	 *            The database name
	 * @param user
	 *            The user name
	 * @param password
	 *            The password
	 * @return GeoTools DataStore for the PostGIS database.
	 */
	public static DataStore getPostGisDataStore(String host, String port, String schema, String database, String user, String password)
			throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dbtype", POSTGIS_DATASTORE_TYPE);
		params.put("host", host);
		params.put("port", port);
		params.put("schema", schema);
		params.put("database", database);
		params.put("user", user);
		params.put("passwd", password);
		DataStore postGisStore = DataStoreFinder.getDataStore(params);
		return postGisStore;
	}
}
