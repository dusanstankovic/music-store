package dev.dstankovic.musicstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "InvoiceLine", schema = "Chinook")
public class InvoiceLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceLineId", nullable = false)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "InvoiceId")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "TrackId")
    private Track track;

    @Column(name = "UnitPrice", nullable = false)
    private double unitPrice;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    public InvoiceLine() {
    }

    public InvoiceLine(double unitPrice, int quantity) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" +
                "id=" + id +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
