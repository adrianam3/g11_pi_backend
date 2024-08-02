package com.hd.helpDesk.modules.tfaqsDetalles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hd.helpDesk.modules.modelos.TfaqsDetalle;

@Repository
public interface TfaqsDetalleRepository extends JpaRepository<TfaqsDetalle, Integer> {

    @Query(value = "SELECT faq.resolucion FROM TfaqsDetalle faq WHERE faq.titulo LIKE %:consulta%")
    public List<String> getSolucionesFaq(@Param("consulta") String consulta);

    @Query(value = "SELECT faq.resolucion FROM TfaqsDetalle faq WHERE faq.sec = 1 AND faq.titulo LIKE :consulta")
    public String getSolucionFaq(@Param("consulta") String consulta);

    @Query(value = "SELECT faq FROM TfaqsDetalle faq WHERE faq.estado ='1'")
    public List<TfaqsDetalle> getSolucionesActivas();

}
