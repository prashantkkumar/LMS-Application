package org.example.contentservice.Reposistory;


import org.example.contentservice.Entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content,Long> {
    List<Content> findByCourseId(Long courseId);
}
