
<%@ page import="cz.urbo.cases.Photo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
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
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>

			<div class="span9">
				
				<div class="page-header">
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
                            <g:sortableColumn property="id" title="${message(code: 'photo.data.id', default: 'Id')}" />
							<g:sortableColumn property="data" title="${message(code: 'photo.data.label', default: 'Data')}" />
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${photoInstanceList}" var="photoInstance">
						<tr>

                            <td>${photoInstance.id}</td>
							<td><img class="urbo-image"
                                     src="${createLink(controller: 'apiFeedback', action:'getPhotoThumbnail', id: photoInstance.id)}"/></td>
						
							<td class="link">
								<g:link action="show" id="${photoInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${photoInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
