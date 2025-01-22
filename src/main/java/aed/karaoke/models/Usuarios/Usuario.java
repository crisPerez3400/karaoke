package aed.karaoke.models.Usuarios;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Usuario")
    private int idUsuario;

    private String nombre;

    @Column(unique = true)
    private String correo;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_Registro")
    private Date fechaRegistro;

    public Usuario() {

    }

    public Usuario(String nombre, String correo, Date fechaRegistro) {
        this.nombre = nombre;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }



}