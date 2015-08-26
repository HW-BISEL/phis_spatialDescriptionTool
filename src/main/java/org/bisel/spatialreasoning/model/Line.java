/*
 * Copyright 2015 BISEL, Heriot-Watt University, Edinburgh, UK (http://www.bisel.uk).
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

import java.util.Objects;

/**
 *
 * @author kcm
 */
public class Line {
    private final Point p1;
    private final Point p2;
    private final Colour c;
        
    public Line(Point p1, Point p2, Colour c) {
        this.p1 = p1;
        this.p2 = p2;
        this.c = c;
    }

    public Point getPoint1() {
        return p1;
    }

    public Point getPoint2() {
        return p2;
    }
    
    public Colour getColour() {
        return c;
    }

    @Override
    public String toString() {
        return p1.toString()+"  "+p2.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || (obj.getClass() != this.getClass())) {
            return false;
        }

        Line temp = (Line) obj;
        if (this.p1.equals(temp.getPoint1()) && this.p2.equals(temp.getPoint2()) && this.c.equals(temp.getColour())) {
                return true;
        }

        return false;    
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.p1);
        hash = 89 * hash + Objects.hashCode(this.p2);
        hash = 89 * hash + Objects.hashCode(this.c);
        return hash;
    }
    
}
