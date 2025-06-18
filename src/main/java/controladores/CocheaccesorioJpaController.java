
package controladores;

import entidades.Accesorios;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.Cocheaccesorio;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author jfervic933
 */
public class CocheaccesorioJpaController {

    private final EntityManagerFactory emf;

    public CocheaccesorioJpaController() {
        // Nombre de la unidad de persistencia definida en persistence.xml
        this.emf = Persistence.createEntityManagerFactory("concesionario");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea un nuevo coche en la base de datos.
     *
     * @param cocheAcce El coche a crear.
     */
    public void create(Cocheaccesorio cocheAcce) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(cocheAcce);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear el accesorio del coche", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Busca un accesorio de un coche por su ID.
     *
     * @param id El ID del acceorio del coche.
     * @return El acceosrio del coche encontrada o null si no existe.
     */
    public Cocheaccesorio findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cocheaccesorio.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los accesorios de los coches de la base de datos.
     *
     * @return Una lista de accesorios que tiene cada coche.
     */
    public List<Cocheaccesorio> findAll() {
        EntityManager em = getEntityManager();
        try {
            // Usa la named query definida en la entidad Coches
            return em.createNamedQuery("Cocheaccesorio.findAll", Cocheaccesorio.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza un acceosrio de un coche existente.
     *
     * @param cocheAcce El accesorio del coche a actualizar.
     */
    public void update(Cocheaccesorio cocheAcce) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cocheAcce);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar el accesorio del coche", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Elimina un accesorio de un coche de la base de datos.
     *
     * @param id El ID del registro del accosorio del coche a eliminar.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cocheaccesorio cocheAcce = em.find(Cocheaccesorio.class, id);
            if (cocheAcce != null) {
                em.remove(cocheAcce);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar el accesorio del coche", ex);
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
            // de la tabla Cocheaccesorio y reiniciar el contador de autoincremento
            // Una native query es una consulta SQL que se ejecuta directamente en la base de datos
            // sin pasar por el mapeo de entidades de JPA
            em.createNativeQuery("delete from cocheaccesorio").executeUpdate();
            em.createNativeQuery("alter table bdconcesionario.cocheaccesorio AUTO_INCREMENT = 1").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar todos los registros de los accesorios de los coches", ex);
        } finally {
            em.close();
        }
    }
    
    
    public void generarFicheroCocheAcce(){
        List<Cocheaccesorio> cocheAcces = this.findAll();
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("CopiaSeguridad/CocheAcce.csv"))) {
            for (Cocheaccesorio cocheAcce : cocheAcces) {
                writer.write(cocheAcce.getIdRegistro()+ ";" + cocheAcce.getIdCoche().getIdCoche()+ ";" + cocheAcce.getIdAccesorio().getIdAccesorio());
                writer.newLine();
            }
            
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
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

