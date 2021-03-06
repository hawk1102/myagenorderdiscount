package com.agen.myagen.repository;


import com.agen.myagen.entity.XxAdmin;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * admin持久化层
 * 基因网机构 持久化层
 *
 * 如果不特殊指定，缓存都放到admins缓存库中
 * @author SXD
 * @date 2017/12/26
 */
@CacheConfig(cacheNames = "admins")
public interface AdminRepository extends JpaRepository<XxAdmin,Integer> {

    /**
     * 查找机构信息
     * 并缓存到redis，键为id  值为XxAdmin
     * @param adminId
     * @return
     */
    @Cacheable(keyGenerator = "firstParamKeyGenerator")
    XxAdmin findXxAdminById(Integer adminId);

    /**
     * 查找所有机构信息
     * 并缓存到redis，缓存库为24h,键为 方法名findAllAdmins，值为List<XxAdmin>
     * 缓存24小时失效  用于可能机构有更新的情况
     * @return
     */
    @Cacheable(value = "24h",keyGenerator = "listkeyGenerator")
    @Query("select admins from XxAdmin admins")
    List<XxAdmin> findAllAdmins();
}
