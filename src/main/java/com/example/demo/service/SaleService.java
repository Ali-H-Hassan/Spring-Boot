package com.example.demo.service;

import com.example.demo.model.Sale;
import com.example.demo.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Transactional(readOnly = true)
    public List<Sale> findAllSales() {
        return saleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Sale> findSaleById(Long id) {
        return saleRepository.findById(id);
    }

    @Transactional
    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Transactional
    public Sale updateSale(Long id, Sale updatedSale) {
        return saleRepository.findById(id)
                .map(existingSale -> {
                    existingSale.setClient(updatedSale.getClient());
                    existingSale.setSeller(updatedSale.getSeller());
                    existingSale.setTotal(updatedSale.getTotal());
                    return saleRepository.save(existingSale);
                }).orElseThrow(() -> new RuntimeException("Sale not found with id " + id));
    }

    @Transactional
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

}
