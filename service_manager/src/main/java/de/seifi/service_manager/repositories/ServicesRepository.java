package de.seifi.service_manager.repositories;

import de.seifi.service_manager.entities.ServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ServicesRepository extends JpaRepository<ServicesEntity, UUID> {

    @Query("SELECT s FROM ServicesEntity s WHERE s.status = :status")
    List<ServicesEntity> findAllByStatus(@Param("status") Integer status);
}
