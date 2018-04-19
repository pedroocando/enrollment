package models;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import io.ebean.Model;
import io.ebean.Finder;
@Entity
@Table(name = "enrollments")
public class Enrollment  extends AbstractEntity {

    // informacion basica
    @Column
    protected Integer id_partner;
    @Column
    protected Integer id_branch;
    @Column
    protected  Integer id_service;
    @Column
    protected  Integer id_plan;
    @Column
    protected  String token;


    // informacion del customer
    @Column
    protected String dni;
    @Column
    protected String nombre;
    @Column
    protected String apellido;
    @Column
    protected String telefono;
    @Column
    protected String direccion;
    @Column
    protected String email;
    @Column
    protected Date fecha_nacimiento;
    @Column
    protected Date fecha_admision;
    @Column
    protected Date fecha_expiracion;
    @Column
    protected String numero_contrato;
    @Column
    protected String codigo;

    // informacion de la tdc

    @Column
    protected String numer_tarjeta;
    @Column
    protected String nombre_propietario;
    @Column
    protected String mes_expiracion;
    @Column
    protected Integer ano_expiracion;

    // estatus de la operacion
    @Column
    protected boolean status;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public Integer getId_plan() {
        return id_plan;
    }

    public void setId_plan(Integer id_plan) {
        this.id_plan = id_plan;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Finder
    private static Finder<Long, Enrollment> finder = new Finder<>(Enrollment.class);


    public Integer getId_partner() {
        return id_partner;
    }

    public void setId_partner(Integer id_partner) {
        this.id_partner = id_partner;
    }

    public Integer getId_branch() {
        return id_branch;
    }

    public void setId_branch(Integer id_branch) {
        this.id_branch = id_branch;
    }

    public Integer getId_service() {
        return id_service;
    }

    public void setId_service(Integer id_service) {
        this.id_service = id_service;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Date getFecha_admision() {
        return fecha_admision;
    }

    public void setFecha_admision(Date fecha_admision) {
        this.fecha_admision = fecha_admision;
    }

    public Date getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(Date fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public String getNumero_contrato() {
        return numero_contrato;
    }

    public void setNumero_contrato(String numero_contrato) {
        this.numero_contrato = numero_contrato;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNumer_tarjeta() {
        return numer_tarjeta;
    }

    public void setNumer_tarjeta(String numer_tarjeta) {
        this.numer_tarjeta = numer_tarjeta;
    }

    public String getNombre_propietario() {
        return nombre_propietario;
    }

    public void setNombre_propietario(String nombre_prpietario) {
        this.nombre_propietario = nombre_prpietario;
    }

    public String getMes_expiracion() {
        return mes_expiracion;
    }

    public void setMes_expiracion(String mes_expiracion) {
        this.mes_expiracion = mes_expiracion;
    }

    public Integer getAno_expiracion() {
        return ano_expiracion;
    }

    public void setAno_expiracion(Integer ano_expiracion) {
        this.ano_expiracion = ano_expiracion;

    }

    public List<Enrollment> get_all(){
        return finder.all();
    }

    public Enrollment update(Enrollment entity){
        //warm: si no exite el <id> pero tampoco se le manda actualizar campos, no genera una excepción
        ((Model) entity).update();
        return entity;
    }

    public Enrollment findById(Integer id){
        Enrollment entity = finder.byId(id.longValue());
        return entity;
    }



}
