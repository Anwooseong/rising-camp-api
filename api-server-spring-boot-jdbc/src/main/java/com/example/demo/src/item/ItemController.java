package com.example.demo.src.item;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.item.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ItemProvider itemProvider;

    @PostMapping("")
    public BaseResponse<PostItemRes> createItem(@RequestBody PostItemReq postItemReq) {
        try {
            PostItemRes postItemRes = itemService.createItem(postItemReq);
            return new BaseResponse<>(postItemRes);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }

    @GetMapping("{itemId}")
    public BaseResponse<GetItemRes> getItem(@PathVariable int itemId) {
        try {
            GetItemRes getItemRes = itemProvider.getItem(itemId);
            return new BaseResponse<>(getItemRes);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }

    @PatchMapping("{itemId}")
    public BaseResponse<GetItemRes> patchItem(@PathVariable int itemId, @RequestBody Item item) {
        try {
            PatchItemReq patchItemReq = new PatchItemReq(itemId, item.getPrice(), item.getStock());
            itemService.modifyItem(patchItemReq);
            GetItemRes getItemRes = itemProvider.getItem(itemId);
            return new BaseResponse<>(getItemRes);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }

    @PutMapping("{itemId}")
    public BaseResponse<PutItemRes> changeItem(@PathVariable int itemId, @RequestBody PostItemReq item) {
        try {
            PutItemRes putItemRes = itemService.putItem(itemId, item);
            return new BaseResponse<>(putItemRes);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }

    @DeleteMapping("{itemId}")
    public BaseResponse<String> deleteItem(@PathVariable int itemId) {
        try {
            String result = itemService.deleteItem(itemId);
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }







}
