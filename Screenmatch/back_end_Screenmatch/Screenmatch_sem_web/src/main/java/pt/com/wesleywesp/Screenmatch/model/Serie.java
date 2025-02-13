package pt.com.wesleywesp.Screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.*;
import pt.com.wesleywesp.Screenmatch.service.ConsultaChatGPT;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.OptionalDouble;

@Entity
@Table(name = "Series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(unique = true, length = 1000)
    private String titulo;
    private Integer totalSeasons;
    @Column(length = 1000)
    private String sinopse;
    private Double avaliacao;
    @Column(length = 1000)
    private String atores;
    @Column(length = 1000)
    private String poster;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    @Column(length = 1000)
    private String premios;
    private LocalDate anoDeLancamento;

    @OneToMany(mappedBy ="serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodios> episodios = new ArrayList<>();

    public Serie(){}

   public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.totalSeasons = dadosSerie.totalSeasons();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
            this.anoDeLancamento = LocalDate.parse(dadosSerie.anoDeLancamento(), formatter);
        } catch (DateTimeException ex) {
            System.out.println(ex.getMessage());
            this.anoDeLancamento = null;
        }
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0.0);

        this.sinopse = ConsultaChatGPT.obterTraducao(dadosSerie.sinopse()).trim();

        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.premios = dadosSerie.premios();
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getPremios() {
        return premios;
    }

    public void setPremios(String premios) {
        this.premios = premios;
    }

    public LocalDate getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(LocalDate anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public void setEpisodios(List<Episodios> episodios) {
        episodios.forEach(e->e.setSerie(this));
        this.episodios = episodios;
    }

    public List<Episodios> getEpisodios() {
        return episodios;
    }

    @Override
    public String toString() {
        return "genero=" + genero + '\'' +
                "titulo='" + titulo + '\'' +
                ", totalSeasons=" + totalSeasons +
                ", sinopse='" + sinopse + '\'' +
                ", avaliacao=" + avaliacao +
                ", atores='" + atores + '\'' +
                ", poster='" + poster + '\'' +
                ", premios='" + premios + '\'' +
                ", anoDeLancamento=" + anoDeLancamento + '\'' +
                ", Episodios='" + episodios ;
    }
}