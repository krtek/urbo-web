<%@ page import="cz.urbo.cases.Feedback" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'feedback.label', default: 'Feedback')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="row-fluid">

    <div class="span3">
        <div class="well">
            <ul class="nav nav-list">
                <li class="nav-header">${entityName}</li>
                <li class="active">
                    <g:link class="list" action="list">
                        <i class="icon-list icon-white"></i>
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
            <h1><g:message code="default.list.label" args="[entityName]"/></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <table class="table table-striped">
            <thead>
            <tr>

                <g:sortableColumn property="title" title="${message(code: 'feedback.title.label', default: 'Title')}"/>

                <g:sortableColumn property="description"
                                  title="${message(code: 'feedback.description.label', default: 'Description')}"/>

                <th class="header"><g:message code="feedback.location.label" default="Location"/></th>

                <th class="header"><g:message code="feedback.author.label" default="Author"/></th>

                <th class="header"><g:message code="feedback.photo.label" default="Photo"/></th>

                <th class="header"><g:message code="feedback.authorityResponse.label"
                                              default="Authority Response"/></th>

                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${feedbackInstanceList}" var="feedbackInstance">
                <tr>

                    <td>${fieldValue(bean: feedbackInstance, field: "title")}</td>

                    <td>${fieldValue(bean: feedbackInstance, field: "description")}</td>

                    <td>${fieldValue(bean: feedbackInstance, field: "location")}</td>

                    <td>${fieldValue(bean: feedbackInstance, field: "author")}</td>

                    <td>${fieldValue(bean: feedbackInstance, field: "photo")}</td>

                    <td>${fieldValue(bean: feedbackInstance, field: "authorityResponse")}</td>

                    <td class="link">
                        <g:link action="show" id="${feedbackInstance.id}" class="btn btn-small">Show &raquo;</g:link>
                        <g:link action="sendUrboItemToMunicipalAuthority" id="${feedbackInstance.id}" class="btn btn-small">Send to municipal authority&raquo;</g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <bootstrap:paginate total="${feedbackInstanceTotal}"/>
        </div>
    </div>

</div>
</body>
</html>
