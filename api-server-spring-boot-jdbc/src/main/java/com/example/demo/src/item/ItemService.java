package com.example.demo.src.item;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.item.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ItemService {

    private final ItemDao itemDao;

    public PostItemRes createItem(PostItemReq postItemReq){
        int itemId = itemDao.createItem(postItemReq);
        return new PostItemRes(itemId, postItemReq.getTitle(), postItemReq.getComments(), postItemReq.getPrice(), postItemReq.getStock(), postItemReq.getImageUrl());
    }

    public void modifyItem(PatchItemReq patchItemReq) {
        int result = itemDao.modifyPriceAndStock(patchItemReq);
        if (result == 0) {
            throw new BaseException(BaseResponseStatus.MODIFY_FAIL_PRICE_AND_STOCK);
        }
    }

    public String deleteItem(int itemId)  {
        int result = itemDao.deleteItem(itemId);
        if (result == 0) {
            throw new BaseException(BaseResponseStatus.DELETE_FAIL_ITEM);
        }
        return "itemId : " + itemId + " 삭제 완료";
    }

    public PutItemRes putItem(int itemId, PostItemReq item) {
        itemDao.changeItem(itemId, item);
        PutItemRes putItemRes = itemDao.findByItem(itemId);
        return putItemRes;
    }
}
