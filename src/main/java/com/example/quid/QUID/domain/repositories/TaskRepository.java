package com.example.quid.QUID.domain.repositories;

import com.example.quid.QUID.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByUserId(int userId);
}
