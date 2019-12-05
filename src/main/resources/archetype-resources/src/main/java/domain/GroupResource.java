package ${package}.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class GroupResource {

    @Autowired
    GroupJpaRepository groupRepository;

    @GetMapping("/groups")
    public List<CGroup> getAllGroups() {
        return groupRepository.findAll();
    }
}
