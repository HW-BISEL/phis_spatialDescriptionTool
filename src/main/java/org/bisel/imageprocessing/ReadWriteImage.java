/*
 * Copyright 2015 BISEL, Heriot-Watt University, Edinburgh, UK (http://www.bisel.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bisel.imageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Reads an image from disk and converts into a BufferedImage object.
 *
 * @author kcm
 * @see java.awt.image.BufferedImage
 */
public class ReadWriteImage {

    private static final Logger LOG = Logger.getLogger(ReadWriteImage.class.getName());

    /**
     * Reads an image from disk and converts into a BufferedImage object.
     * @param fullFileName The complete path and name of the image
     * @return image as a BufferedImage
     * @see java.awt.image.BufferedImage
     */
    public BufferedImage readBufferedImage(String fullFileName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(fullFileName));
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Cannot read image: {0}", fullFileName);
            LOG.severe(e.getMessage());
        }
        return image;
    }

    /**
     * Writes a given image to disk in the location specified.
     * @param image image represented as a BufferedImage
     * @param fullPathName name (and path) of image to be created
     * @see java.awt.image.BufferedImage
     */
    public void writeImageToDisk(BufferedImage image, String fullPathName) {
        if (image == null) {
            LOG.log(Level.SEVERE, "writing null image");
        }
        try {
            ImageIO.write(image, "png", new File(fullPathName));
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
    }    
    
}
