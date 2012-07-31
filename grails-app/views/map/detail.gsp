<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <title>Urbo Mapa</title>
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAbSOm6FoQBeLIAuIoSCPE3oks4k830KSE&sensor=false"></script>
</head>
<body>
<div class="row-fluid">
    <div class="well span8">
        <h1>${feedback.title}</h1>
        <hr/>
        <h2>Popis problému:</h2>
        <p>${feedback.description?.markdownToHtml()}</p>
        <h6>${feedback.author}, <g:formatDate format="${grailsApplication.config.urbo.dateFormat}" date="${feedback.dateCreated}"/></h6>
        <h6>Poslední změna: <g:formatDate format="${grailsApplication.config.urbo.dateFormat}" date="${feedback.lastUpdated}"/></h6>
        <hr />
        <h2>Odpověď úřadu:</h2>
        <g:if test="${feedback.authorityResponse}">
            <p>${feedback.authorityResponse?.markdownToHtml()}</p>
        </g:if>
        <g:else>
            <p>Odpověď zatím nepřišla...</p>
        </g:else>
    </div>
    <div class="well span3">
        <h3>Photo</h3>
    </div>
</div>


<!-- Map initialization-->
<script type="text/javascript">
    jQuery(function($) {

    });
</script>
</body>
</html>