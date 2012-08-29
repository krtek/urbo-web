
<%@ page import="cz.urbo.cases.Photo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
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
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>

                <img class="urbo-image"
                     src="${createLink(controller: 'apiFeedback', action:'getPhotoThumbnail', id: photoInstance.id)}"/>
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${photoInstance?.id}" />
					<div class="form-actions">
                        <g:link class="btn" action="rotateLeft" id="${photoInstance?.id}">
                            <i class="icon-arrow-left"></i>
                            <g:message code="default.button.edit.rotate_left" default="Rotate left" />
                        </g:link>

                        <g:link class="btn" action="rotateRight" id="${photoInstance?.id}">
                            <i class="icon-arrow-right"></i>
                            <g:message code="default.button.edit.rotate_right" default="Rotate right" />
                        </g:link>
                        <g:link class="btn" action="edit" id="${photoInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.replace" default="Replace" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
