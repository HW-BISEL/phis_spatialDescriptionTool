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

import ij.process.ColorProcessor;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import org.bisel.spatialreasoning.model.BoundingBox;
import org.bisel.spatialreasoning.model.Colour;
import org.bisel.spatialreasoning.model.Point;

/**
 *
 * @author kcm
 */
public class ProcessImage {

    private static final Logger LOG = Logger.getLogger(ProcessImage.class.getName());

    /**
     * Given a point it draws a horizontal line and grays out everything below
     * that line.
     *
     * @param image the image to be amended
     * @param p the point from which to draw the line and gray out
     */
    public void grayBelowPoint(BufferedImage image, Point p) {
        for (int x = 0; x < 100; x++) {
            for (int y = p.getY(); y < 100; y++) {
                processPixel(image, x, y);
            }
        }
    }

    /**
     * Given a point it draw a horizontal line and grays out everything above
     * that line.
     *
     * @param image the image to be amended
     * @param p the point from which to draw the line and gray out
     */
    public void grayAbovePoint(BufferedImage image, Point p) {
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y <= p.getY(); y++) {
                processPixel(image, x, y);
            }
        }
    }

    /**
     * Given a point it draw a horizontal line and grays out everything left of
     * that line.
     *
     * @param image the image to be amended
     * @param p the point from which to draw the line and gray out
     */
    public void grayLeftPoint(BufferedImage image, Point p) {
        for (int x = 0; x <= p.getX(); x++) {
            for (int y = 0; y < 100; y++) {
                processPixel(image, x, y);
            }
        }
    }

    /**
     * Given a point it draw a horizontal line and grays out everything right of
     * that line.
     *
     * @param image the image to be amended
     * @param p the point from which to draw the line and gray out
     */
    public void grayRightPoint(BufferedImage image, Point p) {
        for (int x = p.getX(); x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                processPixel(image, x, y);
            }
        }
    }

    /**
     * Finds the most superior point of a given block of colour. If the block is
     * split into several different zones, it will find the most superior point
     * of the most superior zone.
     *
     * @param colour find a block, and its most superior point based on the
     * colour of the pixel.
     * @param image the image to be amended
     * @return the most superior point
     * @see Point
     */
    public Point getSuperiorPoint(Colour colour, BufferedImage image) {
        int sup_x = 100;
        int sup_y = 100;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getRGB(x, y) == colour.getRgb()) {
                    if (sup_y > y) {
                        sup_x = x;
                        sup_y = y;
                    }
                }
            }
        }
        return new Point(sup_x, sup_y);
    }

    /**
     * Finds the most inferior point of a given block of colour. If the block is
     * split into several different zones, it will find the most inferior point
     * of the most inferior zone.
     *
     * @param colour find a block, and its most inferior point based on the
     * colour of the pixel.
     * @param image the image to be amended
     * @return the most inferior point
     * @see Point
     */
    public Point getInferiorPoint(Colour colour, BufferedImage image) {
        int inf_x = 0;
        int inf_y = 0;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getRGB(x, y) == colour.getRgb()) {
                    if (y > inf_y) {
                        inf_x = x;
                        inf_y = y;
                    }
                }
            }
        }
        return new Point(inf_x, inf_y);
    }

    /**
     * Finds the most left point of a given block of colour. If the block is
     * split into several different zones, it will find the most left point of
     * the most left zone.
     *
     * @param colour find a block, and its most left point based on the colour
     * of the pixel.
     * @param image the image to be amended
     * @return the most left point
     * @see Point
     */
    public Point getLeftPoint(Colour colour, BufferedImage image) {
        int lef_x = 100;
        int lef_y = 100;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getRGB(x, y) == colour.getRgb()) {
                    if (x < lef_x) {
                        lef_x = x;
                        lef_y = y;
                    }
                }
            }
        }
        return new Point(lef_x, lef_y);
    }

    /**
     * Finds the most right point of a given block of colour. If the block is
     * split into several different zones, it will find the most right point of
     * the most right zone.
     *
     * @param colour find a block, and its most right point based on the colour
     * of the pixel.
     * @param image the image to be amended
     * @return the most right point
     * @see Point
     */
    public Point getRightPoint(Colour colour, BufferedImage image) {
        int rig_x = 0;
        int rig_y = 0;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getRGB(x, y) == colour.getRgb()) {
                    if (x > rig_x) {
                        rig_x = x;
                        rig_y = y;
                    }
                }
            }
        }
        return new Point(rig_x, rig_y);
    }

    /**
     * Grays out the space left of a given line. The line is defined by the
     * start and end points.
     *
     * @param image the image to be amended
     * @param c1 colour of the line
     * @param p1 start point of line
     * @param p2 end point of line
     */
    public void graySpaceLeftLine(BufferedImage image, Colour c1, Point p1, Point p2) {
        grayLeftRight(image, c1, p1, p2, true);
    }

    /**
     * Grays out the space right of a given line. The line is defined by the
     * start and end points.
     *
     * @param image the image to be amended
     * @param c1 colour of the line
     * @param p1 start point of line
     * @param p2 end point of line
     */
    public void graySpaceRightLine(BufferedImage image, Colour c1, Point p1, Point p2) {
        grayLeftRight(image, c1, p1, p2, false);
    }

    /**
     * Grays out the space above a given line. The line is defined by the start
     * and end points.
     *
     * @param image the image to be amended
     * @param c1 colour of the line
     * @param p1 start point of line
     * @param p2 end point of line
     */
    public void graySpaceAboveLine(BufferedImage image, Colour c1, Point p1, Point p2) {
        grayAboveBelow(image, c1, p1, p2, true);
    }

    /**
     * Grays out the space below a given line. The line is defined by the start
     * and end points.
     *
     * @param image the image to be amended
     * @param c1 colour of the line
     * @param p1 start point of line
     * @param p2 end point of line
     */
    public void graySpaceBelowLine(BufferedImage image, Colour c1, Point p1, Point p2) {
        grayAboveBelow(image, c1, p1, p2, false);
    }

    /**
     * Grays out the space either left OR right of a line specified by the given
     * points. A line (of colour specified) exists between points p1 & p2.
     * Everything to one side (left or right) can be grayed out.
     *
     * @param image the image to be amended
     * @param c1 colour of the line
     * @param p1 start point of line
     * @param p2 end point of line
     * @param left true to gray out everything left of the line, false to gray
     * out everything to the right
     * @see org.bisel.spatialreasoning.model.Colour
     */
    private void grayLeftRight(BufferedImage image, Colour c1, Point p1, Point p2, boolean left) {
        int start_x = getMinX(p1, p2);
        int end_x = getMaxX(p1, p2);
        int start_y = getMinY(p1, p2);
        int end_y = getMaxY(p1, p2);

        for (int x = start_x; x <= end_x; x++) {
            for (int y = start_y; y <= end_y; y++) {
                //if (image.getRGB(x, y) == c1.getRgb()) {
                grayWhiteSpaceHorizontally(left, x, y, image);
                //}
            }
        }
    }

    /**
     * Grays out the space either above OR below a line specified by the given
     * points. A line (of colour specified) exists between points p1 & p2.
     * Everything to one side (above or below) can be grayed out.
     *
     * @param image the image to be amended
     * @param c1 The colour of the line
     * @param p1 Start point of line
     * @param p2 End point of line
     * @param left True to gray out everything above the line, false to gray out
     * everything below
     * @see org.bisel.spatialreasoning.model.Colour
     */
    private void grayAboveBelow(BufferedImage image, Colour c1, Point p1, Point p2, boolean up) {
        int start_x = getMinX(p1, p2);
        int end_x = getMaxX(p1, p2);
        int start_y = getMinY(p1, p2);
        int end_y = getMaxY(p1, p2);

        for (int x = start_x; x <= end_x; x++) {
            for (int y = start_y; y <= end_y; y++) {
                grayWhiteSpaceVertically(up, x, y, image);
            }
        }
    }

    /**
     * Actually grays out the image horizontally.
     *
     * @param left true to gray out everything left of x_limit; false to gray
     * out everything right of x_limit
     * @param x_limit key x coord (gray out everything left or right of this)
     * @param y y value, which is fixed
     * @param image the image to be amended
     * @see #graySpaceRightLine(java.awt.image.BufferedImage,
     * org.bisel.spatialreasoning.model.Colour,
     * org.bisel.spatialreasoning.model.Point,
     * org.bisel.spatialreasoning.model.Point)
     * @see #graySpaceLeftLine(java.awt.image.BufferedImage,
     * org.bisel.spatialreasoning.model.Colour,
     * org.bisel.spatialreasoning.model.Point,
     * org.bisel.spatialreasoning.model.Point)
     */
    private void grayWhiteSpaceHorizontally(boolean left, int x_limit, int y, BufferedImage image) {
        int start = 0;
        int end = 99;

        if (left) {
            end = x_limit;
        } else {
            start = x_limit;
        }

        for (int x = start; x <= end; x++) {
            processPixel(image, x, y);
        }
    }

    /**
     * Actually grays out the image vertically.
     *
     * @param up true to gray out everything above y_limit; false to gray out
     * everything below of y_limit
     * @param x x valie, which is fixed
     * @param y_limit key y coord (gray out everything above or below of this)
     * @param image the image to be amended
     * @see #graySpaceAboveLine(java.awt.image.BufferedImage,
     * org.bisel.spatialreasoning.model.Colour,
     * org.bisel.spatialreasoning.model.Point,
     * org.bisel.spatialreasoning.model.Point)
     * @see #graySpaceBelowLine(java.awt.image.BufferedImage,
     * org.bisel.spatialreasoning.model.Colour,
     * org.bisel.spatialreasoning.model.Point,
     * org.bisel.spatialreasoning.model.Point)
     */
    private void grayWhiteSpaceVertically(boolean up, int x, int y_limit, BufferedImage image) {
        int start = 0;
        int end = 99;

        if (up) {
            end = y_limit;
        } else {
            start = y_limit;
        }
        for (int y = start; y <= end; y++) {
            processPixel(image, x, y);
        }
    }

    /**
     * Compares two Points and returns the minimum x value
     *
     * @param p1 point 1
     * @param p2 point 2
     * @return lowest x value from input points
     * @see org.bisel.imageprocessing.model.Point
     */
    private int getMinX(Point p1, Point p2) {
        if (p1.getX() < p2.getX()) {
            return p1.getX();
        } else if (p1.getX() > p2.getX()) {
            return p2.getX();
        }
        return p1.getX();
    }

    /**
     * Compares two Points and returns the maximum x value
     *
     * @param p1 point 1
     * @param p2 point 2
     * @return highest x value from input points
     * @see org.bisel.imageprocessing.model.Point
     */
    private int getMaxX(Point p1, Point p2) {
        if (p1.getX() < p2.getX()) {
            return p2.getX();
        } else if (p1.getX() > p2.getX()) {
            return p1.getX();
        }
        return p1.getX();
    }

    /**
     * Compares two Points and returns the minimum y value
     *
     * @param p1 point 1
     * @param p2 point 2
     * @return lowest y value from input points
     * @see org.bisel.imageprocessing.model.Point
     */
    private int getMinY(Point p1, Point p2) {
        if (p1.getY() < p2.getY()) {
            return p1.getY();
        } else if (p1.getY() > p2.getY()) {
            return p2.getY();
        }
        return p1.getY();
    }

    /**
     * Compares two Points and returns the maximum y value
     *
     * @param p1 point 1
     * @param p2 point 2
     * @return highest y value from input points
     * @see org.bisel.imageprocessing.model.Point
     */
    private int getMaxY(Point p1, Point p2) {
        if (p1.getY() < p2.getY()) {
            return p2.getY();
        } else if (p1.getY() > p2.getY()) {
            return p1.getY();
        }
        return p1.getY();
    }

    //
    //
    //    
    
    /**
     * Draws a line between 2 supplied points.  The colour of the line is the same as the 
     * colour of point 1.  Uses 
     * @param image the image on which to draw the line
     * @param c1 colour of point 1
     * @param x1 x value of point 1
     * @param y1 y value of point 1
     * @param c2 colour of point 2
     * @param x2 x value of point 2
     * @param y2 y value of point 2
     * @return the image with a line drawn on it
     */
    public BufferedImage drawLine(BufferedImage image, Colour c1, int x1, int y1, Colour c2, int x2, int y2) {
        if (image != null) {
            ColorProcessor cp = new ColorProcessor(image);
            //cp.setColor(new java.awt.Color(-8388480));
            cp.setColor(c1.getRgb());
            cp.drawLine(x1, y1, x2, y2);

            // reinstate colour of end points
            BufferedImage temp = cp.getBufferedImage();
            reinstatePoints(temp, c1, x1, y1);
            reinstatePoints(temp, c2, x2, y2);
            return temp;
        }
        return image;
    }

    /**
     * Turns the given point a given colour.
     * @param image
     * @param colour the colour to make the point
     * @param x x value of point
     * @param y y value of point
     * @see #drawLine(java.awt.image.BufferedImage, org.bisel.spatialreasoning.model.Colour, int, int, org.bisel.spatialreasoning.model.Colour, int, int) 
     * @see org.bisel.spatialreasoning.model.Colour
     */
    private void reinstatePoints(BufferedImage image, Colour colour, int x, int y) {
        image.setRGB(x, y, colour.getRgb());
    }

    
    /**
     * Cannot remember the logic behind this, but give it a box and a direction
     * and it will gray out a part of the image.
     * 
     * @param box the coordinates for the area to be grayed out
     * @param direction one of: superior | inferior | left | right
     * @param image the image to be changed
     * @see BoundingBox
     * @see org.bisel.spatialreasoning.model.RelationshipType
     * @see java.awt.image.BufferedImage
     */
    public void greyWhiteSpace(BoundingBox box, String direction, BufferedImage image) {

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (direction.equals("superior")) {
                    int pos = box.getMinY();
                    if (pos <= y) {
                        processPixel(image, x, y);
                    }
                } else if (direction.equals("inferior")) {
                    int pos = box.getMaxY();
                    if (y <= pos) {
                        processPixel(image, x, y);
                    }
                } else if (direction.equals("left")) {
                    int pos = box.getMinX();
                    if (pos <= x) {
                        processPixel(image, x, y);
                    }
                } else if (direction.equals("right")) {
                    int pos = box.getMaxX();
                    if (x <= pos) {
                        processPixel(image, x, y);
                    }
                }
            }
        }
    }    

    /**
     * Looks at the RGB value of an individual pixel and grays it out depending
     * on its initial colour:
     * <ol>
     * <li>white is always grayed out</ol>
     * <li>points are never grayed out</li>
     * <li>every 2nd pixel in a coloured block is grayed out</li>
     * </ol>
     *
     * @param image the image to be amended
     * @param x x value of pixel
     * @param y y value of pixel
     */
    private void processPixel(BufferedImage image, int x, int y) {
        int rgb = image.getRGB(x, y);
        if (rgb == java.awt.Color.WHITE.getRGB()) {
            image.setRGB(x, y, java.awt.Color.GRAY.getRGB());
        } else if (rgb == -8912696 || rgb == -6724045 || rgb == -16777088 || rgb == -16744320 || rgb == -8388480) {
            // do nothing to points
        } else if (y % 2 == 0 && x % 2 == 0) {
            image.setRGB(x, y, java.awt.Color.GRAY.getRGB());
        }
    }

    public static void main(String[] args) {
        ReadWriteImage ri = new ReadWriteImage();
        BufferedImage image = ri.readBufferedImage("/Users/kcm/test.png");
        ProcessImage wi = new ProcessImage();

        Colour green = new Colour("green");
        Colour blue = new Colour("blue");
        Colour red = new Colour("red");

        Point p1 = wi.getInferiorPoint(green, image);
        Point p2 = wi.getSuperiorPoint(green, image);

        wi.graySpaceAboveLine(image, green, p1, p2);
        ri.writeImageToDisk(image, "/Users/kcm/above_dia.png");

        image = ri.readBufferedImage("/Users/kcm/test.png");
        wi.graySpaceBelowLine(image, green, p1, p2);
        ri.writeImageToDisk(image, "/Users/kcm/below_dia.png");

        image = ri.readBufferedImage("/Users/kcm/test.png");
        wi.graySpaceLeftLine(image, green, p1, p2);
        ri.writeImageToDisk(image, "/Users/kcm/left_dia.png");

        image = ri.readBufferedImage("/Users/kcm/test.png");
        wi.graySpaceRightLine(image, green, p1, p2);
        ri.writeImageToDisk(image, "/Users/kcm/right_dia.png");

        //
        //
        
        image = ri.readBufferedImage("/Users/kcm/test.png");
        p1 = wi.getSuperiorPoint(blue, image);
        p2 = wi.getInferiorPoint(blue, image);

        System.out.println("sup: " + p1);
        System.out.println("inf: " + p2);

        wi.graySpaceAboveLine(image, blue, p1, p2);
        ri.writeImageToDisk(image, "/Users/kcm/above_ver.png");

        image = ri.readBufferedImage("/Users/kcm/test.png");
        wi.graySpaceBelowLine(image, blue, p1, p2);
        ri.writeImageToDisk(image, "/Users/kcm/below_ver.png");

        image = ri.readBufferedImage("/Users/kcm/test.png");
        wi.graySpaceLeftLine(image, blue, p1, p2);
        ri.writeImageToDisk(image, "/Users/kcm/left_ver.png");

        image = ri.readBufferedImage("/Users/kcm/test.png");
        wi.graySpaceRightLine(image, blue, p1, p2);
        ri.writeImageToDisk(image, "/Users/kcm/right_ver.png");

        //
        //
        
        image = ri.readBufferedImage("/Users/kcm/test.png");
        p1 = wi.getLeftPoint(red, image);
        p2 = wi.getRightPoint(red, image);

        System.out.println("lef: " + p1);
        System.out.println("rig: " + p2);

        wi.graySpaceAboveLine(image, red, p1, p2);
        ri.writeImageToDisk(image, "/Users/kcm/above_hor.png");

        image = ri.readBufferedImage("/Users/kcm/test.png");
        wi.graySpaceBelowLine(image, red, p1, p2);
        ri.writeImageToDisk(image, "/Users/kcm/below_hor.png");

        image = ri.readBufferedImage("/Users/kcm/test.png");
        wi.graySpaceLeftLine(image, red, p1, p2);
        ri.writeImageToDisk(image, "/Users/kcm/left_hor.png");

        image = ri.readBufferedImage("/Users/kcm/test.png");
        wi.graySpaceRightLine(image, red, p1, p2);
        ri.writeImageToDisk(image, "/Users/kcm/right_hor.png");

        //
        //
        
        image = ri.readBufferedImage("/Users/kcm/test.png");
        p1 = wi.getLeftPoint(red, image);
        //p2 = wi.getRightPoint(red, image);

        wi.grayAbovePoint(image, p1);
        ri.writeImageToDisk(image, "/Users/kcm/above_point.png");

        image = ri.readBufferedImage("/Users/kcm/test.png");
        wi.grayBelowPoint(image, p1);
        ri.writeImageToDisk(image, "/Users/kcm/below_point.png");

    }
}
