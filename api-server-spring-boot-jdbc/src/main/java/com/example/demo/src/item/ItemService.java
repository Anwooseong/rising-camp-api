package com.example.demo.src.item;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.item.model.GetItemRes;
import com.example.demo.src.item.model.PatchItemReq;
import com.example.demo.src.item.model.PostItemReq;
import com.example.demo.src.item.model.PostItemRes;
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
}
