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

import java.util.Objects;

/**
 * Represents a single statement within the spatial description.  Each statement
 * is of the form: <i>Colour Relationship Colour</i>
 * 
 * todo Needs to be point based :( 
 * 
 * @author kcm
 * @see Relationship
 * @see Colour
 */
public class Description {
    
    // c1 inferior c2 means c1 is inferior to c2
    private Colour colour1;
    private Relationship reln;
    private Colour colour2;

    public Description(Colour c1, Relationship relationship, Colour c2) {
        this.colour1 = c1;
        this.reln = relationship;
        this.colour2 = c2;
    }

    public Colour getColour1() {
        return colour1;
    }
    
    public Colour getColour2() {
        return colour2;
    }

    public Relationship getReln() {
        return reln;
    }   

    @Override
    public String toString() {
        return colour1.toString()+" "+reln.toString()+" "+colour2.toString();
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || (obj.getClass() != this.getClass())) {
            return false;
        }
       
        Description temp = (Description) obj;
        if(temp.getColour1().getColourAsString().equals(this.colour1.getColourAsString())) {
            if(temp.getReln().getRelationshipAsString().equals(this.reln.getRelationshipAsString())) {
                if(temp.getColour2().getColourAsString().equals(this.colour2.getColourAsString())) {
                    return true;
                }
            }            
        }

        return false;        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.colour1);
        hash = 11 * hash + Objects.hashCode(this.reln);
        hash = 11 * hash + Objects.hashCode(this.colour2);
        return hash;
    }    
    
    public static void main(String[] args) {
        Description d = new Description(new Colour("yellow"), new Relationship("superior"), new Colour("blue"));
        System.out.println(d.getColour1().getColourAsString());
        System.out.println(d.getReln().getRelationshipAsString());
    }
    
}
