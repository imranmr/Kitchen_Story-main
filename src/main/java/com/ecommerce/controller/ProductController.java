package com.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.CreateProduct;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.Orders;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductDetail;
import com.ecommerce.model.User;
import com.ecommerce.model.UserAddProduct;
import com.ecommerce.model.UserBuy;
import com.ecommerce.model.UserRemoveProduct;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.OrderItemRepository;
import com.ecommerce.repository.OrdersRepository;
import com.ecommerce.repository.ProductDetailRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;

@RestController
@RequestMapping("/home")
public class ProductController {
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	ProductRepository productrepo;
	
	@Autowired
	ProductDetailRepository productdetailrepo;
	
	@Autowired
	CartRepository cartrepo;
	
	@Autowired
	CartItemRepository cartitemrepo;
	
	@Autowired
	OrdersRepository orderrepo;
	
	@Autowired
	OrderItemRepository orderitemrepo;
	
	@GetMapping("products")
	public List<Product> getAllProduct(){
		return (List<Product>) productrepo.findAll();
	}
	
	@PostMapping("add")
	public Cart userAddtoCart(@RequestBody UserAddProduct uap) throws Exception {
		//Need to find cart of user but cart needs to be active cart not all list of carts
		//Search userid in cart
		long userid = uap.getUserid();
		long productid = uap.getProductid();
		User tempuser = userrepo.findById(userid).orElse(null);
		Product tempproduct = productrepo.findById(productid).orElse(null);
		Cart tempcart = cartrepo.findByUserUserid(userid);
		
		CartItem newItem = cartitemrepo.findCartItemByProductProductid(productid);
		
		int buyquantity = uap.getQuantity();
		
		if (buyquantity <= tempproduct.getProductquantity() && (tempproduct!=null) && (tempuser!=null)) {
			int totalnum = tempproduct.getProductquantity() - buyquantity;
			tempproduct.setProductquantity(totalnum);
			productrepo.save(tempproduct);
			
			if (newItem != null) {//cartitem exists
				newItem.setCartitemquantity(newItem.getCartitemquantity()+ buyquantity);
				newItem.setTotalprice(newItem.getTotalprice()+(buyquantity * tempproduct.getPrice()));
				cartitemrepo.save(newItem);
				
				tempcart.setTotalprice(tempcart.getTotalprice()+(buyquantity * (tempproduct.getPrice())));
				return cartrepo.save(tempcart);
			}
			//If cart doesnt exist
			newItem = new CartItem();
			newItem.setCart(tempcart);
			newItem.setProduct(tempproduct);
			newItem.setCartitemquantity(buyquantity);
			newItem.setItemprice(tempproduct.getPrice());
			newItem.setTotalprice(buyquantity * tempproduct.getPrice());
			cartitemrepo.save(newItem);
			
			tempcart.setTotalprice(tempcart.getTotalprice()+(buyquantity * tempproduct.getPrice()));
			tempcart.setStatus("In Progress");
			return cartrepo.save(tempcart);
			
			
		}else {
			throw new Exception("Cannot add item - Not enough of the item");
		}
		
	}
	
	@PostMapping("remove")
	public Cart userRemovefromCart(@RequestBody UserRemoveProduct urp) throws Exception {
		long userid = urp.getUserid();
		long productid = urp.getProductid();
		long cartid = urp.getCartid();
		int removequantity = urp.getQuantity();
		
		User tempuser = userrepo.findById(userid).orElse(null);
		Product tempproduct = productrepo.findById(productid).orElse(null);
		Cart tempcart = cartrepo.findById(cartid).orElse(null);
		
		List<CartItem> tempcartitems = cartitemrepo.findCartItemByCartCartid(cartid);
		
		if(tempuser != null && tempproduct != null && tempcart != null) {
			for(int i= 0; i<tempcartitems.size();i++) {
				CartItem tempcartitem =  tempcartitems.get(i);
				if  ((tempcartitem.getCart().getCartid() == tempuser.getCart().getCartid()) && (tempcartitem.getProduct().getProductid() == productid)){
					int cartquant = tempcartitem.getCartitemquantity();
					
					if(cartquant-removequantity == 0) {
						cartitemrepo.deleteById(tempcartitem.getCartitemid());
					}else {
						tempcartitem.setCartitemquantity(cartquant-removequantity);
						tempcartitem.setTotalprice((cartquant-removequantity) * tempproduct.getPrice());
						cartitemrepo.save(tempcartitem);
					}
					
					tempproduct.setProductquantity(tempproduct.getProductquantity()+removequantity);//update quantity
					productrepo.save(tempproduct);
						
					float itemprice = tempcartitem.getProduct().getPrice();
					tempcart.setTotalprice(tempcart.getTotalprice()-(removequantity * itemprice));//update total price
					return cartrepo.save(tempcart); // update cart
						
				}
			}
			throw new Exception("Remove item error - User does not match cart");
		}	
		else {
			throw new Exception("Remove item error");
		}
		
	}
	@PostMapping("search")
	public List<Product> searchProduct(@RequestBody Product product){
		return (List<Product>) productrepo.findByProductname(product.getProductname());
	}
	//Get all cart items in cart
	@PostMapping("mycartitems")
	public List<CartItem> showUserCart(@RequestBody User user) throws Exception{
		User findUser = userrepo.findById(user.getUserid()).orElse(null);
		if (findUser != null) {
			List<CartItem> cartitems = cartitemrepo.findCartItemByCartCartid(findUser.getCart().getCartid());
			return cartitems;
		}else {
			throw new Exception("Cannot show cart - User not found");
		}
		
	}
	//Get User cart
	@GetMapping("getcart/{id}")
	public Cart showCart(@PathVariable long id) throws Exception {
		Cart tempcart = cartrepo.findByUserUserid(id);
		if(tempcart!=null) {
			return tempcart;
		}else {
			throw new Exception("Cannot get cart - wrong id");
		}
		
	}
	
	@PostMapping("buy")
	public Optional<Orders> buyProducts(@RequestBody UserBuy ub) throws Exception {
		User tempuser = userrepo.findById(ub.getUserid()).orElse(null);
		
		
		if (tempuser != null) {
			List<CartItem> cartitems = tempuser.getCart().getCartitem();
			Cart tempcart = tempuser.getCart();
			if (cartitems.size()>0) {
				Orders newOrder = new Orders();
				newOrder.setAddress(ub.getAddress());
				newOrder.setTotalprice(tempcart.getTotalprice());
				newOrder.setUser(tempuser);
				orderrepo.save(newOrder);
				
				for (int i=0; i<cartitems.size();i++) {
					OrderItem temporderitem = new OrderItem();
					temporderitem.setItemprice(cartitems.get(i).getItemprice());
					temporderitem.setOrderitemquantity(cartitems.get(i).getCartitemquantity());
					temporderitem.setOrders(newOrder);
					temporderitem.setProduct(cartitems.get(i).getProduct());
					temporderitem.setItemprice(cartitems.get(i).getItemprice());
					temporderitem.setTotalprice(cartitems.get(i).getTotalprice());
					orderitemrepo.save(temporderitem);
					
				}				
				tempcart.setStatus("Empty");
				tempcart.setTotalprice(0);
				tempcart.setCartitem(null);
				cartrepo.save(tempcart);
				
				for (int x=0; x<cartitems.size();x++) {
					System.out.println("Cart item: "+x+" , "+cartitems.get(x).getCartitemid());
					cartitemrepo.delete(cartitems.get(x));
				}
				
				return orderrepo.findById(newOrder.getOrderid());
			}else {
				throw new Exception("Buy error - Cart is empty");
			}
		}else {
			throw new Exception("Buy error - User does not exist!");
		}
		
	}
	
	//Search product by productid
	@GetMapping("filtersearch/{id}")
	public ProductDetail getProductFilter(@PathVariable long id) {
		return productdetailrepo.findByProductProductid(id);
	}
	//Delete Product
	@DeleteMapping("delete/{id}")
	public void deleteProduct(@PathVariable long id) {
		Product tempproduct = productrepo.findById(id).orElse(null);
		ProductDetail tempproductdetail = productdetailrepo.findByProductProductid(id);
		productdetailrepo.delete(tempproductdetail);
		productrepo.delete(tempproduct);
	}
	//Create Product
	@PostMapping("create")
	public Product createProduct(@RequestBody CreateProduct product) {
		Product temp= new Product();
		temp.setPrice(product.getPrice());
		temp.setProductname(product.getProductname());
		temp.setProductquantity(product.getProductquantity());
		temp.setUser(userrepo.findById(product.getUserid()).orElse(null));
		productrepo.save(temp);
		ProductDetail pdt = new ProductDetail();
		pdt.setProduct(temp);
		pdt.setType(product.getType());
		productdetailrepo.save(pdt);
		return temp;
	}
	
	
}
