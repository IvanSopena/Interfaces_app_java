package com.example.foodorder;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Acciones  {

    Utiles fc = new Utiles();
    View resultado;
    final ArrayList<Integer> precios = new ArrayList<Integer>();
    Files_action read = new Files_action("like.json");
    Gson gson = new Gson();
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Integer> getPrecios() {
        return precios;
    }

    public View getResultado() {
        return resultado;
    }

    public void setResultado(View resultado) {
        this.resultado = resultado;
    }

    public void carga_info (String categoria, LayoutInflater inflanter, LinearLayout container, JSONArray jsonArray, Context context,View msg_err,View msg_ok,Toast toast){
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                if (jsonObj.getString("categories").equals(categoria)) {
                    View view = inflanter.inflate(R.layout.items, container, false);
                    TextView time = view.findViewById(R.id.time);
                    TextView name = view.findViewById(R.id.menu_name);
                    TextView price = view.findViewById(R.id.price);
                    TextView note = view.findViewById(R.id.note);
                    TextView type = view.findViewById(R.id.type);
                    ImageView foto_menu = view.findViewById(R.id.img_menu);
                    ImageButton likeButton = view.findViewById(R.id.imageButton2);

                    time.setText(jsonObj.getString("duration"));
                    name.setText(jsonObj.getString("name"));
                    price.setText(jsonObj.getString("price"));
                    note.setText(jsonObj.getString("rating"));
                    type.setText("Burguers");

                    String ruta = "com.example.foodorder:drawable/" + jsonObj.getString("photo");
                    String photo = jsonObj.getString("photo");
                   int id = context.getResources().getIdentifier(ruta, null, null);

                    foto_menu.setImageResource(id);

                    view.setTag(i+1);

                    likeButton.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            String id = view.getTag().toString();
                            Favorito fav = new Favorito(id,name.getText().toString(),categoria,photo,note.getText().toString());
                            String json = gson.toJson(fav);
                            if(read.write_json(context,"likes.json",json)){
                                //Toast.makeText(context, "Añadido a tus Favoritos", Toast.LENGTH_SHORT).show();
                                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(msg_ok);
                                toast.show();
                            }else{
                                //Toast.makeText(context, "Error al agregar el favorito", Toast.LENGTH_LONG).show();
                                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(msg_err);
                                toast.show();
                            }
                        }
                    });

                    name.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            String id = view.getTag().toString();
                            Intent order = new Intent(v.getContext(),activity_order.class);
                            Bundle b = new Bundle();
                            b.putInt("key",Integer.parseInt(id)-1);
                            order.putExtras(b);
                            v.getContext().startActivity(order);

                        }
                    });
                    foto_menu.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            String id = view.getTag().toString();
                            Intent order = new Intent(v.getContext(),activity_order.class);
                            Bundle b = new Bundle();
                            b.putInt("key",Integer.parseInt(id)-1);
                            order.putExtras(b);
                            v.getContext().startActivity(order);

                        }
                    });
                    container.addView(view);
                }
            }
        } catch (JSONException e) {
                //msg.setText(e.getMessage());
                //msg.setVisibility(View.VISIBLE);
        }
    }

    public void carga_resturantes (String categoria, LayoutInflater inflanter, LinearLayout container, JSONArray jsonArray, Context context){
        try {

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                if (jsonObj.getString("category").equals(categoria)) {
                    View view = inflanter.inflate(R.layout.restaurants, container, false);
                    TextView name = view.findViewById(R.id.menu_name);
                    TextView price = view.findViewById(R.id.price);
                    TextView note = view.findViewById(R.id.note);
                    TextView type = view.findViewById(R.id.type);
                    ImageView foto_menu = view.findViewById(R.id.img_menu);



                    name.setText(jsonObj.getString("name"));
                    price.setText(jsonObj.getString("comments"));
                    note.setText(jsonObj.getString("rating"));
                    type.setText(jsonObj.getString("direction"));

                    String ruta = "com.example.foodorder:drawable/" + jsonObj.getString("photo");

                    int id = context.getResources().getIdentifier(ruta, null, null);

                    foto_menu.setImageResource(id);
                    view.setTag(i+1);
                    name.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            String id = view.getTag().toString();
                            Intent order = new Intent(v.getContext(),RestaurantActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("key",Integer.parseInt(id)-1); //-1
                            order.putExtras(b);
                            v.getContext().startActivity(order);

                        }
                    });
                    foto_menu.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            String id = view.getTag().toString();
                            Intent order = new Intent(v.getContext(),RestaurantActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("key",Integer.parseInt(id)-1);
                            order.putExtras(b);
                            v.getContext().startActivity(order);

                        }
                    });

                    container.addView(view);
                }
            }
        } catch (JSONException e) {
            //msg.setText(e.getMessage());
            //msg.setVisibility(View.VISIBLE);
        }
    }

    public void carga_fav (String categoria, LayoutInflater inflanter, LinearLayout container, JSONArray jsonArray, Context context,View msg,Toast toast){
        try {

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                if (jsonObj.getString("categories").equals(categoria)) {
                    View view = inflanter.inflate(R.layout.fav, container, false);
                    TextView name = view.findViewById(R.id.menu_name);
                    TextView note = view.findViewById(R.id.note);
                    ImageView foto_menu = view.findViewById(R.id.img_menu);
                    ImageButton comments = view.findViewById(R.id.imageButton3);
                    ImageButton del = view.findViewById(R.id.imageButton);

                    name.setText(jsonObj.getString("name"));
                    note.setText(jsonObj.getString("rating"));

                    String ruta = "com.example.foodorder:drawable/" + jsonObj.getString("photo");

                    int id = context.getResources().getIdentifier(ruta, null, null);

                    foto_menu.setImageResource(id);
                    view.setTag(i+1);


                    comments.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            String id = view.getTag().toString();
                            Intent order = new Intent(v.getContext(),RestaurantActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("key",Integer.parseInt(id)-1);
                            order.putExtras(b);
                            v.getContext().startActivity(order);

                        }
                    });
                    foto_menu.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            String id = view.getTag().toString();
                            Intent order = new Intent(v.getContext(),RestaurantActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("key",Integer.parseInt(id)-1);
                            order.putExtras(b);
                            v.getContext().startActivity(order);

                        }
                    });


                    del.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                                //Toast.makeText(context, "Error al agregar el favorito", Toast.LENGTH_LONG).show();
                                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(msg);
                                toast.show();
                        }
                    });

                    container.addView(view);
                }
            }
        } catch (JSONException e) {
            //msg.setText(e.getMessage());
            //msg.setVisibility(View.VISIBLE);
        }
    }

    public void carga_menu (int id,LayoutInflater inflanter, LinearLayout container,JSONArray jsonArray, Context context,TextView restaurante){

        try {
            JSONObject jsonId = jsonArray.getJSONObject(id);
            String res_id = jsonId.getString("retaurant_id");
            restaurante.setText(jsonId.getString("retaurant_name"));
            int contador =0;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                if (jsonObj.getString("retaurant_id").equals(res_id)) {

                    View view = inflanter.inflate(R.layout.menus_order, container, false);
                    TextView menu = view.findViewById(R.id.menu_name);
                    TextView desc = view.findViewById(R.id.txt_desc);

                    TextView cal = view.findViewById(R.id.calorias);
                    TextView precio = view.findViewById(R.id.precio);
                    ImageView foto_menu = view.findViewById(R.id.img_menu);

                    int dato = Integer.parseInt(fc.GetItemList(jsonObj.getString("price"),1," "));
                    precios.add(dato);

                    menu.setText(jsonObj.getString("name"));
                    desc.setText(jsonObj.getString("description"));
                    cal.setText(jsonObj.getString("calories"));
                    precio.setText(jsonObj.getString("price"));

                    String ruta = "com.example.foodorder:drawable/" + jsonObj.getString("photo");

                    int id_photo = context.getResources().getIdentifier(ruta, null, null);

                    foto_menu.setImageResource(id_photo);


                    //disponibles.setProgress(contador);

                    contador = contador+1;
                   // SeekBar disponibles = findViewById(R.id.disponibles);
                    container.addView(view);
                }
            }

        } catch (JSONException e) {
            //msg.setText(e.getMessage());
            //msg.setVisibility(View.VISIBLE);
        }

    }

    public void carga_comments (int id,LayoutInflater inflanter, LinearLayout container,JSONArray jsonArray, Context context,TextView restaurante){
        try {
            JSONObject jsonId = jsonArray.getJSONObject(id);
            String res_id = String.valueOf(id+1);//
            this.setId(res_id);
           // res_id = jsonId.getString("id_restaurant");
            restaurante.setText(jsonId.getString("name"));

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                if (jsonObj.getString("id_restaurant").equals(res_id)) {

                    View view = inflanter.inflate(R.layout.comments, container, false);
                    TextView comment = view.findViewById(R.id.comentario);

                    ImageView foto_tipo = view.findViewById(R.id.tipo);

                    comment.setText(jsonObj.getString("Comment"));

                    int dato = Integer.parseInt(jsonObj.getString("idComment"));
                    precios.add(dato);

                    String ruta = "com.example.foodorder:drawable/" + jsonObj.getString("type");

                    int id_photo = context.getResources().getIdentifier(ruta, null, null);

                    foto_tipo.setImageResource(id_photo);

                    container.addView(view);
                }
            }


        } catch (JSONException e) {
            int z = 0;
            z= z+1;
            //msg.setText(e.getMessage());
            //msg.setVisibility(View.VISIBLE);
        }
    }


}
