package com.lxx.mall.service.impl;

import com.lxx.mall.common.Constant;
import com.lxx.mall.exception.LxxMallException;
import com.lxx.mall.exception.LxxMallExceptionEnum;
import com.lxx.mall.filter.UserFilter;
import com.lxx.mall.model.dao.CartMapper;
import com.lxx.mall.model.dao.OrderItemMapper;
import com.lxx.mall.model.dao.OrderMapper;
import com.lxx.mall.model.dao.ProductMapper;
import com.lxx.mall.model.pojo.Order;
import com.lxx.mall.model.pojo.OrderItem;
import com.lxx.mall.model.pojo.Product;
import com.lxx.mall.model.request.CreateOrderReq;
import com.lxx.mall.model.vo.CartVO;
import com.lxx.mall.model.vo.OrderItemVO;
import com.lxx.mall.model.vo.OrderVO;
import com.lxx.mall.service.CartService;
import com.lxx.mall.service.OrderService;
import com.lxx.mall.util.OrderCodeFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 林修贤
 * @date 2023/2/18
 * @description 订单接口实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CartService cartService;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    //数据库事务
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String create(CreateOrderReq createOrderReq){
        //拿到用户ID
        Integer userId = UserFilter.currentUser.getId();
        //从购物车查找已勾选的商品
        List<CartVO> cartVOList = cartService.list(userId);
        ArrayList<CartVO> cartVOListTemp = new ArrayList<>();
        for (int i = 0; i < cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            if (cartVO.getSelected().equals(Constant.Cart.CHECKED)){
                cartVOListTemp.add(cartVO);
            }
        }
        cartVOList = cartVOListTemp;
        //如果已勾选的为空， 报错
        if(CollectionUtils.isEmpty(cartVOList)){
            throw new LxxMallException(LxxMallExceptionEnum.CART_EMPTY);
        }
        //判断商品是否存在，上下架状态，库存
        validSaleStatusAndStock(cartVOList);
        //把购物车对象转化为订单item对象
        List<OrderItem> orderItemList = cartVOListToOrderItemList(cartVOList);
        //扣库存
        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            Product product = productMapper.selectByPrimaryKey(orderItem.getProductId());
            int stock = product.getStock() - orderItem.getQuantity();
            if(stock < 0){
                throw new LxxMallException(LxxMallExceptionEnum.NOT_ENOUGH);
            }
            product.setStock(stock);
            productMapper.updateByPrimaryKeySelective(product);
        }
        //把购物车中已勾选的商品删除
        cleanCart(cartVOList);
        //生成订单
        Order order = new Order();
        //生成订单号， 独立规则
        String orderNo = OrderCodeFactory.getOrderCode(Long.valueOf(userId));
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setTotalPrice(totalPrice(orderItemList));
        order.setReceiverName(createOrderReq.getReceiverName());
        order.setReceiverMobile(createOrderReq.getReceiverMobile());
        order.setReceiverAddress(createOrderReq.getReceiverAddress());
        order.setOrderStatus(Constant.OrderStatusEnum.NOT_PAID.getCode());
        order.setPostage(0);
        order.setPaymentType(1);
        //插入到order 表中
        orderMapper.insertSelective(order);
        //循环保存每个商品到 order_item 表
        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            orderItem.setOrderNo(order.getOrderNo());
            orderItemMapper.insertSelective(orderItem);
        }
        //把结果返回
        return orderNo;
    }

    private Integer totalPrice(List<OrderItem> orderItemList) {
        Integer totalPrice = 0;
        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    private void cleanCart(List<CartVO> cartVOList) {
        for (int i = 0; i < cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            cartMapper.deleteByPrimaryKey(cartVO.getId());
        }
    }

    private List<OrderItem> cartVOListToOrderItemList(List<CartVO> cartVOList) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (int i = 0; i < cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartVO.getProductId());
            //记录商品快照信息
            orderItem.setProductName(cartVO.getProductName());
            orderItem.setProductImg(cartVO.getProductImage());
            orderItem.setUnitPrice(cartVO.getPrice());
            orderItem.setQuantity(cartVO.getQuantity());
            orderItem.setTotalPrice(cartVO.getTotalPrice());
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    private void validSaleStatusAndStock(List<CartVO> cartVOList) {
        for (int i = 0; i < cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            Product product = productMapper.selectByPrimaryKey(cartVO.getProductId());
            //判断商品是否存在,是否上架
            if (product == null || product.getStatus().equals(Constant.SaleStatus.NOT_SALE)){
                throw new LxxMallException(LxxMallExceptionEnum.NOT_SALE);
            }
            //判断商品库存
            if (cartVO.getQuantity() > product.getStock()){
                throw new LxxMallException(LxxMallExceptionEnum.NOT_ENOUGH);
            }
        }

    }

    @Override
    public OrderVO detail(String orderNo){
        Order order = orderMapper.selectByOrderNo(orderNo);
        //订单不存在, 报错
        if(order == null){
            throw new LxxMallException(LxxMallExceptionEnum.NO_ORDER);
        }
        //订单存在，判断所属
        Integer userId = UserFilter.currentUser.getId();
        if(!order.getUserId().equals(userId)){
            throw new LxxMallException(LxxMallExceptionEnum.NOT_YOUR_ORDER);
        }

        OrderVO orderVO = getOrderVO(order);
        return orderVO;
    }

    private OrderVO getOrderVO(Order order) {
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        // 获取订单对应的 orderItemVOList
        List<OrderItem> orderItemList = orderItemMapper.selectByOrderNo(order.getOrderNo());
        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            OrderItemVO orderItemVO = new OrderItemVO();
            BeanUtils.copyProperties(orderItem, orderItemVO);
            orderItemVOList.add(orderItemVO);
        }
        orderVO.setOrderItemVOList(orderItemVOList);
        orderVO.setOrderStatusName(Constant.OrderStatusEnum.codeOf(orderVO.getOrderStatus()).getValue());
        return orderVO;
    }

    @Override
    public void cancel(String orderNo){
        Order order = orderMapper.selectByOrderNo(orderNo);
        //查不到订单报错
        if(order == null){
            throw new LxxMallException(LxxMallExceptionEnum.NO_ORDER);
        }
        //订单存在，判断所属
        Integer userId = UserFilter.currentUser.getId();
        if(!order.getUserId().equals(userId)){
            throw new LxxMallException(LxxMallExceptionEnum.NOT_YOUR_ORDER);
        }
        if(order.getOrderStatus().equals(Constant.OrderStatusEnum.NOT_PAID.getCode())){
            order.setOrderStatus(Constant.OrderStatusEnum.CANCELED.getCode());
            order.setEndTime(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
        } else {
            throw new LxxMallException(LxxMallExceptionEnum.WRONG_ORDER_STATUS);
        }
    }
}
