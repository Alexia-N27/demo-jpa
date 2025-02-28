package fr.epsi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnexionJpa {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa");
             EntityManager em = emf.createEntityManager()) {

            System.out.println("Ça marche!!!");
        }
    }
}
