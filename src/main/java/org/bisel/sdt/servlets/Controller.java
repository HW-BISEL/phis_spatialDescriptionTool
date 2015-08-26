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
package org.bisel.sdt.servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bisel.imageprocessing.GetProperties;
import org.bisel.imageprocessing.ReadWriteImage;
import org.bisel.imageprocessing.ProcessImage;
import org.bisel.spatialreasoning.model.BoundingBox;
import org.bisel.spatialreasoning.ReasoningImpl;
import org.bisel.spatialreasoning.model.Colour;
import org.bisel.spatialreasoning.model.Description;
import org.bisel.spatialreasoning.model.Point;
import org.bisel.spatialreasoning.model.Relationship;
import org.bisel.spatialreasoning.model.Semantics;
import org.json.simple.JSONObject;

/**
 *
 * @author kcm
 */
@WebServlet(name = "Control", urlPatterns = {"/process"})
public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        boolean success = true;
        String message = "";

        String image = request.getParameter("image");
        String semantics = request.getParameter("semantics");
        String[] left = request.getParameterValues("left");
        String[] right = request.getParameterValues("right");
        String[] superior = request.getParameterValues("superior");
        String[] inferior = request.getParameterValues("inferior");
        String[] line = request.getParameterValues("line");

        HashSet<Description> sd = new HashSet();
        /*
         * Descriptions for the form ROI reln colour are meaningless for BSPO;
         * you must use colour reln ROI.
         *
         * For 'all' semantics, either form works.
         *
         * For simplicity we shall only use colour reln ROI regardless of semantics
         * chosen.
         */
        try {
            for (String temp : left) {
                sd.add(new Description(new Colour(temp), new Relationship("left"), new Colour("roi")));
            }
        } catch (Exception e) {
            // ignore null pointer
        }
        try {
            for (String temp : right) {
                sd.add(new Description(new Colour(temp), new Relationship("right"), new Colour("roi")));
            }
        } catch (Exception e) {
            // ignore null
        }
        try {
            for (String temp : superior) {
                sd.add(new Description(new Colour(temp), new Relationship("superior"), new Colour("roi")));
            }
        } catch (Exception e) {
            // ignore null
        }
        try {
            for (String temp : inferior) {
                sd.add(new Description(new Colour(temp), new Relationship("inferior"), new Colour("roi")));
            }
        } catch (Exception e) {
            // ignore null
        }
        try {
            for (String temp : line) {
                StringTokenizer st = new StringTokenizer(temp, "£");
                sd.add(new Description(new Colour(st.nextToken()), new Relationship("line"), new Colour(st.nextToken())));
            }
        } catch (Exception e) {

        }

        ReasoningImpl r = new ReasoningImpl(sd, Semantics.bspo);
        sd = r.getSpatialDescription(); // optimised

        // get image
        BufferedImage bi = null;
        String name = "";
        ReadWriteImage rwi = new ReadWriteImage();
        BoundingBox bb = new BoundingBox(0, 0, 0, 0);
        try {
            bi = rwi.readBufferedImage(GetProperties.getPath() + image + ".png");
        } catch (Exception e) {
            message = "image not found";
            success = false;
        }

        if (success && bi != null) {
            ProcessImage wi = new ProcessImage();

            // draw lines
            /*
            for (Description tempD : sd) {
                LOG.log(Level.OFF, tempD.toString());
                if (tempD.getReln().equals(new Relationship("line"))) {
                    BoundingBox one = bb.generateBoundingBox(tempD.getColour1(), bi);
                    BoundingBox two = bb.generateBoundingBox(tempD.getColour2(), bi);
                    LOG.log(Level.OFF, "({0}, {1}) ({2}, {3})", new Object[]{one.getMinX(), one.getMinY(), two.getMinX(), two.getMinY()});
                    bi = wi.drawLine(bi, tempD.getColour1(), one.getMinX(), one.getMinY(), tempD.getColour2(), two.getMinX(), two.getMinY());
                }
            }
            */

            // grey out
            try {
                Point tempPoint;
                for (Description tempD : sd) {
                    if (semantics.equals("all")) {
                        // pink superior roi = grey everything superior to most inferior point of pink
                        if (tempD.getReln().equals(new Relationship("superior"))) {
                            tempPoint = wi.getInferiorPoint(tempD.getColour1(), bi);
                            wi.grayAbovePoint(bi, tempPoint);
                            //BoundingBox tempB = bb.generateBoundingBox(tempD.getColour1(), bi);
                            //wi.greyWhiteSpace(tempB, "superior", bi);
                        } else if (tempD.getReln().equals(new Relationship("inferior"))) {
                            tempPoint = wi.getSuperiorPoint(tempD.getColour1(), bi);
                            wi.grayBelowPoint(bi, tempPoint);
                            //BoundingBox tempB = bb.generateBoundingBox(tempD.getColour1(), bi);
                            //wi.greyWhiteSpace(tempB, "inferior", bi);
                        } else if (tempD.getReln().equals(new Relationship("left"))) {
                            tempPoint = wi.getRightPoint(tempD.getColour1(), bi);
                            wi.grayLeftPoint(bi, tempPoint);
                            //BoundingBox tempB = bb.generateBoundingBox(tempD.getColour1(), bi);
                            //wi.greyWhiteSpace(tempB, "left", bi);
                        } else if (tempD.getReln().equals(new Relationship("right"))) {
                            tempPoint = wi.getLeftPoint(tempD.getColour1(), bi);
                            wi.grayRightPoint(bi, tempPoint);
                            //BoundingBox tempB = bb.generateBoundingBox(tempD.getColour1(), bi);
                            //wi.greyWhiteSpace(tempB, "right", bi);
                        }
                    } else if(semantics.equals("bspo")) {
                        // pink superior roi = grey everything superior to most superior point of pink                        
                        if (tempD.getReln().equals(new Relationship("superior"))) {
                            tempPoint = wi.getSuperiorPoint(tempD.getColour1(), bi);
                            wi.grayAbovePoint(bi, tempPoint);
                        } else if(tempD.getReln().equals(new Relationship("inferior"))) {
                            tempPoint = wi.getInferiorPoint(tempD.getColour1(), bi);
                            wi.grayBelowPoint(bi, tempPoint);
                        } else if(tempD.getReln().equals(new Relationship("left"))) {
                            tempPoint = wi.getLeftPoint(tempD.getColour1(), bi);
                            wi.grayLeftPoint(bi, tempPoint);
                        } else if(tempD.getReln().equals(new Relationship("right"))) {
                            tempPoint = wi.getRightPoint(tempD.getColour1(), bi);
                            wi.grayRightPoint(bi, tempPoint);
                        }
                    }                    
                }
            } catch (Exception e) {
                LOG.log(Level.SEVERE, e.getMessage());
            }


            // write out
            if (bi == null) {
                success = false;
                message = "no valid relationship supplied";
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                name = formatter.format(new Date());
                rwi.writeImageToDisk(bi, GetProperties.getPath() + name + ".png");
            }
        }

        try (PrintWriter out = response.getWriter()) {
            JSONObject obj = new JSONObject();
            if (success) {
                obj.put("result", "success");
                obj.put("image", GetProperties.getURI() + name + ".png");
            } else {
                obj.put("result", "fail");
                obj.put("error", message);
            }
            out.print(obj);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
