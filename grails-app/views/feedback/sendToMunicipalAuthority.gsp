<%@ page import="cz.urbo.cases.Feedback" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="admin">
    <g:set var="entityName" value="${message(code: 'feedback.label', default: 'Feedback')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
    <script type="text/javascript"
            src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAbSOm6FoQBeLIAuIoSCPE3oks4k830KSE&sensor=false"></script>
    <style>

    .urbo-item-map-view {
        display: inline;
    }

    </style>
</head>

<body>
<div class="row-fluid">

    <div class="span3">
        <div class="well">
            <ul class="nav nav-list">
                <li class="nav-header">${entityName}</></li>
                <li>
                    <g:link class="list" action="list">
                        <i class="icon-list"></i>
                        <g:message code="default.list.label" args="[entityName]"/>
                    </g:link>
                </li>
                <li>
                    <g:link class="create" action="create">
                        <i class="icon-plus"></i>
                        <g:message code="default.create.label" args="[entityName]"/>
                    </g:link>
                </li>
            </ul>
        </div>
    </div>

    <div class="span9">

        <div class="page-header">
            <h1>Urbo Review Before Sending To Authority</h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <g:form>
            <dl>
                <g:if test="${feedbackInstance?.title}">
                    <dt><g:message code="feedback.title.label" default="Title"/></dt>

                    <dd><g:field value="${feedbackInstance?.title}" max="255" name="title" type="text"/></dd>

                </g:if>

                <g:if test="${feedbackInstance?.description}">
                    <dt><g:message code="feedback.description.label" default="Description"/></dt>

                    <dd><g:fieldValue bean="${feedbackInstance}" field="description"/></dd>

                </g:if>

                <g:if test="${feedbackInstance?.location}">
                    <dt>
                        <g:message code="feedback.location.label" default="Location"/>
                    </dt>

                    <dd>

                        <div class="urbo-item-map-view">
                            <!-- map -->
                            <div id="urbo-item-map" style="width: 300px; height: 300px;"></div>

                            <!-- streetview to validate if photo is right -->
                            <div id="urbo-item-streetview" style="width: 300px; height: 300px;"></div>

                        </div>

                        <g:link controller="location" action="show"
                                id="${feedbackInstance?.location?.id}">${feedbackInstance?.location?.encodeAsHTML()}</g:link>
                        (<span class="address">${feedbackInstance?.location.toAddress()}</span>)
                    </dd>

                </g:if>

                <g:if test="${feedbackInstance?.photo}">
                    <dt><g:message code="feedback.photo.label" default="Photo"/></dt>

                    <dd>
                        <img src="${createLink(controller: "apiFeedback", action: "getPhotoThumbnail", id: feedbackInstance?.photo?.id)}"/>
                    </dd>

                    <dd>
                        <g:link controller="photo" action="show" id="${feedbackInstance?.photo?.id}"><g:message
                                code="feedback.photo.edit" default="Edit"/></g:link>
                    </dd>

                </g:if>


                <g:if test="${feedbackInstance?.author}">
                    <dt><g:message code="feedback.author.label" default="Author"/></dt>
                    <dd><g:link controller="author" action="show"
                                id="${feedbackInstance?.author?.id}">${feedbackInstance?.author?.encodeAsHTML()}</g:link></dd>
                </g:if>

                <g:if test="${feedbackInstance?.dateCreated}">
                    <dt><g:message code="feedback.dateCreated.label" default="Date Created"/></dt>

                    <dd><g:formatDate date="${feedbackInstance?.dateCreated}"/></dd>

                </g:if>

                <g:if test="${feedbackInstance?.lastUpdated}">
                    <dt><g:message code="feedback.lastUpdated.label" default="Last Updated"/></dt>

                    <dd><g:formatDate date="${feedbackInstance?.lastUpdated}"/></dd>

                </g:if>

                <g:if test="${municipalAuthorityMail}">
                    <dt><g:message code="feedback.municipalAuthorityEmail.label" default="Municipal authority email"/></dt>
                    <dd><g:field type="text" value="${municipalAuthorityMail}" name="sentToMunicipalAuthorityEmail"/></dd>
                </g:if>

            </dl>


            <g:hiddenField name="id" value="${feedbackInstance?.id}"/>
            <div class="form-actions">

                <button class="btn" name="_action_edit" type="submit">
                %{--<g:actionSubmit class="btn" action="edit" value="Update">--}%
                    <i class="icon-pencil"></i>
                    <g:message code="default.button.update.label" default="Update"/>
                %{--</g:actionSubmit>--}%
                </button>

                <g:link class="btn btn-success" controller="feedback" action="sendUrboItemToMunicipalAuthority">
                    <i class="icon-ok"></i>
                    <g:message code="default.sendEmail.label" default="Send email"/>
                </g:link>

                <button class="btn btn-danger" type="submit" name="_action_delete">
                    <i class="icon-trash icon-white"></i>
                    <g:message code="default.button.delete.label" default="Delete"/>
                </button>

            </div>
        </g:form>

    </div>

</div>

<script type="text/javascript">

    function initializeMapWithUrboItem() {
        var urboItemLocation = new google.maps.LatLng(
                ${feedbackInstance.location.latitude},
                ${feedbackInstance.location.longitude});

        var mapOptions = {
            center:urboItemLocation,
            zoom:15,
            mapTypeId:google.maps.MapTypeId.ROADMAP
        };

        var map = new google.maps.Map(document.getElementById("urbo-item-map"), mapOptions);

        var urboItemMarker = new google.maps.Marker({
            position:urboItemLocation,
            title:'${feedbackInstance?.title ?: "n/a"}'
        });

        urboItemMarker.setMap(map);

        // --- streetview panorama

        var panoramaOptions = {
            position:urboItemLocation,
            pov:{
                heading:34,
                pitch:10,
                zoom:1
            }
        };
        var panorama = new google.maps.StreetViewPanorama(document.getElementById("urbo-item-streetview"), panoramaOptions);
        map.setStreetView(panorama);
    }

    jQuery(document).ready(function ($) {
        initializeMapWithUrboItem();
    });

</script>

</body>
</html>
