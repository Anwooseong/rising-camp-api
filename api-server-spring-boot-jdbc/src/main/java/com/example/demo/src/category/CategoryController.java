package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.category.model.GetItemsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryProvider categoryProvider;
    private final CategoryService categoryService;

    @GetMapping("/categories/{categoryId}")
    public BaseResponse<List<GetItemsRes>> getItemsRes(@PathVariable int categoryId) {
        try {
            List<GetItemsRes> getItemsRes = categoryProvider.getItems(categoryId);
            return new BaseResponse<>(getItemsRes);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }
}
