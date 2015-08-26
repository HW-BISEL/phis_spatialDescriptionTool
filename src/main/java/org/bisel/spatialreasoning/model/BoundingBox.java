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

package org.bisel.spatialreasoning.model;

import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Bounding box is a rectangle around a group of pixels.  Frequently, we assume the
 * pixels are grouped according to colour.  The bounding box essentially provides the
 * <ul>
 * <li>maximum X value</li>
 * <li>minimum X value</li>
 * <li>maximum Y value</li>
 * <li>minimum Y value</li>
 * </ul>
 * 
 * 
 * @author kcm
 */
public class BoundingBox {

    private int minX = -1;
    private int minY = -1;
    private int maxX = -1;
    private int maxY = -1;
    private static final Logger LOG = Logger.getLogger(BoundingBox.class.getName());
    
    

    public BoundingBox(int min_x, int min_y, int max_x, int max_y) {
            minX = min_x;
            minY = min_y;
            maxX = max_x;
            maxY = max_y;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    @Override
    public String toString() {
        return "(" + minX + ", " + minY + "), (" + maxX + ", " + maxY + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || (obj.getClass() != this.getClass())) {
            return false;
        }

        BoundingBox temp = (BoundingBox) obj;
        if (temp.maxX == this.maxX && temp.maxY == this.maxY && temp.minX == this.minX && temp.minY == this.minY) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.minX;
        hash = 47 * hash + this.minY;
        hash = 47 * hash + this.maxX;
        hash = 47 * hash + this.maxY;
        return hash;
    }
    
    /**
     * Generates a bounding box for a particular coloured region.
     * @param colour the colour of the region that you want the bounding box for
     * @param image the image in which the region exists
     * @return The newly generated bounding box     
     * @see Colour
     */
    public BoundingBox generateBoundingBox(Colour colour, BufferedImage image) {
        int min_X = 1000;
        int min_Y = 1000;
        int max_X = 0;
        int max_Y = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getRGB(x, y) == colour.getRgb()) {
                    if (x < min_X) {
                        min_X = x;
                    }
                    if (x > max_X) {
                        max_X = x;
                    }
                    if (y < min_Y) {
                        min_Y = y;
                    }
                    if (y > max_Y) {
                        max_Y = y;
                    }
                }
            }
        }
        try {
            return new BoundingBox(min_X, min_Y, max_X, max_Y);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }    
}
