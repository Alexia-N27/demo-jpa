package fr.epsi;

import fr.epsi.bo.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class ConnexionJpa {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa");
             EntityManager em = emf.createEntityManager()) {

            System.out.println("Ça marche!!!");

            em.getTransaction().begin();

            // Récupération d'un livre
            Livre livre = em.find(Livre.class, 1);
            if (livre != null) {
                System.out.println("Livre avec id: " + livre.getId());
            }

            em.getTransaction().commit();
        }
    }
}
