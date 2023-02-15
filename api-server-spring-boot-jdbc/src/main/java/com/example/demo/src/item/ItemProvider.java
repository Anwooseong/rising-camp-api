package com.example.demo.src.item;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.item.model.GetItemRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemProvider {

    private final ItemDao itemDao;
    public GetItemRes getItem(int itemId) throws BaseException {
        try {
            GetItemRes getItemRes = itemDao.getItem(itemId);
            return getItemRes;
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }

    }
}
