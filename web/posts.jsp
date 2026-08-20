<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, java.util.Map"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Presidentes de Colombia · API</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <!-- CSS externo separado del HTML → web/Css/posts_jsp.css -->
    <link rel="stylesheet" href="Css/posts_jsp.css">
</head>
<body>

<div class="top-bar-posts">
    <a href="index.jsp" class="btn-back">← Volver al Inicio</a>
</div>

<%-- ═══ Recuperar atributos enviados por el Servlet ═══ --%>
<%
    List<Map<String, Object>> listaPresidentes =
        (List<Map<String, Object>>) request.getAttribute("listaPresidentes");
    Integer totalPresidentes  = (Integer) request.getAttribute("totalPresidentes");
    Integer statusCode  = (Integer) request.getAttribute("statusCode");
    String  errorMensaje = (String) request.getAttribute("errorMensaje");
%>

<%-- ═══ Si hay error, mostrarlo ═══ --%>
<% if (errorMensaje != null) { %>
<div class="error-box">
    <h2>⚠️ Error al obtener datos</h2>
    <p><%= errorMensaje %></p>
</div>
<% } else { %>

<%-- ═══ Cabecera de la página ═══ --%>
<div class="page-header">
    <div class="header-left">
        <div class="title-row">
            <span class="badge-co">CO</span>
            <h1>Presidentes de Colombia</h1>
        </div>
        <p class="subtitle-data">
            <span class="icon-db">🗄️</span> Datos obtenidos de <strong>https://api-colombia.com/api/v1/President</strong> · HTTP <%= statusCode %>
        </p>
    </div>
    <div class="header-right">
        <div class="stat-box">
            <span class="stat-number text-blue" id="total-visible"><%= totalPresidentes %></span>
            <span class="stat-label">PRESIDENTES TOTALES</span>
        </div>
        <div class="stat-box">
            <span class="stat-number text-red"><%= statusCode %></span>
            <span class="stat-label">HTTP STATUS</span>
        </div>
    </div>
</div>

<%-- ═══ Barra de búsqueda ═══ --%>
<div class="search-bar-wrap">
    <span class="search-icon">🔍</span>
    <input type="text" id="buscador" placeholder="Buscar por nombre o partido..." oninput="filtrarTabla()" autocomplete="off">
</div>

<%-- ═══ Grid de Presidentes ═══ --%>
<div class="president-grid" id="grid-container">

    <%-- ── Scriptlet JSP: recorre la lista y genera tarjetas ── --%>
    <% if (listaPresidentes != null && !listaPresidentes.isEmpty()) {
           for (Map<String, Object> presidente : listaPresidentes) {
               int    id          = (int)    presidente.get("id");
               String nombre     = (String) presidente.get("nombre");
               String partido    = (String) presidente.get("partido");
               String periodo    = (String) presidente.get("periodo");
               String descripcion = (String) presidente.get("descripcion");
    %>
    <div class="president-card">
        <div class="president-info">
            <span class="president-id">ID: <strong class="id-number"><%= id %></strong></span>
            <h3 class="president-name"><%= nombre %></h3>
            <div class="president-period">📅 <%= periodo %></div>
            <div class="president-party">
                <span class="party-badge"><%= partido %></span>
            </div>
            <p class="president-description"><%= descripcion %></p>
        </div>
    </div>
    <%   }  // fin for
       } else { %>
    <div class="no-results">
        No se encontraron presidentes.
    </div>
    <% } %>

</div>

<%-- ═══ Footer ═══ --%>
<div class="page-footer">
    <div class="footer-left">
        <strong>API_PROJECT</strong>
    </div>
    <div class="footer-center">
        © 2024 Java EE Colombian API Project - Built with Jakarta Servlet & MVC
    </div>
    <div class="footer-right">
        <a href="#">Source Code</a>
        <a href="#">API Provider</a>
        <a href="#">Terms</a>
        <a href="#">Privacy</a>
    </div>
</div>

<% } // fin del else (sin error) %>

<%-- ═══════════════════════════════════════════
     JavaScript para búsqueda
     ═══════════════════════════════════════════ --%>
<script>
    function filtrarTabla() {
        const input = document.getElementById('buscador').value.toLowerCase();
        const tarjetas = document.querySelectorAll('.president-card');
        let visibles = 0;
        
        tarjetas.forEach(tarjeta => {
            const texto = tarjeta.innerText.toLowerCase();
            if (texto.includes(input)) { 
                tarjeta.style.display = ''; 
                visibles++; 
            } else { 
                tarjeta.style.display = 'none'; 
            }
        });
        
        const totalVisibleEl = document.getElementById('total-visible');
        if (totalVisibleEl) {
            totalVisibleEl.textContent = visibles;
        }
    }
</script>

</body>
</html>
