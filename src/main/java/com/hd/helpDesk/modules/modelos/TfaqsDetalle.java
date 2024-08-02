package com.hd.helpDesk.modules.modelos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the TfaqsDetalle database table.
 * 
 */
@Getter
@Setter
@Entity
@NamedQuery(name = "TfaqsDetalle.findAll", query = "SELECT t FROM TfaqsDetalle t")
public class TfaqsDetalle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sec")
    private int sec;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "estado")
    private String estado;

    @Column(name = "id_departamento")
    private String idDepartamento;

    @Column(name = "respuesta")
    private String respuesta;

    @Column(name = "resolucion", columnDefinition = "TEXT")
    private String resolucion;

}
