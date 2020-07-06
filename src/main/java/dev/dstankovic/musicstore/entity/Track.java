package dev.dstankovic.musicstore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "track", schema = "chinook")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trackId", nullable = false)
    private int id;

    @NotNull(message = "Track name is required")
    @Column(name = "Name", length = 200, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "AlbumId")
    private Album album;

    @NotNull(message = "Media type is required")
    @ManyToOne
    @JoinColumn(name = "MediaTypeId")
    private MediaType mediaType;

    @ManyToOne
    @JoinColumn(name = "GenreId")
    private Genre genre;

    @Column(name = "Composer", length = 220)
    private String composer;

    @NotNull(message = "Length in milliseconds is required")
    @Column(name = "Milliseconds", nullable = false)
    private int milliseconds;

    @Column(name = "Bytes")
    private int bytes;

    @NotNull(message = "Unit price is required")
    @Column(name = "UnitPrice", nullable = false)
    private double unitPrice;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "playlisttrack",
            joinColumns = @JoinColumn(name = "TrackId"),
            inverseJoinColumns = @JoinColumn(name = "PlaylistId"))
    private List<Playlist> playlists;

    @OneToMany(mappedBy = "track", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<InvoiceLine> invoiceLines;

    public Track() {
    }

    public Track(String name, String composer, int milliseconds, int bytes, double unitPrice) {
        this.name = name;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    // convenience method for bi-directional relationship
    public void addInvoiceLine(InvoiceLine invoiceLine) {
        if (invoiceLines == null) {
            invoiceLines = new ArrayList<>();
        }
        invoiceLines.add(invoiceLine);
        invoiceLine.setTrack(this);
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", composer='" + composer + '\'' +
                ", milliseconds=" + milliseconds +
                ", bytes=" + bytes +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
