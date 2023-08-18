package com.auctionapp.service;

import com.auctionapp.model.bid.Bid;
import com.auctionapp.model.product.Product;
import com.auctionapp.repository.BidRepository;
import com.auctionapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidService {
    private BidRepository bidRepository;

    private ProductRepository productRepository;

    @Autowired
    public BidService(BidRepository bidRepository, ProductRepository productRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
    }

    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    public List<Bid> findByProductId(long productId) {
        Optional<Product> result = productRepository.findById(productId);
        Product product = null;
        if (result.isPresent()) {
            product = result.get();
        } else {
            throw new RuntimeException("Did not find product with id " + productId);
        }
        return bidRepository.findByProduct(product);
    }

    public Bid findById(Long id) {
        Optional<Bid> result = bidRepository.findById(id);
        Bid bid = null;
        if (result.isPresent()) {
            bid = result.get();
        } else {
            throw new RuntimeException("Did not find bid with id " + id);
        }
        return bid;
    }

    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    public void deleteById(Long id) {
        bidRepository.deleteById(id);
    }
}
