insert into usuarios(apellido, correo, estado, nombre, id_rol, contrasena)
values ('Aguilar', 'aguilar@gmail.com', 1, 'Josue', 1, '$2a$12$zKmHa6QmPZPSuLK8NifKt.SJ7lXs9ca.clZlPHplGl1/7R7jbSbO2');

alter table usuarios
drop column contraseña;

insert into usuario_rol(usuario_id, rol_id)
values (2, 1);