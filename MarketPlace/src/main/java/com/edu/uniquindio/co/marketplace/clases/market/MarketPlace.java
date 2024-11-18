package com.edu.uniquindio.co.marketplace.clases.market;

import com.edu.uniquindio.co.marketplace.clases.Hilos.GuardadoArchivos;
import com.edu.uniquindio.co.marketplace.clases.enums.EstadoProducto;
import com.edu.uniquindio.co.marketplace.clases.interfaces.AdministrarComentarios;
import com.edu.uniquindio.co.marketplace.clases.interfaces.AdministrarProductos;
import com.edu.uniquindio.co.marketplace.clases.interfaces.AdministrarVendedores;
import com.edu.uniquindio.co.marketplace.clases.interfaces.Estadistica;
import com.edu.uniquindio.co.marketplace.clases.personas.Administrador;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import com.edu.uniquindio.co.marketplace.clases.util.Logger;
import com.edu.uniquindio.co.marketplace.clases.util.Persistencia;
import com.edu.uniquindio.co.marketplace.clases.util.Utilities;
import javafx.scene.image.Image;


import java.beans.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class MarketPlace implements AdministrarVendedores, AdministrarProductos, AdministrarComentarios, Estadistica, Serializable {
    private String nombre;
    private ArrayList<Vendedor> listaVendedores;
    private ArrayList<Administrador> listaAdministradores;
    private static MarketPlace instance;
    private String claveSeguridad;
    private boolean guardarPersistencia = true;
    private static final long serialVersionUID = 1L;


    private MarketPlace() {
        this.nombre = "Quick Trove";
        this.claveSeguridad = "Admin123";
        this.listaVendedores = new ArrayList<>();
        this.listaAdministradores = new ArrayList<>();
    }

    public static MarketPlace getInstance() {
        if (instance == null) {
            instance = new MarketPlace();
        }
        return instance;
    }

    public  void  agregarVendedor(Vendedor vendedor){
        listaVendedores.add(vendedor);
    }

    public List<Vendedor> obtenerVendedores(){
        return listaVendedores;
    }

    public boolean validarClave(String clave) {
        return clave.equals(claveSeguridad);
    }

    @Override
    public void agregarVendedor(String nombre, String apellido, String cedula, String direccion, String correo, String contrasenia, Image imagen) throws IOException {
        Vendedor vendedor = new Vendedor(nombre, apellido, cedula, direccion, correo, contrasenia, imagen);
        if (!existeVendedor(vendedor) && buscarVendedorPorCorreo(correo).isEmpty() && buscarVendedorPorCedula(cedula).isEmpty()) {
            listaVendedores.add(vendedor);

            if(guardarPersistencia) {
                GuardadoArchivos guardadoArchivos = new GuardadoArchivos("vendedores", listaVendedores);
                guardadoArchivos.start();
            }
            Logger.registrarAccionUsuario(correo, "Vendedor agregado", "MarketPlace");
        } else {
            Logger.registrarAccionUsuario(correo, "Intento de agregar vendedor fallido - Vendedor ya existe", "MarketPlace");
        }
    }

    public void agregarVendedor(String nombre, String apellido, String cedula, String direccion, String correo, String contrasenia, String codigo) throws IOException {
        Vendedor vendedor = new Vendedor(nombre, apellido, cedula, direccion, correo, contrasenia, codigo);
        System.out.println("Nombre: " + nombre + " Apellido: " + apellido + " Cedula: " + cedula + " Direccion: " + direccion + " Correo: " + correo + " Contrasenia: " + contrasenia + " Codigo: " + codigo);
        if (!existeVendedor(vendedor) && buscarVendedorPorCorreo(correo).isEmpty() && buscarVendedorPorCedula(cedula).isEmpty()) {
            listaVendedores.add(vendedor);
            vendedor.getId();
            if(guardarPersistencia) {
                GuardadoArchivos guardadoArchivos = new GuardadoArchivos("vendedores", listaVendedores);
                guardadoArchivos.start();
            }
            Logger.registrarAccionUsuario(correo, "Vendedor agregado", "MarketPlace");
        } else {
            Logger.registrarAccionUsuario(correo, "Intento de agregar vendedor fallido - Vendedor ya existe", "MarketPlace");
        }
    }

    @Override
    public void eliminarVendedor(Vendedor vendedor) throws IOException {
        if (listaVendedores.contains(vendedor)) {
            listaVendedores.remove(vendedor);
            if(guardarPersistencia) {
                GuardadoArchivos guardadoArchivos = new GuardadoArchivos("vendedores", listaVendedores);
                guardadoArchivos.start();
            }
            Logger.registrarAccionUsuario(vendedor.getCorreo(), "Vendedor eliminado", "MarketPlace");
        } else {
        }
    }

    @Override
    public void modificarVendedor(Vendedor vendedor, Vendedor vendedorModificado) throws IOException {
        if (listaVendedores.contains(vendedor)) {
            listaVendedores.set(listaVendedores.indexOf(vendedor), vendedorModificado);
            if(guardarPersistencia) {
                GuardadoArchivos guardadoArchivos = new GuardadoArchivos("vendedores", listaVendedores);
                guardadoArchivos.start();
            }
            Logger.registrarAccionUsuario(vendedor.getCorreo(), "Vendedor modificado", "MarketPlace");
        }
    }

    public boolean existeVendedor(Vendedor vendedor) throws IOException {
        boolean existeVendedor = buscarVendedorPorCedula(vendedor.getCedula()).isPresent()
                || buscarVendedorPorCorreo(vendedor.getCorreo()).isPresent();
        if (existeVendedor) {
            return true;
        } else {
            return false;
        }
    }

    public Optional<Vendedor> buscarVendedorPorCedula(String cedula) {
        Predicate<Vendedor> condicion = vendedor -> vendedor.getCedula().equals(cedula);
        return listaVendedores.stream().filter(condicion).findAny();
    }

    public Optional<Vendedor> buscarVendedorPorCorreo(String correo) {
        Predicate<Vendedor> condicion = vendedor -> vendedor.getCorreo().equals(correo);
        return listaVendedores.stream().filter(condicion).findAny();
    }

    public Vendedor obtenerVendedorPorCodigo(String codigo) {
        for (Vendedor vendedor : listaVendedores) {
            if (vendedor.getId().equals(codigo)) {
                return vendedor;
            }
        }
        return null;

    }

    public Administrador obtenerAdministradorPorCodigo(String codigo) {
        for (Administrador administrador : listaAdministradores) {
            if (administrador.getCedula().equals(codigo)) {
                return administrador;
            }
        }
        return null;
    }

    public void agregarAdministrador(String nombre, String apellido, String cedula, String correo, String contrasenia, Image imagen) throws IOException {
        Administrador administrador = new Administrador(nombre, apellido, cedula, correo, contrasenia, imagen);
            if(!existeAdministrador(administrador)){
                listaAdministradores.add(administrador);
                if(guardarPersistencia) {
                    GuardadoArchivos guardadoArchivos = new GuardadoArchivos("administradores", listaAdministradores);
                    guardadoArchivos.start();
                }
                Logger.registrarAccionUsuario(correo, "Administrador agregado", "MarketPlace");
            }


    }

    public void eliminarAdministrador(Administrador administrador) throws IOException {
        if (listaAdministradores.contains(administrador)) {
            listaAdministradores.remove(administrador);
            if(guardarPersistencia) {
                GuardadoArchivos guardadoArchivos = new GuardadoArchivos("administradores", listaAdministradores);
                guardadoArchivos.start();
            }
            Logger.registrarAccionUsuario(administrador.getCorreo(), "Administrador eliminado", "MarketPlace");
        } else {
            Logger.registrarAccionUsuario(administrador.getCorreo(), "Intento de eliminar administrador fallido - Administrador no existe", "MarketPlace");
        }
    }

    public boolean existeAdministrador(Administrador administrador) throws IOException {
        boolean existeAdministrador = buscarAdministradorPorCedula(administrador.getCedula()).isPresent()
                || buscarVendedorPorCorreo(administrador.getCorreo()).isPresent();
        if (existeAdministrador) {
            return true;
        } else {
            return false;
        }
    }

    public Optional<Administrador> buscarAdministradorPorCedula(String cedula) {
        Predicate<Administrador> condicion = administrador -> administrador.getCedula().equals(cedula);
        return listaAdministradores.stream().filter(condicion).findAny();
    }

    public Optional<Administrador> buscarAdministradorPorCorreo(String correo) {
        Predicate<Administrador> condicion = administrador -> administrador.getCorreo().equals(correo);
        return listaAdministradores.stream().filter(condicion).findAny();
    }

    @Override
    public void eliminarProducto(Producto producto) {
        for (Vendedor vendedor : listaVendedores) {
            vendedor.getProductos().remove(producto);
        }
    }

    @Override
    public ArrayList<Producto> obtenerProducto() {
        ArrayList<Producto> productos = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores) {
            productos.addAll(vendedor.getProductos());
        }
        return productos;
    }

    @Override
    public void eliminarComentario(Comentario comentario) {
        for (Vendedor vendedor : listaVendedores) {
            vendedor.getListaComentarios().remove(comentario);
        }
    }

    @Override
    public ArrayList<Comentario> obtenerComentarios() {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores) {
            comentarios.addAll(vendedor.getListaComentarios());
        }
        return comentarios;
    }

    @Override
    public int obtenerCantidadMensajesEnviados(Vendedor vendedor, Vendedor vendedor2) {
        int contador = 0;

        for (Chat chat : vendedor.getChats()) {
            if (chat.getVendedor2().equals(vendedor2) || chat.getVendedor().equals(vendedor2)) {
                String mensajes = chat.getMensajes();

                contador += mensajes.split("\n").length - 1;
            }
        }
        return contador;
    }

    @Override
    public int obtenerCantidadProductosPublicadosPorFecha(LocalDate fecha) {
        int cantidad = 0;
        for (Vendedor vendedor : listaVendedores) {
            for (Producto producto : vendedor.getProductos()) {
                if (producto.getFechaPublicacion().toLocalDate().equals(fecha)) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    @Override
    public int obtenerCantidadProductosPublicadosPorVendedor(Vendedor vendedor) {
        int contador = 0;
        for (Producto producto : vendedor.getProductos()) {
            if (producto.getEstado().equals(EstadoProducto.PUBLICADO)) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public int obtenerCantidadContactosVendedor(Vendedor vendedor) {
        if (vendedor.getContactos() != null) {
            return vendedor.getContactos().size();
        }
        return 0;
    }

    @Override
    public ArrayList<Producto> obtenerProductosMasGustados() {
        ArrayList<Producto> productos = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores) {
            productos.addAll(vendedor.getProductos());
        }
        productos.sort((p1, p2) -> Integer.compare(p2.getLikes(), p1.getLikes()));
        return new ArrayList<>(productos.subList(0, Math.min(productos.size(), 10)));
    }

    public void serializarProductos(String ruta, ArrayList<Producto> productos) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(productos);
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Producto> deserializarProductos(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (ArrayList<Producto>) ois.readObject();
        }
    }
    public static void serializarObjetoXML(String nombreArchivo, Object objeto) throws IOException {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(nombreArchivo)))) {

            encoder.setPersistenceDelegate(LocalDate.class, new DefaultPersistenceDelegate() {
                @Override
                protected Expression instantiate(Object obj, Encoder enc) {
                    LocalDate localDate = (LocalDate) obj;
                    return new Expression(localDate, LocalDate.class, "of",
                            new Object[]{localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()});
                }
            });

            encoder.setPersistenceDelegate(LocalDateTime.class, new DefaultPersistenceDelegate() {
                @Override
                protected Expression instantiate(Object obj, Encoder enc) {
                    LocalDateTime localDateTime = (LocalDateTime) obj;
                    return new Expression(localDateTime, LocalDateTime.class, "of",
                            new Object[]{localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(),
                                    localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond()});
                }
            });

            encoder.writeObject(objeto);
        }
    }

    public static Object deserializarObjetoXML(String nombreArchivo) throws IOException {
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nombreArchivo)))) {
            return decoder.readObject();
        }
    }



    public void serializarAdministradores(String ruta, ArrayList<Administrador> administradores) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(administradores);
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Administrador> deserializarAdministradores(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (ArrayList<Administrador>) ois.readObject();
        }
    }


    public void iniciarDatos() throws IOException, ClassNotFoundException {
        ResourceBundle rutas = ResourceBundle.getBundle("rutas");
        Vendedor.setContador(Persistencia.leerContadorID());
        ArrayList<Vendedor> vendedoresPersistencia = new ArrayList<>();

        if (rutas.getString("serializacionPrincipal").equals("binario")) {
            // Verifica la existencia de los archivos antes de deserializar
            File archivoVendedores = new File(rutas.getString("carpetaPrincipal") + "/vendedores.dat");
            if (archivoVendedores.exists()) {
                vendedoresPersistencia = (ArrayList<Vendedor>) Utilities.deserializarObjetoBinario(archivoVendedores.getPath());
                listaVendedores.addAll(vendedoresPersistencia);
            }

            File archivoAdministradores = new File(rutas.getString("carpetaPrincipal") + "/administradores.dat");
            if (archivoAdministradores.exists()) {
                ArrayList<Administrador> administradoresPersistencia = (ArrayList<Administrador>) Utilities.deserializarObjetoBinario(archivoAdministradores.getPath());
                listaAdministradores.addAll(administradoresPersistencia);
            }

            for (Vendedor vendedor : vendedoresPersistencia) {
                cargarProductos(vendedor, rutas.getString("carpetaPrincipal"), "dat");
            }
        } else if (rutas.getString("serializacionPrincipal").equals("xml")) {
            File archivoVendedores = new File(rutas.getString("carpetaPrincipal") + "/vendedores.xml");
            if (archivoVendedores.exists()) {
                vendedoresPersistencia = (ArrayList<Vendedor>) Utilities.deserializarObjetoXML(archivoVendedores.getPath());
                listaVendedores.addAll(vendedoresPersistencia);
            }

            File archivoAdministradores = new File(rutas.getString("carpetaPrincipal") + "/administradores.xml");
            if (archivoAdministradores.exists()) {
                ArrayList<Administrador> administradoresPersistencia = (ArrayList<Administrador>) Utilities.deserializarObjetoXML(archivoAdministradores.getPath());
                listaAdministradores.addAll(administradoresPersistencia);
            }

            for (Vendedor vendedor : vendedoresPersistencia) {
                cargarProductos(vendedor, rutas.getString("carpetaPrincipal"), "xml");
            }
        }
    }

    private void cargarProductos(Vendedor vendedor, String carpetaPrincipal, String extension) throws IOException, ClassNotFoundException {
        String[] estados = {"Publicado", "Cancelado", "Vendido"};
        for (String estado : estados) {
            File archivoProducto = new File(carpetaPrincipal + "producto" + estado + vendedor.getId() + "." + extension);
            if (archivoProducto.exists()) {
                ArrayList<Producto> productosPersistencia;
                if (extension.equals("dat")) {
                    productosPersistencia = (ArrayList<Producto>) Utilities.deserializarObjetoBinario(archivoProducto.getPath());
                } else {
                    productosPersistencia = (ArrayList<Producto>) Utilities.deserializarObjetoXML(archivoProducto.getPath());
                }
                vendedor.getProductos().addAll(productosPersistencia);
            }
        }
    }

    //----------------------------Gets y Sets-------------------------------------

    public ArrayList<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public ArrayList<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
        this.listaVendedores = listaVendedores;
    }

    public void setListaAdministradores(ArrayList<Administrador> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }

    public static void setInstance(MarketPlace instance) {
        MarketPlace.instance = instance;
    }

    public String getClaveSeguridad() {
        return claveSeguridad;
    }

    public void setClaveSeguridad(String claveSeguridad) {
        this.claveSeguridad = claveSeguridad;
    }

    public boolean getGuardarPersistencia(){
        return guardarPersistencia;
    }

    public boolean isGuardarPersistencia() {
        return guardarPersistencia;
    }

    public void setGuardarPersistencia(boolean guardarPersistencia) {
        this.guardarPersistencia = guardarPersistencia;
    }

    public void enviarSolicitudVendedores(String id, String id1) throws IOException {
        Vendedor vendedor = obtenerVendedorPorCodigo(id);
        Vendedor vendedor1 = obtenerVendedorPorCodigo(id1);
        vendedor.enviarSolicitud(vendedor1);
        if(guardarPersistencia) {
            GuardadoArchivos guardadoArchivos = new GuardadoArchivos("administradores", listaAdministradores);
            guardadoArchivos.start();
        }
    }

    public void aceptarSolicitud(String id, String id1) throws IOException {
        Vendedor vendedor = obtenerVendedorPorCodigo(id);
        Vendedor vendedor1 = obtenerVendedorPorCodigo(id1);
        System.out.println("Numero de solicitudes: " + vendedor1.getSolicitudes().size());
        for(Solicitud solicitud: vendedor1.getSolicitudes()){
            if(solicitud.getVendedor().equals(vendedor)){
                System.out.println("Solicitud encontrada");
               solicitud.aceptarSolicitud();
                if(guardarPersistencia) {
                    GuardadoArchivos guardadoArchivos = new GuardadoArchivos("administradores", listaAdministradores);
                    guardadoArchivos.start();
                }
               return;
            }
        }
    }

    public void enviarMensaje(String id, String id1, String mensaje){
        Vendedor vendedor = obtenerVendedorPorCodigo(id);
        Vendedor vendedor1 = obtenerVendedorPorCodigo(id1);
        for(Chat chat: vendedor.getChats()){
            if(chat.getVendedor2().equals(vendedor1) || chat.getVendedor().equals(vendedor1)){
                chat.setMensajes(mensaje);
                return;
            }
        }
    }

    public void anadirProducto(String nombre, String descripcion, String categoria, double v, String estado, String id) {
        Vendedor vendedor = obtenerVendedorPorCodigo(id);
        EstadoProducto est;
        if (estado.equals("Publicado")) {
            est = EstadoProducto.PUBLICADO;
        } else if (estado.equals("Vendido")) {
            est = EstadoProducto.VENDIDO;
        } else {
            est = EstadoProducto.CANCELADO;
        }
        Producto producto = new Producto(nombre, descripcion, null,  categoria, v, est, vendedor);
        vendedor.agregarProducto(producto);

    }
}
