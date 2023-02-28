package com.example.demo.src.item;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.item.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ItemProvider itemProvider;

    @PostMapping("")
    public BaseResponse<PostItemRes> createItem(@Validated @RequestBody PostItemReq postItemReq){
        PostItemRes postItemRes = itemService.createItem(postItemReq);
        return new BaseResponse<>(postItemRes);
    }

    @GetMapping("{itemId}")
    public BaseResponse<GetItemRes> getItem(@PathVariable int itemId) {
        GetItemRes getItemRes = itemProvider.getItem(itemId);
        return new BaseResponse<>(getItemRes);
    }

    @PatchMapping("{itemId}")
    public BaseResponse<GetItemRes> patchItem(@PathVariable int itemId,@Validated @RequestBody Item item){
        PatchItemReq patchItemReq = new PatchItemReq(itemId, item.getPrice(), item.getStock());
        itemService.modifyItem(patchItemReq);
        GetItemRes getItemRes = itemProvider.getItem(itemId);
        return new BaseResponse<>(getItemRes);
    }

    @PutMapping("{itemId}")
    public BaseResponse<PutItemRes> changeItem(@PathVariable int itemId,@Validated @RequestBody PostItemReq item){
        PutItemRes putItemRes = itemService.putItem(itemId, item);
        return new BaseResponse<>(putItemRes);
    }

    @DeleteMapping("{itemId}")
    public BaseResponse<String> deleteItem(@PathVariable int itemId) {
        String result = itemService.deleteItem(itemId);
        return new BaseResponse<>(result);
    }







}
