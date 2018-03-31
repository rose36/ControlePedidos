package br.ufc.rcm.systech;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Garen on 12/12/2015.
 */
public class Json {
        JSONObject json;
    String nome, senha;
    int id;

    public String criaJson(String nome, String senha, String flag) throws JSONException {
        json = new JSONObject();
        json.put("Nome", nome);
        json.put("Senha", senha);
        json.put("Flag", flag);
        json.put("id", id);

        return json.toString();
    }
    public String criaJsonComanda(String nome, String quantidade, String id, String date, String operacao) throws JSONException {
        json = new JSONObject();
        json.put("Nome", nome);
        json.put("unidade", quantidade);
        json.put("data", date);
        json.put("id", id);
        json.put("Flag", operacao);

        return json.toString();
    }
}
