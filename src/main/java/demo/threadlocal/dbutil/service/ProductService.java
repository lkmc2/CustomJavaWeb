package demo.threadlocal.dbutil.service;

/**
 * @author lkmc2
 * @date 2018/9/15
 * @description 产品服务接口
 */
public interface ProductService {
    /**
     * 更新产品价格
     * @param productId 产品id
     * @param price 价格
     */
    void updateProductPrice(long productId, int price);
}
