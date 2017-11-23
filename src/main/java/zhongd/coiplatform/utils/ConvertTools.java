package zhongd.coiplatform.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class ConvertTools {
	public static <T> T convert(Class<T> clazz, Object preObj){
		if(preObj != null) {
			Object newObj = null;
			try {
				newObj = clazz.newInstance();
				BeanUtils.copyProperties(newObj, preObj);
				return (T) newObj;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static <T, D> List<T> convert(Class<T> clazz, List<D> objList) {
		ArrayList<T> resultList = new ArrayList<T>();
		if (CollectionUtil.isEmpty(objList)) {
			return resultList;
		} else {
			Iterator<D> arg2 = objList.iterator();

			while (arg2.hasNext()) {
				Object obj = arg2.next();
				resultList.add(convert(clazz, obj));
			}

			return resultList;
		}
	}
	
}
