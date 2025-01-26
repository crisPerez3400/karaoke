package aed.karaoke.controllers.HistorialController;
import aed.karaoke.models.Historial;
import aed.karaoke.models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
public class HistorialService {
    private EntityManagerFactory emf;
    private EntityManager em;

    public HistorialService() {
        emf = Persistence.createEntityManagerFactory("persistence");
        em = emf.createEntityManager();
    }

    public List<Historial> obtenerHistorial() {
        return em.createQuery("SELECT u FROM Historial u", Historial.class).getResultList();
    }

    public void agregarHistorial(Historial historial) {

        em.getTransaction().begin();
        em.persist(historial);
        em.getTransaction().commit();
    }

    public void eliminarHistorail(Historial historial) {
        em.getTransaction().begin(); // Inicia una transacción
        em.remove(historial); // Elimina el usuario
        em.getTransaction().commit();
    }

    public void modificarHistorial(Historial historial) {
        em.getTransaction().begin(); // Inicia una transacción
        em.merge(historial); // Actualiza el usuario
        em.getTransaction().commit();
    }


    public void cerrar() {
        em.close();
        emf.close();
    }
}
