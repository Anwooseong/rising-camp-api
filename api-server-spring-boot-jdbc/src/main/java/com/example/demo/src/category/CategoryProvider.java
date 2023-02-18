package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.category.model.GetItemsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryProvider {
    private final CategoryDao categoryDao;
    public List<GetItemsRes> getItems(int categoryId) throws BaseException {
        try {
            List<GetItemsRes> getItemsRes = categoryDao.getItems(categoryId);
            return getItemsRes;
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
