package pt.com.wesleywesp.Screenmatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.com.wesleywesp.Screenmatch.dto.EpisodioDTO;
import pt.com.wesleywesp.Screenmatch.dto.SerieDTO;
import pt.com.wesleywesp.Screenmatch.service.SerieService;

import java.util.List;


@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
   private SerieService serieService = new SerieService();

    @GetMapping
    public List<SerieDTO> obterSerie() {
         return serieService.obterTodasAsSerie();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series(){
    return serieService.obterTop5Serie();
    }
    @GetMapping("/lancamentos")
    public List<SerieDTO> obterlancamentos(){
        return serieService.obterLancamentos();
    }
    @GetMapping("/{id}")
    public SerieDTO obterPortId(@PathVariable("id") Long id){

        return serieService.obterPorId(id);
    }
    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas(@PathVariable("id") Long id){
        return serieService.obterTodasTemporasadasEpisodios(id);
    }
    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO>obterTEmporadaPorNumero(@PathVariable("id")Long id, @PathVariable("numero") Long numero){
        return serieService.obterTemporadasPorNumero(id, numero);
    }
    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDTO> obeterPorGenero(@PathVariable("nomeGenero") String nomeGenero){
        return serieService.obeterPOrGenero(nomeGenero);
    }
}
