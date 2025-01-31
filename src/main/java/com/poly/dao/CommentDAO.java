package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.models.Comment;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Integer> {

}
