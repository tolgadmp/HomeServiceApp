package com.finalproject.homeservice.repository;

import com.finalproject.homeservice.entity.Comment;
import com.finalproject.homeservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Post, Long> {

   // List<Comment> findByPostId(long postId);
}
