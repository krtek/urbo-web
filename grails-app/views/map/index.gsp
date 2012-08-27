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
                var desc = '<div id="content"><h4><g:link controller="map" action="detail" id="${feedback.id}">${feedback.title}</g:link></h4>${feedback.description?.markdownToHtml()}<h6>Vytvořil: ${feedback.author}</h6></div>'
                var marker = new google.maps.Marker({map:map, draggable:true, position: location, title:"${feedback.title}", icon: "${resource(dir: 'images', file: 'skull.png')}"});
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
        <div id="map-canvas"></div>
    </div>
    <div class="span4">
        <g:each in="${lastFeedbacks}" var="feedback">
            <div class="well urbo-item">
                <span class="label label-info">${feedback.state.description}</span>
                <h4 class="urbo-item-title">
                    <g:link controller="map" action="detail" id="${feedback.id}">${feedback.title}</g:link>
                </h4>
                <br/>
                <div class="urbo-mini-detail">
                    <a href="${createLink(controller: 'apiFeedback', action:'getPhoto', id: feedback.photo?.id)}" rel="shadowbox[images];player=img" title="${feedback.title}">
                        <img class="urbo-thumbnail"
                             src="${createLink(controller: 'apiFeedback', action:'getPhotoThumbnail', id: feedback.photo?.id, width: 200, height: 200)}"/>
                    </a>
                    <div class="urbo-description">
                        ${feedback.description?.markdownToHtml()}
                    </div>
                </div>
                <br/>
                <h6>Vytvořil: ${feedback.author}</h6>
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