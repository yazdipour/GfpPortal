/*
 * Geopaparazzi - Digital field mapping on Android based devices
 * Copyright (C) 2016  HydroloGIS (www.hydrologis.com)
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
package gfp.ir.vtmintegration.geolibrary.util;

/**
 * A runnable that runs on text.
 * 
 * @author Andrea Antonello (www.hydrologis.com)
 */
public abstract class TextAndBooleanRunnable implements Runnable {
    protected String theTextToRunOn = ""; //$NON-NLS-1$
    protected boolean theBooleanToRunOn = false;

    /**
     * @param text text to set.
     */
    public void setText( String text ) {
        theTextToRunOn = text;
    }

    /**
     * @param check the flag to set.
     */
    public void setTheBoolean(boolean check) {
        this.theBooleanToRunOn = check;
    }
}
