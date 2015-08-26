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

package org.bisel.spatialreasoning;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bisel.spatialreasoning.model.Anatomy;
import org.bisel.spatialreasoning.model.Colour;
import org.bisel.spatialreasoning.model.Description;
import org.bisel.spatialreasoning.model.Relationship;
import org.bisel.spatialreasoning.model.Semantics;

/**
 *
 * @author kcm
 */
public class ReasoningImpl implements ReasoningInterface {
    
    private final HashSet<Description> spatialDescription;    
    private final HashSet<Description> anatomyDescription;       
    private static final Logger LOG = Logger.getLogger(ReasoningImpl.class.getName());
    

    /**
     * 
     * @param spatialD a set of Description objects that describe a particular ROI
     * @param reasoningSemantics the type of reasoning semantics to apply
     * @see org.bisel.spatialreasoning.model.Description
     * @see org.bisel.spatialreasoning.model.Semantics
     */
    public ReasoningImpl(HashSet<Description> spatialD, Semantics reasoningSemantics) {
        this.spatialDescription = spatialD;
        
        
        Anatomy anatomy = new Anatomy();
        if(reasoningSemantics.equals(Semantics.all)) {
            anatomyDescription = anatomy.getAnatomyDescriptionForAllSemantics();            
        } else if(reasoningSemantics.equals(Semantics.bspo)) {
            anatomyDescription = anatomy.getAnatomyDescriptionForBSPOSemantics();            
        } else {
            anatomyDescription = new HashSet();
            LOG.log(Level.SEVERE, "no valid semantics choosen");
        }
    }
    
    

    
    
    /** 
     * Optimises and then returns the spatial description provided at creation
     * 
     * @return optimised description of space
     * @see #optimiseSD() 
     * @see Description
     */
    @Override
    public HashSet<Description> getSpatialDescription() {
        optimiseSD();
        return spatialDescription;
    }
    
    
    /**
     * Optimises the spatial description by removing the statements that are not needed.
     * For example, if you know <i>blue inferior to red</i>, and you have the statements:
     * <ol>
     * <li>ROI inferior red</li>
     * <li>ROI inferior blue</li> 
     * </ol>
     * 
     * Because blue is inferior to red, then the statement <i>ROI inferior red</i> is unnecessary
     * as it is already covered by <i>ROI inferior blue</i>.  This method will remove the statement
     * <i>ROI inferior red</i>.
     * 
     * todo Method currently only works for know colours and cannot be used with points or line.  Consequently,
     * this needs rewritten.
     */
    private void optimiseSD() {
        Colour mostSuperiorColour = null;
        Description mostSuperiorDesc = null;
        Colour mostInferiorColour = null;
        Description mostInferiorDesc = null;
        Colour mostLeftColour = null;
        Description mostLeftDesc = null;
        Colour mostRightColour = null;
        Description mostRightDesc = null;
        HashSet<Description> optimised = new HashSet();        
        
        for(Description tempD : spatialDescription) {
            if(tempD.getReln().equals(new Relationship("superior"))) {
                if(mostSuperiorColour == null) {
                    mostSuperiorColour = tempD.getColour1();
                    mostSuperiorDesc = tempD;
                } else {
                    if(isSuperior(tempD.getColour1(), mostSuperiorColour)) {
                        //mostSuperiorColour = tempD.getColour1();
                        //optimised.add(mostSuperiorDesc);
                        //mostSuperiorDesc = tempD;
                        
                        optimised.add(tempD);
                    } else {
                        //optimised.add(tempD);
                        
                        optimised.add(mostSuperiorDesc);
                        mostSuperiorColour = tempD.getColour1();
                        mostSuperiorDesc = tempD;
                    }
                }
            }
            
            if(tempD.getReln().equals(new Relationship(("inferior")))) {
                if(mostInferiorColour == null) {
                    mostInferiorColour = tempD.getColour1();
                    mostInferiorDesc = tempD;
                } else {
                    if(isInferior(tempD.getColour1(), mostInferiorColour)) {
                        //mostInferiorColour = tempD.getColour1();
                        //optimised.add(mostInferiorDesc);
                        //mostInferiorDesc = tempD;
                        
                        optimised.add(tempD);
                    } else {
                        //optimised.add(tempD);
                        
                        optimised.add(mostInferiorDesc);
                        mostInferiorColour = tempD.getColour1();
                        mostInferiorDesc = tempD;
                    }
                }
            }
            
            if(tempD.getReln().equals(new Relationship(("left")))) {
                if(mostLeftColour == null) {
                    mostLeftColour = tempD.getColour1();
                    mostLeftDesc = tempD;
                } else {
                    if(isLeft(tempD.getColour1(), mostLeftColour)) {
                        //mostLeftColour = tempD.getColour1();
                        //optimised.add(mostLeftDesc);
                        //mostLeftDesc = tempD;
                        
                        optimised.add(tempD);
                    } else {
                        //optimised.add(tempD);
                        
                        optimised.add(mostLeftDesc);
                        mostLeftColour = tempD.getColour1();
                        mostLeftDesc = tempD;
                    }
                }
            }   
            
            if(tempD.getReln().equals(new Relationship(("right")))) {
                if(mostRightColour == null) {
                    mostRightColour = tempD.getColour1();
                    mostRightDesc = tempD;                    
                } else {
                    if(isRight(tempD.getColour1(), mostRightColour)) {                        
                        //mostRightColour = tempD.getColour1();
                        //optimised.add(mostRightDesc);
                        //mostRightDesc = tempD;                        
                        
                        optimised.add(tempD);
                    } else {
                        //optimised.add(tempD);                        
                        
                        optimised.add(mostRightDesc);
                        mostRightColour = tempD.getColour1();
                        mostRightDesc = tempD;
                    }
                }
            } 
                        
            if(tempD.getReln().equals(new Relationship("line"))) {
                // do nothing
            }            
        }
        
        for(Description temp : optimised) {
           spatialDescription.remove(temp);
        }
    }
    
    /**
     * Determines (based on the given anatomy database) whether or not one 
     * block is inferior to another.  Blocks are determined by their colour and
     * also named by their colour.
     * 
     * Uses recursion to determine to determine which colour is inferior.
     * 
     * todo Will need to replace with a point based mechanism
     * 
     * @param c1 first colour
     * @param c2 second colour
     * @return True if c1 is inferior to c2 otherwise false
     * @see #buildAnatomyDescription() 
     * @see #recurse(java.util.LinkedList, org.bisel.spatialreasoning.model.Colour, org.bisel.spatialreasoning.model.Relationship) 
     * @see org.bisel.spatialreasoning.model.Colour
     */
    @Override
    public boolean isInferior(Colour c1, Colour c2) {
        LinkedList<Colour> infList = new LinkedList();
        infList.add(c1);
        
        return recurse(infList, c2, new Relationship("inferior"));                
        
    }
    
    
    /**
     * Determines (based on the given anatomy database) whether or not one 
     * block is superior to another.  Blocks are determined by their colour and
     * also named by their colour.
     * 
     * Uses recursion to determine to determine which colour is inferior.
     * 
     * todo Will need to replace with a point based mechanism
     * 
     * @param c1 first colour
     * @param c2 second colour
     * @return True if c1 is inferior to c2 otherwise false
     * @see #buildAnatomyDescription() 
     * @see #recurse(java.util.LinkedList, org.bisel.spatialreasoning.model.Colour, org.bisel.spatialreasoning.model.Relationship) 
     * @see org.bisel.spatialreasoning.model.Colour
     */    
    @Override
    public boolean isSuperior(Colour c1, Colour c2) {
        LinkedList<Colour> supList = new LinkedList();
        supList.add(c1);
        
        return recurse(supList, c2, new Relationship("superior"));                
    }
    
    
    /**
     * Determines (based on the given anatomy database) whether or not one 
     * block is right of another.  Blocks are determined by their colour and
     * also named by their colour.
     * 
     * Uses recursion to determine to determine which colour is inferior.
     * 
     * todo Will need to replace with a point based mechanism
     * 
     * @param c1 first colour
     * @param c2 second colour
     * @return True if c1 is inferior to c2 otherwise false
     * @see #buildAnatomyDescription() 
     * @see #recurse(java.util.LinkedList, org.bisel.spatialreasoning.model.Colour, org.bisel.spatialreasoning.model.Relationship) 
     * @see org.bisel.spatialreasoning.model.Colour
     */    
    @Override
    public boolean isRight(Colour c1, Colour c2) {        
        LinkedList<Colour> rightList = new LinkedList();        
        rightList.add(c1);
        
        return recurse(rightList, c2, new Relationship("right"));                        
    }
    
    
    /**
     * Determines (based on the given anatomy database) whether or not one 
     * block is left of another.  Blocks are determined by their colour and
     * also named by their colour.
     * 
     * Uses recursion to determine to determine which colour is inferior.
     * 
     * todo Will need to replace with a point based mechanism
     * 
     * @param c1 first colour
     * @param c2 second colour
     * @return True if c1 is inferior to c2 otherwise false
     * @see #buildAnatomyDescription() 
     * @see #recurse(java.util.LinkedList, org.bisel.spatialreasoning.model.Colour, org.bisel.spatialreasoning.model.Relationship) 
     * @see org.bisel.spatialreasoning.model.Colour
     */    
    @Override
    public boolean isLeft(Colour c1, Colour c2) {
        LinkedList<Colour> leftList = new LinkedList();
        leftList.add(c1);
        
        return recurse(leftList, c2, new Relationship("left"));                
    }    
    
    
    /**
     * Recursion method that uses itself to iterate over the anatomy description
     * to determine if one colour (block) is inferior/superior/right/left of
     * another colour (block).
     *      
     * 
     * todo Will need to replace with a point based mechanism
     * 
     * @param c1 first colour
     * @param c2 second colour
     * @return True if c1 is inferior to c2 otherwise false
     * @see #buildAnatomyDescription() 
     * @see #isInferior(org.bisel.spatialreasoning.model.Colour, org.bisel.spatialreasoning.model.Colour) 
     * @see #isLeft(org.bisel.spatialreasoning.model.Colour, org.bisel.spatialreasoning.model.Colour) 
     * @see #isRight(org.bisel.spatialreasoning.model.Colour, org.bisel.spatialreasoning.model.Colour) 
     * @see #isSuperior(org.bisel.spatialreasoning.model.Colour, org.bisel.spatialreasoning.model.Colour) 
     * @see org.bisel.spatialreasoning.model.Colour
     */    
    private boolean recurse(LinkedList<Colour> supList, Colour c2, Relationship r) {
        
        // termination clause
        if(supList.isEmpty()) {
            return false;
        }
        
        Colour c1 = supList.removeFirst();
        
        for(Description d : anatomyDescription) {
            if(d.equals(new Description(c1, r, c2))) {
                return true;
            }
            
            if(d.getReln().equals(r)) {
                if(d.getColour1().equals(c1)) {
                    supList.add(d.getColour2());
                }
            }
        }        
        return recurse(supList, c2, r);        
    }
       
    //
    //
    //
    
    public static void main(String[] args) {
        HashSet<Description> sd = new HashSet();
        sd.add(new Description(new Colour("black"), new Relationship("inferior"), new Colour("cyan")));
        ReasoningImpl r = new ReasoningImpl(sd, Semantics.bspo);
        System.out.println(r.isSuperior(new Colour("pink"), new Colour("red")));
        System.out.println(r.isInferior(new Colour("pink"), new Colour("red")));
        System.out.println(r.isLeft(new Colour("red"), new Colour("yellow")));
        System.out.println(r.isRight(new Colour("green"), new Colour("yellow")));
        
        //
        
        r = new ReasoningImpl(sd, Semantics.all);
        System.out.println(r.isSuperior(new Colour("pink"), new Colour("red")));
        System.out.println(r.isInferior(new Colour("pink"), new Colour("red")));
        System.out.println(r.isLeft(new Colour("red"), new Colour("yellow")));
        System.out.println(r.isRight(new Colour("green"), new Colour("yellow")));
        
    }
    
    
}
