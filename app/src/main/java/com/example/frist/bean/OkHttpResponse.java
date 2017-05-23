package com.example.frist.bean;

import java.io.Serializable;

/**
 *
 * @ClassName: HttpApiResponse
 * @Description:
 * @author dh
 * @date 2014-4-9 下午4:17:57
 *
 */
public class OkHttpResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer Code;
	private String Msg;

	public Integer getCode() {
		return Code;
	}

	public void setCode(Integer Code) {
		this.Code = Code;
	}


	public void setMsg(String Msg) {

		this.Msg = Msg;
	}

	public String getMsg() {
		return Msg;
	}

	public boolean isOK() {
		return Code == 0;
	}
}
