package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.category.model.GetItemsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CategoryProvider {
    private final CategoryDao categoryDao;
    public List<GetItemsRes> getItems(int categoryId) {
        List<GetItemsRes> getItemsRes = categoryDao.getItems(categoryId);
        return getItemsRes;
    }
}
