/*
 * Geopaparazzi - Digital field mapping on Android based devices
 * Copyright (C) 2010  HydroloGIS (www.hydrologis.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gfp.ir.vtmintegration.spatilite_core.core.enums;

/**
 * Spatialite table types.
 *
 * @author Andrea Antonello (www.hydrologis.com)
 */
@SuppressWarnings("nls")
public enum TableTypes {
    /**
     * "normal" spatial table
     */
    SPATIALTABLE("SpatialTable"),
    /**
     * spatial view
     */
    SPATIALVIEW("SpatialView");

    private String description;

    /**
     * @param description a description for the table type.
     */
    private TableTypes(String description) {
        this.description = description;
    }

    /**
     * @return the db type's description.
     */
    public String getDescription() {
        return description;
    }

}
