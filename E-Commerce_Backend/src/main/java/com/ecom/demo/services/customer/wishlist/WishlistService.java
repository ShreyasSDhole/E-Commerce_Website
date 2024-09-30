package com.ecom.demo.services.customer.wishlist;

import java.util.List;

import com.ecom.demo.dto.WishlistDto;

public interface WishlistService 
{
	WishlistDto addProductToWishlist(WishlistDto wishlistDto);
	
	List<WishlistDto> getWishlistByUserId(int userId);
}
