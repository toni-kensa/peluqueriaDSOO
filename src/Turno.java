/**
 * Representa un turno reservado en la peluquería.
 * Relaciona un cliente y un servicio mediante sus identificadores en la base de datos.
 */
public class Turno {

    private long id;
    private long clienteId;
    private long servicioId;
    private String fecha; // dd/mm/yyyy
    private String hora;   // hh:mm
    private String estado;

    /**
     * Crea un turno con los datos indicados.
     *
     * @param id identificador del turno
     * @param clienteId id del cliente que reserva
     * @param servicioId id del servicio reservado
     * @param fecha fecha del turno dd/mm/yyyy
     * @param hora hora del turno hh:mm
     * @param estado estado del turno (Pendiente, Confirmado, Cancelado, Realizado)
     */
    public Turno(long id, long clienteId, long servicioId, String fecha, String hora, String estado) {
        this.id = id;
        this.clienteId = clienteId;
        this.servicioId = servicioId;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public long getServicioId() {
        return servicioId;
    }

    public void setServicioId(long servicioId) {
        this.servicioId = servicioId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Turno turno = (Turno) obj;
        return id == turno.id;
    }

    @Override
    public String toString() {
        return "Turno #" + id + " - Fecha: " + fecha + " " + hora;
    }
}