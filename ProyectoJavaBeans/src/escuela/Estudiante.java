package escuela;
import java.io.Serializable;

import java.time.LocalDateTime;
public class Estudiante implements Serializable {
private static final long serialVersionUID = 7556602068002620452L;
String nombre;
String curso;
LocalDateTime fechaHoraMatricula;
public Estudiante() {
this.nombre = "Desconocido";
this.curso = "Java";
this.fechaHoraMatricula = LocalDateTime.now();
}
public String getNombre() {
return nombre;
}
public void setNombre(String nombre) {
this.nombre = nombre;
}
public String getCurso() {
return curso;
}
public void setCurso(String curso) {
this.curso = curso;
}
public LocalDateTime getFechaHoraMatricula() {
return fechaHoraMatricula;
}
public void setFechaHoraMatricula(LocalDateTime fechaHoraMatricula) {
this.fechaHoraMatricula = fechaHoraMatricula;
}
}