package com.keyin.Repository;

import com.keyin.Entities.TreeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TreeRepository extends JpaRepository<TreeRecord, Long> {
    List<TreeRecord> findAllByOrderByCreatedAtDesc();
}