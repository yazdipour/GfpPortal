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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import gfp.ir.vtmintegration.geolibrary.database.GPLog;

/**
 * @author Andrea Antonello (www.hydrologis.com)
 */
public class FileUtilities {

    /**
     * Copy a file.
     * 
     * @param fromFile source file.
     * @param toFile dest file.
     * @throws IOException  if something goes wrong. 
     */
    public static void copyFile( String fromFile, String toFile ) throws IOException {
        File in = new File(fromFile);
        File out = new File(toFile);
        copyFile(in, out);
    }

    /**
     * Copy a file.
     * 
     * @param in source file.
     * @param out dest file.
     * @throws IOException  if something goes wrong. 
     */
    public static void copyFile( File in, File out ) throws IOException {
        FileInputStream fis = new FileInputStream(in);
        FileOutputStream fos = new FileOutputStream(out);
        copyFile(fis, fos);
    }

    /**
     * Copy a file.
     * 
     * @param fis source file.
     * @param fos dest file.
     * @throws IOException  if something goes wrong. 
     */
    public static void copyFile( InputStream fis, OutputStream fos ) throws IOException {
        try {
            byte[] buf = new byte[1024];
            int i = 0;
            while( (i = fis.read(buf)) != -1 ) {
                fos.write(buf, 0, i);
            }
        } finally {
            if (fis != null)
                fis.close();
            if (fos != null)
                fos.close();
        }
    }

    /**
     * Get the file name without extension.
     * 
     * @param file the file.
     * @return the name.
     */
    public static String getNameWithoutExtention( File file ) {
        String name = file.getName();
        int lastDot = name.lastIndexOf("."); //$NON-NLS-1$
        name = name.substring(0, lastDot);
        return name;
    }

    /** 
     * Read a file to string.
     * 
     * @param file file to read.
     * @return the read string.
     * @throws IOException  if something goes wrong.
     */
    public static String readfile( File file ) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while( (line = br.readLine()) != null ) {
                if (line.length() == 0 || line.startsWith("#")) { //$NON-NLS-1$
                    continue;
                }
                sb.append(line).append("\n"); //$NON-NLS-1$
            }
            return sb.toString();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    GPLog.error("FILEUTILS", e.getLocalizedMessage(), e); //$NON-NLS-1$
                    e.printStackTrace();
                }
            }
        }
    }

    /** 
     * Read a file to a list of line strings.
     * 
     * @param file file to read.
     * @return the read lines list.
     * @throws IOException  if something goes wrong.
     */
    public static List<String> readfileToList( File file ) throws IOException {
        BufferedReader br = null;
        List<String> linesList = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while( (line = br.readLine()) != null ) {
                if (line.length() == 0 || line.startsWith("#")) { //$NON-NLS-1$
                    continue;
                }
                linesList.add(line.trim());
            }
            return linesList;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    GPLog.error("FILEUTILS", e.getLocalizedMessage(), e); //$NON-NLS-1$
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Write a string to file.
     * 
     * @param text the string.
     * @param file the file.
     * @throws IOException  if something goes wrong.
     */
    public static void writefile( String text, File file ) throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(text);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    GPLog.error("FILEUTILS", e.getLocalizedMessage(), e); //$NON-NLS-1$
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Write a byte[] to file.
     * 
     * @param data byte[].
     * @param fileName the fileName.
     * @throws IOException  if something goes wrong.
     */
    public static void writefiledata( byte[] data, String fileName ) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            out.write(data);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    GPLog.error("FILEUTILS", e.getLocalizedMessage(), e); //$NON-NLS-1$
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Returns true if all deletions were successful. If a deletion fails, the method stops
     * attempting to delete and returns false.
     *
     * @param filehandle file to remove.
     * @return true if all deletions were successful
     */
    public static boolean deleteFileOrDir( File filehandle ) {

        if (filehandle.isDirectory()) {
            String[] children = filehandle.list();
            for( int i = 0; i < children.length; i++ ) {
                boolean success = deleteFileOrDir(new File(filehandle, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        boolean isdel = filehandle.delete();
        if (!isdel) {
            // if it didn't work, which often happens on windows systems,
            // remove on exit
            filehandle.deleteOnExit();
        }

        return isdel;
    }

    /**
     * Delete file or folder recursively on exit of the program
     *
     * @param filehandle file to remove.
     * @return true if all went well
     */
    public static boolean deleteFileOrDirOnExit( File filehandle ) {
        if (filehandle.isDirectory()) {
            String[] children = filehandle.list();
            for( int i = 0; i < children.length; i++ ) {
                boolean success = deleteFileOrDir(new File(filehandle, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        filehandle.deleteOnExit();
        return true;
    }

    /**
     * Checks if a given file exists in a supplied folder.
     *
     * @param fileName the name of the file to check.
     * @param folder the folder.
     * @return <code>true</code>, if the file exists
     */
    public static boolean fileExistsInFolder( final String fileName, File folder ) {
        File[] listFiles = folder.listFiles(new FilenameFilter(){
            public boolean accept( File arg0, String tmpName ) {
                return fileName.trim().equals(tmpName.trim());
            }
        });

        return listFiles.length > 0;
    }

    /**
     * Read files to byte array.
     *
     * @param file the file to read.
     * @return the read byte array.
     * @throws IOException  if something goes wrong.
     */
    public static byte[] readFileToByte( File file ) throws IOException {
        RandomAccessFile f = new RandomAccessFile(file, "r");
        try {
            long length = f.length();
            byte[] data = new byte[(int) length];
            f.readFully(data);
            return data;
        } finally {
            f.close();
        }
    }
    /**
     * Recursive search of files with a specific extension.
     *
     * <p>This can be called multiple times, adding to the same list
     *
     * @param searchDir the directory to read.
     * @param searchExtentions the extensions of the files to search for.
     * @param returnFiles the List<File> where the found files will be added to.
     * @return the number of files found.
     */
    public static int searchDirectoryRecursive( File searchDir, String[] searchExtentions, List<File> returnFiles ) {
        File[] listFiles = searchDir.listFiles();
        for( File thisFile : listFiles ) {
            // mj10777: collect desired extension
            if (thisFile.isDirectory()) {
                // mj10777: read recursive directories inside the
                // sdcard/maps directory
                searchDirectoryRecursive(thisFile, searchExtentions, returnFiles);
            } else {
                for (String searchExtention : searchExtentions) {
                    if (thisFile.getName().endsWith(searchExtention)) {
                        returnFiles.add(thisFile);
                    }
                }
            }
        }
        return returnFiles.size();
    }
}
