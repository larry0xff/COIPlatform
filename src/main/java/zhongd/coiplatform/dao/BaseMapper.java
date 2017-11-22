package zhongd.coiplatform.dao;

import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * **********************************************************
 *
 * BaseSelectMapper<T>包含：
 *
 * 接口：SelectMapper<T>
 * 方法：List<T> select(T record);
 * 说明：根据实体中的属性值进行查询，查询条件使用等号
 *
 * 接口：SelectByPrimaryKeyMapper<T>
 * 方法：T selectByPrimaryKey(Object key);
 * 说明：根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
 *
 * 接口：SelectAllMapper<T>
 * 方法：List<T> selectAll();
 * 说明：查询全部结果，select(null)方法能达到同样的效果
 *
 * 接口：SelectOneMapper<T>
 * 方法：T selectOne(T record);
 * 说明：根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
 *
 * 接口：SelectCountMapper<T>
 * 方法：int selectCount(T record);
 * 说明：根据实体中的属性查询总数，查询条件使用等号
 *
 * BaseInsertMapper<T>包含：
 *
 * 接口：InsertMapper<T>
 * 方法：int insert(T record);
 * 说明：保存一个实体，null的属性也会保存，不会使用数据库默认值
 *
 * 接口：InsertSelectiveMapper<T>
 * 方法：int insertSelective(T record);
 * 说明：保存一个实体，null的属性不会保存，会使用数据库默认值
 *
 * BaseUpdateMapper<T> 包含
 *
 * 接口：UpdateByPrimaryKeyMapper<T>
 * 方法：int updateByPrimaryKey(T record);
 * 说明：根据主键更新实体全部字段，null值会被更新
 *
 * 接口：UpdateByPrimaryKeySelectiveMapper<T>
 * 方法：int updateByPrimaryKeySelective(T record);
 * 说明：根据主键更新属性不为null的值
 *
 * BaseDeleteMapper<T>包含：
 *
 * 接口：DeleteMapper<T>
 * 方法：int delete(T record);
 * 说明：根据实体属性作为条件进行删除，查询条件使用等号
 *
 * 接口：DeleteByPrimaryKeyMapper<T>
 * 方法：int deleteByPrimaryKey(Object key);
 * 说明：根据主键字段进行删除，方法参数必须包含完整的主键属性
 *
 * 接口：InsertListMapper<T>
 * 方法int InsertListMapper(List<Object> list )
 * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
 */
public interface BaseMapper<T> extends BaseSelectMapper<T>,
        BaseInsertMapper<T>,
        BaseUpdateMapper<T>,
        BaseDeleteMapper<T>,InsertListMapper<T> {

}
