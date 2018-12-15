package com.tinkoff.test.controller;

import com.tinkoff.test.entity.Book;
import com.tinkoff.test.entity.DeliveryDesk;
import com.tinkoff.test.entity.Reader;
import com.tinkoff.test.service.impl.DeliveryDeskServiceImpl;
import com.tinkoff.test.service.impl.ReaderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryDeskController {
    @Autowired
    private DeliveryDeskServiceImpl deliveryDeskService;

    @GetMapping()
    public List<DeliveryDesk> getAllReader() {
        return deliveryDeskService.getAllRecords();
    }

    @PostMapping("/startRead")
    public DeliveryDesk startRead(@RequestBody Integer reader, @RequestBody Integer book) {
        return deliveryDeskService.startRead(reader, book);
    }

    @PostMapping("/endRead")
    public DeliveryDesk endRead(@RequestBody Integer deliveryDesk) {
        return deliveryDeskService.endRead(deliveryDesk);
    }

    @GetMapping("/count")
    public Integer getCountOfReadersByBookId(@RequestBody Book book) {
        return deliveryDeskService.getCountOfReadersByBookId(book);
    }

//    @GetMapping()
//    public Date getAverageTimeByBookId(@RequestBody Integer id) {DeliveryDesk
//        return deliveryDeskService.getAverageTimeByBookId(id);
//    }
//


}
