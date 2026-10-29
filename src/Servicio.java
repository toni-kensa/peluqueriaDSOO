/**
 * Representa un servicio ofrecido por la peluquería
 * Guarda el nombre,la duración en minutos y el precio
 */
public class Servicio {

    private int id;
    private String nombre;
    private int duracion; // en minutos
    private double precio;

    /**
     * Crea un servicio con los datos indicados
     *
     * @param id identificador del servicio
     * @param nombre nombre del servicio
     * @param duracion duración en minutos
     * @param precio precio del servicio
     */
    public Servicio(int id, String nombre, int duracion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.precio = precio;
    }

    /**
     * Devuelve el id del servicio
     *
     * @return el id del servicio
     */
    public int getId() {
        return id;
    }

    /**
     * establece el id del servicio
     *
     * @param id el nuevo id del servicio
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del servicio
     *
     * @return el nombre del servicio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del servicio
     *
     * @param nombre el nuevo nombre del servicio
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la duracion del servicio
     *
     * @return la duración del servicio en minutos
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración del servicio
     *
     * @param duracion nueva duración en minutos
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Devuelve el precio del servicio
     *
     * @return el precio del servicio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del servicio
     *
     * @param precio el nuevo precio del servicio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Compara dos servicios por su id
     *
     * @param obj el objeto a comparar
     * @return true si ambos servicios tienen el mismo id
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Servicio servicio = (Servicio) obj;
        return id == servicio.id;
    }

    /**
     * Devuelve una representación en texto del servicio
     *
     * @return el nombre del servicio
     */
    @Override
    public String toString() {
        return nombre;
    }
}
