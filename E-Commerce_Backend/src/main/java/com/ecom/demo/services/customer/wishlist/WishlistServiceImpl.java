package com.ecom.demo.services.customer.wishlist;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.demo.dto.WishlistDto;
import com.ecom.demo.entity.Product;
import com.ecom.demo.entity.User;
import com.ecom.demo.entity.Wishlist;
import com.ecom.demo.repository.ProductRepository;
import com.ecom.demo.repository.UserRepository;
import com.ecom.demo.repository.WishlistRepository;

//import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@Service
public class WishlistServiceImpl implements WishlistService
{
	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final ProductRepository productRepository;
	
	@Autowired
	private final WishlistRepository wishlistRepository;

	public WishlistServiceImpl(UserRepository userRepository, ProductRepository productRepository,
			WishlistRepository wishlistRepository) 
	{
		super();
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.wishlistRepository = wishlistRepository;
	}
	
	public WishlistDto addProductToWishlist(WishlistDto wishlistDto)
	{
		Optional<Product> optionalProduct = productRepository.findById(wishlistDto.getProductId());
		Optional<User> optionalUser = userRepository.findById(wishlistDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalUser.isPresent())
		{
			Wishlist wishlist = new Wishlist();
			wishlist.setProduct(optionalProduct.get());
			wishlist.setUser(optionalUser.get());
			
			return wishlistRepository.save(wishlist).getWishlistDto();
		}
		return null;
	}
	
	public List<WishlistDto> getWishlistByUserId(int userId)
	{
		return wishlistRepository.findAllByUserId(userId).stream().map(Wishlist::getWishlistDto).collect(Collectors.toList());
	}
	

}
