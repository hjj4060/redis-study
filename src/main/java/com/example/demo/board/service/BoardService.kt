package com.example.demo.board.service

import com.example.demo.board.entity.Board
import com.example.demo.board.repository.BoardRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository
) {

    @Cacheable(cacheNames = ["getBoards"], key = "'boards:page:' + #page + ':size:' + #size", cacheManager = "boardCacheManager")
    fun getBoards(page: Int, size: Int): List<Board> {
        val pageable = PageRequest.of(page - 1, size)
        return boardRepository.findAllByOrderByCreatedAtDesc(pageable).toList()
    }
}