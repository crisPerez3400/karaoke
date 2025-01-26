package aed.karaoke.controllers.Usuarios;

import aed.karaoke.models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UsuarioService {
    private EntityManagerFactory emf;
    private EntityManager em;

    public UsuarioService() {
        emf = Persistence.createEntityManagerFactory("persistence");
        em = emf.createEntityManager();
    }

    public List<Usuario> obtenerUsuarios() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public void agregarUsuario(Usuario usuario) {

        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    public void eliminarUsuario(Usuario usuario) {
        em.getTransaction().begin(); // Inicia una transacción
        em.remove(usuario); // Elimina el usuario
        em.getTransaction().commit();
    }

    public void modificarUsuario(Usuario usuario) {
        em.getTransaction().begin(); // Inicia una transacción
        em.merge(usuario); // Actualiza el usuario
        em.getTransaction().commit();
    }

    // Cerrar conexiones
    public void cerrar() {
        em.close();
        emf.close();
    }
}
