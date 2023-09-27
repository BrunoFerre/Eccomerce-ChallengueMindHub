package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
