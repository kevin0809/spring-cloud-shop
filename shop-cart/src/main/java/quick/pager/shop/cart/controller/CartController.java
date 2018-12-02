package quick.pager.shop.cart.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.common.response.Response;
import quick.pager.shop.cart.dto.CartDTO;
import quick.pager.shop.cart.request.CartRequest;
import quick.pager.shop.cart.service.CartListService;
import quick.pager.shop.cart.service.CartModifyService;

/**
 * 购物车<br />
 * 添加购物车
 * 删除购物车商品
 */
@RestController
@Api(description = "购物车")
public class CartController {

    @Autowired
    private CartListService cartListService;
    @Autowired
    private CartModifyService cartModifyService;

    /**
     * 购物车列表
     *
     * @param userId 用户Id
     */
    @ApiOperation("购物车列表")
    @RequestMapping(value = "/cart/list/{userId}", method = RequestMethod.POST)
    public Response cartList(@PathVariable("userId") Long userId) {
        CartDTO dto = new CartDTO();
        dto.setUserId(userId);
        return cartListService.doService(dto);
    }

    /**
     * 购物车商品的添加与删除
     */
    @ApiOperation("购物车商品的添加与删除")
    @RequestMapping("/cart/modify")
    public Response addCart(CartRequest request) {
        CartDTO dto = new CartDTO();
        dto.setUserId(request.getUserId());
        dto.setGoodsIds(request.getGoodsIds());
        dto.setOperation(request.getOperation());

        return cartModifyService.doService(dto);
    }


}
