package aed.karaoke.models.Usuarios;

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

    // Cerrar conexiones
    public void cerrar() {
        em.close();
        emf.close();
    }
}
