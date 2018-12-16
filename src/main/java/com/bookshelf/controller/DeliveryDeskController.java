package com.bookshelf.controller;

import com.bookshelf.entity.DeliveryDesk;
import com.bookshelf.service.impl.DeliveryDeskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public DeliveryDesk startRead(@RequestBody Integer reader, @RequestBody UUID book) {
        return deliveryDeskService.startRead(reader, book);
    }

    @PostMapping("/endRead")
    public DeliveryDesk endRead(@RequestBody Integer deliveryDesk) {
        return deliveryDeskService.endRead(deliveryDesk);
    }

//    @GetMapping("/count")
//    public Integer getCountOfReadersByBookId(@RequestBody Book book) {
//        return deliveryDeskService.getCountOfReadersByBookId(book);
//    }

//    @GetMapping()
//    public Date getAverageTimeByBookId(@RequestBody Integer id) {DeliveryDesk
//        return deliveryDeskService.getAverageTimeByBookId(id);
//    }
//


}
