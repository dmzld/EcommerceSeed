package com.example.EcommerceSeed.dto.response;

import com.example.EcommerceSeed.util.MessageUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private String result;
    private String caution;

    public BaseResponse(){
        this.result = MessageUtils.SUCCESS;
        this.caution = "";
    }
    public BaseResponse(String caution){
        result = MessageUtils.FAIL;
        this.caution = caution;
    }
}
