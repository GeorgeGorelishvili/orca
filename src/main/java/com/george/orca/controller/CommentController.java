package com.george.orca.controller;

import com.george.orca.domain.CommentEntity;
import com.george.orca.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("add")
    public ResponseEntity<CommentEntity> add(@RequestParam String comment,
                                          @RequestParam Long loanId,
                                          @RequestParam Boolean promise,
                                          @RequestParam Date promiseDate) {
        //TODO ავტორის ამოღება დალოგინებული იუზერიდან
        String author = "";
        CommentEntity commentEntity = CommentEntity.builder()
                .comment(comment)
                .loanId(loanId)
                .author(author)
                .promise(promise)
                .promiseDate(promiseDate)
                .build();
        commentEntity = commentService.edit(commentEntity);
        return ResponseEntity.ok(commentEntity);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public CommentEntity edit(@RequestBody CommentEntity commentEntity) {
        return commentService.edit(commentEntity);
    }

    @GetMapping("get")
    public List<CommentEntity> add(@RequestParam Long loanId) {
        return commentService.list(loanId);
    }



    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long commentId) {
        commentService.delete(commentId);
    }
}
