package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.Coches;
import entidades.Marcas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author jfervic933
 */
public class CochesJpaController {

    private final EntityManagerFactory emf;
    private MarcasJpaController mc = new MarcasJpaController();

    public CochesJpaController() {
        // Nombre de la unidad de persistencia definida en persistence.xml
        this.emf = Persistence.createEntityManagerFactory("concesionario");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea un nuevo coche en la base de datos.
     *
     * @param coche El coche a crear.
     */
    public void create(Coches coche) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(coche);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear el coche", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Busca un coche por su ID.
     *
     * @param id El ID del coche.
     * @return El coche encontrada o null si no existe.
     */
    public Coches findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Coches.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los coches de la base de datos.
     *
     * @return Una lista de coches.
     */
    public List<Coches> findAll() {
        EntityManager em = getEntityManager();
        try {
            // Usa la named query definida en la entidad Coches
            return em.createNamedQuery("Coches.findAll", Coches.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza un coche existente.
     *
     * @param coche El coche a actualizar.
     */
    public void update(Coches coche) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(coche);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar el coche", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Elimina un coche de la base de datos.
     *
     * @param id El ID del coche a eliminar.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Coches coche = em.find(Coches.class, id);
            if (coche != null) {
                em.remove(coche);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar el coche", ex);
        } finally {
            em.close();
        }
    }

    // Elimina todos los datos de la entidad Coches
    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            em.createNativeQuery("delete from coches").executeUpdate();
            em.createNativeQuery("alter table bdconcesionario.coches AUTO_INCREMENT = 1").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar todos los coches", ex);
        } finally {
            em.close();
        }
    }

    // Genera un fichero de la entidad Coches
    public void generarFicheroCoches() {
        List<Coches> coches = this.findAll();
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("CopiaSeguridad/Coches.csv"))) {
            for (Coches coche : coches) {
                writer.write(coche.getIdCoche() + ";" + coche.getModelo() + ";" + coche.getAnio() + ";" + coche.getPrecio()
                        + ";" + coche.getTipoMotor() + ";" + coche.getIdMarca().getNombre());
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    // Lee el fichero que se genero y guarda los datos en la entidad Coches
    public void leerCsvCoches() {
        try (BufferedReader br = new BufferedReader(new FileReader("CopiaSeguridad/Coches.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 6) {
                    int id = Integer.parseInt(datos[0]);
                    String modelo = datos[1];
                    int anio = Integer.parseInt(datos[2]);
                    double precio = Double.parseDouble(datos[3]);
                    String tipoMotor = datos[4];
                    
                    int idMarca = 0;
                    List<Marcas> marcas = this.mc.findAll();

                    for (Marcas marca : marcas) {
                        if (datos[5].equals(marca.getNombre())) {
                             idMarca = marca.getIdMarca();
                        }
                    }
                    this.create(new Coches(id, modelo, anio, precio, tipoMotor, this.mc.findById(idMarca)));
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
