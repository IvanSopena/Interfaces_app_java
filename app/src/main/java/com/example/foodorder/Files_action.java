package com.example.foodorder;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;



public class Files_action {

    String Fichero;
    JSONArray jsonArray;
    Errores error = new Errores();

    public Files_action(String Path)
    {
        this.Fichero = Path;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    private String readJSON(Context context) throws IOException
    {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(context.getAssets().open(this.Fichero), "UTF-8"));

        String content = "";
        String line;
        while ((line = reader.readLine()) != null)
        {
            content = content + line;
        }

        return content;

    }

    public boolean leer_json(Context context){
        String jsonFileContent = null;

        try{
            jsonFileContent = this.readJSON(context);
            jsonArray = new JSONArray(jsonFileContent);

            return true;

        }catch (IOException| JSONException e){
            error.setMensaje(e.getMessage());

            return false;
        }
    }

    public boolean write_json (Context context,String filename,String datos)  {
        try {
            FileOutputStream fos = context.openFileOutput(filename,Context.MODE_PRIVATE);
            fos.write(datos.getBytes(),0,datos.length());
            fos.close();

            return true;

        }catch (IOException ex) {
            return false;
        }

    }

}
