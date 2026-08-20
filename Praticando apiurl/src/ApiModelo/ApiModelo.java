package ApiModelo;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiModelo {
    
    private final String api_url;
    public HttpResponse<String> respuesta_api;
    
    public ApiModelo (String dato_url) {
        this.api_url = dato_url;
        this.respuesta_api = null;
    }
    
    public void hacer_peticion_get(){
        try {
            //creacion del cliente - protocolo HTTP
            HttpClient cliente = HttpClient.newHttpClient();
            //creacion de la peticion del cliente
            HttpRequest peticion = HttpRequest.newBuilder()
                    .uri(URI.create(this.api_url))
                    .header("Accept","text/plain").GET().build();
            this.respuesta_api = cliente.send(peticion, HttpResponse
                    .BodyHandlers.ofString());   
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    } 
    
    public void hacer_peticion_post() {
        String texto_json = """
                            {"cedula": "127044901", "nombre": "nelson"}""";
        try {
            //crear el cliente - protocolo HTTP
            HttpClient cliente = HttpClient.newHttpClient();
            //creacion de la peticion del cliente 
            HttpRequest peticion = HttpRequest.newBuilder()
                    .uri(URI.create(this.api_url))
                    .header("Content-Type", "application/jason")
                    .POST(HttpRequest.BodyPublishers.ofString(texto_json))
                    .build();
            this.respuesta_api = cliente.send(peticion, HttpResponse
                    .BodyHandlers.ofString());
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex.getMessage());
        }                      
    }  
    
    public void imprimir_info() {
        System.out.println("El cuerpo de la respuesta es: " + this.respuesta_api.body());
        System.out.println("El cuerpo de la respuesta es: " + this.respuesta_api.statusCode());
    }
    
    public void info_respuesta() {
        try {
      JSONArray array_json = new JSONArray(this.respuesta_api.body());
      for (int i = 0; i < array_json.length(); i++) {
         JSONObject obj_info = array_json.getJSONObject(i);
         int id = obj_info.getInt("id");
         String titulo = obj_info.getString("title");

         System.out.println("El id: " + id + " - el titulo: " + titulo);
      }
   } catch (JSONException ex) {
      System.out.println("Error al procesar el JSON: " + ex.getMessage());
   }
    }
}
