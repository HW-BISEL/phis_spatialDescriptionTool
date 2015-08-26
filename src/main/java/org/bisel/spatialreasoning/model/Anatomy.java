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

import java.util.HashSet;

/**
 *
 * @author kcm
 */
public class Anatomy {

    private HashSet<Description> anatomyDescription;

    public Anatomy() {
        anatomyDescription = new HashSet();
    }

    public HashSet<Description> getAnatomyDescriptionForAllSemantics() {
        anatomyDescription.clear();
        buildAnatomyDescriptionForAllSemantics();
        return anatomyDescription;
    }

    public HashSet<Description> getAnatomyDescriptionForBSPOSemantics() {
        anatomyDescription.clear();
        buildAnatomyDescriptionForBSPOSemantics();
        return anatomyDescription;
    }

    /**
     * Generates a local anatomy database in memory based on "ALL" semantics.
     *
     * todo Not a good approach as it is fixed for a single image. Might be
     * better to replace with an actual database or perhaps a file that can be
     * read in.
     *
     * Generates a series of Descriptions which are statements of the form:
     * <i>Colour Relationship Colour</i>
     *
     * @see org.bisel.spatialreasoning.model.Description
     */
    private void buildAnatomyDescriptionForAllSemantics() {
        anatomyDescription.add(new Description(new Colour("red"), new Relationship("right"), new Colour("blue")));
        anatomyDescription.add(new Description(new Colour("yellow"), new Relationship("right"), new Colour("blue")));
        anatomyDescription.add(new Description(new Colour("cyan"), new Relationship("right"), new Colour("blue")));
        anatomyDescription.add(new Description(new Colour("pink"), new Relationship("right"), new Colour("red")));
        anatomyDescription.add(new Description(new Colour("pink"), new Relationship("right"), new Colour("yellow")));
        anatomyDescription.add(new Description(new Colour("magenta"), new Relationship("right"), new Colour("red")));
        anatomyDescription.add(new Description(new Colour("magenta"), new Relationship("right"), new Colour("yellow")));
        anatomyDescription.add(new Description(new Colour("orange"), new Relationship("right"), new Colour("red")));
        anatomyDescription.add(new Description(new Colour("orange"), new Relationship("right"), new Colour("yellow")));
        anatomyDescription.add(new Description(new Colour("green"), new Relationship("right"), new Colour("orange")));
        anatomyDescription.add(new Description(new Colour("green"), new Relationship("right"), new Colour("pink")));
        anatomyDescription.add(new Description(new Colour("green"), new Relationship("right"), new Colour("magenta")));

        anatomyDescription.add(new Description(new Colour("orange"), new Relationship("left"), new Colour("green")));
        anatomyDescription.add(new Description(new Colour("magenta"), new Relationship("left"), new Colour("green")));
        anatomyDescription.add(new Description(new Colour("pink"), new Relationship("left"), new Colour("green")));
        anatomyDescription.add(new Description(new Colour("red"), new Relationship("left"), new Colour("pink")));
        anatomyDescription.add(new Description(new Colour("red"), new Relationship("left"), new Colour("magenta")));
        anatomyDescription.add(new Description(new Colour("red"), new Relationship("left"), new Colour("orange")));
        anatomyDescription.add(new Description(new Colour("yellow"), new Relationship("left"), new Colour("pink")));
        anatomyDescription.add(new Description(new Colour("yellow"), new Relationship("left"), new Colour("magenta")));
        anatomyDescription.add(new Description(new Colour("yellow"), new Relationship("left"), new Colour("orange")));
        anatomyDescription.add(new Description(new Colour("blue"), new Relationship("left"), new Colour("red")));
        anatomyDescription.add(new Description(new Colour("blue"), new Relationship("left"), new Colour("yellow")));
        anatomyDescription.add(new Description(new Colour("blue"), new Relationship("left"), new Colour("cyan")));

        anatomyDescription.add(new Description(new Colour("pink"), new Relationship("superior"), new Colour("magenta")));
        anatomyDescription.add(new Description(new Colour("pink"), new Relationship("superior"), new Colour("yellow")));
        anatomyDescription.add(new Description(new Colour("red"), new Relationship("superior"), new Colour("magenta")));
        anatomyDescription.add(new Description(new Colour("red"), new Relationship("superior"), new Colour("yellow")));
        anatomyDescription.add(new Description(new Colour("green"), new Relationship("superior"), new Colour("cyan")));
        anatomyDescription.add(new Description(new Colour("yellow"), new Relationship("superior"), new Colour("cyan")));
        anatomyDescription.add(new Description(new Colour("magenta"), new Relationship("superior"), new Colour("cyan")));
        anatomyDescription.add(new Description(new Colour("cyan"), new Relationship("superior"), new Colour("orange")));

        anatomyDescription.add(new Description(new Colour("orange"), new Relationship("inferior"), new Colour("cyan")));
        anatomyDescription.add(new Description(new Colour("cyan"), new Relationship("inferior"), new Colour("yellow")));
        anatomyDescription.add(new Description(new Colour("cyan"), new Relationship("inferior"), new Colour("magenta")));
        anatomyDescription.add(new Description(new Colour("cyan"), new Relationship("inferior"), new Colour("green")));
        anatomyDescription.add(new Description(new Colour("yellow"), new Relationship("inferior"), new Colour("red")));
        anatomyDescription.add(new Description(new Colour("yellow"), new Relationship("inferior"), new Colour("pink")));
        anatomyDescription.add(new Description(new Colour("magenta"), new Relationship("inferior"), new Colour("red")));
        anatomyDescription.add(new Description(new Colour("magenta"), new Relationship("inferior"), new Colour("pink")));
    }

    /**
     * Generates a local anatomy database in memory based on "BSPO" semantics.
     *
     * todo Not a good approach as it is fixed for a single image. Might be
     * better to replace with an actual database or perhaps a file that can be
     * read in.
     *
     * Generates a series of Descriptions which are statements of the form:
     * <i>Colour Relationship Colour</i>
     *
     * @see org.bisel.spatialreasoning.model.Description
     */
    private void buildAnatomyDescriptionForBSPOSemantics() {
        anatomyDescription.add(new Description(new Colour("red"), new Relationship("right"), new Colour("blue")));
        anatomyDescription.add(new Description(new Colour("yellow"), new Relationship("right"), new Colour("blue")));
        anatomyDescription.add(new Description(new Colour("magenta"), new Relationship("right"), new Colour("yellow")));
        anatomyDescription.add(new Description(new Colour("magenta"), new Relationship("right"), new Colour("red")));
        anatomyDescription.add(new Description(new Colour("pink"), new Relationship("right"), new Colour("magenta")));
        anatomyDescription.add(new Description(new Colour("orange"), new Relationship("right"), new Colour("pink")));
        anatomyDescription.add(new Description(new Colour("green"), new Relationship("right"), new Colour("orange")));
        anatomyDescription.add(new Description(new Colour("cyan"), new Relationship("right"), new Colour("orange")));

        anatomyDescription.add(new Description(new Colour("orange"), new Relationship("left"), new Colour("green")));
        anatomyDescription.add(new Description(new Colour("magenta"), new Relationship("left"), new Colour("orange")));
        anatomyDescription.add(new Description(new Colour("pink"), new Relationship("left"), new Colour("magenta")));
        anatomyDescription.add(new Description(new Colour("red"), new Relationship("left"), new Colour("pink")));
        anatomyDescription.add(new Description(new Colour("yellow"), new Relationship("left"), new Colour("pink")));
        anatomyDescription.add(new Description(new Colour("cyan"), new Relationship("left"), new Colour("pink")));
        anatomyDescription.add(new Description(new Colour("blue"), new Relationship("left"), new Colour("red")));
        anatomyDescription.add(new Description(new Colour("blue"), new Relationship("left"), new Colour("yellow")));
        anatomyDescription.add(new Description(new Colour("blue"), new Relationship("left"), new Colour("cyan")));

        anatomyDescription.add(new Description(new Colour("pink"), new Relationship("superior"), new Colour("red")));
        anatomyDescription.add(new Description(new Colour("green"), new Relationship("superior"), new Colour("red")));
        anatomyDescription.add(new Description(new Colour("red"), new Relationship("superior"), new Colour("blue")));
        anatomyDescription.add(new Description(new Colour("blue"), new Relationship("superior"), new Colour("magenta")));
        anatomyDescription.add(new Description(new Colour("magenta"), new Relationship("superior"), new Colour("yellow")));
        anatomyDescription.add(new Description(new Colour("yellow"), new Relationship("superior"), new Colour("cyan")));
        anatomyDescription.add(new Description(new Colour("cyan"), new Relationship("superior"), new Colour("orange")));

        anatomyDescription.add(new Description(new Colour("orange"), new Relationship("inferior"), new Colour("cyan")));
        anatomyDescription.add(new Description(new Colour("blue"), new Relationship("inferior"), new Colour("cyan")));
        anatomyDescription.add(new Description(new Colour("cyan"), new Relationship("inferior"), new Colour("yellow")));
        anatomyDescription.add(new Description(new Colour("cyan"), new Relationship("inferior"), new Colour("magenta")));
        anatomyDescription.add(new Description(new Colour("cyan"), new Relationship("inferior"), new Colour("green")));
        anatomyDescription.add(new Description(new Colour("yellow"), new Relationship("inferior"), new Colour("pink")));
        anatomyDescription.add(new Description(new Colour("magenta"), new Relationship("inferior"), new Colour("pink")));
        anatomyDescription.add(new Description(new Colour("green"), new Relationship("inferior"), new Colour("pink")));
        anatomyDescription.add(new Description(new Colour("pink"), new Relationship("inferior"), new Colour("red")));
    }

}
