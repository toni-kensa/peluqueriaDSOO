public class Cliente {
    private long id;
    private String nombre;
    private String apellido;
    private String contacto;
    private String categoria;
    private boolean promociones;

    public Cliente(long id, String nombre, String apellido, String contacto, String categoria, boolean promociones) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contacto = contacto;
        this.categoria = categoria;
        this.promociones = promociones;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public boolean isPromociones() { return promociones; }
    public void setPromociones(boolean promociones) { this.promociones = promociones; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return id == cliente.id;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}