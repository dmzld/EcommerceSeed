package com.example.EcommerceSeed.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataResponse<T> extends BaseResponse{
    private T data;

    public DataResponse(T data){
        super();
        this.data = data;
    }

    public DataResponse(String caution, T data){
        super(caution);
        this.data = data;
    }
}
