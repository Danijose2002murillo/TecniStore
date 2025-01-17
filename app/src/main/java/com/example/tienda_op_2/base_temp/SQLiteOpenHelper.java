package com.example.tienda_op_2.base_temp;


import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import com.example.tienda_op_2.CarritoCompras;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    private static String NOMBRE_BD= "base_temp";
    private static int VERSION_BD= 1;

    private static String TABLA_CARRITO= "create table carrito (" +
            "idPrdocuto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "nombreProducto text UNIQUE, " +
            "descripcionProducto text, " +
            "precioProducto double, " +
            "cantidadCompra int, " +
            "stock int, " +
            "imagenProducto text,"+
            "estado int,"+
            "id_producto)";


    private static String TABLA_USUARIO= "create table usuario (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "nombreUsuario text, " +
            "tipoUsuario text, " +
            "clave text, " +
            "estado text)"; //ESTADOS: REGISTRADO / POR REGISTAR


    public SQLiteOpenHelper(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase base) {
        base.execSQL(TABLA_CARRITO);
        base.execSQL(TABLA_USUARIO);
    }

    //
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLA_CARRITO);
        sqLiteDatabase.execSQL(TABLA_CARRITO);
        sqLiteDatabase.execSQL(TABLA_USUARIO);
    }

    public boolean agregarCarrito(int id_producto, String nombre, String descripcion, String precio, String cantidad, String img, int stock, boolean estado){
        SQLiteDatabase bd= getWritableDatabase();
        int tipo=0;
        if (estado!=false){
            tipo=1;
        }else {
            tipo=0;
        }
        if (bd!=null){
            try{
                //bd.execSQL("INSERT INTO carrito VALUES('"+id+"','"+cedula+"','"+nombre+"','"+apellido+"','"+telefono+"','"+email+"')");

                bd.execSQL("INSERT INTO carrito VALUES("+null+",'"+nombre+"','"+descripcion+"',"+precio+","+cantidad+","+stock+",'"+img+"', "+tipo+","+id_producto+")");
                System.out.println(" base creada"+ id_producto);
                bd.close();
                return true;
            }catch (SQLiteConstraintException e){
                return false;
            }

        }else{
            return false;
        }
    }

    public void editarCarrito(String nombre, String cantidadCompra){
        SQLiteDatabase bd= getWritableDatabase();
        bd.execSQL("UPDATE carrito SET cantidadCompra="+cantidadCompra+" WHERE nombreProducto='"+nombre+"'");
        bd.close();
    }

    public void eliminarCarrito(String name){

        SQLiteDatabase bd= getWritableDatabase();
        String sql=null;
        if (name == null) {
            sql= "DELETE FROM carrito";
        }else {
            sql= "DELETE FROM carrito WHERE nombreProducto='"+name+"'";
        }
        bd.execSQL(sql);
        bd.close();

    }


    ///// TABLA USUARIO ///////

    public boolean agregarUsuario(String nombreUsu, String tipoUsu, String claveUsu, String estado){
        SQLiteDatabase bd= getWritableDatabase();

        if (bd!=null){
            try{
                //bd.execSQL("INSERT INTO carrito VALUES('"+id+"','"+cedula+"','"+nombre+"','"+apellido+"','"+telefono+"','"+email+"')");
                bd.execSQL("INSERT INTO usuario VALUES("+null+",'"+nombreUsu+"','"+tipoUsu+"','"+claveUsu+"','"+estado+"')");
                bd.close();
                return true;
            }catch (SQLiteConstraintException e){
                return false;
            }

        }else{
            return false;
        }
    }

    ///// TABLA COMPRAR AHORA ///////

   public boolean agregarProductoComprarAhora(int id, String nombre, String descripcion, String precio, String cantidad, String img, int stock) {
        SQLiteDatabase bd = getWritableDatabase();

        if (bd != null) {
            try {
                //bd.execSQL("INSERT INTO carrito VALUES('"+id+"','"+cedula+"','"+nombre+"','"+apellido+"','"+telefono+"','"+email+"')");
                bd.execSQL("INSERT INTO comprar_ahora VALUES(" + null + ",'" + nombre + "','" + descripcion + "'," + precio + "," + cantidad + "," + stock + ",'" + img + "')");
                bd.close();
                return true;
            } catch (SQLiteConstraintException e) {
                return false;
            }

        } else {
            return false;
        }
    }


}
