package fr.epsi;

import fr.epsi.bo.Client;
import fr.epsi.bo.Emprunt;
import fr.epsi.bo.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Set;


public class ConnexionJpa {
    private static Livre[] livreAssocies;

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
            
            // Récupération d'un emprunt par son id
            Emprunt emprunt = em.find(Emprunt.class, 1);
            if (emprunt != null) {
                System.out.println("Emprunt avec id: " + emprunt.getId());

                Set<Livre> livresAssocies = emprunt.getLivres();
                if (livresAssocies != null && !livresAssocies.isEmpty()) {
                    System.out.println("Livres associés à cet emprunt :");
                    for (Livre livreAssocie : livresAssocies) {
                        if (livreAssocie != null) {
                            System.out.println("- " + livreAssocie.getId());
                        }
                    }
                } else {
                    System.out.println("Aucun livre associé à cet emprunt.");
                }
            } else {
                System.out.println("Emprunt non trouvé.");
            }

            // Récupérer un client par son id
            Client client = em.find(Client.class, 1);
            if (client != null) {
                System.out.println("Client avec id: "+ client.getId());

                // Requête pour récupérer tous les emprumts d'un client
                List<Emprunt> emprunts = em.createQuery(
                        "SELECT e FROM Emprunt e WHERE e.client.id = :clientId", Emprunt.class)
                        .setParameter("clientId", client.getId())
                        .getResultList();
                if (!emprunts.isEmpty()) {
                    System.out.println("Emprunts associés à ce client :");
                    for (Emprunt empruntClient : emprunts) {
                        System.out.println("- Emprunt ID: " + empruntClient.getId());
                    }
                } else {
                    System.out.println("Aucun emprunt trouvé pour ce client.");
                }
            }

            em.flush();
            em.getTransaction().commit();
        }
    }
}
