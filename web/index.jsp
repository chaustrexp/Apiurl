<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>API REST con Java Servlets</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <!-- CSS externo separado del HTML → web/Css/index_jsp.css -->
    <link rel="stylesheet" href="Css/index_jsp.css?v=2">
</head>
<body>

    <div class="hero">
        <div class="badge"><span class="blue-dot"></span> JAVA EE · SERVLET + JSP</div>

        <h1>API REST<br><span class="highlight">con Servlets</span></h1>

        <p class="subtitle">
            Proyecto de práctica: consume la <strong>API de Colombia</strong> y muestra los
            presidentes en una tabla HTML usando el patrón <strong>MVC</strong>.
        </p>

        <div class="stack-chips">
            <span class="chip">☕ Java 11+</span>
            <span class="chip">🌐 Jakarta Servlet</span>
            <span class="chip">📄 JSP</span>
            <span class="chip">📦 org.json</span>
            <span class="chip">🔗 HttpClient</span>
        </div>

        <a href="posts" id="btn-ver-posts" class="btn-primary">📋 Ver Presidentes</a>

        <div class="info-box">
            <h3>FLUJO DE LA PETICIÓN</h3>
            <div class="flow-container">
                <div class="flow-row">
                    <span class="flow-item">Navegador</span>
                    <span class="flow-arrow">→</span>
                    <span class="flow-item">PostsServlet.java</span>
                    <span class="flow-arrow">→</span>
                    <span class="flow-item">ApiModelo.java</span>
                    <span class="flow-arrow">→</span>
                    <span class="flow-item">API Colombia</span>
                </div>
                <div class="flow-row flow-row-2">
                    <span class="flow-arrow-down">↘</span>
                    <span class="flow-item">posts.jsp</span>
                    <span class="flow-arrow">→</span>
                    <span class="flow-item">HTML Tabla</span>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
