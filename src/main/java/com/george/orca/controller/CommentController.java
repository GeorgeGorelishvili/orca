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

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<CommentEntity> add(@RequestBody CommentEntity commentEntity) {

        Date date = new Date();


        //TODO ავტორის ამოღება დალოგინებული იუზერიდან
        commentEntity.setCreateDate(date);

        String author = "";

        commentEntity = commentService.edit(commentEntity);
        return ResponseEntity.ok(commentEntity);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @CrossOrigin
    public CommentEntity edit(@RequestBody CommentEntity commentEntity) {
        Date date = new Date();


        commentEntity.setCreateDate(date);
        return commentService.edit(commentEntity);
    }

    @GetMapping("get")
    @CrossOrigin
    public List<CommentEntity> add(@RequestParam Long loanId) {
        return commentService.list(loanId);
    }



    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long commentId) {
        commentService.delete(commentId);
    }
}
