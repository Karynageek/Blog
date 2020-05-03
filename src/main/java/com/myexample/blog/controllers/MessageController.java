package com.myexample.blog.controllers;

import com.myexample.blog.models.Message;
import com.myexample.blog.models.Post;
import com.myexample.blog.models.User;
import com.myexample.blog.repo.MessageRepo;
import com.myexample.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/message")
    public  String messageViews(Model model){
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "message";
    }
    @PostMapping("/message")
    public  String messageSendAll(@AuthenticationPrincipal User user, @RequestParam String tag, @RequestParam String text ){
        Message message = new Message(user, tag, text);
        messageRepo.save(message);
        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}")
    public  String messageSend(@AuthenticationPrincipal User user, @RequestParam String tag, @RequestParam String text ){
        Message message = new Message(user, tag, text);
        messageRepo.save(message);
        return "redirect:/blog/{id}";
    }
    @PostMapping("/blog/{id}/filter")
    public  String filter(@RequestParam String filter, Map<String, Object> model ){
        List<Message> messages = messageRepo.findByTag(filter);
        model.put("messages", messages);
        return "blog-details";
    }
}
