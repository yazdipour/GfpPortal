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
package gfp.ir.vtmintegration.spatilite_core.util.comparators;

import java.util.Comparator;

import gfp.ir.vtmintegration.spatilite_core.core.tables.AbstractSpatialTable;

/**
 * Comparator for table names.
 * 
 * @author Andrea Antonello (www.hydrologis.com)
 */
public class SpatialTableNameComparator implements Comparator<AbstractSpatialTable> {

    @Override
    public int compare( AbstractSpatialTable t1, AbstractSpatialTable t2 ) {
        return t1.getTableName().compareTo(t2.getTableName());
    }
}
