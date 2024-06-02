package com.sroblesdorado.apptaller;

public class ListaElementos  {
    public String imagenId;
    public String nombre;
    public String precio;
    public String status;




    public String getImagenId() {
        return imagenId;
    }

    public void setImagenId(String imagenId) {
        this.imagenId = imagenId;
    }

    public ListaElementos(String imagenId, String nombre, String precio, String status) {
        this.imagenId = imagenId;
        this.nombre = nombre;
        this.precio = precio;
        this.status = status;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
