package com.example.tienda_op_2.validaciones;

public interface Validacion_user {
    void toggleProgressBar(boolean status);
    void lanzarActividad(Class<?> tipoActividad);
    void showMessage(String msg);
}
