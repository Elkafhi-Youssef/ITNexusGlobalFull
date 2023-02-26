package com.itnexusglobal.person.person;

import com.itnexusglobal.person.role.Role;
import com.itnexusglobal.person.role.RoleRepository;
import com.itnexusglobal.person.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    public PersonService(final PersonRepository personRepository,
            final RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    public List<PersonDTO> findAll() {
        final List<Person> persons = personRepository.findAll(Sort.by("id"));
        return persons.stream()
                .map((person) -> mapToDTO(person, new PersonDTO()))
                .collect(Collectors.toList());
    }

    public PersonDTO get(final Long id) {
        return personRepository.findById(id)
                .map(person -> mapToDTO(person, new PersonDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PersonDTO personDTO) {
        final Person person = new Person();
        mapToEntity(personDTO, person);
        return personRepository.save(person).getId();
    }

    public void update(final Long id, final PersonDTO personDTO) {
        final Person person = personRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(personDTO, person);
        personRepository.save(person);
    }

    public void delete(final Long id) {
        personRepository.deleteById(id);
    }

    private PersonDTO mapToDTO(final Person person, final PersonDTO personDTO) {
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setEmail(person.getEmail());
        personDTO.setAddress(person.getAddress());
        personDTO.setPassword(person.getPassword());
        personDTO.setImage(person.getImage());
        personDTO.setTel(person.getTel());
        personDTO.setLinkedIn(person.getLinkedIn());
        personDTO.setGithub(person.getGithub());
        personDTO.setPersonRoles(person.getPersonRoleRoles() == null ? null : person.getPersonRoleRoles().stream()
                .map(role -> role.getId())
                .collect(Collectors.toList()));
        return personDTO;
    }

    private Person mapToEntity(final PersonDTO personDTO, final Person person) {
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setEmail(personDTO.getEmail());
        person.setAddress(personDTO.getAddress());
        person.setPassword(personDTO.getPassword());
        person.setImage(personDTO.getImage());
        person.setTel(personDTO.getTel());
        person.setLinkedIn(personDTO.getLinkedIn());
        person.setGithub(personDTO.getGithub());
        final List<Role> personRoles = roleRepository.findAllById(
                personDTO.getPersonRoles() == null ? Collections.emptyList() : personDTO.getPersonRoles());
        if (personRoles.size() != (personDTO.getPersonRoles() == null ? 0 : personDTO.getPersonRoles().size())) {
            throw new NotFoundException("one of personRoles not found");
        }
        person.setPersonRoleRoles(personRoles.stream().collect(Collectors.toSet()));
        return person;
    }

    public boolean emailExists(final String email) {
        return personRepository.existsByEmailIgnoreCase(email);
    }

    public boolean telExists(final String tel) {
        return personRepository.existsByTelIgnoreCase(tel);
    }

}
