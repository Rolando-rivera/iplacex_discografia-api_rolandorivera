package cl.iplacex.discografia.artistas;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/artistas")
public class ArtistaController {

    private final IArtistaRepository artistaRepository;

    public ArtistaController(IArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    // POST /api/artistas
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> crear(@RequestBody Artista artista) {
        Artista guardado = artistaRepository.save(artista);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // GET /api/artistas
    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artista>> listar() {
        return ResponseEntity.ok(artistaRepository.findAll());
    }

    // GET /api/artistas/{id}
    @GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> obtener(@PathVariable String id) {
        return artistaRepository.findById(id)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado"));
    }

    // PUT /api/artistas/{id}
    @PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizar(@PathVariable String id, @RequestBody Artista artista) {
        if (!artistaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
        }
        artista.set_id(id); // aquí dejas tu lógica con _id
        return ResponseEntity.ok(artistaRepository.save(artista));
    }

    // DELETE /api/artistas/{id}
    @DeleteMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminar(@PathVariable String id) {
        if (!artistaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
        }
        artistaRepository.deleteById(id);
        return ResponseEntity.ok("Artista eliminado correctamente");
    }
}
