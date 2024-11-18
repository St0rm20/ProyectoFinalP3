package com.edu.uniquindio.co.marketplace.clases.personas;

import com.edu.uniquindio.co.marketplace.clases.Hilos.ActualizadorDatos;
import com.edu.uniquindio.co.marketplace.clases.Hilos.GuardadoArchivos;
import com.edu.uniquindio.co.marketplace.clases.enums.EstadoProducto;
import com.edu.uniquindio.co.marketplace.clases.interfaces.AdministrarProductosUsuario;
import com.edu.uniquindio.co.marketplace.clases.market.*;
import com.edu.uniquindio.co.marketplace.clases.util.Alerta;
import com.edu.uniquindio.co.marketplace.clases.util.Persistencia;
import com.edu.uniquindio.co.marketplace.clases.util.Utilities;
import javafx.scene.image.Image;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Vendedor extends Persona implements AdministrarProductosUsuario, Serializable {

    private String direccion;
    private String id;
    private ArrayList<Comentario> listaComentarios;
    private ArrayList<Vendedor> contactos;
    private static int contador;
    private ArrayList<Producto> likes;
    private ArrayList<Producto> carrito;
    private ArrayList<Venta> ventas;
    private ArrayList<Chat> chats;
    private ArrayList<Solicitud> solicitudes;
    private Muro muro;
    private float resenias;
    private  ArrayList<Producto> productos;
    private static final long serialVersionUID = 1L;
    private ArrayList<Integer> listaResenias;

    public Vendedor(String nombre, String apellido, String cedula,
                    String direccion, String correo, String contrasenia,
                    Image file) throws IOException {
        super(nombre, apellido, cedula, correo, contrasenia, file);
        contador = Persistencia.leerContadorID();
        this.direccion = direccion;
        id = String.valueOf(contador++);
        this.contactos = new ArrayList<>();
        this.listaComentarios = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.chats = new ArrayList<>();
        this.carrito = new ArrayList<>();
        this.solicitudes = new ArrayList<>();
        this.listaResenias = new ArrayList<>();
        muro = new Muro("", this);
        this.productos = new ArrayList<>();
        if(MarketPlace.getInstance().getGuardarPersistencia()) {
            Persistencia.guardarContadorID();
        }
    }

    public Vendedor(String nombre, String apellido, String cedula,
                    String direccion, String correo, String contrasenia,
                    String codigo) throws IOException {
        super(nombre, apellido, cedula, correo, contrasenia, null);
        contador = Integer.parseInt(codigo);
        this.direccion = direccion;
        id = codigo;
        this.contactos = new ArrayList<>();
        this.listaComentarios = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.chats = new ArrayList<>();
        this.carrito = new ArrayList<>();
        this.solicitudes = new ArrayList<>();
        this.listaResenias = new ArrayList<>();
        muro = new Muro("", this);
        this.productos = new ArrayList<>();
    }
    public Vendedor(){
        super();
    }

    public void agregarContacto(Vendedor contacto) throws IOException {
        if (contactos.contains(contacto)) {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElContactoYaEstaAgregado"));
        } else {
            if (contactos.size() >= 10) {
                Alerta.mostrarError(Utilities.getIdioma().getString("alertaNoPuedeAgregarMasDe10Contactos"));
                return;
            }

            contactos.add(contacto);
            new Chat(this, contacto);
        }
    }

    public void eliminarContacto(Vendedor contacto) throws IOException {
        if(contactos.contains(contacto)){
            contactos.remove(contacto);
        }else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElContactoNoExiste"));
        }
    }

    @Override
    public void agregarProducto(String nombre, String descripcion, Image imagen, String categoria, double precio, EstadoProducto estado) {
        productos.add(new Producto(nombre, descripcion, imagen, categoria, precio, estado,this));
        serializarProductos();
    }

    public void agregarProducto(Producto producto){
        productos.add(producto);
        serializarProductos();
    }


    @Override
    public void eliminarProducto(Producto producto) throws IOException {
        if (productos.contains(producto)) {
            productos.remove(producto);
            serializarProductos();
        } else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElProductoNoExiste"));
        }
    }



    @Override
    public void modificarProducto(Producto producto, Producto nuevoProducto) {
        productos.set(productos.indexOf(producto), nuevoProducto);
        serializarProductos();
    }

    public void agregarComentario(String comentario, Vendedor vendedor) {
        vendedor.getListaComentarios().add(new Comentario(comentario, this));
    }

    public void darLike(Producto producto) throws IOException {
        if (likes.contains(producto)) {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaYaLeDioLikeAEsteProducto"));
        } else {
            producto.darLike();
            likes.add(producto);
        }
    }




    public void venderProducto(Producto producto, Vendedor comprador) throws IOException {
        if(contactos.contains(comprador)){
            producto.setEstado(EstadoProducto.VENDIDO);
            ventas.add(new Venta(producto, comprador));
            serializarProductos();
        }else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElCompradorNoEstaEnLaListaDeContactos"));
        }

    }

    public void comprarProducto(Producto producto) throws IOException {
        if (producto.getEstado().equals(EstadoProducto.VENDIDO)) {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElProductoYaFueVendido"));
            return;
        }
        producto.getVendedor().venderProducto(producto, this);
        ventas.add(new Venta(producto, this));
    }

    public void cancelarProducto(Producto producto) throws IOException {
        if (productos.contains(producto)) {
            producto.setEstado(EstadoProducto.CANCELADO);
            serializarProductos();
        } else {
            throw new IOException("AlertaElProductoFueCancelado.");
        }
    }

    public void eliminarComentario(Comentario comentario) throws IOException {
        if (listaComentarios.contains(comentario)) {
            listaComentarios.remove(comentario);
        } else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElComentarioNoExiste"));
        }
    }

    public void agregarProductoAlCarrito(Producto producto) throws IOException {
        if (carrito.contains(producto)) {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElProductoYaEstaEnElCarrito"));
        } else {
            carrito.add(producto);
        }
    }

    public void eliminarProductoDelCarrito(Producto producto) throws IOException {
        if (carrito.contains(producto)) {
            carrito.remove(producto);
        } else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElProductoNoEstaEnElCarrito"));
        }
    }

    public void enviarSolicitud(Vendedor vendedor) throws IOException {
        if (contactos.contains(vendedor)) {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElContactoYaEstaEnLaListaDeContactos"));
        } else {
            for(Solicitud solicitud : vendedor.getSolicitudes()){
                if(solicitud.getSolicitado().equals(vendedor)){
                    return;
                }
            }
            new Solicitud(this , vendedor);
        }
    }

    public void eliminarSolicitud(Solicitud solicitud) throws IOException {
        if (solicitudes.contains(solicitud)) {
            solicitudes.remove(solicitud);
        } else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaLaSolicitudNoExiste"));
        }
    }

    public int getNumeroContactosEnComun(){
        ArrayList<Vendedor> contactosEnComun = new ArrayList<>();
        for(Vendedor contacto: contactos){
            for(Vendedor contacto2: contactos){
                if(contacto.equals(contacto2)){
                    contactosEnComun.add(contacto);
                }
            }
        }
        return contactosEnComun.size();
    }

    public void serializarProductos(){

        if(MarketPlace.getInstance().getGuardarPersistencia()) {
            ArrayList<Producto> productosPublicados= new ArrayList<>();
            ArrayList<Producto> productosCancelados= new ArrayList<>();
            ArrayList<Producto> productosVendidos= new ArrayList<>();

            for(Producto producto:productos){
                if(producto.getEstado().equals(EstadoProducto.PUBLICADO)) {
                    productosPublicados.add(producto);
                }
                else if(producto.getEstado().equals(EstadoProducto.CANCELADO)) {
                    productosCancelados.add(producto);
                }else if(producto.getEstado().equals(EstadoProducto.VENDIDO)){
                    productosVendidos.add(producto);
                }
            }

            GuardadoArchivos guardadoArchivos = new GuardadoArchivos("productoPublicado" + id, productosPublicados);
            guardadoArchivos.start();
            GuardadoArchivos guardadoArchivos1 = new GuardadoArchivos("productoCancelado" + id , productosCancelados);
            guardadoArchivos1.start();
            GuardadoArchivos guardadoArchivos2 = new GuardadoArchivos("productoVendido"+id,  productosVendidos);
            guardadoArchivos2.start();

            ActualizadorDatos actualizadorDatos = new ActualizadorDatos("productoVendido" + id + ".txt",productosVendidos);
            actualizadorDatos.start();
        }
    }
    //----------------------------Gets y Sets-------------------------------------
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Vendedor> getContactos() {
        return contactos;
    }

    public ArrayList<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public ArrayList<Producto> getLikes() {
        return likes;
    }

    public Muro getMuro() {
        return muro;
    }

    public void setMuro(Muro muro) {
        this.muro = muro;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Vendedor.contador = contador;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public ArrayList<Producto> getProductos() {return productos;}


    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    public void setListaComentarios(ArrayList<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public void setContactos(ArrayList<Vendedor> contactos) {
        this.contactos = contactos;
    }


    public void setLikes(ArrayList<Producto> likes) {
        this.likes = likes;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public ArrayList<Producto> getCarrito() {
        return carrito;
    }

    public void setCarrito(ArrayList<Producto> carrito) {
        this.carrito = carrito;
    }

    public ArrayList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public float getResenias() {
        if (listaResenias.isEmpty()) {
            return 0;
        }
        float suma = 0;
        for (int i : listaResenias) {
            suma += i;
        }
        return suma / listaResenias.size();
    }

    public void setResenias(float resenias) {
        this.resenias = resenias;
    }

    public ArrayList<Integer> getListaResenias() {
        return listaResenias;
    }

    public void setListaResenias(ArrayList<Integer> listaResenias) {
        this.listaResenias = listaResenias;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "nombre='" + nombre + '\'' +
                ", ID='" + id + '\'' +
                '}';
    }
}


