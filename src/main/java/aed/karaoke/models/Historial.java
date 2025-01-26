package aed.karaoke.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "historial")
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;  // Relación con la entidad Usuario

    @Column(name = "id_cancion", nullable = false)
    private String cancion; // Esto sigue siendo una cadena simple

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_cantada")
    private Date fechaCantada; // Fecha en la que se cantó la canción

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public Date getFechaCantada() {
        return fechaCantada;
    }

    public void setFechaCantada(Date fechaCantada) {
        this.fechaCantada = fechaCantada;
    }
}
