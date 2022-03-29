package com.example.tienda_op_2.api;

import android.content.Context;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tienda_op_2.adapter.ProductoAdapter;
import com.example.tienda_op_2.modelo.Producto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

//import static com.example.tecnitienda.MainActivity.VARIABLE_GLOBAL;


public class servicioApi {

    JSONObject jsonObject;
    Context context;
    RecyclerView recyclerView;
    //RecyclerView listProductos;
    String endPoint;
    apiProductos api= new apiProductos();

    ProductoAdapter productoAdapter;
    ArrayList<Producto> productos= new ArrayList<>();

    public servicioApi() {

    }

    public servicioApi(Context context, String endPoint) {
        this.context = context;
        this.endPoint= endPoint;
    }


    public void datosList(RecyclerView recyclerView){
        String URL="https://tecnistoreaapi.rj.r.appspot.com/"+endPoint;

        JsonArrayRequest usersJSON= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                /*Swicht para identificar el end point y sacar los datos*/

                try {
                    switch (endPoint){
                        case "producto":
                            apiProductos serviProducto= new apiProductos(context, recyclerView);
                            serviProducto.parseJSON(response);
                            break;
                        case "categoria":
                            apiCategorias serviCategoria= new apiCategorias(context, recyclerView);
                            serviCategoria.parseJSON(response);
                            break;
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();
                    Toast.makeText(null,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(context).add(usersJSON);
    }

}
