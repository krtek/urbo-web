<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <r:require module="jquery"/>
    <title>Urbo: ${feedback.title}</title>
    <!-- STYLES -->
    <link href="${resource(dir: 'css', file: 'urbo.css')}" rel="stylesheet" type="text/css">
    <link href="${resource(dir: 'css', file: 'shadowbox.css')}" rel="stylesheet" type="text/css">
    <!-- SCRIPT -->
    <script type="text/javascript" src="${resource(dir: 'js', file: 'shadowbox.js')}"></script>

    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAbSOm6FoQBeLIAuIoSCPE3oks4k830KSE&sensor=false"></script>
    <script type="text/javascript">
        function initialize() {
            var mapOptions = {
                center: new google.maps.LatLng(${feedback.location.latitude}, ${feedback.location.longitude}),
                zoom: 15,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map-thumbnail"),
                    mapOptions);
            var infowindow = new google.maps.InfoWindow({content: null});
            (function(){
                var location = new google.maps.LatLng(${feedback.location.latitude}, ${feedback.location.longitude});
                var desc = '<div id="content"><h4>${feedback.title}</h4>${feedback.description?.markdownToHtml()}<h6>Vytvořil: ${feedback.author}</h6></div>'
                var marker = new google.maps.Marker({map:map, draggable:true, position: location, title:"${feedback.title}"});
                google.maps.event.addListener(marker, 'click', function() {
                    if (infowindow) {
                        infowindow.close();
                    }
                    infowindow = new google.maps.InfoWindow({content: desc});
                    infowindow.open(map,marker);
                });
            })();
        }
    </script>

</head>
<body>
<div class="row-fluid">
    <div class="span8">
        <h1>${feedback.title}</h1>
        <hr/>
        <div class="well">
            <h2>Popis problému:</h2>
            <p>${feedback.description?.markdownToHtml()}</p>
            <h6>${feedback.author}, <g:formatDate format="${grailsApplication.config.urbo.dateFormat}" date="${feedback.dateCreated}"/></h6>
            <h6>Poslední změna: <g:formatDate format="${grailsApplication.config.urbo.dateFormat}" date="${feedback.lastUpdated}"/></h6>
        </div>
        <div class="well">
            <h2>Odpověď úřadu:</h2>
            <g:if test="${feedback.authorityResponse}">
                <p>${feedback.authorityResponse.content?.markdownToHtml()}</p>
            </g:if>
            <g:else>
                <p>Odpověď zatím nepřišla...</p>
            </g:else>
            </div>
    </div>
    <div class="well span3">
        <a href="${createLink(controller: 'apiFeedback', action:'getPhoto', id: feedback.photo?.id)}" rel="shadowbox[images];player=img" title="${feedback.title}">
            <img class="urbo-image"
                 src="${createLink(controller: 'apiFeedback', action:'getPhoto', id: feedback.photo?.id)}"/>
        </a>

        <hr/>
        <div id="map-thumbnail"></div>
    </div>
</div>


<!-- Map initialization-->
<script type="text/javascript">
    jQuery(function($) {
        initialize();
    });
    Shadowbox.init({
        handleOversize: "resize",
        modal: false
    });
</script>
</body>
</html>