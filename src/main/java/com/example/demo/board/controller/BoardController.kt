package com.example.demo.board.controller

import com.example.demo.board.entity.Board
import com.example.demo.board.service.BoardService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("boards")
class BoardController(
    private val boardService: BoardService // 주생성자를 통한 의존성 주입
) {

    @GetMapping
    fun getBoards(
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): List<Board> {
        return boardService.getBoards(page, size)
    }
}