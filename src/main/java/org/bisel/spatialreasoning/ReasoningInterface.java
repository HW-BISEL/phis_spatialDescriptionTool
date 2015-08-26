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
package org.bisel.spatialreasoning;

import java.util.HashSet;
import org.bisel.spatialreasoning.model.Colour;
import org.bisel.spatialreasoning.model.Description;

/**
 *
 * @author kcm
 */
public interface ReasoningInterface {

    public HashSet<Description> getSpatialDescription();

    public boolean isInferior(Colour c1, Colour c2);

    public boolean isSuperior(Colour c1, Colour c2);

    public boolean isLeft(Colour c1, Colour c2);

    public boolean isRight(Colour c1, Colour c2);

}
