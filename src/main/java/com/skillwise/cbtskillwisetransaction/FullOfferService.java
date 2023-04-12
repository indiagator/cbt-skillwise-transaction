package com.skillwise.cbtskillwisetransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FullOfferService {

    @Autowired
    ProductOfferRepository productOfferRepository;
    @Autowired
    ProductListRepository productListRepository;

    public FullOffer composeFullOffer(Integer offerid)
    {
        FullOffer fullOffer = new FullOffer();

        ProductOffer productOffer =  productOfferRepository.findById(offerid).get();

        List<Product> productList = productListRepository.findAll().stream().filter((Product product) -> {return product.getOfferid().equals(offerid);}).toList();

        Integer offerAmount = productList.stream().map((Product product) ->{ return product.getQty() * product.getUnitprice();}).reduce(0,Math::addExact);

        offerAmount = offerAmount / 100000;

        fullOffer.setId(productOffer.getId());
        fullOffer.setUsername(productOffer.getUsername());
        fullOffer.setOffername(productOffer.getOffername());
        fullOffer.setDescription(productOffer.getDescription());
        fullOffer.setProductList(productList);
        fullOffer.setOfferAmount(offerAmount);

        return fullOffer;
    }
}
