package cn.com.struts2.action.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 这里封装一些共用的东西
 * @author Administrator
 *
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	private Class clazz;
	private T t;
	public BaseAction(){
		try {
			ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
			clazz=(Class) type.getActualTypeArguments()[0];
			this.t=(T) this.clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static final String LISTACTION="listaction";
	public static final String ADDUI="addUI";
	public static final String UPDATEUI="updateUI";
	public static final String ACTION2ACTION="action2action";
	public String listAction=LISTACTION;
	public String updateUI=UPDATEUI;
	public String addUI=ADDUI;
	public String action2action=ACTION2ACTION;
	@Override
	public T getModel() {
		return this.t;
	}
}
