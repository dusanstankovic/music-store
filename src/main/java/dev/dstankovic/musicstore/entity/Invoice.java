package dev.dstankovic.musicstore.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Invoice", schema = "Chinook")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceId", nullable = false)
    private int id;

    @NotNull(message = "Customer is required")
    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date must be today or in the past")
    @Column(name = "InvoiceDate", nullable = false)
    private LocalDate invoiceDate;

    @Column(name = "BillingAddress", length = 70)
    private String billingAddress;

    @Column(name = "BillingCity", length = 40)
    private String billingCity;

    @Column(name = "BillingState", length = 40)
    private String billingState;

    @Column(name = "BillingCountry", length = 40)
    private String billingCountry;

    @Column(name = "BillingPostalCode", length = 10)
    private String billingPostalCode;

    @Digits(integer = 10, fraction = 2, message = "Amount is out of range")
    @Column(name = "Total", nullable = false)
    private double total;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<InvoiceLine> invoiceLines;

    public Invoice() {
    }

    public Invoice(int id,
                   @PastOrPresent(message = "Date must be today or in the past") LocalDate invoiceDate,
                   String billingAddress,
                   String billingCity,
                   String billingState,
                   String billingCountry,
                   String billingPostalCode,
                   @NotBlank(message = "Total is required") double total) {
        this.id = id;
        this.invoiceDate = invoiceDate;
        this.billingAddress = billingAddress;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingCountry = billingCountry;
        this.billingPostalCode = billingPostalCode;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoiceDate=" + invoiceDate +
                ", billingAddress='" + billingAddress + '\'' +
                ", billingCity='" + billingCity + '\'' +
                ", billingState='" + billingState + '\'' +
                ", billingCountry='" + billingCountry + '\'' +
                ", billingPostalCode='" + billingPostalCode + '\'' +
                ", total=" + total +
                '}';
    }
}
