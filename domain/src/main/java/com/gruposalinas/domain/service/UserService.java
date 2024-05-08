package com.gruposalinas.domain.service;

import com.gruposalinas.domain.model.User;
import com.gruposalinas.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  // Retorna todos los usuarios de la base de datos.
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  // Encuentra un usuario por su ID y lo retorna, o retorna null si no se encuentra.
  public User getUserById(String id) {
    return userRepository.findById(id).orElse(null);
  }

  // Intenta crear un usuario. Si el usuario ya existe (basado en el email), retorna el usuario existente.
  // Si no existe, guarda el nuevo usuario y lo retorna.
  public Optional<User> createUser(User user) {
    Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
    if (existingUser.isPresent()) {
      // Si el usuario ya existe, retorna el usuario existente
      return existingUser;
    } else {
      // Si el usuario no existe, guarda y retorna el nuevo usuario
      User savedUser = userRepository.save(user);
      return Optional.of(savedUser);
    }
  }

  // Actualiza la información de un usuario si este existe, o guarda un nuevo usuario si no existe.
  public User updateUser(String id, User user) {
    return userRepository.findById(id)
        .map(existingUser -> {
          existingUser.setEmail(user.getEmail());
          existingUser.setName(user.getName());
          existingUser.setCreationDate(user.getCreationDate());
          return userRepository.save(existingUser);
        }).orElseGet(() -> userRepository.save(user));
  }

  // Elimina un usuario basado en su ID.
  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }
}
