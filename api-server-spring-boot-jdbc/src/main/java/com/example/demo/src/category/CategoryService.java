package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.category.model.PostCategoryReq;
import com.example.demo.src.category.model.PostCategoryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryDao categoryDao;
    public PostCategoryRes createCategory(PostCategoryReq postCategoryReq) throws BaseException {
        if(categoryDao.findCategory(postCategoryReq)==1){
            throw new BaseException(BaseResponseStatus.POST_CATEGORY_NAME);
        }
        categoryDao.createCategory(postCategoryReq);
        PostCategoryRes postCategoryRes = categoryDao.findByReq(postCategoryReq);
        return postCategoryRes;
    }
}
