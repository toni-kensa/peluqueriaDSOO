/**
 * Representa un turno reservado en la peluquería.
 * Relaciona a un cliente con un servicio en una fecha y hora determinadas.
 */
public class Turno {

    private int id;
    private String cliente;   // pasar a cliente_id cuando se conecte la BD
    private String servicio;  // pasar a servicio_id cuando se conecte la BD
    private String fecha; // dd/mm/yyyy
    private String hora;   // hh:mm
    private String estado;

    /**
     * Crea un turno con los datos indicados
     *
     * @param id identificador del turno
     * @param cliente nombre del cliente que reserva
     * @param servicio nombre del servicio reservado
     * @param fecha fecha del turno dd/mm/yyyy
     * @param hora hora del turno hh:mm
     * @param estado estado del turno (pendiente, confirmado, cancelado o realizado)
     */
    public Turno(int id, String cliente, String servicio, String fecha, String hora, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    /**
     * Devuelve el id del turno
     *
     * @return id del turno
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del turno
     *
     * @param id el nuevo id del turno
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el cliente del turno
     *
     * @return el nombre del cliente del turno
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente del turno
     *
     * @param cliente el nuevo nombre de cliente
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * Devuelve el servicio del turno
     *
     * @return el nombre del servicio del turno
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * Establece el servicio del turno
     *
     * @param servicio el nuevo nombre de servicio
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * Devuelve la fecha del turno
     *
     * @return la fecha del turno, formato dd/mm/yyyy
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del turno.
     *
     * @param fecha la nueva fecha, formato dd/mm/yyyy
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve la hora del turno
     *
     * @return la hora del turno, formato hh:mm
     */
    public String getHora() {
        return hora;
    }

    /**
     * Establece la hora del turno.
     *
     * @param hora la nueva hora, formato hh:mm
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Devuelve el estado del turno
     *
     * @return el estado del turno
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del turno
     *
     * @param estado el nuevo estado del turno
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Compara dos turnos por su id
     *
     * @param obj el objeto a comparar
     * @return true si ambos turnos tienen el mismo id
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Turno turno = (Turno) obj;
        return id == turno.id;
    }

    /**
     * Devuelve una representación en texto del turno
     *
     * @return el cliente con la fecha y hora del turno
     */
    @Override
    public String toString() {
        return cliente + " - " + fecha + " " + hora;
    }
}
