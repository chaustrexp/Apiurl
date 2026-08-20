package servlet;

import ApiModelo.ApiModelo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "PostsServlet", urlPatterns = {"/posts"})
public class PostsServlet extends HttpServlet {

    // URL de la API de Colombia (Presidentes)
    private static final String API_URL = "https://api-colombia.com/api/v1/President";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ApiModelo apiModelo = new ApiModelo(API_URL);
        apiModelo.hacer_peticion_get();

        List<Map<String, Object>> listaPresidentes = new ArrayList<>();
        int statusCode = apiModelo.getStatusCode();

        if (statusCode == 200) {
            try {
                JSONArray array = new JSONArray(apiModelo.getBody());

                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);

                    Map<String, Object> presidente = new LinkedHashMap<>();
                    presidente.put("id", obj.getInt("id"));
                    
                    String nombreCompleto = obj.getString("name");
                    if (obj.has("lastName") && !obj.isNull("lastName")) {
                        nombreCompleto += " " + obj.getString("lastName");
                    }
                    presidente.put("nombre", nombreCompleto);
                    
                    String partido = obj.has("politicalParty") && !obj.isNull("politicalParty") 
                            ? obj.getString("politicalParty") 
                            : "Desconocido";
                    presidente.put("partido", partido);
                    
                    String inicio = obj.has("startPeriodDate") && !obj.isNull("startPeriodDate") ? obj.getString("startPeriodDate") : "?";
                    String fin = obj.has("endPeriodDate") && !obj.isNull("endPeriodDate") ? obj.getString("endPeriodDate") : "?";
                    presidente.put("periodo", inicio + " a " + fin);
                    presidente.put("inicioRaw", inicio.equals("?") ? "9999-99-99" : inicio); // Para ordenamiento

                    // Nueva extracción: Imagen y Descripción
                    String imagen = obj.has("image") && !obj.isNull("image") ? obj.getString("image") : "";
                    String descripcion = obj.has("description") && !obj.isNull("description") ? obj.getString("description") : "Sin descripción.";
                    
                    presidente.put("imagen", imagen);
                    presidente.put("descripcion", descripcion);

                    listaPresidentes.add(presidente);
                }

                // Ordenar cronológicamente (desde los más viejos hasta los más nuevos)
                listaPresidentes.sort((p1, p2) -> {
                    String d1 = (String) p1.get("inicioRaw");
                    String d2 = (String) p2.get("inicioRaw");
                    return d1.compareTo(d2);
                });

            } catch (Exception e) {
                request.setAttribute("errorMensaje", "Error al parsear JSON: " + e.getMessage());
            }
        } else {
            request.setAttribute("errorMensaje", "Error HTTP: código " + statusCode);
        }

        request.setAttribute("listaPresidentes", listaPresidentes);
        request.setAttribute("totalPresidentes", listaPresidentes.size());
        request.setAttribute("statusCode", statusCode);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/posts.jsp");
        dispatcher.forward(request, response);
    }
}
