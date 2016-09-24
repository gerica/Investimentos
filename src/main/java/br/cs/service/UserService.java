package br.cs.service;

import br.cs.entity.Role;
import br.cs.entity.Usuario;
import br.cs.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService
  implements UserDetailsService
{
  @Autowired
  private UsuarioRepository repository;
  
  public Usuario loadUserByUsername(String username)
  {
    Usuario usuario = this.repository.findByNome(username);
    if (usuario != null)
    {
      Role r = new Role();
      r.setName("ROLE_ADMIN");
      List<Role> roles = new ArrayList<Role>();
      roles.add(r);
      usuario.setAuthorities(roles);
    }
    return usuario;
  }
}
