<%@ page import="cz.urbo.cases.Photo" %>



<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'data', 'error')} required">
	<label for="data">
		<g:message code="photo.data.label" default="Data" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="data" name="data" />
</div>

