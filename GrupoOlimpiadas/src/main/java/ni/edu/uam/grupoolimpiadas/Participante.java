package ni.edu.uam.grupoolimpiadas;

public class Participante {
    private String nombre;
    private double calificacion;
    private String grupo;

    public Participante(String nombre, double calificacion) {
        this.nombre = nombre;
        this.calificacion = calificacion;


        this.grupo = (calificacion >= 80) ? "Grupo 1 (Avanzado)" : "Grupo 2 (Básico)";
    }

    public String getNombre() { return nombre; }
    public double getCalificacion() { return calificacion; }
    public String getGrupo() { return grupo; }
}