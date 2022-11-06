package com.autumn.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {

    protected String getValidationMsg(BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                stringBuilder.append(fieldError.getDefaultMessage());
            }
            return stringBuilder.toString();
        }
        return null;
    }

    protected Map<String, Object> pageToMap(Page pageInfo) {
        HashMap resultMap = new HashMap(16);
        resultMap.put("total", pageInfo.getTotal());
        resultMap.put("pages", pageInfo.getPages());
        resultMap.put("pageSize", pageInfo.getSize());
        resultMap.put("pageNum", pageInfo.getCurrent());
        resultMap.put("list", pageInfo.getRecords());
        return resultMap;
    }
}
