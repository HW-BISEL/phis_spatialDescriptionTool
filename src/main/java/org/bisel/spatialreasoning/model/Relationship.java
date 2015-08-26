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
 * Represents an individual relationship between two Colours
 * @author kcm
 * @see ColourClass
 */
public class Relationship {
    private RelationshipType reln;
    
    public Relationship(String relationship) {
        if(relationship.equalsIgnoreCase("left")) {
            reln = RelationshipType.LEFT;
        } else if(relationship.equalsIgnoreCase("right")) {
            reln = RelationshipType.RIGHT;
        } else if(relationship.equalsIgnoreCase("superior")) {
            reln = RelationshipType.SUPERIOR;
        } else if(relationship.equalsIgnoreCase("inferior")) {
            reln = RelationshipType.INFERIOR;
        } else if(relationship.equalsIgnoreCase("overlap")) {
            reln = RelationshipType.OVERLAP;
        } else if(relationship.equalsIgnoreCase("line")) {
            reln = RelationshipType.LINE;
        }
    }
    
    public RelationshipType getRelationship() {
        return reln;
    }
    
    public String getRelationshipAsString() {
        return this.reln.toString().toLowerCase();
    }

    @Override
    public String toString() {
        return getRelationshipAsString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || (obj.getClass() != this.getClass())) {
            return false;
        }
       
        Relationship temp = (Relationship) obj;
        if(temp.getRelationshipAsString().equalsIgnoreCase(this.getRelationshipAsString())) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;        
        hash = 59 * hash + Objects.hashCode(this.reln);
        return hash;
    }
    
}
