package com.example.demo.board.repository

import com.example.demo.board.entity.Board
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.domain.Pageable

interface BoardRepository : JpaRepository<Board, Long> {
    fun findAllByOrderByCreatedAtDesc(pageable: Pageable): Page<Board>
}