package net.musecom.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.musecom.backend.entity.PostFile;

public interface PostFileRepository extends JpaRepository<PostFile, Long>{

    List<PostFile> findAllByNtime(Long ntime);

    void deleteFileByNtime(Long ntime);
}
