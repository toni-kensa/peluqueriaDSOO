/**
 * Representa un cliente de la peluqueria.
 * Guarda sus datos personales, su categoría y si acepta promociones
 */
public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String contacto;
    private String categoria;
    private boolean promociones; //  booleano para la casilla de verificación

    /**
     * Crea un cliente con los datos indicados
     *
     * @param id identificador del cliente
     * @param nombre nombre del cliente
     * @param apellido apellido del cliente
     * @param contacto teléfono o correo de contacto
     * @param categoria categoría del cliente
     * @param promociones si el cliente acepta promociones
     */
    public Cliente(int id, String nombre, String apellido, String contacto, String categoria, boolean promociones) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contacto = contacto;
        this.categoria = categoria;
        this.promociones = promociones;
    }

    // Getters y Setters

    /**
     * devuelve el id del cliente
     *
     * @return el id del cliente
     */
    public int getId() { return id; }

    /**
     * Establece el id del cliente
     *
     * @param id el nuevo id del cliente
     */
    public void setId(int id) { this.id = id; }

    /**
     * Devuelve el nombre del cliente
     *
     * @return el nombre del cliente
     */
    public String getNombre() { return nombre; }

    /**
     * Establece el nombre del cliente
     *
     * @param nombre el nuevo nombre del cliente
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * devuelve el apellido del cliente
     *
     * @return el apellido del cliente
     */
    public String getApellido() { return apellido; }

    /**
     * Establece el apellido del cliente
     *
     * @param apellido el nuevo apellido del cliente
     */
    public void setApellido(String apellido) { this.apellido = apellido; }

    /**
     * Devuelve el contacto del cliente
     *
     * @return el contacto del cliente
     */
    public String getContacto() { return contacto; }

    /**
     * Establece el contacto del cliente
     *
     * @param contacto el nuevo contacto del cliente
     */
    public void setContacto(String contacto) { this.contacto = contacto; }

    /**
     * Devuelve la categoría del cliente
     *
     * @return la categoría del cliente
     */
    public String getCategoria() { return categoria; }

    /**
     * Establece la categoría del cliente
     *
     * @param categoria la nueva categoría del cliente
     */
    public void setCategoria(String categoria) { this.categoria = categoria; }

    /**
     * Indica si el cliente acepta promociones
     *
     * @return true si el cliente acepta promociones
     */
    public boolean isPromociones() { return promociones; }

    /**
     * Establece si el cliente acepta promociones
     *
     * @param promociones indica si el cliente acepta promociones
     */
    public void setPromociones(boolean promociones) { this.promociones = promociones; }

    /**
     * Compara dos clientes por su id
     *
     * @param obj el objeto a comparar
     * @return true si ambos clientes tienen el mismo id
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return id == cliente.id;
    }

    /**
     * Devuelve una represntación en texto del cliente
     *
     * @return el nombre y apellido del cliente
     */
    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
