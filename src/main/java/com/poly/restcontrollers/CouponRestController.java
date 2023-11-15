package com.poly.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.poly.dao.CouponDAO;
import com.poly.models.Coupon;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/coupon")
public class CouponRestController {
    @Autowired
    CouponDAO cpDAO;

    @GetMapping
    public List<Coupon> findAll() {
        return cpDAO.findAll();
    }

    @PostMapping
    public void add(@RequestBody Coupon coupon) {
        coupon.setCouponCode(coupon.getCouponCode().toUpperCase().replace(" ", ""));
        cpDAO.save(coupon);
    }

    @PutMapping
    public void update(@RequestBody Coupon coupon) {
        coupon.setCouponCode(coupon.getCouponCode().toUpperCase().replace(" ", ""));
        cpDAO.save(coupon);
    }
    

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int couponId) {
        cpDAO.deleteById(couponId);
    }
}
