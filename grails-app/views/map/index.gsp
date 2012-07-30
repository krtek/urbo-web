<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <title>Urbo Mapa</title>
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAbSOm6FoQBeLIAuIoSCPE3oks4k830KSE&sensor=false"></script>
    <script type="text/javascript">
        function initialize() {
            var mapOptions = {
                center: new google.maps.LatLng(50, 15),
                zoom: 7,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map_canvas"),
                    mapOptions);
            var infowindow = new google.maps.InfoWindow({content: null});
            <g:each in="${feedbacks}" var="feedback">
            (function(){
                var location = new google.maps.LatLng(${feedback.location.latitude}, ${feedback.location.longitude});
                var desc = '<div id="content"><h4>${feedback.title}</h4>${feedback.description.encodeAsHTML().replaceAll("\n","<br/>")}<h6>Vytvořil: ${feedback.author}</h6></div>'
                var marker = new google.maps.Marker({map:map, draggable:true, position: location, title:"${feedback.title}"});
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
        <h3>Urbo mapa</h3>
        <hr/>
        <div id="map_canvas" style="width:100%; height:500px;"></div>
    </div>
    <div class="span4">
        <h3>Poslední kauzy</h3>
        <hr/>
        <g:each in="${feedbacks}" var="feedback">
            <div class="well">
                <h4>${feedback.title}&nbsp;<span class="label label-info">Nový</span></h4>
                <br/>
                <div>${feedback.description.encodeAsHTML().replaceAll("\n","<br/>")}</div>
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
</script>
</body>
</html>