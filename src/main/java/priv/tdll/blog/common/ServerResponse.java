package priv.tdll.blog.common;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {

	private int code;
	private String message;
	private T data;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> ServerResponse<T> createBySuccess() {
		ServerResponse<T> serverResponse = new ServerResponse<>();
		serverResponse.code = (ResponseCode.SUCCESS.getCode());
		return serverResponse;
	}

	public static <T> ServerResponse<T> createBySuccess(String message) {
		ServerResponse<T> serverResponse = new ServerResponse<>();
		serverResponse.code = (ResponseCode.SUCCESS.getCode());
		serverResponse.message = message;
		return serverResponse;
	}

	public static <T> ServerResponse<T> createByFailure(int code, String message) {
		ServerResponse<T> serverResponse = new ServerResponse<>();
		serverResponse.code = code;
		serverResponse.message = message;
		return serverResponse;
	}

	public static <T> ServerResponse<T> createByFailure(ResponseCode responseCode) {
		ServerResponse<T> serverResponse = new ServerResponse<>();
		serverResponse.code = responseCode.getCode();
		serverResponse.message = responseCode.getMessage();
		return serverResponse;
	}

	public ModelAndView toModelAndView() {
		ModelAndView mav = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		Map<String, Object> result = new HashMap<>();
		result.put("code", this.code);
		result.put("message", this.message);
		result.put("data", this.data);
		jsonView.setAttributesMap(result);
		mav.setView(jsonView);
		return mav;
	}

}
