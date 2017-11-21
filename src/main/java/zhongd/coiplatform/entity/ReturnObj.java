package zhongd.coiplatform.entity;

/**
 * @author zhongd
 * 返回到前端的对象
 */
public class ReturnObj {
	/**
	 * 返回码
	 */
	private Integer returnCode;
	
	/**
	 * 备注信息
	 */
	private String msg;
	/**
	 * 返回的数据
	 */
	private Object data;
	public Integer getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
