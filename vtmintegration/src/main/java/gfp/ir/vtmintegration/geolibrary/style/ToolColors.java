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

package gfp.ir.vtmintegration.geolibrary.style;

/**
 * Color for tools.
 *
 * @author Andrea Antonello (www.hydrologis.com)
 */
public enum ToolColors {
    selection_stroke("#ffff00"), //
    selection_fill("#ff0000"), //
    preview_stroke("#00bdbd"), //
    preview_fill("#00ffff"), //
    infoselection_stroke("#0000ff"), //
    infoselection_fill("#0000ff") //
    ; //

    private String hex;

    ToolColors(String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return hex;
    }
}
