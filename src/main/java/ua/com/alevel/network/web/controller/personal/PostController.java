package ua.com.alevel.network.web.controller.personal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.com.alevel.network.facade.PostFacade;
import ua.com.alevel.network.web.data.request.PostData;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 2:20 PM
 */

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostFacade postFacade;

    public PostController(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    @GetMapping("/all")
    public String findAllPost(WebRequest request, Model model) {
        model.addAttribute("pageData", postFacade.findAll(request));
        return "page/personal/post_all";
    }

    @GetMapping("/my")
    public String findMyPost(WebRequest request, Model model) {
        model.addAttribute("pageData", postFacade.findAll(request));
        return "page/personal/post_my";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Integer id, @RequestParam("reaction") boolean reaction) {
        model.addAttribute("postFullData", postFacade.findById(id));
        model.addAttribute("reaction", reaction);
        return "page/personal/post_details";
    }

    @GetMapping("/new")
    public String redirectToNewPage(Model model) {
        model.addAttribute("postForm", new PostData());
        return "page/personal/post_new";
    }

    @PostMapping("/new")
    public String newPost(@ModelAttribute("postForm") PostData data) {
        postFacade.create(data);
        return "redirect:/post/my";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdatePage(@PathVariable Integer id, Model model) {
        model.addAttribute("postForm", postFacade.findById(id));
        return "page/personal/post_update";
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute("postForm") PostData data, @RequestParam Integer id) {
        postFacade.update(data, id);
        return "redirect:/post/my";
    }

    @GetMapping("/like/{id}")
    public String likePost(@PathVariable Integer id) {
        postFacade.like(id);
        return "redirect:/post/details/" + id + "?reaction=true";
    }

    @GetMapping("/dislike/{id}")
    public String dislikePost(@PathVariable Integer id) {
        postFacade.dislike(id);
        return "redirect:/post/details/" + id + "?reaction=true";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        postFacade.delete(id);
        return "redirect:/post/my";
    }

    @PostMapping("/upload/{id}")
    public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer id, RedirectAttributes attributes) {
        System.out.println("id = " + id);
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/post/details/" + id + "?reaction=false";
        }
        postFacade.uploadFile(file, id);
        return "redirect:/post/details/" + id + "?reaction=false";
    }
}
