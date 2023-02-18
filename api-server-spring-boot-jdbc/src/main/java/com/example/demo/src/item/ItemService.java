package com.example.demo.src.item;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.item.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemDao itemDao;
    public PostItemRes createItem(PostItemReq postItemReq) throws BaseException{
        try {
            int itemId = itemDao.createItem(postItemReq);
            return new PostItemRes(itemId, postItemReq.getTitle(), postItemReq.getComments(), postItemReq.getPrice(), postItemReq.getStock(), postItemReq.getImageUrl());
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public void modifyItem(PatchItemReq patchItemReq) throws  BaseException{
        try {
            int result = itemDao.modifyPriceAndStock(patchItemReq);
            if (result == 0) {
                throw new BaseException(BaseResponseStatus.MODIFY_FAIL_PRICE_AND_STOCK);
            }
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public String deleteItem(int itemId) throws BaseException {
        try {
            int result = itemDao.deleteItem(itemId);
            if (result == 0) {
                throw new BaseException(BaseResponseStatus.DELETE_FAIL_ITEM);
            }
            return "itemId : " + itemId + " 삭제 완료";
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public PutItemRes putItem(int itemId, PostItemReq item) throws BaseException {
        try {
            itemDao.changeItem(itemId, item);
            PutItemRes putItemRes = itemDao.findByItem(itemId);
            return putItemRes;
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
