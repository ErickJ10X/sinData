package Base;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URI;

public class ClienteHttp{
    HttpURLConnection con;
    String metodo;

    public ClienteHttp(URI url, String metodo) {
        try {
            this.metodo = metodo;
            con = (HttpURLConnection) url.toURL().openConnection();
            con.setRequestMethod(metodo);
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRespuesta() {
        if (!metodo.equals("GET")) {
            return "Metodo GET no implementado";
        }
        StringBuilder respuesta = new StringBuilder();
        try {
            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    respuesta.append(inputLine);
                }
                in.close();
            } else {
                respuesta.append("Error: ").append(status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta.toString();
    }


}
