package controladores;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import entidades.Accesorios;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author jfervic933
 */
public class AccesoriosJpaController {

    private final EntityManagerFactory emf;

    public AccesoriosJpaController() {
        // Nombre de la unidad de persistencia definida en persistence.xml
        this.emf = Persistence.createEntityManagerFactory("concesionario");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea un nuevo accesorio en la base de datos.
     *
     * @param accesorio El accesorio a crear.
     */
    public void create(Accesorios accesorio) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(accesorio);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear el accesorio", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Busca un accesorio por su ID.
     *
     * @param id El ID del accesorio.
     * @return El accesorio encontrada o null si no existe.
     */
    public Accesorios findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Accesorios.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los accesorios de la base de datos.
     *
     * @return Una lista de accesorios.
     */
    public List<Accesorios> findAll() {
        EntityManager em = getEntityManager();
        try {
            // Usa la named query definida en la entidad Coches
            return em.createNamedQuery("Accesorios.findAll", Accesorios.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza un accesorio existente.
     *
     * @param accesorio El accesorio a actualizar.
     */
    public void update(Accesorios accesorio) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(accesorio);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar el accesorio", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Elimina un accesorio de la base de datos.
     *
     * @param id El ID del accesorio a eliminar.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Accesorios accesorio = em.find(Accesorios.class, id);
            if (accesorio != null) {
                em.remove(accesorio);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar el accesorio", ex);
        } finally {
            em.close();
        }
    }

    // Elimina todos los datos de la entidad Accesorios
    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            em.createNativeQuery("delete from accesorios").executeUpdate();
            em.createNativeQuery("alter table bdconcesionario.accesorios AUTO_INCREMENT = 1").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar todos los accesorios", ex);
        } finally {
            em.close();
        }
    }

    // Genera un fichero de la entidad Accesorios
    public void generarFicheroAccesorios() {
        List<Accesorios> accesorios = this.findAll();
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("CopiaSeguridad/Accesorios.csv"))) {
            for (Accesorios Acce : accesorios) {
                writer.write(Acce.getIdAccesorio() + ";" + Acce.getNombre() + ";" + Acce.getDescripcion());
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    // Lee el fichero que se genero y guarda los datos en la entidad Accesorios
    public void leerCsvAccesorios() {
        try (BufferedReader br = new BufferedReader(new FileReader("CopiaSeguridad/Accesorios.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 3) {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String descripcion = datos[2];

                    this.create(new Accesorios(id, nombre, descripcion));

                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
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
