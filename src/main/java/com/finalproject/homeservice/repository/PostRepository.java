package com.finalproject.homeservice.repository;

import com.finalproject.homeservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
