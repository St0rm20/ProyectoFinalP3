package com.edu.uniquindio.co.marketplace.clases;

import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;

import java.io.*;
import java.net.Socket;

public class ClienteHandler implements Runnable {

    private Socket cliente;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;
    private MarketPlace marketPlace = MarketPlace.getInstance();



    public ClienteHandler(Socket cliente) throws IOException {
        this.cliente = cliente;
        this.in = new DataInputStream(cliente.getInputStream());
        this.out = new DataOutputStream(cliente.getOutputStream());
        this.objectOut = new ObjectOutputStream(cliente.getOutputStream());
        this.objectIn = new ObjectInputStream(cliente.getInputStream());
    }

    @Override
    public void run() {
        try {
            objectOut.writeObject(marketPlace);
            objectOut.flush();
            while (true) {
                String mensaje = in.readUTF();
                descifrarMensaje(mensaje);
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectado: " + cliente.getInetAddress());
        } finally {
            cerrarConexion();
        }
    }
    public void enviarMensaje(String mensaje) throws IOException {
        out.writeUTF(mensaje);
    }

    private void cerrarConexion() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (cliente != null) cliente.close();
            ServerMarketPlace.usuarios.remove(cliente);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void descifrarMensaje(String mensaje) throws IOException {
        if (mensaje.startsWith("HYM:inicioSesion#")) {
            String[] partes = mensaje.split("#");

            if (partes.length >= 3) {
                String correo = partes[1];
                String contrasena = partes[2];
                System.out.println("Correo: " + correo + " Contraseña: " + contrasena);
                String codigo = ServerMarketPlace.inicioSesion(correo, contrasena);
                System.out.println("Codigo: " + codigo);
                if (codigo != null) {
                    System.out.println("Inicio de sesión exitoso");
                    System.out.println("HYM:inicioSesion#" + codigo);
                    out.writeUTF("HYM:inicioSesion#" + codigo);
                } else {
                    enviarMensaje("Correo o contraseña incorrectos");
                }
                ServerMarketPlace.inicioSesion(correo, contrasena);
            }
        } else if (mensaje.startsWith("HYM:registro#")) {
            String[] partes = mensaje.split("#");
           // String mensaje = "HYM:registro#" + name + "#" + lastName + "#" + id + "#" + email + "#" + password + "#" + address;

            if (partes.length >= 6) {
                String nombre = partes[1];
                String apellido = partes[2];
                String id = partes[3];
                String correo = partes[4];
                String contrasena = partes[5];
                String direccion = partes[6];
                System.out.println("Nombre: " + nombre + " Apellido: " + apellido + " ID: " + id + " Correo: " + correo + " Contraseña: " + contrasena + " Dirección: " + direccion);
                ServerMarketPlace.agregarVendedor(nombre, apellido, id, correo, contrasena, direccion);

            }

        } else if (mensaje.startsWith("HYM:Adminregistro#")) {
            String[] partes = mensaje.split("#");

            if (partes.length >= 5) {
                String nombre = partes[1];
                String apellido = partes[2];
                String id = partes[3];
                String correo = partes[4];
                String contrasena = partes[5];
                System.out.println("Nombre: " + nombre + " Apellido: " + apellido + " ID: " + id + " Correo: " + correo + " Contraseña: " + contrasena);
                ServerMarketPlace.agregarAdministrador(nombre, apellido, id, correo, contrasena);
                out.writeUTF("HYM:Adminregistro#");
            }

        } else if (mensaje.startsWith("HYM:solicitud#")) {
            //            String mensaje = "HYM:solicitud#" + id + "#" + id1;
            String[] partes = mensaje.split("#");
            String id = partes[1];
            String id1 = partes[2];
            System.out.println("ID: " + id + " ID1: " + id1);
            ServerMarketPlace.enviarSolicitud(id, id1);

        } else if (mensaje.startsWith("HYM:aceptarSolicitud#")) {
            //            String mensaje = "HYM:aceptarSolicitud#" + solicitud.getVendedor().getId() + "#" + solicitud.getSolicitado().getId();
            String[] partes = mensaje.split("#");
            String id = partes[1];
            String id1 = partes[2];
            System.out.println("ID: " + id + " ID1: " + id1);
            ServerMarketPlace.aceptarSolicitud(id, id1);
        } else if (mensaje.startsWith("HYM:enviarMensaje#")) {
            //            String mensajeEnviar = "HYM:enviarMensaje#" +
            //            chat.getVendedor().getId() + "#" + chat.getVendedor2()
            //            .getId() + "#" + mensaje;
            String[] partes = mensaje.split("#");
            String id = partes[1];
            String id1 = partes[2];
            System.out.println("ID: " + id + " ID1: " + id1+ "Mensaje: " + partes[3]);
            ServerMarketPlace.enviarMensaje(id, id1, partes[3]);
        } else if (mensaje.startsWith("HYM:anadirProducto#")) {
            String[] partes = mensaje.split("#");
            if (partes.length >= 6) {
                String nombre = partes[1];
                String descripcion = partes[2];
                String categoria = partes[3];
                String precio = partes[4];
                String estado = partes[5];
                String id = partes[6];
                System.out.println("Nombre: " + nombre + " Descripción: " + descripcion + " Categoría: " + categoria + " Precio: " + precio + " Estado: " + estado + " ID: " + id);
                ServerMarketPlace.anadirProducto(nombre, descripcion, categoria, precio, estado, id);
                out.writeUTF("HYM:anadirProducto#");
            //            String mensaje = "HYM:anadirProducto#" + nombre + "#" + descripcion + "#" + categoria + "#" + precio + "#" + estado;
        }
        } else {
            System.out.println("No se reconoce el comando: " + mensaje);
        }
    }

    public void enviarRegistro(Vendedor vendedor) throws IOException {
        out.writeUTF("HYM:registro#" + vendedor.getNombre() + "#" + vendedor.getApellido()
                + "#" + vendedor.getId() + "#" + vendedor.getCorreo() + "#"
                + vendedor.getContrasenia() + "#" + vendedor.getDireccion()
        + "#" + vendedor.getCedula());
    }

    public void enviarSolicitud(String id, String id1) throws IOException {
        out.writeUTF("HYM:solicitud#" + id + "#" + id1);
    }

    public void enviarAceptacion(String id, String id1) throws IOException {
        out.writeUTF("HYM:aceptarSolicitud#" + id + "#" + id1);
    }


    public void enviarMensaje(String id, String id1, String mensaje) throws IOException{
        out.writeUTF("HYM:enviarMensaje#"+ id + "#"+ id1 + "#" + mensaje);
    }

    public void enviarAnadirProducto(String nombre, String descripcion, String categoria, String precio, String estado) {
        try {
            out.writeUTF("HYM:anadirProducto#" + nombre + "#" + descripcion + "#" + categoria + "#" + precio + "#" + estado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}