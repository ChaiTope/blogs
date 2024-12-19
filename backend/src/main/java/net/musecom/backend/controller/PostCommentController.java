package net.musecom.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.musecom.backend.entity.PostComment;
import net.musecom.backend.repository.PostCommentRepository;

@RestController
@RequestMapping("/api/posts/comment")
public class PostCommentController {
 
    private final PostCommentRepository postCommentRepository;

    @Autowired
    public PostCommentController(PostCommentRepository postCommentRepository){
        this.postCommentRepository = postCommentRepository;
    }

    //목록
    @GetMapping("{postId}")
    public List<PostComment> getAllPostsComments(@PathVariable("postId") long postId){
        return postCommentRepository.findAllByPostId(postId);
    }

    //삭제
    @DeleteMapping
    public String deletePostComment(@RequestParam long id) {
        if(postCommentRepository.existsById(id)){
            postCommentRepository.deleteById(id);
            return "삭제했습니다.";
        }else{
            return "삭제 할 댓글이 존재하지 않습니다.";
        }
    }

    //쓰기
    @PostMapping
    public PostComment createPostComment(@RequestBody PostComment postComment) {
        return postCommentRepository.save(postComment);
    }
}
