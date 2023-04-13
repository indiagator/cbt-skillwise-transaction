package com.skillwise.cbtskillwisetransaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/1.1")
public class MainRestController
{
    @Autowired
    ProductOfferRepository productOfferRepository;
    @Autowired
    ProductListRepository productListRepository;

    @Autowired
    FullOfferService fullOfferService;

    @Autowired
    OrderRepository orderRepository;

    @PostMapping("save/offer")
    public ProductOffer saveOffer(@RequestBody ProductOffer offer)
    {
        // Auth and Auth Code

        Integer tempId = (int) (Math.random()*10000);

        offer.setId(tempId);

        productOfferRepository.save(offer);

        return offer;
    }

    @PostMapping("save/product")
    public Product saveProduct(@RequestBody Product product)
    {
        // Auth and Auth Code


        Integer tempId = (int) (Math.random()*10000);

        product.setId(tempId);

        productListRepository.save(product);

        return product;
    }

    @GetMapping("getall/offer/sellerwise")
    public List<FullOffer> getOffersSellerwise(@RequestParam("username") String username)
    {
        if(!(username == null))
        {
            List<ProductOffer> offerList =  productOfferRepository.findAll();
            List<ProductOffer> offersSellerwise = offerList.stream().filter((ProductOffer offer) -> {return offer.getUsername().equals(username);}).toList();

            List<FullOffer> fullOffersSellerwise = offersSellerwise.stream().map((ProductOffer offer) -> {return fullOfferService.composeFullOffer(offer.getId());}).toList();

            return fullOffersSellerwise;

        }
        else {

            List<ProductOffer> offerList =  productOfferRepository.findAll();
            List<FullOffer> fullOffers = offerList.stream().map((ProductOffer offer) -> {return fullOfferService.composeFullOffer(offer.getId());}).toList();

            return fullOffers;


        }

    }

    @GetMapping("getall/offer")
    public List<FullOffer> getOffers()
    {
            List<ProductOffer> offerList =  productOfferRepository.findAll();
            List<FullOffer> fullOffers = offerList.stream().map((ProductOffer offer) -> {return fullOfferService.composeFullOffer(offer.getId());}).toList();

            return fullOffers;
    }


    @PostMapping("save/order")
    public Order saveOrder(@RequestBody Order order)
    {
        Integer tempId = (int) (Math.random()*10000);
        order.setId(tempId);

        orderRepository.save(order);

        return order;
    }

    @GetMapping("get/order/buyerwise")
    public List<FullOrder> getOrdersBuyerwise(@RequestParam("username") String username)
    {
       List<Order> orderList =  orderRepository.findAll().stream().filter((Order order) -> {return order.getUsername().equals(username);}).toList();

       return orderList.stream().map((Order order) -> {

           FullOrder fullOrder = new FullOrder();
           fullOrder.setId(order.getId());
           fullOrder.setUsername(order.getUsername());
           fullOrder.setOfferid(order.getOfferid());
           fullOrder.setBid(order.getBid());
           fullOrder.setComments(order.getComments());
           fullOrder.setFullOffer(fullOfferService.composeFullOffer(order.getOfferid()));

           RestTemplate restTemplate = new RestTemplate();
           String url = "http://localhost:8091/api/1.1/get/userdetails?username="+fullOrder.getFullOffer().getUsername(); //name of the seller
           ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);

           ObjectMapper objectMapper = new ObjectMapper();
           try {
               Userdetail sellerDetails =  objectMapper.readValue(response.getBody(),Userdetail.class);
               fullOrder.setSellerDetails(sellerDetails);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }

           return fullOrder;

       }).toList();
    }

    @GetMapping("get/order/offerwise")
    public List<FullOrder> getOrdersOfferwise(@RequestParam Integer offerid)
    {
        List<Order> orderList =  orderRepository.findAll().stream().filter((Order order) -> {return order.getOfferid().equals(offerid);}).toList();

        return orderList.stream().map((Order order) -> {

            FullOrder fullOrder = new FullOrder();
            fullOrder.setId(order.getId());
            fullOrder.setUsername(order.getUsername());
            fullOrder.setOfferid(order.getOfferid());
            fullOrder.setBid(order.getBid());
            fullOrder.setComments(order.getComments());
            fullOrder.setFullOffer(fullOfferService.composeFullOffer(order.getOfferid()));

            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8091/api/1.1/get/userdetails?username="+fullOrder.getFullOffer().getUsername(); //name of the seller
            ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
               Userdetail sellerDetails =  objectMapper.readValue(response.getBody(),Userdetail.class);
                fullOrder.setSellerDetails(sellerDetails);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return fullOrder;

        }).toList();
    }

    @GetMapping("getall/order")
    public List<FullOrder> getAllOrders()
    {

        List<Order> orderList = orderRepository.findAll();

        return orderList.stream().map((Order order) -> {

            FullOrder fullOrder = new FullOrder();
            fullOrder.setId(order.getId());
            fullOrder.setUsername(order.getUsername());
            fullOrder.setOfferid(order.getOfferid());
            fullOrder.setBid(order.getBid());
            fullOrder.setComments(order.getComments());
            fullOrder.setFullOffer(fullOfferService.composeFullOffer(order.getOfferid()));

            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8091/api/1.1/get/userdetails?username="+fullOrder.getFullOffer().getUsername(); //name of the seller
            ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Userdetail sellerDetails =  objectMapper.readValue(response.getBody(),Userdetail.class);
                fullOrder.setSellerDetails(sellerDetails);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return fullOrder;

        }).toList();


    }

    @GetMapping("get/userdetails/resttemplate")
    public String getUserDetails(@RequestParam("username") String username)
    {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8091/api/1.1/get/userdetails?username="+username;
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);

        return response.getBody();
    }

    @PostMapping("delete/order")
    ResponseEntity<String> deleteOrder(@RequestParam("orderid") Integer orderid)
    {
        orderRepository.deleteById(orderid);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }


}
