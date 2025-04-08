package com.keyin.Repository;

import com.keyin.Entities.TreeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<TreeRecord, Long> {
}