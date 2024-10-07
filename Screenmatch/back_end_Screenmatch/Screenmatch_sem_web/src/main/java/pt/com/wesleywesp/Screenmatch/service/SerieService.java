package pt.com.wesleywesp.Screenmatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.com.wesleywesp.Screenmatch.dto.EpisodioDTO;
import pt.com.wesleywesp.Screenmatch.dto.SerieDTO;
import pt.com.wesleywesp.Screenmatch.model.Categoria;
import pt.com.wesleywesp.Screenmatch.model.DadosTemporada;
import pt.com.wesleywesp.Screenmatch.model.Episodios;
import pt.com.wesleywesp.Screenmatch.model.Serie;
import pt.com.wesleywesp.Screenmatch.repository.SerieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obterTodasAsSerie(){
        return converteDados(repository.findAll());

    }

    public List<SerieDTO> obterTop5Serie() {

        return converteDados(repository.findTop5ByOrderByAvaliacaoDesc());

    }
    public List<SerieDTO> obterLancamentos() {
        return converteDados(repository.lancamentosMaisRecentes());
    }

    public  SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if(serie.isPresent()){
            Serie s = serie.get();

            return new SerieDTO(s.getId(), s.getTitulo(),s.getTotalSeasons(),
                    s.getAvaliacao(),s.getAnoDeLancamento(),s.getAtores(),
                    s.getSinopse(),s.getPremios(),s.getPoster());
        }
        return null;
    }

    private List<SerieDTO> converteDados(List<Serie> series) {
        return  series.stream().map(s->new SerieDTO(s.getId(), s.getTitulo(),s.getTotalSeasons(),
                s.getAvaliacao(),s.getAnoDeLancamento(),s.getAtores(),
                s.getSinopse(),s.getPremios(),s.getPoster())).collect(Collectors.toList());
    }


    public List<EpisodioDTO> obterTodasTemporasadasEpisodios(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if(serie.isPresent()){
            Serie s = serie.get();

            return  s.getEpisodios().stream()
                    .map(e-> new EpisodioDTO(e.getTitulo(), e.getTemporada(),
                            e.getNumeroEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> obterTemporadasPorNumero(Long id, Long numero) {
        List<Episodios> episodio =repository.obterEpisodiosPorTemporada(id,numero);
        return ConverterDadosEpisodioDTO(episodio);

    }
    private List<EpisodioDTO> ConverterDadosEpisodioDTO(List<Episodios> episodio){
         return episodio.stream().map(e-> new EpisodioDTO(e.getTitulo(), e.getTemporada(),
                        e.getNumeroEpisodio()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obeterPOrGenero(String nomeGenero) {
        Categoria categoria = Categoria.fromPortugues(nomeGenero);

        return converteDados(repository.findByGenero(categoria));
    }
}
