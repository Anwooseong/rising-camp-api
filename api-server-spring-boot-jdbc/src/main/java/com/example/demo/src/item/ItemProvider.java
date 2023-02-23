package com.example.demo.src.item;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.item.model.GetItemRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ItemProvider {

    private final ItemDao itemDao;
    public GetItemRes getItem(int itemId) {
        GetItemRes getItemRes = itemDao.getItem(itemId);
        return getItemRes;
    }
}
