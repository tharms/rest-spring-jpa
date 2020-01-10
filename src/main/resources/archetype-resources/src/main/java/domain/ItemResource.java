package ${package}.domain;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class ItemResource {

	@Autowired
	private ItemJpaRepository itemRepository;

	@GetMapping("/groups/{group}/items")
	public List<Item> getAllItems(@PathVariable String group) {
		return itemRepository.findAll();
	}

	@GetMapping("/groups/{group}/items/{id}")
	public Item getItem(@PathVariable String group, @PathVariable long id) {
		return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item Not Found with id " + id));
	}

	@DeleteMapping("/groups/{group}/items/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable String group, @PathVariable long id) {

		itemRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/groups/{group}/items/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable Integer group, @PathVariable long id,
                                             @RequestBody Item item) {

		item.setCgroup(group);
		
		Item itemUpdated = itemRepository.save(item);

		return new ResponseEntity<Item>(itemUpdated, HttpStatus.OK);
	}

	@PostMapping("/groups/{group}/items")
	public ResponseEntity<Void> createItem(@PathVariable Integer group, @RequestBody Item item) {
		
		item.setCgroup(group);

		Item createdItem = itemRepository.save(item);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdItem.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}