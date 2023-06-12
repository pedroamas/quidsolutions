package com.example.quid.QUID.domain.entities;

import com.example.quid.QUID.domain.commons.TaskStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE tasks SET deleted = CURRENT_TIMESTAMP() WHERE id=?")
@Where(clause = "deleted IS NULL")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status =  TaskStatus.PENDIENTE;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "deleted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted;
}
