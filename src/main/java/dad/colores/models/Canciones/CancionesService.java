package dad.colores.models.Canciones;

import dad.colores.models.Canciones.Canciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CancionesService {
    private EntityManagerFactory emf;
    private EntityManager em;

    public CancionesService() {
        emf = Persistence.createEntityManagerFactory("persistence");
        em = emf.createEntityManager();
    }

    public List<Canciones> obtenerCanciones() {
        return em.createQuery("SELECT u FROM Canciones u", Canciones.class).getResultList();
    }

    // Cerrar conexiones
    public void cerrar() {
        em.close();
        emf.close();
    }
}
