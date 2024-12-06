package net.musecom.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.musecom.backend.entity.MyTimelines;

public interface MyTimelinesRepository extends JpaRepository<MyTimelines, Long>{

}
