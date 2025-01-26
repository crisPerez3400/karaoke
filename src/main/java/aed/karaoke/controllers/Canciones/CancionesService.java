package aed.karaoke.controllers.Canciones;

import aed.karaoke.models.Canciones;

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

    public void eliminarCancion(Canciones cancion) {
        em.getTransaction().begin(); // Inicia una transacción
        em.remove(cancion); // Elimina el usuario
        em.getTransaction().commit();
    }

    public void agregarCancion(Canciones cancion) {
        em.getTransaction().begin();
        em.persist(cancion);
        em.getTransaction().commit();
    }

    public List<Canciones> obtenerCanciones() {
        return em.createQuery("SELECT u FROM Canciones u", Canciones.class).getResultList();
    }

    public void modificarCancion(Canciones cancion) {
        em.getTransaction().begin(); // Inicia una transacción
        em.merge(cancion); // Actualiza el usuario
        em.getTransaction().commit();
    }

    // Cerrar conexiones
    public void cerrar() {
        em.close();
        emf.close();
    }
}
