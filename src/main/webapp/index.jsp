<%-- 
    Document   : index
    Created on : 04-Aug-2015, 17:32:56
    Author     : kcm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SDT</title>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />

    </head>
    <body>
        <div class="row">
            <div class="col-md-12">
                <center>
                    <h2>Spatial Description Tool</h2>
                </center>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
            </div>            
            <div class="col-md-4">
                <center><h5>Instructions</h5></center>
                <p>In the image below, the black box is the region of interest (ROI).</p>
                <p>The other colours represent different pseudo anatomical domains.</p>
                <p>The idea is to grey out as much of the image as possible, without greying out the ROI.</p>
                <p>To grey out an area of the image create a spatial description of the form: "<i>colour</i> is <i>relationship</i> to <b>ROI</b>". For example, ROI left blue.</p>
                <!-- <p>The example, ROI left blue, will grey out everything to the right of the blue shape including the blue block itself.</p> -->
                <p>When you are happy with your description, click the submit button and the image will refresh to show you what areas have been greyed out.</p>
            </div>
            <div class="col-md-4">
                <center>
                    <h5>Choose an image</h5>
                </center>
                <p>There are currently 6 different images you can use.</p>
                <p>The underlying anatomy is consistent across all 6 images, only the location of the ROI changes.</p>
                <p>When you use the listbox to select an image, the image below updates.</p>
                Which image?
                <select id="pic" onchange="pickImage()">
                    <option value="one">one</option>
                    <option value="two">two</option>
                    <option value="three">three</option>
                    <option value="four">four</option>
                    <option value="five">five</option>
                    <option value="six">six</option>
                </select>
            </div>
            <div class="col-md-2">
            </div>

            <br />
            <div class="row">
                <div class="col-md-12">
                    <center>
                        <b>Superior</b>
                    </center>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">                                   
                    <center><div id="image"><img width="300" height="300" src="<%= org.bisel.imageprocessing.GetProperties.getURI()%>one.png"/></div></center>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12"> 
                    <br />
                    <center><b>Inferior</b></center>
                </div>        
            </div>    

            <br />
            <hr />
            <br />


            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-3">                                
                    <h5>BSPO semantics</h5>
                    <p>One extremity of the block has a relationship to the same extremity of the ROI.</p>
                    <p>For example, <i>pink inferior ROI</i> means the most inferior point of pink is inferior to the most inferior point of the ROI.</p>
                </div>
                <div class="col-md-3">                                
                    <h5>All semantics</h5>
                    <p>The entire block (every point) must have the same relationship to the ROI.</p>
                    <p>For example, <i>pink inferior ROI</i> means ALL of the pink block is inferior to the ROI.</p>
                </div>                
                <div class="col-md-4">
                    <p>Which semantics do you wish to use?</p>
                    <select id="semantics">
                        <option value="all">ALL</option>
                        <option value="bspo">BSPO</option>
                    </select>
                </div>
                <div class="col-md-1"></div>
            </div>

            <br />
            <hr />
            <br />            

            <div class="row">         
                <div class="col-md-1"></div>
                <div class="col-md-3">                                
                    <h5>Instructions</h5>
                    <p>Build a spatial description using statements of the form:<br /><i>colour</i> is <i>relationship</i> to ROI.</p>     
                    <p>Click <i>add</i> to add your description to the list below.</p>
                </div>
                <div class="col-md-8">
                    <div>
                        The
                        <select id="shape">
                            <option value="blue">blue</option>
                            <option value="cyan">cyan</option>
                            <option value="green">green</option>
                            <option value="magenta">magenta</option>
                            <option value="pink">pink</option>    
                            <option value="orange">orange</option>        
                            <option value="red">red</option>
                            <option value="yellow">yellow</option>    
                        </select>
                        <strong>block</strong> is
                        <select id="reln">
                            <option value="left">left of</option>
                            <option value="right">right of</option>
                            <option value="superior">superior to</option>
                            <option value="inferior">inferior to</option>
                            <!--<option value="line">line between</option>-->
                        </select>  
                        <b>ROI</b>
                        <button onclick="addSD()">Add</button>
                    </div>
                    <br />
                    <div>
                        <div>
                            The
                            <select id="shapeP">
                                <option value="dark_blue">blue</option>
                                <option value="brown">brown</option>
                                <option value="dark_green">green</option>
                                <option value="purple">purple</option>
                            </select>
                            <strong>point</strong> is
                            <select id="relnP">
                                <option value="left">left of</option>
                                <option value="right">right of</option>
                                <option value="superior">superior to</option>
                                <option value="inferior">inferior to</option>
                                <!--<option value="line">line between</option>-->
                            </select>  
                            <b>ROI</b>                                                   
                            <button onclick="addSD_P()">Add</button>
                        </div>
                    </div>
                </div>
            </div>

            <br />
            <hr />
            <br />

            <div class="row">
                <div class="col-md-1">
                </div>
                <div class="col-md-3">                
                    <h5>Spatial Description you have built:</h5>
                </div>
                <div id="sdList" class="col-md-3">
                    <p>No description as yet; use the above form to create one.</p>
                </div>    
                <div class="col-md-3">
                    <button onclick="clearAll()">reset</button>
                    <!--<button onclick="submit()">submit</button>-->
                </div>
                <div class="col-md-2">                                
                </div>
            </div>


            <script>
                var sd = [];
                var pic = "one";

                function pickImage() {
                    pic = document.getElementById("pic").value;
                    document.getElementById("image").innerHTML = '<img width="300" height="300" src="<%= org.bisel.imageprocessing.GetProperties.getURI()%>' + pic + '.png"/>';
                    document.getElementById("sdList").innerHTML = "<p>No description as yet; use the above form to create one.</p>";
                    sd = [];
                }

                function addSD() {
                    var shape = document.getElementById("shape").value;
                    var reln = document.getElementById("reln").value;
                    sd.push(reln + "*" + shape);
                    drawSDList();
                }

                function addSD_P() {
                    var shape = document.getElementById("shapeP").value;
                    var reln = document.getElementById("relnP").value;
                    sd.push(reln + "*" + shape);
                    drawSDList();
                }

                function drawSDList() {
                    document.getElementById("sdList").innerHTML = "";
                    for (index = 0; index < sd.length; index++) {
                        var temp = sd[index].split("*");
                        if (temp[1].indexOf("£") === -1) {
                            document.getElementById("sdList").innerHTML += "<p>" + temp[1] + " " + temp[0] + " of ROI  <button onClick='removeSD(\"" + temp[0] + "*" + temp[1] + "\")'>remove</button></p>";
                        } else {
                            var temp2 = temp[1].split("£");
                            document.getElementById("sdList").innerHTML += "<p>Line from " + temp2[0] + " to " + temp2[1] + " <button onClick='removeSD(\"" + temp[0] + "*" + temp2[0] + "£" + temp2[1] + "\")'>remove</button></p>";
                        }
                    }
                    submit();
                }

                function removeSD(item) {
                    var tempArray = [];
                    for (index = 0; index < sd.length; index++) {
                        if (sd[index] !== item) {
                            tempArray.push(sd[index]);
                        }
                    }
                    sd = tempArray;
                    drawSDList();
                }

                function clearAll() {
                    document.getElementById("sdList").innerHTML = "<p>No description as yet; use the above form to create one.</p>";
                    document.getElementById("image").innerHTML = '<img width="300" height="300" src="<%= org.bisel.imageprocessing.GetProperties.getURI()%>one.png"/>';
                    sd = [];
                }

                function submit() {
                    var params = "";
                    for (index = 0; index < sd.length; index++) {
                        var temp = sd[index].split("*");
                        if (index !== 0) {
                            params += "&";
                        }
                        params += temp[0] + "=" + temp[1];
                    }

                    params += "&semantics=" + document.getElementById("semantics").value;

                    var queryURL = "<%= org.bisel.imageprocessing.GetProperties.getURI()%>SDT/process?image=" + pic + "&" + params;

                    $.getJSON(queryURL, function (data) {
                        if (data.result === "success") {
                            document.getElementById("image").innerHTML = "<img width=\"300\" height=\"300\" src=\"" + data.image + "\">";
                        } else {
                            alert("error:" + data.message);
                        }
                    });
                }

            </script>
    </body>
</html>
