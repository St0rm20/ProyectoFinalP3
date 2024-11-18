package com.edu.uniquindio.co.marketplace.clases.market;

import com.edu.uniquindio.co.marketplace.clases.enums.EstadoProducto;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import com.edu.uniquindio.co.marketplace.clases.util.Persistencia;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Producto implements Serializable {
    private String nombre;
    private String codigo;
    private String descripcion;
    private Image imagen;
    private LocalDateTime fechaPublicacion;
    private String categoria;
    private int likes;
    private double precio;
    private static int contador;
    private EstadoProducto estado;
    private Vendedor vendedor;
    private ArrayList<Comentario> comentarios;
    private static final long serialVersionUID = 1L;

    public Producto(String nombre, String descripcion, Image imagen, String categoria, double precio, EstadoProducto estado, Vendedor vendedor) {
        contador = Persistencia.leerContadorProducto();
        System.out.println(contador);
        this.nombre = nombre;
        this.codigo = String.valueOf(contador++);
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fechaPublicacion = LocalDateTime.now();
        this.categoria = categoria;
        this.precio = precio;
        this.likes = 0;
        this.estado = estado;
        this.vendedor = vendedor;
        this.comentarios = new ArrayList<>();
        if(MarketPlace.getInstance().getGuardarPersistencia()) {
            Persistencia.guardarContadorProducto();
        }
    }

    public Producto(){
    }

    public void darLike(){
        likes++;
    }

    public void marcarVendido(){
        estado = EstadoProducto.VENDIDO;
    }

    public void marcarCancelado(){
        estado = EstadoProducto.CANCELADO;
    }




    //----------------------------Gets y Sets-------------------------------------

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Producto.contador = contador;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}