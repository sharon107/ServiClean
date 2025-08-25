package org.ServiClean.servicios.implementaciones;

import org.ServiClean.modelos.Usuario;
import org.ServiClean.repositorios.IUsuarioRepository;
import org.ServiClean.servicios.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectamos el PasswordEncoder

    @Override
    public Page<Usuario> buscarTodosPaginados(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario crearOEditar(Usuario usuario) {
        // Si el ID es nulo, es un nuevo usuario, por lo que encriptamos la contraseña.
        if (usuario.getId() == null) {
            if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
                usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            }
        } else {
            // Es una edición de un usuario existente.
            Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findById(usuario.getId());

            if (usuarioExistenteOpt.isPresent()) {
                Usuario usuarioExistente = usuarioExistenteOpt.get();

                // Si se proporcionó una nueva contraseña, la encriptamos.
                if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
                    usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
                } else {
                    // Si no se proporcionó una nueva contraseña, mantenemos la que ya existe.
                    usuario.setContrasena(usuarioExistente.getContrasena());
                }
            } else {
                // Si el usuario a editar no se encuentra, lo tratamos como una creación.
                if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
                    usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
                }
            }
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarPorId(Integer id) {
        usuarioRepository.deleteById(id);
    }
}