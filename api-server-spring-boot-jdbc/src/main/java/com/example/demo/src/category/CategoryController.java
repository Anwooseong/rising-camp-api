package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.category.model.GetItemsRes;
import com.example.demo.src.category.model.PostCategoryReq;
import com.example.demo.src.category.model.PostCategoryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryProvider categoryProvider;
    private final CategoryService categoryService;

    @GetMapping("/categories/{categoryId}")
    public BaseResponse<List<GetItemsRes>> getItemsRes(@PathVariable int categoryId) {
        List<GetItemsRes> getItemsRes = categoryProvider.getItems(categoryId);
        return new BaseResponse<>(getItemsRes);
    }

    @PostMapping("/categories")
    public BaseResponse<PostCategoryRes> createCategory(@Validated @RequestBody PostCategoryReq postCategoryReq) {
        PostCategoryRes postCategoryRes = categoryService.createCategory(postCategoryReq);
        return new BaseResponse<>(postCategoryRes);
    }
}
