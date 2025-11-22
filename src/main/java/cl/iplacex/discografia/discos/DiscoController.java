package cl.iplacex.discografia.discos;

import java.util.List;

import cl.iplacex.discografia.artistas.IArtistaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    private final IDiscoRepository discoRepository;
    private final IArtistaRepository artistaRepository;

    public DiscoController(IDiscoRepository discoRepository, IArtistaRepository artistaRepository) {
        this.discoRepository = discoRepository;
        this.artistaRepository = artistaRepository;
    }

    // POST /api/disco
    @PostMapping(value = "/disco",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> crear(@RequestBody Disco disco) {
        if (!artistaRepository.existsById(disco.getIdArtista())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un artista con id " + disco.getIdArtista());
        }
        Disco guardado = discoRepository.save(disco);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // GET /api/discos
    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> listar() {
        return ResponseEntity.ok(discoRepository.findAll());
    }

    // GET /api/disco/{id}
    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> obtener(@PathVariable String id) {
        return discoRepository.findById(id)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disco no encontrado"));
    }

    // PUT /api/disco/{id}
    @PutMapping(value = "/disco/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizar(@PathVariable String id, @RequestBody Disco disco) {
        if (!discoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disco no encontrado");
        }
        if (!artistaRepository.existsById(disco.getIdArtista())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un artista con id " + disco.getIdArtista());
        }
        disco.set_id(id);
        return ResponseEntity.ok(discoRepository.save(disco));
    }

    // DELETE /api/disco/{id}
    @DeleteMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminar(@PathVariable String id) {
        if (!discoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disco no encontrado");
        }
        discoRepository.deleteById(id);
        return ResponseEntity.ok("Disco eliminado correctamente");
    }

    // GET /api/artista/{id}/discos
    @GetMapping(value = "/artista/{id}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> porArtista(@PathVariable("id") String idArtista) {
        return ResponseEntity.ok(discoRepository.findDiscosByIdArtista(idArtista));
    }
}
