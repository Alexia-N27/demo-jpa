package fr.epsi.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPRUNT")
public class Emprunt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "date_debut")
    private LocalDate date_debut;
    @Column(name = "date_fin")
    private LocalDate date_fin;
    @Column(name = "delai")
    private Integer delai;

    @ManyToMany
        @JoinTable(
            name = "COMPO",
            joinColumns = @JoinColumn(name = "ID_EMP"),
            inverseJoinColumns = @JoinColumn(name = "ID_LIV")
        )
    private Set<Livre> livres;
    {livres = new HashSet<Livre>();}

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID")
    private Client client;

    public Emprunt() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public Integer getDelai() {
        return delai;
    }

    public void setDelai(Integer delai) {
        this.delai = delai;
    }

    public Set<Livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", delai=" + delai +
                '}';
    }
}
