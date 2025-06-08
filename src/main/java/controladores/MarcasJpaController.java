
package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.Marcas;

/**
 * @author jfervic933
 */
public class MarcasJpaController {

    private final EntityManagerFactory emf;

    public MarcasJpaController() {
        // Nombre de la unidad de persistencia definida en persistence.xml
        this.emf = Persistence.createEntityManagerFactory("concesionario");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea una nueva marca en la base de datos.
     *
     * @param marca La marca a crear.
     */
    public void create(Marcas marca) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(marca);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear la marca", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Busca una marca por su ID.
     *
     * @param id El ID del cliente.
     * @return La marca encontrada o null si no existe.
     */
    public Marcas findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Marcas.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos las marcas de la base de datos.
     *
     * @return Una lista de marcas.
     */
    public List<Marcas> findAll() {
        EntityManager em = getEntityManager();
        try {
            // Usa la named query definida en la entidad Marcas
            return em.createNamedQuery("marcas.findAll", Marcas.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza una marca existente.
     *
     * @param marca La marca a actualizar.
     */
    public void update(Marcas marca) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(marca);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar la marca", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Elimina una marca de la base de datos.
     *
     * @param id El ID de la marca a eliminar.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Marcas marca = em.find(Marcas.class, id);
            if (marca != null) {
                em.remove(marca);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar la marca", ex);
        } finally {
            em.close();
        }
    }

    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Ejemplo de uso de una consulta nativa para eliminar todos los registros
            // de la tabla marcas y reiniciar el contador de autoincremento
            // Una native query es una consulta SQL que se ejecuta directamente en la base de datos
            // sin pasar por el mapeo de entidades de JPA
            em.createNativeQuery("delete from marcas").executeUpdate();
            em.createNativeQuery("alter table bdconcesionario.marcas AUTO_INCREMENT = 1").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar todos las marcas", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Cierra el EntityManagerFactory cuando ya no se necesita.
     */
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}