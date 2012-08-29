<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="user"/>

    <title>Urbo: zlepši svět!</title>

    <!-- STYLES -->
    <link href="${resource(dir: 'css', file: 'shadowbox.css')}" rel="stylesheet" type="text/css">
    <link href="${resource(dir: 'css', file: 'urbo.css')}" rel="stylesheet" type="text/css">


    <!-- SCRIPTS -->
    <script type="text/javascript" src="${resource(dir: 'js', file: 'shadowbox.js')}"></script>
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAbSOm6FoQBeLIAuIoSCPE3oks4k830KSE&sensor=false"></script>
    <script type="text/javascript">
        function initialize() {
            var mapOptions = {
                center: new google.maps.LatLng(50, 15),
                zoom: 7,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map-canvas"),
                    mapOptions);
            var infowindow = new google.maps.InfoWindow({content: null});
            <g:each in="${allFeedbacks}" var="feedback">
            (function(){
                var location = new google.maps.LatLng(${feedback.location.latitude}, ${feedback.location.longitude});
                //copy paste from below - see right panel
                var desc = '<h5 class="urbo-item-title">'+
                            '<span class="label label-info" style="float: right; margin-left:10px;">${feedback.state.description}</span>' +
                            '<g:link controller="map" action="detail" id="${feedback.id}">' +
                            '<div class="urbo-thumbnail-container-small">' +
                            '<img class="urbo-thumbnail" src="${createLink(controller: "apiFeedback", action:"getSquareThumbnail", id: feedback.photo?.id, params: ["width": "60"])}"/>' +
                            '</div>' +
                            '<div>${feedback.title}</div>' +
                            '</g:link>' +
                            '</h5>' +
                            '<div class="urbo-item-footer">' +
                            '<h6>${feedback.author}<br/>' +
                            '<g:formatDate format="${grailsApplication.config.urbo.dateFormat}" date="${feedback.dateCreated}"/></h6>' +
                            '</div>'
                var marker = new google.maps.Marker({map:map, draggable:true, position: location, title:"${feedback.title}", icon: "${resource(dir: 'images', file: 'star-3.png')}"});
                google.maps.event.addListener(marker, 'click', function() {
                    if (infowindow) {
                        infowindow.close();
                    }
                    infowindow = new google.maps.InfoWindow({content: desc});
                    infowindow.open(map,marker);
                });
            })();
            </g:each>
        }
    </script>
</head>
<body>
<div class="row-fluid">
    <div class="span8">
        <div id="map-canvas" class="thumbnail"></div>
    </div>
    <div class="span4">
        <g:each in="${lastFeedbacks}" var="feedback">
            <div class="well urbo-item">
                <h5 class="urbo-item-title">
                    <span class="label label-info" style="float: right;margin-left:10px;">${feedback.state.description}</span>
                    <g:link controller="map" action="detail" id="${feedback.id}">
                        <div class="urbo-thumbnail-container-small">
                            <img class="urbo-image thumbnail" src="${createLink(controller: 'apiFeedback', action:'getSquareThumbnail', id: feedback.photo?.id, params: ['width': '60'])}"/>
                        </div>
                        <div>${feedback.title}</div>
                    </g:link>
                </h5>
                <div class="urbo-item-footer">
                    <h6>${feedback.author}<br/>
                    <g:formatDate format="${grailsApplication.config.urbo.dateFormat}" date="${feedback.dateCreated}"/></h6>
                </div>
            </div>
        </g:each>
    </div>
</div>


<!-- Map initialization-->
<script type="text/javascript">
    jQuery(function($) {
        initialize()
    });
    Shadowbox.init({
        handleOversize: "resize",
        modal: false
    });
</script>
</body>
</html>