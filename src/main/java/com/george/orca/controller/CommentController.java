package com.george.orca.controller;

import com.george.orca.domain.CommentEntity;
import com.george.orca.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("add")
    public ResponseEntity<CommentEntity> add(@RequestParam String comment,
                                          @RequestParam Long loanId) {
        CommentEntity commentEntity = CommentEntity.builder()
                .comment(comment)
                .loan_id(loanId)
                .build();
        commentEntity = commentService.edit(commentEntity);
        return ResponseEntity.ok(commentEntity);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public CommentEntity edit(@RequestBody CommentEntity commentEntity) {
        return commentService.edit(commentEntity);
    }

    @GetMapping("list")
    public List<CommentEntity> employees() {
        return commentService.list();
    }



    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long commentId) {
        commentService.delete(commentId);
    }
}
