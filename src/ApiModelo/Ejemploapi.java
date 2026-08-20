
package ApiModelo;

public class Ejemploapi {

    public static void main(String[] args) {
        String dato_url = "https://api-colombia.com/api/v1/";
        ApiModelo obj_api = new ApiModelo(dato_url);
        obj_api.hacer_peticion_get();
        //obj_api.hacer_peticion_post();
        obj_api.imprimir_info();
        obj_api.info_respuesta();
    }
}

