package com.example.demo.src.item;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.item.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemDao itemDao;

    @Transactional
    public PostItemRes createItem(PostItemReq postItemReq) throws BaseException{
        int itemId = itemDao.createItem(postItemReq);
        return new PostItemRes(itemId, postItemReq.getTitle(), postItemReq.getComments(), postItemReq.getPrice(), postItemReq.getStock(), postItemReq.getImageUrl());
    }

    @Transactional
    public void modifyItem(PatchItemReq patchItemReq) throws  BaseException{
        int result = itemDao.modifyPriceAndStock(patchItemReq);
        if (result == 0) {
            throw new BaseException(BaseResponseStatus.MODIFY_FAIL_PRICE_AND_STOCK);
        }
    }

    @Transactional
    public String deleteItem(int itemId) throws BaseException {
        int result = itemDao.deleteItem(itemId);
        if (result == 0) {
            throw new BaseException(BaseResponseStatus.DELETE_FAIL_ITEM);
        }
        return "itemId : " + itemId + " 삭제 완료";
    }

    @Transactional
    public PutItemRes putItem(int itemId, PostItemReq item) throws BaseException {
        itemDao.changeItem(itemId, item);
        PutItemRes putItemRes = itemDao.findByItem(itemId);
        return putItemRes;
    }
}
