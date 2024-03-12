package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private String seller;

    @Column(nullable = false)
    private Double total;

   @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SaleDetail> saleDetails = new HashSet<>();

    public Sale() {
    }

    public Sale(LocalDateTime creationDate, Client client, String seller, Double total) {
        this.creationDate = creationDate;
        this.client = client;
        this.seller = seller;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Set<SaleDetail> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(Set<SaleDetail> saleDetails) {
        this.saleDetails = saleDetails;
    }

    public void addSaleDetail(SaleDetail saleDetail) {
        saleDetails.add(saleDetail);
        saleDetail.setSale(this);
    }

    public void removeSaleDetail(SaleDetail saleDetail) {
        saleDetails.remove(saleDetail);
        saleDetail.setSale(null);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", client=" + client +
                ", seller='" + seller + '\'' +
                ", total=" + total +
                '}';
    }

}
