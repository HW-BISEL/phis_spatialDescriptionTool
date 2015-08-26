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

import java.awt.Color;
import java.util.Objects;

/**
 * Simpler version of JAVA's Color
 * 
 * @author kcm
 * @see ColourClass
 * @see java.awt.Color
 */
public class Colour {

    private ColourClass colour;
    private int rgb;

    public Colour(String colour) {
        if (colour.equalsIgnoreCase("blue")) {
            this.colour = ColourClass.BLUE;
            this.rgb = Color.BLUE.getRGB();
        } else if (colour.equalsIgnoreCase("cyan")) {
            this.colour = ColourClass.CYAN;
            this.rgb = Color.CYAN.getRGB();
        } else if (colour.equalsIgnoreCase(("green"))) {
            this.colour = ColourClass.GREEN;
            this.rgb = Color.GREEN.getRGB();
        } else if (colour.equalsIgnoreCase("magenta")) {
            this.colour = ColourClass.MAGENTA;
            this.rgb = Color.MAGENTA.getRGB();
        } else if (colour.equalsIgnoreCase("orange")) {
            this.colour = ColourClass.ORANGE;
            this.rgb = Color.ORANGE.getRGB();
        } else if (colour.equalsIgnoreCase("pink")) {
            this.colour = ColourClass.PINK;
            this.rgb = Color.PINK.getRGB();
        } else if (colour.equalsIgnoreCase("red")) {
            this.colour = ColourClass.RED;
            this.rgb = Color.RED.getRGB();
        } else if (colour.equalsIgnoreCase("yellow")) {
            this.colour = ColourClass.YELLOW;
            this.rgb = Color.YELLOW.getRGB();
        } else if (colour.equalsIgnoreCase("black") || colour.equalsIgnoreCase(("roi"))) {
            this.colour = ColourClass.ROI;
            this.rgb = Color.BLACK.getRGB();                 
        } else if(colour.equalsIgnoreCase("dark_blue")) {
            this.colour = ColourClass.DARK_BLUE;
            this.rgb = -16777088;
        } else if(colour.equalsIgnoreCase("brown")) {
            this.colour = ColourClass.BROWN;
            this.rgb = -6724045;
        } else if(colour.equalsIgnoreCase("purple")) {
            this.colour = ColourClass.PURPLE;
            this.rgb = -8912696;
        } else if(colour.equalsIgnoreCase("dark_green")) {
            this.colour = ColourClass.DARK_GREEN;
            this.rgb = -16744320;
        } 
    }

    public ColourClass getColour() {
        return colour;
    }

    public int getRgb() {
        return rgb;
    }

    public String getColourAsString() {
        return this.colour.toString().toLowerCase();
    }

    @Override
    public String toString() {
        return getColourAsString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || (obj.getClass() != this.getClass())) {
            return false;
        }

        Colour temp = (Colour) obj;
        if (temp.getColourAsString().equals(this.getColourAsString())) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.colour);
        hash = 97 * hash + this.rgb;
        return hash;
    }

}
