package cn.com.utils;

public class OAUtils {
	public static Long[] String2Longs(String ids){
		String[] s=ids.split(",");
		Long[] aa=new Long[s.length];
		int index=0;
		for(String str:s){
			aa[index]=Long.parseLong(str);
			index++;
		}
		return aa;
	}
}
