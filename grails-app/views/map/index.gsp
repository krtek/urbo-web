<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <title>Urbo Mapa</title>
</head>
<body>
<div class="row-fluid">
    <div class="span8">
        <h2>Tady bude mapa</h2>
    </div>
    <div class="span4">
        <h3>Poslední kauzy</h3>
        <g:each in="${feedbacks}" var="feedback">
            <div class="well">
                <div><h4>${feedback.title}</h4></div>
                <br/>
                <div>${feedback.description}</div>
                <br/>
                <div><h6>Vytvořil: ${feedback.author}</h6></div>
            </div>
        </g:each>
    </div>
</div>
</body>
</html>